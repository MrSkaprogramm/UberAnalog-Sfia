import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Order;
import by.epam.tr.controller.Command;
import by.epam.tr.service.DriverService;
import by.epam.tr.service.OrderService;
import by.epam.tr.service.PaymentService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.DriverOptionView;

/**
 * Класс - контроллер для опции начала поездки водителем
 * @see DriverOptionView#applicationMenu()
 */
public class DriverBeginTripCommand implements Command {
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private OrderService orderService = provider.getOrderService();
  private PaymentService paymentService = provider.getPaymentService();
  private DriverService driverService = provider.getDriverService();

  public String execute(String[] requestParts) {
    
    if (driverService.chooseDriver().getCar() != null) {
      Driver driver = driverService.chooseDriver();
      Order order = orderService.startTrip(driver);

      String orderResponse = "You have successfully completed the trip in " + order.getTotalTime()
            + " minutes, the total length of the route is " + order.getTotalDistance() + " km";
      String payResponse = paymentService.makeTransaction(order, driver);

      return orderResponse + "\n" + payResponse + "\n";
    } else {
      return "Please choose a car";
  }
}
}
