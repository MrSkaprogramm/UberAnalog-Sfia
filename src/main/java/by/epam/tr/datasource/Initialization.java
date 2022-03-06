package by.epam.tr.datasource;

import by.epam.tr.beans.Rate;
import by.epam.tr.dao.CarDAO;
import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.MapDAO;

/**
 * Database initialization class at the beginning of the program * @see DataSource
 */
public class Initialization {
  /**
   * Initialization of DAO layer objects using DAOProvider
   */
  private final DAOProvider provider = DAOProvider.getDaoProvider();
  private CarDAO carDAO = provider.getCarDAO();
  private MapDAO mapDAO = provider.getMapDAO();

  /**
   * Method of loading a list of city streets into a database
   */
  public void initStreetsDatabase() {
    try {
      mapDAO.addStreet("Aivazovsky" + " " + 6.5);
      mapDAO.addStreet("Avtodorovskaya" + " " + 4.9);
      mapDAO.addStreet("Alibegova" + " " + 6.7);
      mapDAO.addStreet("Avakyan" + " " + "3.9");
      mapDAO.addStreet("Independence" + " " + "6.4");
      mapDAO.addStreet("Avangardnaya" + " " + "6.3");
      mapDAO.addStreet("Vaupshasova" + " " + "7.8");
      mapDAO.addStreet("Vysotsky" + " " + "7.8");
      mapDAO.addStreet("Kalinina" + " " + "4.8");
      mapDAO.addStreet("Melezha" + " " + "3.7");
      mapDAO.addStreet("Kolas" + " " + "5.8");
      mapDAO.addStreet("Mazurova" + " " + "10.9");
      mapDAO.addStreet("Briquette" + " " + "10.7");
      mapDAO.addStreet("International" + " " + "0.4");
      mapDAO.addStreet("Ratomskaya" + " " + "9.0");
    } catch (DAOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method of loading the list of cars into the database
   */
  public void initCarsDatabase() {
    carDAO.getCarsSuitableTaxis().put("VW POLO", Rate.MINIMAL);
    carDAO.getCarsSuitableTaxis().put("RENAULT LOGAN", Rate.MINIMAL);
    carDAO.getCarsSuitableTaxis().put("KIA RIO", Rate.MINIMAL);
    carDAO.getCarsSuitableTaxis().put("CITROEN C3", Rate.MINIMAL);
    carDAO.getCarsSuitableTaxis().put("HONDA ACCORD", Rate.MIDDLE);
    carDAO.getCarsSuitableTaxis().put("VW PASSAT", Rate.MIDDLE);
    carDAO.getCarsSuitableTaxis().put("TOYOTA CAMRY", Rate.MIDDLE);
    carDAO.getCarsSuitableTaxis().put("JAGUAR XF", Rate.MIDDLE_PLUS);
    carDAO.getCarsSuitableTaxis().put("AUDI A5", Rate.MIDDLE_PLUS);
    carDAO.getCarsSuitableTaxis().put("MERCEDES S600", Rate.LUX);
    carDAO.getCarsSuitableTaxis().put("TESLA MODELS", Rate.LUX);
  }
}
