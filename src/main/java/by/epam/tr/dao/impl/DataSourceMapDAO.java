package by.epam.tr.dao.impl;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import by.epam.tr.dao.MapDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Класс реализации интерфейса слоя DAO
 * @see MapDAO
 */
public class DataSourceMapDAO implements MapDAO {
  private DataSource database = new DataSource();
  private Map<String, Double> streetsLocationsFromCentre = database.getStreetsLocationsFromCentre();

  /**
   * @return Возвращает все улицы города и расстояние до центра
   *         {@link DataSource#getStreetsLocationsFromCentre()}
   */
    public Map<String, Double> getStreets() {
      return streetsLocationsFromCentre;
    }

    /**
     * @param street название улицы
     * @return Возвращает boolean - значение нахождения улицы в списке по названию
     *         {@link DataSource#getStreetsLocationsFromCentre()}
     */
    public boolean checkLocation(String street) {
      return streetsLocationsFromCentre.containsKey(street);
    }

    /**
     * @return Возвращает случайное название улицы города
     *         {@link DataSource#getStreetsLocationsFromCentre()}
     */
    public String getRandomLocation() {
      Random generator = new Random();
      Set<String> cityStreets = streetsLocationsFromCentre.keySet();
      Object[] streets = cityStreets.toArray();
      String location = (String) streets[generator.nextInt(streets.length)];
      return location;
    }

    /**
     * @param street название улицы
     * @return Возвращает дистанцию улицы города от центра
     *         {@link DataSource#getStreetsLocationsFromCentre()}
     */
    public double getLocationDistance(String street) {
      return streetsLocationsFromCentre.get(street);
    }
}
