package by.epam.tr.service;

import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Rate;
import by.epam.tr.dao.DAOException;

/**
 * Service layer interface for bean * @see Driver
 */
public interface DriverService {
  /**
   * Checking the correctness of the driver's data entry
   */
  public boolean validate(String name, String surname, String legalEntityName);

  /**
   * Registers a new driver
   */
  public boolean register(String name, String surname, String legalEntityName) throws DAOException;

  /**
   * Driver's choice of car
   */
  public boolean selectCar(String carModel, String carNumber);

  /**
   * Generates multiple drivers next to the passenger
   */
  public boolean generateDrivers() throws DAOException;

  /**
   * Selects the first driver in the data source list next to the passenger
   */
  public Driver chooseDriver();

  /**
   * Deletes all drivers in the list that do not match the fare selected by the passenger and
   * records relevant drivers are listed in the data source
   */
  public boolean choiseDriverByRate(Rate rate);
}
