package by.epam.tr.service.impl;

import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.MapDAO;
import by.epam.tr.service.MapService;

/**
 * Класс реализации интерфейса слоя Service
 * @see MapService
 */
public class MapServiceImpl implements MapService {
  /**
   * Инициализация объектов слоя DAO с помощью DAOProvider
   */
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private MapDAO mapDAO = provider.getMapDAO();

    /**
     * Проверяет корректность названия улицы в соответствии со списком улиц города
     * @param street Название улицы
     * @return Возвращает boolean - значение
     */
    public boolean checkLocation(String street) {
      return mapDAO.checkLocation(street);
	}
    // 3 для теста

    /**
     * Находит случайную улицу
     * @return Возвращает строку с названием улицы
     */
    public String defineLocation() {
      return mapDAO.getRandomLocation();
    }

    /**
     * @param street Название улицы
     * @return Возвражает значение расстояния до выбранной улицы
     */
    public double locationDistance(String street) {
      return mapDAO.getLocationDistance(street);
    }

    // 4 для теста
    /**
     * Вычисляет путь между двумя точками маршрута
     * @param passengerLocation местоположение пассажира
     * @param deliveryAddress местоположение точки прибытия
     * @return Возвращает значение расстояния
     */
    public double calculateTripDistance(String passengerLocation, String deliveryAddress) {
      return locationDistance(passengerLocation) + locationDistance(deliveryAddress);
    }
    // 5 для теста
}
