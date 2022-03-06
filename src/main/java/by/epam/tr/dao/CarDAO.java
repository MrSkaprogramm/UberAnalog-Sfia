package by.epam.tr.dao;

import java.util.Map;
import by.epam.tr.beans.Rate;
import by.epam.tr.datasource.DataSource;

/**
 * DAO layer interface for bean * @see Car
 */
public interface CarDAO {
  /**
   * Returns a list of available cars {@link DataSource#getSuitableCarsTaxis()} for driving in taxi
   */
  public Map<String, Rate> getCarsSuitableTaxis();

  /**
   * Returns the boolean of the possible presence of the car model in the list of available cars
   */
  public boolean checkCar(String carModel);

  /**
   * Returns the Rate of the car model from the list of available cars
   */
  public Rate getCarRate(String carModel);

  /**
   * Returns a random car model from the list of available cars
   */
  public String getRandomCarModel();
}
