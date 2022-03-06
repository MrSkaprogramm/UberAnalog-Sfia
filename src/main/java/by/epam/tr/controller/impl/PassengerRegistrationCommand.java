package by.epam.tr.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Command;
import by.epam.tr.dao.DAOException;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Controller class for passenger check-in option
 * 
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerRegistrationCommand implements Command {
  /**
   * Initializing Service Layer objects using ServiceProvider
   * 
   * @see PassengerBeginTripCommand#logger Объект логгера
   */
  private Logger logger = LogManager.getLogger(PassengerRegistrationCommand.class);
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private PassengerService passengerService = provider.getPassengerService();

  /**
   * Registers a passenger in the system
   */
  public String execute(String[] requestParts) {
    String passengerName = requestParts[1];
    String passengerSurname = requestParts[2];

    boolean result = false;
    try {
      result = passengerService.register(passengerName, passengerSurname);
    } catch (DAOException e) {
      logger.error("Failed to define location");
      return "Failed to define location";
    }

    if (result) {
      return "True";
    } else {
      return "False";
    }
  }
}
