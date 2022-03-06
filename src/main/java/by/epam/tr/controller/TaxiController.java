package by.epam.tr.controller;

import by.epam.tr.datasource.Initialization;

/**
 * Class - implementation of the Controller layer interface
 */
public class TaxiController implements Controller {
  /**
   * Accepts a string from the View layer, determines the object of the command the user needs and
   * redirects to the corresponding command
   * 
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
