import by.epam.tr.datasource.Initialization;

/**
 * Класс - реализация интерфейса слоя Controller
 */
public class TaxiController implements Controller {
  /**
   * Принимает строку со слоя View, определяет объект нужной пользователю команды и перенаправляет в
   * соответствующую команду
   * @see Initialization#initStreetsDatabase()
   * @see CommandProvider#getCommand(String commandName)
   */
  public String doAction(String request) {
    String[] requestParts;
    requestParts = request.split(" ");
    String commandName = requestParts[0];

    CommandProvider commandProvider = new CommandProvider();
    Command command = commandProvider.getCommand(commandName);

    return command.execute(requestParts);
  }
}
