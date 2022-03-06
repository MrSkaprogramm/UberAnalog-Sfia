package by.epam.tr.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Order;
import by.epam.tr.controller.Command;
import by.epam.tr.dao.DAOException;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.OrderService;
import by.epam.tr.service.PaymentService;
import by.epam.tr.service.ServiceException;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Controller class for the option of starting a trip by a driver
 * 
 * @see DriverOptionView#applicationMenu()
 */
public class DriverBeginTripCommand implements Command {
  private Logger logger = LogManager.getLogger(DriverBeginTripCommand.class);
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private OrderService orderService = provider.getOrderService();
  private PaymentService paymentService = provider.getPaymentService();
  private DriverService driverService = provider.getDriverService();

  /**
   * The driver starts the trip, receives the payment and receives a response in the form of
   * completed trip data
   */
  public String execute(String[] requestParts) {

    if (driverService.chooseDriver().getCar() != null) {
      Driver driver = driverService.chooseDriver();
      Order order = null;
      try {
        order = orderService.startTrip(driver);
      } catch (ServiceException | DAOException e) {
        logger.error("Failed to make an order for trip");
        return "Failed to make an order for trip";
      }

      String orderResponse = "You have successfully completed the trip in " + order.getTotalTime()
          + " minutes, the total length of the route is " + order.getTotalDistance() + " km";
      String payResponse = paymentService.makeTransaction(order, driver);

      return orderResponse + "\n" + payResponse + "\n";
    } else {
      return "Please choose a car";
    }
  }
}
