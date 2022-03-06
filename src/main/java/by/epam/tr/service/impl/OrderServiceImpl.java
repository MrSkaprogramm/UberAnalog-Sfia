package by.epam.tr.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.NavigationMap;
import by.epam.tr.beans.Order;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Payment;
import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.OrderDAO;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.MapService;
import by.epam.tr.service.OrderService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceException;
import by.epam.tr.service.ServiceProvider;

/**
 * Implementation class of the Service layer interface * @see OrderService
 */
public class OrderServiceImpl implements OrderService {
  /**
   * Initialization of DAO layer objects using DAOProvider
   */
  private final DAOProvider instance = new DAOProvider();
  private OrderDAO orderDAO = instance.getOrderDAO();

  /**
   * Starting a trip, creating an order and placing it in the data source
   *
   * @param role The user's role in the application
   * @throws ServiceException
   * @throws DAOException
   */
  @Override
  public <T> Order startTrip(T role) throws ServiceException, DAOException {
    ServiceProvider serviceProvider = ServiceProvider.getServiceProvider();
    MapService mapService = serviceProvider.getMapService();
    DriverService driverService = serviceProvider.getDriverService();
    PassengerService passengerService = serviceProvider.getPassengerService();

    Passenger passsenger = passengerService.choosePassenger();
    Driver driver = driverService.chooseDriver();

    Order order = new Order(new Payment(), passsenger, driver, new NavigationMap());

    LocalDateTime currentDate = LocalDateTime.now();
    order.setOrderTime(currentDate);
    order.setTotalTime(Math.random() * 30);
    order.setTotalWaitingTime(Math.random() * 5);

    if (role instanceof Driver) {
      NavigationMap driverMap = driver.getMap();
      String passengerLocation = driverMap.getPassengerLocation();
      String driverLocation = driverMap.getDriverLocation();
      double totalOrderDistance =
          mapService.calculateTripDistance(passengerLocation, driverLocation);
      order.setTotalDistance(totalOrderDistance);
    } else {
      NavigationMap passengerMap = passsenger.getMap();
      String passengerLocation = passengerMap.getPassengerLocation();
      String driverLocation = passengerMap.getDriverLocation();
      double totalOrderDistance =
          mapService.calculateTripDistance(passengerLocation, driverLocation);
      order.setTotalDistance(totalOrderDistance);
    }

    orderDAO.addOrder(order);

    return order;
  }

  /***
   * Reading the contents of a file with completed orders
   * 
   * @return Returns a string with order parameters
   */
  @Override
  public String showOrdersHistory() throws ServiceException {
    List<Order> orders = orderDAO.showOrdersHistory();
    StringBuilder reportsString = new StringBuilder();
    for (Order order : orders) {
      reportsString.append("Order date: " + order.getOrderTime().toString() + "\n" + "Total time: "
          + order.getTotalTime() + " minutes" + "\n" + "Driver name: " + order.getDriver().getName()
          + "\n" + "Car number: " + order.getDriver().getCar().getCarNumber() + "\n"
          + "Passenger name: " + order.getPassenger().getName() + "\n");
    }
    return reportsString.toString();
  }
}
