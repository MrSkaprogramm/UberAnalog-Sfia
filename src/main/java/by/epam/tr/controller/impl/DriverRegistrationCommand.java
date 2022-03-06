package by.epam.tr.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Command;
import by.epam.tr.dao.DAOException;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Controller class for driver registration option
 * 
 * @see DriverOptionView#registrationMenu()
 */
public class DriverRegistrationCommand implements Command {
  private Logger logger = LogManager.getLogger(DriverRegistrationCommand.class);
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private DriverService driverService = provider.getDriverService();

  /**
   * Registers the driver in the system
   */
  public String execute(String[] requestParts) {
    String driverName = requestParts[1];
    String driverSurname = requestParts[2];
    String driverLegalEntityName = requestParts[3];

    boolean result = false;
    try {
      result = driverService.register(driverName, driverSurname, driverLegalEntityName);
    } catch (DAOException e) {
      logger.error("Failed to define location");
      return "False";
    }

    if (result) {
      return "True";
    } else {
      return "False";
    }
  }
}
