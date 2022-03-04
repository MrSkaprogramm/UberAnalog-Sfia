import by.epam.tr.beans.Order;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Rate;
import by.epam.tr.controller.Command;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.OrderService;
import by.epam.tr.service.PassengerService;
import by.epam.tr.service.PaymentService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Класс - контроллер для опции добавления начала поездки пассажира
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerBeginTripCommand implements Command {
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private OrderService orderService = provider.getOrderService();
  private PaymentService paymentService = provider.getPaymentService();
  private PassengerService passengerService = provider.getPassengerService();
  private DriverService driverService = provider.getDriverService();

  public String execute(String[] requestParts) {
    Rate rate = null;

    if (requestParts[1].equals("Minimal")) {
      rate = Rate.MINIMAL;
    } else if (requestParts[1].equals("Middle")) {
      rate = Rate.MIDDLE;
    } else if (requestParts[1].equals("Middle+")) {
      rate = Rate.MIDDLE_PLUS;
    } else if (requestParts[1].equals("Lux")) {
      rate = Rate.LUX;
    }

    Passenger passenger = passengerService.choosePassenger();

    if (driverService.choiseDriverByRate(rate)) {
      Order order = orderService.startTrip(passenger);

      String orderResponse = "You have successfully completed the trip in " + order.getTotalTime()
          + " minute, the total length of the route is " + order.getTotalDistance() + " km";
      String payResponse = paymentService.makeTransaction(order, passenger);

      return orderResponse + "\n" + payResponse + "\n";
    } else {
      return "Unfortunately, not a single driver was found who would travel at the chosen fare. "
          + "Try to start the trip with a different fare";
    }
  }
}
