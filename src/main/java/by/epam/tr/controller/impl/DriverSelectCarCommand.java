import by.epam.tr.controller.Command;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Класс - контроллер для опции выбора авто водителем
 * @see DriverOptionView#applicationMenu()
 */
public class DriverSelectCarCommand implements Command {
  /**
   * @see DriverSelectCarCommand#NAME_REGEX регулярное выражение для проверки наименований
   * @see DriverSelectCarCommand#CAR_NUM_REGEX регулярное выражение для проверки номера авто
   */
  private static final String NAME_REGEX = "[a-zA-Z ]+";
  private static final String CAR_NUM_REGEX = "[a-zA-Z0-9]+";
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private DriverService driverService = provider.getDriverService();

  public String execute(String[] requestParts) {
    String carModel = requestParts[1];
    int spasePos = Integer.valueOf(requestParts[2]);
    String carNumber = requestParts[3];
    StringBuilder modelBuilder = new StringBuilder(carModel);
    modelBuilder.insert(spasePos, " ");
    carModel = modelBuilder.toString();

    boolean result = false;
    if (carModel.matches(NAME_REGEX) && carNumber.matches(CAR_NUM_REGEX)) {
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
