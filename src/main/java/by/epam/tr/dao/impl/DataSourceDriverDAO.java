package by.epam.tr.dao.impl;

import java.util.HashSet;
import java.util.Set;
import by.epam.tr.beans.Driver;
import by.epam.tr.dao.DriverDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Класс реализации интерфейса слоя DAO
 * @see DriverDAO
 */
public class DataSourceDriverDAO implements DriverDAO {
  /**
   * @param dataSource объект класса DataSource
   */
  private DataSource dataSource = new DataSource();
  private Set<Driver> drivers = dataSource.getDrivers();

  /**
   * Добавляет водителя в список водителей
   * @param newDriver объект класса Driver {@link DataSource#getDrivers()}
   */
  public void addDriver(Driver newDriver) {
    drivers.add(newDriver);
	}

    /**
     * @return Возвращает список водителей {@link DataSource#getDrivers()}
     */
    public Set<Driver> getDrivers() {
      return drivers;
    }

    /**
     * @return Возвращает первого водителя в списке {@link DataSource#getDrivers()}
     */
    public Driver getNearestDriver() {
      return drivers.iterator().next();
    }

    /**
     * @return Очищает список водителей {@link DataSource#getDrivers()}
     */
    public boolean clearListOfDrivers() {
      drivers.clear();
      return true;
    }

    /**
     * @param drivers Обновлённый список водителей
     * @return Загружает обновлённый список водителей в базу данных {@link DataSource#getDrivers()}
     */
    public boolean setupDriversByRate(Set<Driver> allDrivers) {
      drivers.addAll(allDrivers);
      return true;
    }

    /**
     * @return Возвращает новый объект списка водителей из базы данных
     *         {@link DataSource#getDrivers()}
     */
    public Set<Driver> getDriversOnTheLine() {
      Set<Driver> drivers = new HashSet<Driver>();
      drivers.addAll(dataSource.getDrivers());
      return drivers;
    }
}
