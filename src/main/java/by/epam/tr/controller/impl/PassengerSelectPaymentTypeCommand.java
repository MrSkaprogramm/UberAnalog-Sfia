package by.epam.tr.controller.impl;

import by.epam.tr.controller.Command;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;

/**
 * Class - controller for the option of choosing the preferred payment method of the passenger
 * * @see PassengerOptionView#applicationMenu()
 */
public class PassengerSelectPaymentTypeCommand implements Command {
  /**
   * Initialization of Service layer objects using ServiceProvider
   */
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private PassengerService passengerService = provider.getPassengerService();

  /**
   * Selects the preferred payment method by the passenger
   */
  public String execute(String[] requestParts) {
    String type = requestParts[1];

    String response = passengerService.selectPaymentType(type);
    return response;
  }
}
