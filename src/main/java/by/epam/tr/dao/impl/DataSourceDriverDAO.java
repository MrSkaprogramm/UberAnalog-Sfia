package by.epam.tr.dao.impl;

import java.util.HashSet;
import java.util.Set;
import by.epam.tr.beans.Driver;
import by.epam.tr.dao.DriverDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Implementation class of the DAO layer interface * @see DriverDAO
 */
public class DataSourceDriverDAO implements DriverDAO {
  /**
   * @param dataSource object of the DataSource class
   */
  private DataSource dataSource = new DataSource();
  private Set<Driver> drivers = dataSource.getDrivers();

  /**
   * Adds a driver to the list of drivers *
   * 
   * @param newDriver новый водитель {@link DataSource#getDrivers()}
   */
  public void addDriver(Driver newDriver) {
    drivers.add(newDriver);
  }

  /**
   * @return Returns the first driver in the list {@link DataSource#getDrivers()}
   */
  public Driver getNearestDriver() {
    return drivers.iterator().next();
  }

  /**
   * Clears the list of drivers
   * 
   * @return Returns boolean confirmation of the action {@link DataSource#getDrivers()}
   */
  public boolean clearListOfDrivers() {
    drivers.clear();
    return true;
  }

  /**
   * @param drivers Updated list of drivers
   * @return Uploads the updated list of drivers to the database {@link DataSource#getDrivers()}
   */
  public boolean updateDrivers(Set<Driver> allDrivers) {
    drivers.addAll(allDrivers);
    return true;
  }

  /**
   * Passes a copy of the driver list to the Service layer
   * 
   * @return Returns a new driver list object {@link DataSource#getDrivers()}
   */
  public Set<Driver> getDriversOnTheLine() {
    Set<Driver> drivers = new HashSet<Driver>();
    drivers.addAll(dataSource.getDrivers());
    return drivers;
  }
}
