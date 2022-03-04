package by.epam.tr.service.impl;

import by.epam.tr.beans.Car;
import by.epam.tr.beans.CreditCard;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Order;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Payment;
import by.epam.tr.beans.Promocode;
import by.epam.tr.beans.Rate;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.PassengerDAO;
import by.epam.tr.service.PaymentService;

/**
 * Класс реализации интерфейса слоя Service
 * @see PaymentService
 */
public class PaymentServiceImpl implements PaymentService {
  /**
   * Инициализация объектов слоя DAO с помощью DAOProvider
   */
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private PassengerDAO passengerDAO = provider.getPassengerDAO();

  /**
   * Выполняет платёж пассажира за проезд или получение денег водителем в зависимости от роли
   * @param order Заказ, который нужно оплатить или получить оплату
   * @param role роль пользователя
   * @return Возвращает строку статуса выполнения операции
   */
  public <T> String makeTransaction(Order order, T role) {
    Driver orderDriver = order.getDriver();
    Car orderCar = orderDriver.getCar();
    Rate orderRate = orderCar.getRate();
    Payment orderPayment = order.getPayment();
    double tripTotalDistance = order.getTotalDistance();
    double tripTime = order.getTotalTime();
    double tripWaitingTime = order.getTotalWaitingTime();
    double tripPayment = tripTotalDistance * orderRate.getTripRate();
    double timePayment = tripTime * orderRate.getTripTimeRate();
    double waitingTimePayment = tripWaitingTime * orderRate.getWaitingRate();
    double paymentSum = tripPayment + timePayment + waitingTimePayment;
    orderPayment.setPaymentSum(paymentSum);

    if (role instanceof Passenger) {
      Passenger passenger = ((Passenger) role);
      Payment passengerPayment = passenger.getPayment();
      Promocode passengerPromocode = passengerPayment.getPromocode();
      if (passengerPromocode != null) {
        double discount = passengerPromocode.getDiscount();
        paymentSum = paymentSum - paymentSum * discount;
        orderPayment.setPaymentSum(paymentSum);
        passengerPayment.setPromocode(null);
      }
      Passenger orderPassenger = order.getPassenger();
      String orderPassengerPaymentType = orderPassenger.getPaymentType();
      if (orderPassengerPaymentType.equalsIgnoreCase("creditcard")) {
        return "You paid for the trip " + paymentSum + "$ by bank card";
      } else {
        return "You paid for the trip " + paymentSum + "$ in cash";
      }
    } else {
      return "You have earned for the trip " + paymentSum + "$ to your account";
    }
  }

  /**
   * Добавляет кредитную карту
   * @param creditCardNum Номер кредитной карты
   * @param expiringDate дата действия кредитной карты
   */
  public boolean addCreditCard(int creditCardNum, String expiringDate) {
    Passenger passenger = passengerDAO.getNearestPassenger();
    Payment passengerPayment = passenger.getPayment();
    passengerPayment.setCreditCard(new CreditCard(creditCardNum, expiringDate));

    return true;
  }

  /**
   * Добавляет промокод
   * @param promocodeNum Номер промокода
   * @param discount скидка промокода
   */
  public boolean addPromocode(String promocodeNum, double discount) {
    Passenger passenger = passengerDAO.getNearestPassenger();
    Payment passengerPayment = passenger.getPayment();
    passengerPayment.setPromocode(new Promocode(promocodeNum, discount));
    return true;
  }
}
