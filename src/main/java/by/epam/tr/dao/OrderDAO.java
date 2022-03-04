package by.epam.tr.dao;

import by.epam.tr.beans.Order;

/**
 * Интерфейс слоя DAO для bean
 * @see Order
 */
public interface OrderDAO {
  /**
   * Добавляет заказ в список заказов
   */
  public boolean addOrder(Order order);

  /**
   * Возвращает заказ по номеру из списка заказов
   */
  public Order getOrder(int orderNum);

  /**
   * Возвращает количество заказов в списке
   */
  public int getNumOfOrders();
}
