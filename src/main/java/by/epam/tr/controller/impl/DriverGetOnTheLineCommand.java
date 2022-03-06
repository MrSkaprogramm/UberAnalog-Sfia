package by.epam.tr.controller.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.NavigationMap;
import by.epam.tr.controller.Command;
import by.epam.tr.dao.DAOException;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.MapService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Controller class for the option of getting on the line by the driver
 * 
 * @see DriverOptionView#applicationMenu()
 */
public class DriverGetOnTheLineCommand implements Command {
  private Logger logger = LogManager.getLogger(DriverGetOnTheLineCommand.class);
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private DriverService driverService = provider.getDriverService();
  private PassengerService passengerService = provider.getPassengerService();
  private MapService mapService = provider.getMapService();

  /**
   * The system finds passengers nearby and outputs the answer in the form of the distance to the
   * nearest passenger
   */
  public String execute(String[] requestParts) {
    boolean passengersThere = false;
    try {
      passengersThere = passengerService.generatePassengers();
    } catch (DAOException e1) {
      logger.error("Failed to generate passengers");
    }

    if (passengersThere) {
      Driver driver = driverService.chooseDriver();
      NavigationMap driverMap = driver.getMap();
      String passangerLocation = null;
      try {
        passangerLocation = mapService.defineLocation();
        String driverLocation = mapService.defineLocation();
        String deliveryAddress = mapService.defineLocation();
        driverMap.setPassengerLocation(passangerLocation);
        driverMap.setDriverLocation(driverLocation);
        driverMap.setDeliveryAddress(deliveryAddress);
      } catch (DAOException e) {
        logger.error("Failed to define location");
        return "Failed to define location";
      }

      String response = null;
      try {
        response = "The nearest passanger is " + mapService.locationDistance(passangerLocation)
            + "km away from you";
      } catch (DAOException e) {
        logger.error("Failed to get distance of passenger location");
      }
      return response;
    } else {
      return "There are no passengers near you";
    }
  }
}
