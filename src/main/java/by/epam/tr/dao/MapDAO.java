package by.epam.tr.dao;

import java.util.Map;

/**
 * DAO layer interface for bean * @see NavigationMap
 */
public interface MapDAO {

  /**
   * Reading a data source with city streets
   */
  public Map<String, Double> readSource() throws DAOException;

  /**
   * Adding a street to the list of city streets
   */
  public void addStreet(String street) throws DAOException;

  /**
   * Returns boolean - the value of finding a street in the list by name
   */
  public boolean checkLocation(String street) throws DAOException;

  /**
   * Returns a random city street name
   */
  public String getRandomLocation() throws DAOException;

  /**
   * Returns the distance of a city street from the center
   */
  public double getLocationDistance(String street) throws DAOException;
}
