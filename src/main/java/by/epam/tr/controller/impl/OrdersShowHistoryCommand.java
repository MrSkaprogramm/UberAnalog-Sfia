package by.epam.tr.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Command;
import by.epam.tr.service.OrderService;
import by.epam.tr.service.ServiceException;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;
import by.epam.tr.view.PassengerOptionView;

/**
 * Controller class for the option to view the trip history (common for the driver and passenger
 * roles)
 * 
 * @see DriverOptionView#applicationMenu()
 * @see PassengerOptionView#applicationMenu()
 */
public class OrdersShowHistoryCommand implements Command {
  /**
   * Initializing Service Layer objects using ServiceProvider
   * 
   * @see OrdersShowHistoryCommand#logger Объект логгера
   */
  private Logger logger = LogManager.getLogger(OrdersShowHistoryCommand.class);
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private OrderService orderService = provider.getOrderService();

  /**
   * Transmits information about the trip history to the View layer
   */
  public String execute(String[] requestParts) {
    String response = null;
    try {
      response = orderService.showOrdersHistory();
    } catch (ServiceException e) {
      e.getCause();
      e.printStackTrace();
      logger.error("Failed to read a report");
      return "Failed to read a report";
    }
    return response;
  }
}
