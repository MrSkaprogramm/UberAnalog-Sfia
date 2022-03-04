import by.epam.tr.controller.Command;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Класс - контроллер для опции регистрации пассажира
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerRegistrationCommand implements Command {
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private PassengerService passengerService = provider.getPassengerService();

  public String execute(String[] requestParts) {
    String passengerName = requestParts[1];
    String passengerSurname = requestParts[2];

    boolean result = passengerService.register(passengerName, passengerSurname);

    if (result) {
      return "True";
    } else {
      return "False";
    }
  }
}
