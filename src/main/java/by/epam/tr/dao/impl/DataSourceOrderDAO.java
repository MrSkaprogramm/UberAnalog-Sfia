package by.epam.tr.dao.impl;

import java.util.List;
import by.epam.tr.beans.Order;
import by.epam.tr.dao.OrderDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Класс реализации интерфейса слоя DAO
 * @see OrderDAO
 */
public class DataSourceOrderDAO implements OrderDAO {
  private DataSource database = new DataSource();
  private List<Order> orders = database.getOrders();

  /**
   * @param order заказ
   * @return Добавляет заказ в список заказов {@link DataSource#getOrders()}
   */
  public boolean addOrder(Order order) {
    orders.add(order);
    return true;
  }

  /**
   * @param orderNum номер заказа
   * @return Возвращает заказ по номеру из списка заказов {@link DataSource#getOrders()}
   */
  public Order getOrder(int orderNum) {
    return orders.get(orderNum);
  }

  /**
   * @return Возвращает количество заказов в списке {@link DataSource#getOrders()}
   */
  public int getNumOfOrders() {
    return orders.size();
  }
}
