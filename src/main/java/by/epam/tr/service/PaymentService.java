package by.epam.tr.service;

import by.epam.tr.beans.Order;
import by.epam.tr.beans.Payment;

/**
 * Интерфейс слоя Service для bean
 * @see Payment
 */
public interface PaymentService {
  /**
   * Выполняет платёж пассажира за проезд или получение денег водителем в зависимости от роли
   */
  public <T> String makeTransaction(Order order, T role);

  /**
   * Добавляет кредитную карту
   */
  public boolean addCreditCard(int creditCardNum, String expiringDate);

  /**
   * Добавляет промокод
   */
  public boolean addPromocode(String promocodeNum, double discount);
}
