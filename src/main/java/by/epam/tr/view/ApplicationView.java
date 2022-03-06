package by.epam.tr.view;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Controller;
import by.epam.tr.controller.TaxiController;

/**
 * Application View layer class
 */
public abstract class ApplicationView {
  /**
   * @see ApplicationView#CHOISE_REGEXP Regular expression to check user input when selecting
   * @see ApplicationView#sc Scanner for user input to the console
   * @see ApplicationView#controller the Controller object to communicate with the Controller layer
   * @see ApplicationView#request variable containing the user's request and the received data for
   *      it
   * @see ApplicationView#menuAnswer variable containing the Controller layer's response to the
   *      request user
   */
  private static final String CHOISE_REGEXP = "[0-9]+";
  private static Scanner sc = new Scanner(System.in);
  private Controller controller = new TaxiController();
  private String request;
  private String menuAnswer;

  private static Logger logger = LogManager.getLogger(ApplicationView.class);

  public static Scanner getSc() {
    return sc;
  }

  public static void setSc(Scanner sc) {
    ApplicationView.sc = sc;
  }

  public Controller getController() {
    return controller;
  }

  public void setController(Controller controller) {
    this.controller = controller;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public String getMenuAnswer() {
    return menuAnswer;
  }

  public void setMenuAnswer(String menuAnswer) {
    this.menuAnswer = menuAnswer;
  }

  public Logger getLogger() {
    return logger;
  }

  /**
   * Start console role selection menu
   */
  public static void logination() {
    logger.info(
        "You're welcome in AirTaxi service!\nChoose your role:\nPress 1 if you are a Passenger "
            + "\nor 2 if you are a driver\nor any other number if you want to get out");
    String userRole = sc.nextLine();
    if (userRole.matches(CHOISE_REGEXP) && Integer.valueOf(userRole) == 1) {
      PassengerOptionView passengerOption = new PassengerOptionView();
      passengerOption.registrationMenu();
      logination();
    } else if (userRole.matches(CHOISE_REGEXP) && Integer.valueOf(userRole) == 2) {
      DriverOptionView driverOption = new DriverOptionView();
      driverOption.registrationMenu();
    } else if (!userRole.matches(CHOISE_REGEXP)) {
      logination();
    } else {
      logger.info("Have a nice day!");
      return;
    }
  }

  public abstract void registrationMenu();

  public abstract void applicationMenu();
}
