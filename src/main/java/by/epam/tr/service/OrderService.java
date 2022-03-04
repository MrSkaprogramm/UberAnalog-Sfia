package by.epam.tr.service;

import by.epam.tr.beans.Order;

/**
 * Интерфейс слоя Service для bean
 * @see Order
 */
public interface OrderService {
  /**
   * Начало поездки, создание заказа и помещение его в список заказов
   */
  public <T> Order startTrip(T role);

  /**
   * Запись всех заказов из списка выполненных заказов в файл
   */
  public boolean makeOrderReport() throws ServiceException;

  /**
   * Чтение содержимого файла с выполненными заказами
   */
  public String showOrdersHistory() throws ServiceException;
}