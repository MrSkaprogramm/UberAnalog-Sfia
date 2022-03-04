package by.epam.tr.dao;

import java.util.Map;
import by.epam.tr.beans.NavigationMap;

/**
 * Интерфейс слоя DAO для bean
 * @see NavigationMap
 */
public interface MapDAO {
  /**
   * Возвращает все улицы города и расстояние до центра
   */
  public Map<String, Double> getStreets();

  /**
   * Возвращает boolean - значение нахождения улицы в списке по названию
   */
    public boolean checkLocation(String street);

  /**
   * Возвращает случайное название улицы города
   */
    public String getRandomLocation();

  /**
   * Возвращает дистанцию улицы города от центра
   */
    public double getLocationDistance(String street);
}
