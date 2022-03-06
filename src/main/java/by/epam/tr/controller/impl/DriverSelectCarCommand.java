package by.epam.tr.controller.impl;

import by.epam.tr.controller.Command;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Класс - контроллер для опции выбора авто водителем
 * 
 * @see DriverOptionView#applicationMenu()
 */
public class DriverSelectCarCommand implements Command {
  /**
   * Initializing Service Layer objects using ServiceProvider
   * 
   * @see DriverSelectCarCommand#CAR_NAME_REGEX регулярное выражение для проверки наименований
   * @see DriverSelectCarCommand#CAR_NUM_REGEX регулярное выражение для проверки номера авто
   */
  private static final String CAR_NAME_REGEX = "[a-zA-Z0-9 ]+";
  private static final String CAR_NUM_REGEX = "[a-zA-Z0-9]+";
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private DriverService driverService = provider.getDriverService();

  /**
   * The received data about the driver's car is checked and a confirmation response or incorrect
   * data is displayed
   */
  public String execute(String[] requestParts) {
    String carModel = requestParts[1];
    int spasePos = Integer.valueOf(requestParts[2]);
    String carNumber = requestParts[3];
    StringBuilder modelBuilder = new StringBuilder(carModel);
    modelBuilder.insert(spasePos, " ");
    carModel = modelBuilder.toString();

    boolean result = false;
    if (carModel.matches(CAR_NAME_REGEX) && carNumber.matches(CAR_NUM_REGEX)) {
      result = driverService.selectCar(carModel.toUpperCase(), carNumber);
    } else {
      return "You entered incorrect data. Check it and try again";
    }

    if (result) {
      return "You have chosen your car";
    } else {
      return "You entered incorrect data. Check it and try again";
    }
  }
}
