package by.epam.tr.dao;

import by.epam.tr.dao.impl.DataSourceCarDAO;
import by.epam.tr.dao.impl.DataSourceDriverDAO;
import by.epam.tr.dao.impl.DataSourceMapDAO;
import by.epam.tr.dao.impl.DataSourceOrderDAO;
import by.epam.tr.dao.impl.DataSourcePassengerDAO;

/**
 * Класс - провайдер слоя DAO
 */
public class DAOProvider {

  private static final DAOProvider daoProvider = new DAOProvider();
	
  private PassengerDAO passengerDAO = new DataSourcePassengerDAO();
  private DriverDAO driverDAO = new DataSourceDriverDAO();
  private MapDAO mapDAO = new DataSourceMapDAO();
  private OrderDAO orderDAO = new DataSourceOrderDAO();
  private CarDAO carDAO = new DataSourceCarDAO();
	
	public DAOProvider() {}
	
    public static DAOProvider getDaoProvider() {
      return daoProvider;
    }

    public PassengerDAO getPassengerDAO() {
		return passengerDAO;
	}

	public void setPassengerDAO(PassengerDAO passengerDAO) {
		this.passengerDAO = passengerDAO;
	}

	public DriverDAO getDriverDAO() {
		return driverDAO;
	}

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

	public MapDAO getMapDAO() {
		return mapDAO;
	}

	public void setMapDAO(MapDAO mapDAO) {
		this.mapDAO = mapDAO;
      }

      public OrderDAO getOrderDAO() {
        return orderDAO;
      }

      public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
      }

      public CarDAO getCarDAO() {
        return carDAO;
      }

      public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
      }
}
