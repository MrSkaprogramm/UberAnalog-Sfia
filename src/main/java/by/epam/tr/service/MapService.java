package by.epam.tr.service;

import by.epam.tr.beans.NavigationMap;

/**
 * Интерфейс слоя Service для bean
 * @see NavigationMap
 */
public interface MapService {
  /**
   * Находит случайную улицу
   */
  public String defineLocation();
	
  /**
   * Проверяет корректность названия улицы в соответствии со списком улиц города
   */
  public boolean checkLocation(String street);

  /**
   * Возвражает значение расстояния до выбранной улицы
   */
  public double locationDistance(String street);

  /**
   * Вычисляет путь между двумя точками маршрута
   */
  public double calculateTripDistance(String passengerLocation, String deliveryAddress);
}
