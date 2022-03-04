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
 * Класс - контроллер для опции просмотра истории поездок (общая для ролей водитель и пассажир)
 * @see DriverOptionView#applicationMenu()
 * @see PassengerOptionView#applicationMenu()
 */
public class OrdersShowHistoryCommand implements Command {
  /**
   * @see OrdersShowHistoryCommand#logger Объект логгера
   */
  private Logger logger = LogManager.getLogger(OrdersShowHistoryCommand.class);
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private OrderService orderService = provider.getOrderService();

  public String execute(String[] requestParts) {
    String response = null;
    try {
      orderService.makeOrderReport();
    } catch (ServiceException e) {
      logger.warn("Failed to make a report");
      return "Failed to make a report";
    }

    try {
      response = orderService.showOrdersHistory();
    } catch (ServiceException e) {
      logger.warn("Failed to read a report");
      return "Failed to read a report";
    }
    return response;
  }

}
