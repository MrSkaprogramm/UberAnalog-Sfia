package by.epam.tr.dao;

import java.util.Set;
import by.epam.tr.beans.Driver;

/**
 * Интерфейс слоя DAO для bean
 * @see Driver
 */
public interface DriverDAO {
  /**
   * Добавляет водителя в список водителей
   */
  public void addDriver(Driver newDriver);

  /**
   * Загружает обновлённый список водителей в базу данных
   */
  public boolean setupDriversByRate(Set<Driver> drivers);

  /**
   * Возвращает список водителей
   */
  public Set<Driver> getDrivers();

  /**
   * Возвращает новый объект списка водителей из базы данных
   */
  public Set<Driver> getDriversOnTheLine();

  /**
   * Возвращает первого водителя в списке
   */
  public Driver getNearestDriver();

  /**
   * Очищает список водителей
   */
  public boolean clearListOfDrivers();
}
