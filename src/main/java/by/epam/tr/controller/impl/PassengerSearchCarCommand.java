import by.epam.tr.beans.NavigationMap;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Rate;
import by.epam.tr.controller.Command;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.MapService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Класс - контроллер для опции поиска водителя и выбора тарифа пассажиром
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerSearchCarCommand implements Command {
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private DriverService driverService = provider.getDriverService();
  private PassengerService passengerService = provider.getPassengerService();
  private MapService mapService = provider.getMapService();

  public String execute(String[] requestParts) {
        String deliveryAddress = requestParts[1];
        boolean checkDeliveryAddress = mapService.checkLocation(deliveryAddress);
        boolean driversThere = driverService.generateDrivers();

        if (driversThere && checkDeliveryAddress) {
          Passenger passenger = passengerService.choosePassenger();
          NavigationMap passengerMap = passenger.getMap();
          String passengerLocation = mapService.defineLocation();
          String driverLocation = mapService.defineLocation();
          passengerMap.setDriverLocation(driverLocation);
          passengerMap.setDeliveryAddress(deliveryAddress);
          passengerMap.setPassengerLocation(passengerLocation);

          String response = "Which tariff will you choose?" + "\n" + "Press 1 to choose Minimal:"
              + Rate.MINIMAL.getTripRate() + "$ per km" + "\n" + "Press 2 to choose Middle:"
              + Rate.MIDDLE.getTripRate() + "$ per km" + "\n" + "Press 3 to choose Middle+:"
              + Rate.MIDDLE_PLUS.getTripRate() + "$ per km" + "\n" + "Press 4 to choose  Lux:"
              + Rate.LUX.getTripRate() + "$ per km";
          return response;
        } else if (!checkDeliveryAddress) {
          return "You entered an incorrect delivery address";
        } else {
          return "There are no passengers near you";
        }
	}
}
