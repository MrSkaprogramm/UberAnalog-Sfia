package by.epam.tr.datasource;

import by.epam.tr.beans.Rate;
import by.epam.tr.dao.CarDAO;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.MapDAO;

/**
 * Класс инициализации базы данных в начале программы
 * @see DataSource
 */
public class Initialization {
  /**
   * Инициализация объектов слоя DAO с помощью DAOProvider
   */
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private CarDAO carDAO = provider.getCarDAO();
  private MapDAO mapDAO = provider.getMapDAO();

  /**
   * Метод загрузки списка улиц города в базу данных
   */
	public void initStreetsDatabase() {
      mapDAO.getStreets().put("Aivazovsky", 6.5);
      mapDAO.getStreets().put("Angarsk", 11.8);
      mapDAO.getStreets().put("Ales Dudara", 8.2);
      mapDAO.getStreets().put("Arkady Smolich", 8.4);
      mapDAO.getStreets().put("Avtodorovskaya", 4.9);
      mapDAO.getStreets().put("Alibegova", 6.7);
      mapDAO.getStreets().put("Avakyan", 3.9);
      mapDAO.getStreets().put("Independence Ave", 6.4);
      mapDAO.getStreets().put("Avangardnaya", 6.3);
      mapDAO.getStreets().put("Vaupshasova", 7.8);
      mapDAO.getStreets().put("Academician Vysotsky", 7.8);
      mapDAO.getStreets().put("Kalinina", 4.8);
      mapDAO.getStreets().put("Melezha", 3.7);
      mapDAO.getStreets().put("Yakub Kolas", 5.8);
      mapDAO.getStreets().put("Mazurova", 10.9);
      mapDAO.getStreets().put("Briquette", 10.7);
      mapDAO.getStreets().put("International", 0.4);
      mapDAO.getStreets().put("Ratomskaya", 9.0);
	}

    /**
     * Метод загрузки списка автомобилей в базу данных
     */
    public void initCarsDatabase() {
      carDAO.getCarsSuitableTaxis().put("VW POLO", Rate.MINIMAL);
      carDAO.getCarsSuitableTaxis().put("Renault Logan", Rate.MINIMAL);
      carDAO.getCarsSuitableTaxis().put("KIA RIO", Rate.MINIMAL);
      carDAO.getCarsSuitableTaxis().put("CITROEN C3", Rate.MINIMAL);
      carDAO.getCarsSuitableTaxis().put("Honda Accord", Rate.MIDDLE);
      carDAO.getCarsSuitableTaxis().put("VW PASSAT", Rate.MIDDLE);
      carDAO.getCarsSuitableTaxis().put("TOYOTA CAMRY", Rate.MIDDLE);
      carDAO.getCarsSuitableTaxis().put("JAGUAR XF", Rate.MIDDLE_PLUS);
      carDAO.getCarsSuitableTaxis().put("AUDI A5", Rate.MIDDLE_PLUS);
      carDAO.getCarsSuitableTaxis().put("MERCEDES S600", Rate.LUX);
      carDAO.getCarsSuitableTaxis().put("TESLA MODELS", Rate.LUX);
	}
}
