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
 * Implementation class of the Service layer interface * @see PaymentService
 */
public class PaymentServiceImpl implements PaymentService {
  /**
   * Initialization of DAO layer objects using DAOProvider
   */
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private PassengerDAO passengerDAO = provider.getPassengerDAO();

  /**
   * Performs passenger payment for travel or receipt of money by the driver, depending on the role
   * 
   * @param order Order to be paid or receive payment
   * @param role user role
   * @return Returns the operation status string
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
   * Adds a credit card
   * 
   * @param creditCardNum Credit Card Number
   * @param expiringDate credit card expiration date
   */
  public boolean addCreditCard(int creditCardNum, String expiringDate) {
    Passenger passenger = passengerDAO.getNearestPassenger();
    Payment passengerPayment = passenger.getPayment();
    passengerPayment.setCreditCard(new CreditCard(creditCardNum, expiringDate));

    return true;
  }

  /**
   * Adds a promo code
   * 
   * @param promocodeNum Promo Code number
   * @param discount promo code discount
   */
  public boolean addPromocode(String promocodeNum, double discount) {
    Passenger passenger = passengerDAO.getNearestPassenger();
    Payment passengerPayment = passenger.getPayment();
    passengerPayment.setPromocode(new Promocode(promocodeNum, discount));
    return true;
  }
}
