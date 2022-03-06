package by.epam.tr.service;

import by.epam.tr.beans.Passenger;
import by.epam.tr.dao.DAOException;

/**
 * Service layer interface for bean * @see Passenger
 */
public interface PassengerService {
  /**
   * Checks for compliance with the passenger's first and last name
   */
  public boolean validate(String name, String surname);

  /**
   * Registers a new passenger
   */
  public boolean register(String name, String surname) throws DAOException;

  /**
   * Generates multiple passengers next to the driver
   */
  public boolean generatePassengers() throws DAOException;

  /**
   * Setting the type of payment means by the passenger
   */
  public String selectPaymentType(String type);

  /**
   * Selects the first passenger in the list next to the driver
   */
  public Passenger choosePassenger();
}
