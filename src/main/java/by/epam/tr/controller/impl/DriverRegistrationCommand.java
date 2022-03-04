package by.epam.tr.controller.impl;

import by.epam.tr.controller.Command;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Класс - контроллер для опции регистрации водителя
 * @see DriverOptionView#registrationMenu()
 */
public class DriverRegistrationCommand implements Command {
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private DriverService driverService = provider.getDriverService();

  public String execute(String[] requestParts) {
    String driverName = requestParts[1];
    String driverSurname = requestParts[2];
    String driverLegalEntityName = requestParts[3];

    boolean result = driverService.register(driverName, driverSurname, driverLegalEntityName);

    if (result) {
      return "True";
    } else {
      return "False";
    }
  }
}
