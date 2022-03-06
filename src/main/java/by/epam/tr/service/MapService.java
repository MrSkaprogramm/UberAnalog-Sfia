package by.epam.tr.service;

import by.epam.tr.dao.DAOException;

/**
 * Service layer interface for bean * @see NavigationMap
 */
public interface MapService {
  /**
   * Finds a random street
   */
  public String defineLocation() throws DAOException;

  /**
   * Checks the correctness of the street name in accordance with the list of streets of the city
   */
  public boolean checkLocation(String street) throws DAOException;

  /**
   * Determines the distance from the city center to the selected point
   */
  public double locationDistance(String street) throws DAOException;

  /**
   * Calculates the path between two route points
   */
  public double calculateTripDistance(String passengerLocation, String deliveryAddress)
      throws DAOException;
}
