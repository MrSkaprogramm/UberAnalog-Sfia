package by.epam.tr.dao.impl;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import by.epam.tr.beans.Rate;
import by.epam.tr.dao.CarDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Implementation class of the DAO layer interface * @see CarDAO
 */
public class DataSourceCarDAO implements CarDAO {
  /**
   * @param dataSource object of the DataSource class
   * @see DataSourceCarDAO#suitableCarTaxis Cars allowed for Taxi rides
   */
  private DataSource dataSource = new DataSource();
  private Map<String, Rate> suitableCarTaxis = dataSource.getSuitableCarsTaxis();

  /**
   * @return Returns a list of available cars {@link DataSource#getSuitableCarsTaxis()} for driving
   *         in a taxi
   */
  public Map<String, Rate> getCarsSuitableTaxis() {
    return dataSource.getSuitableCarsTaxis();
  }

  /**
   * Searches for a car model in the list of available cars
   * 
   * @param CarModel Car Model
   * @return Returns the boolean of the possible presence of the car model in the list of available
   *         cars {@link DataSource#getSuitableCarsTaxis()}
   */
  public boolean checkCar(String carModel) {
    return suitableCarTaxis.containsKey(carModel);
  }

  /**
   * @param CarModel Car Model
   * @return Returns the Rate of the car model from the list of available cars
   *         {@link DataSource#getSuitableCarsTaxis()}
   */
  public Rate getCarRate(String carModel) {
    return suitableCarTaxis.get(carModel);
  }

  /**
   * @return Returns a random car model from the list of available cars
   *         {@link DataSource#getSuitableCarsTaxis()}
   */
  public String getRandomCarModel() {
    Random generator = new Random();
    Set<String> carModelsSet = suitableCarTaxis.keySet();
    Object[] carModels = carModelsSet.toArray();
    String driverCarModel = (String) carModels[generator.nextInt(carModels.length)];
    return driverCarModel;
  }
}
