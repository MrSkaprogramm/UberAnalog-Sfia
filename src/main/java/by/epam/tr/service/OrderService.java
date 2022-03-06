package by.epam.tr.service;

import by.epam.tr.beans.Order;
import by.epam.tr.dao.DAOException;

/**
 * Service layer interface for bean * @see Order
 */
public interface OrderService {
  /**
   * Starting a trip, creating an order and placing it in a data source
   */
  public <T> Order startTrip(T role) throws ServiceException, DAOException;

  /**
   * Reading the contents of a file with completed orders
   */
  public String showOrdersHistory() throws ServiceException;
}