package by.epam.tr.view;

import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Controller;

/**
 * Class of the Driver Role View layer
 */
public class DriverOptionView extends ApplicationView {
  /**
   * @see DriverOptionView#CHOISE_REGEXP Regular expression to check user input when selecting
   * @see DriverOptionView#sc Scanner for user input to the console
   * @see DriverOptionView#controller Controller object to communicate with the Controller layer
   * @see DriverOptionView#request variable containing the user's request and the received data for
   *      him
   * @see DriverOptionView#menuAnswer variable containing the Controller layer's response to the
   *      request user
   */
  private static final String CHOISE_REGEXP = "[0-9]+";
  private Scanner sc = super.getSc();
  private Controller controller = super.getController();
  private String request;
  private String menuAnswer;
  private Logger logger = super.getLogger();

  /**
   * Console driver registration menb
   */
  @Override
  public void registrationMenu() {
    logger.info("Enter please your name:");
    String driverName = sc.nextLine();
    logger.info("Enter please your surname:");
    String driverSurame = sc.nextLine();
    logger.info("Enter please your legal entity name:");
    String legalEntityName = sc.nextLine();

    request = "driverRegistration" + " " + driverName + " " + driverSurame + " " + legalEntityName;
    menuAnswer = controller.doAction(request);
    if (menuAnswer.equalsIgnoreCase("True")) {
      logger.info("You have successfully registered");
      applicationMenu();
    } else {
      logger.info("You entered incorrect data. Check it and try again");
      registrationMenu();
    }
  }

  /**
   * Console menu of driver options
   */
  @Override
  public void applicationMenu() {
    logger.info("AirTaxi" + "\n" + "Press 1 to get on the line" + "\n" + "Press 2 to select car"
        + "\n" + "Press 3 to show order history" + "\n" + "Press any other number to exit");
    String choise = sc.nextLine();

    if (choise.matches(CHOISE_REGEXP) && Integer.valueOf(choise) == 1) {
      request = "driverGetOnLine";
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);

      if (menuAnswer.equalsIgnoreCase("There are no passengers near you")) {
        applicationMenu();
      } else {
        logger.info("Do you want to take an order?" + "\n" + "Press 1 to get an order" + "\n"
            + "Press 2 to cancel an order");
        String takeOrder = sc.nextLine();
        if (!takeOrder.matches(CHOISE_REGEXP)) {
          applicationMenu();
        } else if (Integer.valueOf(choise) == 1) {
          request = "driverBeginTrip";
          menuAnswer = controller.doAction(request);
          logger.info(menuAnswer);
          applicationMenu();
        } else if (Integer.valueOf(choise) == 2) {
          request = "ordersShowHistory";
          menuAnswer = controller.doAction(request);
          logger.info(menuAnswer);
          applicationMenu();
        }
      }
    } else if (choise.matches(CHOISE_REGEXP) && Integer.valueOf(choise) == 2) {
      logger.info("Enter your car's model:");
      String carModel = sc.nextLine();
      carModel = carModel.trim();
      carModel = carModel.replaceAll(" +", " ");
      int spacePos = 0;
      for (int i = 0; i < carModel.length(); i++) {
        if (String.valueOf(carModel.charAt(i)).matches(" ")) {
          spacePos = i;
          break;
        }
      }
      carModel = carModel.replaceAll(" ", "");
      logger.info("Enter your car's number:");
      String carNumber = sc.nextLine();
      request = "driverSelectCar" + " " + carModel + " " + spacePos + " " + carNumber;
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);
      applicationMenu();
    } else if (choise.matches(CHOISE_REGEXP) && Integer.valueOf(choise) == 3) {
      request = "ordersShowHistory";
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);
      applicationMenu();
    } else if (!choise.matches(CHOISE_REGEXP)) {
      applicationMenu();
    } else {
      logger.info("Have a nice day!");
      return;
    }
  }
}
