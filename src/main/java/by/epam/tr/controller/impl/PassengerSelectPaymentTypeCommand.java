package by.epam.tr.controller.impl;

import by.epam.tr.controller.Command;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Класс - контроллер для опции выбора предпочитаемого способа оплаты пассажира
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerSelectPaymentTypeCommand implements Command {
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private PassengerService passengerService = provider.getPassengerService();

  public String execute(String[] requestParts) {
        String type = requestParts[1];
		
        String response = passengerService.selectPaymentType(type);
		return response;
	}
}
