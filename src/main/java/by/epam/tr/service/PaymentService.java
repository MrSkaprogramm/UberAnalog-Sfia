package by.epam.tr.service;

import by.epam.tr.beans.Order;

/**
 * Service layer interface for bean * @see Payment
 */
public interface PaymentService {
  /**
   * Performs passenger payment for travel or receipt of money by the driver, depending on the role
   */
  public <T> String makeTransaction(Order order, T role);

  /**
   * Adds a credit card
   */
  public boolean addCreditCard(int creditCardNum, String expiringDate);

  /**
   * Adds a promo code
   */
  public boolean addPromocode(String promocodeNum, double discount);
}
