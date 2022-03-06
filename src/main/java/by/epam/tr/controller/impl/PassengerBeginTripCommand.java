package by.epam.tr.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.beans.Order;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Rate;
import by.epam.tr.controller.Command;
import by.epam.tr.dao.DAOException;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.OrderService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.PaymentService;
import by.epam.tr.service.ServiceException;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Controller class for the option of adding the start of a passenger's trip
 * 
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerBeginTripCommand implements Command {
  /**
   * Initializing Service Layer objects using ServiceProvider
   * 
   * @see PassengerBeginTripCommand#logger Logger Object
   */
  private Logger logger = LogManager.getLogger(PassengerBeginTripCommand.class);
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private OrderService orderService = provider.getOrderService();
  private PaymentService paymentService = provider.getPaymentService();
  private PassengerService passengerService = provider.getPassengerService();
  private DriverService driverService = provider.getDriverService();

  /**
   * Based on the data received from the View layer, selects the fare preferred for the passenger,
   * searches for drivers working at a given tariff and outputs a response with the data of the
   * completed trip or that drivers do not work at such a tariff
   */
  public String execute(String[] requestParts) {
    Rate rate = null;

    if (requestParts[1].equals("Minimal")) {
      rate = Rate.MINIMAL;
    } else if (requestParts[1].equals("Middle")) {
      rate = Rate.MIDDLE;
    } else if (requestParts[1].equals("Middle+")) {
      rate = Rate.MIDDLE_PLUS;
    } else if (requestParts[1].equals("Lux")) {
      rate = Rate.LUX;
    }

    Passenger passenger = passengerService.choosePassenger();

    if (driverService.choiseDriverByRate(rate)) {
      Order order = null;
      try {
        order = orderService.startTrip(passenger);
      } catch (ServiceException | DAOException e) {
        logger.error("Failed to make an order for trip");
        return "Failed to make an order for trip";
      }

      String orderResponse = "You have successfully completed the trip in " + order.getTotalTime()
          + " minute, the total length of the route is " + order.getTotalDistance() + " km";
      String payResponse = paymentService.makeTransaction(order, passenger);

      return orderResponse + "\n" + payResponse + "\n";
    } else {
      return "Unfortunately, not a single driver was found who would travel at the chosen fare. "
          + "Try to start the trip with a different fare";
    }
  }
}
