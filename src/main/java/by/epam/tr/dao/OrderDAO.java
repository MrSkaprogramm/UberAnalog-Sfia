package by.epam.tr.dao;

import java.util.List;
import by.epam.tr.beans.Order;
import by.epam.tr.service.ServiceException;

/**
 * DAO layer interface for bean * @see Order
 */
public interface OrderDAO {
  /**
   * Adds a new order to the data source
   */
  public boolean addOrder(Order order) throws DAOException;

  /**
   * Reading the data source, saving it to the list of completed orders and passing it to the
   * Service layer
   */
  public List<Order> showOrdersHistory() throws ServiceException;
}
