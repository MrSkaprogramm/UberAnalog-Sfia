package by.epam.tr.dao;

import java.util.Set;
import by.epam.tr.beans.Driver;

/**
 * DAO layer interface for bean * @see Driver
 */
public interface DriverDAO {
  /**
   * Adds a driver to the list of drivers
   */
  public void addDriver(Driver newDriver);

  /**
   * Uploads the updated list of drivers to the database
   */
  public boolean updateDrivers(Set<Driver> drivers);

  /**
   * Passes a copy of the driver list to the Service layer
   */
  public Set<Driver> getDriversOnTheLine();

  /**
   * Returns the first driver in the list
   */
  public Driver getNearestDriver();

  /**
   * Clears the list of drivers
   */
  public boolean clearListOfDrivers();
}
