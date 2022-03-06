package by.epam.tr.dao.impl;

import java.util.Set;
import by.epam.tr.beans.Passenger;
import by.epam.tr.dao.PassengerDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Implementation class of the DAO layer interface * @see PassengerDAO
 */
public class DataSourcePassengerDAO implements PassengerDAO {
  private DataSource database = new DataSource();
  private Set<Passenger> passengers = database.getPassengers();

  /**
   * Adds a passenger to the passenger list {@link DataSource#getPassengers()}
   * 
   * @param newPassenger an object of the Passenger class
   */
  public void addPassenger(Passenger newPassenger) {
    passengers.add(newPassenger);
  }

  /**
   * @return returns the first passenger in the list {@link DataSource#getPassengers()}
   */
  public Passenger getNearestPassenger() {
    return passengers.iterator().next();
  }

  /**
   * Clears the passenger list {@link DataSource#getPassengers()}
   */
  public boolean clearListOfPassengers() {
    passengers.clear();
    return true;
  }
}
