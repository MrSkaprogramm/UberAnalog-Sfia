package by.epam.tr.controller.impl;

import by.epam.tr.beans.Driver;
import by.epam.tr.beans.NavigationMap;
import by.epam.tr.controller.Command;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.MapService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Класс - контроллер для опции выхода на линию водителем
 * @see DriverOptionView#applicationMenu()
 */
public class DriverGetOnTheLineCommand implements Command {
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private DriverService driverService = provider.getDriverService();
  private PassengerService passengerService = provider.getPassengerService();
  private MapService mapService = provider.getMapService();

  public String execute(String[] requestParts) {
    boolean passengersThere = passengerService.generatePassengers();

    if (passengersThere) {
      Driver driver = driverService.chooseDriver();
      NavigationMap driverMap = driver.getMap();
      String passangerLocation = mapService.defineLocation();
      String driverLocation = mapService.defineLocation();
      String deliveryAddress = mapService.defineLocation();
      driverMap.setPassengerLocation(passangerLocation);
      driverMap.setDriverLocation(driverLocation);
      driverMap.setDeliveryAddress(deliveryAddress);

      String response = "The nearest passanger is " + mapService.locationDistance(passangerLocation)
          + "away from you";
      return response;
    } else {
      return "There are no passengers near you";
    }
  }

}
