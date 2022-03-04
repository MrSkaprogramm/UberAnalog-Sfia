package by.epam.tr.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.NavigationMap;
import by.epam.tr.beans.Order;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Payment;
import by.epam.tr.dao.DAOProvider;
import by.epam.tr.dao.OrderDAO;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.MapService;
import by.epam.tr.service.OrderService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceException;
import by.epam.tr.service.ServiceProvider;

/**
 * Класс реализации интерфейса слоя Service
 * @see OrderService
 */
public class OrderServiceImpl implements OrderService {
  /**
   * Инициализация объектов слоя DAO с помощью DAOProvider
   */
  private final DAOProvider instance = new DAOProvider();
  private OrderDAO orderDAO = instance.getOrderDAO();

  /**
   * Начало поездки, создание заказа и помещение его в список заказов
   * @param role Роль пользователя в приложении
   */
  @Override
  public <T> Order startTrip(T role) {
    ServiceProvider serviceProvider = ServiceProvider.getServiceProvider();
    MapService mapService = serviceProvider.getMapService();
    DriverService driverService = serviceProvider.getDriverService();
    PassengerService passengerService = serviceProvider.getPassengerService();

    Passenger passsenger = passengerService.choosePassenger();
    Driver driver = driverService.chooseDriver();

    Order order = new Order(new Payment(), passsenger, driver, new NavigationMap());
    System.out.println("orderNum " + order.getOrderNum());

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

  /**
   * Запись всех заказов из списка выполненных заказов в файл
   */
	@Override
    public boolean makeOrderReport() throws ServiceException {
      File file = new File(Paths.get("").toAbsolutePath().toString(), "TaxiOrders.txt");
      if (!file.exists()) {
        try {
          file.createNewFile();
        } catch (IOException e) {
          throw new ServiceException();
        }
      }

      try (FileOutputStream addOrder = new FileOutputStream(file);
          ObjectOutputStream addOrderObj = new ObjectOutputStream(addOrder);) {
        for (int i = 0; i < orderDAO.getNumOfOrders(); i++) {
          System.out.println("RepositoryorderNum " + orderDAO.getOrder(i).getCurrentOrderNum());
          Order writingOrder = orderDAO.getOrder(i);
          addOrderObj.writeObject(writingOrder);
        }
      } catch (IOException e) {
        throw new ServiceException();
      }
      return true;
	}

    /**
     * Чтение содержимого файла с выполненными заказами
     * @return Возвразает строку с параметрами заказа
     */
	@Override
    public String showOrdersHistory() throws ServiceException {
      File file = new File(Paths.get("").toAbsolutePath().toString(), "TaxiOrders.txt");
      StringBuilder reportsString = new StringBuilder();

      try (FileInputStream readOrder = new FileInputStream(file);
          ObjectInputStream readOrderObj = new ObjectInputStream(readOrder);) {
        boolean count = true;
        while (count) {
          if (readOrder.available() != 0) {
            Order order = (Order) readOrderObj.readObject();
            reportsString.append("Order date: " + order.getOrderTime().toString() + "\n"
                + "Total time: " + order.getTotalTime() + " minutes" + "\n" + "Driver name: "
                + order.getDriver().getName() + "\n" + "Car number: "
                + order.getDriver().getCar().getCarNumber() + "\n" + "Passenger name: "
                + order.getPassenger().getName() + "\n");
          } else {
            count = false;
          }
        }
      } catch (FileNotFoundException e) {
        throw new ServiceException();
      } catch (IOException e) {
        throw new ServiceException();
      } catch (ClassNotFoundException e) {
        throw new ServiceException();
      }
      return reportsString.toString();
    }
}
