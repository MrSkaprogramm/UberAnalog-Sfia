package by.epam.tr.service.impl;

import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.MapDAO;
import by.epam.tr.service.MapService;

/**
 * Implementation class of the Service layer interface * @see MapService
 */
public class MapServiceImpl implements MapService {
  /**
   * Initialization of DAO layer objects using DAOProvider
   */
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private MapDAO mapDAO = provider.getMapDAO();

  /**
   * Checks the correctness of the street name in accordance with the list of streets of the city
   *
   * @param street Street name
   * @return Returns a boolean value
   * @throws DAOException
   */
  public boolean checkLocation(String street) throws DAOException {
    return mapDAO.checkLocation(street);
  }

  /**
   * Finds a random street
   * 
   * @return Returns a string with the street name
   * @throws DAOException
   */
  public String defineLocation() throws DAOException {
    return mapDAO.getRandomLocation();
  }

  /**
   * Determines the distance from the city center to the selected point
   *
   * @param street Street name
   * @return Returns the value of the distance to the selected street
   * @throws DAOException
   */
  public double locationDistance(String street) throws DAOException {
    return mapDAO.getLocationDistance(street);
  }

  /**
   * Calculates the path between two route points
   *
   * @param passengerLocation passenger location
   * @param DeliveryAddress location of the arrival point
   * @return Returns the distance value
   * @throws DAOException
   */
  public double calculateTripDistance(String passengerLocation, String deliveryAddress)
      throws DAOException {
    return locationDistance(passengerLocation) + locationDistance(deliveryAddress);
  }
}
