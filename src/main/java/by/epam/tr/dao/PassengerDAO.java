package by.epam.tr.dao;

import by.epam.tr.beans.Passenger;

/**
 * DAO layer interface for bean * @see Passenger
 */
public interface PassengerDAO {
  /**
   * Adds a passenger to the passenger list
   */
  public void addPassenger(Passenger newPassenger);

  /**
   * Returns the first passenger in the list
   */
  public Passenger getNearestPassenger();

  /**
   * Clears the passenger list
   */
  public boolean clearListOfPassengers();
}
