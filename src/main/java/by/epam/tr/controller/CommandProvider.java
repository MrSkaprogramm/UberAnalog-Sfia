import java.util.HashMap;
import java.util.Map;
import by.epam.tr.controller.impl.DriverBeginTripCommand;
import by.epam.tr.controller.impl.DriverGetOnTheLineCommand;
import by.epam.tr.controller.impl.DriverRegistrationCommand;
import by.epam.tr.controller.impl.DriverSelectCarCommand;
import by.epam.tr.controller.impl.OrdersShowHistoryCommand;
import by.epam.tr.controller.impl.PassengerAddCreditCardCommand;
import by.epam.tr.controller.impl.PassengerAddPromocodeCommand;
import by.epam.tr.controller.impl.PassengerBeginTripCommand;
import by.epam.tr.controller.impl.PassengerRegistrationCommand;
import by.epam.tr.controller.impl.PassengerSearchCarCommand;
import by.epam.tr.controller.impl.PassengerSelectPaymentTypeCommand;

/**
 * Провайдер команд пользователя слоя Controller
 */
public class CommandProvider {
  private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandProvider() {
		commands.put("driverGetOnLine", new DriverGetOnTheLineCommand());
		commands.put("driverRegistration", new DriverRegistrationCommand());
		commands.put("passengerRegistration", new PassengerRegistrationCommand());
		commands.put("passengerSearchCar", new PassengerSearchCarCommand());
		commands.put("passengerSelectPaymentType", new PassengerSelectPaymentTypeCommand());
		commands.put("driverSelectCar", new DriverSelectCarCommand());
        commands.put("passengerAddCreditCard", new PassengerAddCreditCardCommand());
        commands.put("passengerAddPromocode", new PassengerAddPromocodeCommand());
        commands.put("driverBeginTrip", new DriverBeginTripCommand());
        commands.put("passengerBeginTrip", new PassengerBeginTripCommand());
        commands.put("ordersShowHistory", new OrdersShowHistoryCommand());
	}

    /**
     * Метод берёт объект команды, соответствующей названию команды
     * 
     * @param commandName из
     * @param commands
     */
	public Command getCommand(String commandName) {
      Command command = commands.get(commandName);
		
		return command;
	}
}
