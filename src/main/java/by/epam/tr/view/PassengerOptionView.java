package by.epam.tr.view;

import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Controller;

/**
 * Класс слоя View роли пассажира
 */
public class PassengerOptionView extends ApplicationView {
  /**
   * @see PassengerOptionView#CHOISE_REGEXP Регулярное выражение для проверки ввода пользователя при
   *      выборе
   * @see PassengerOptionView#sc Сканнер ввода пользователем данных в консоль
   * @see PassengerOptionView#controller объект Controller для связи со слоем Controller
   * @see PassengerOptionView#request переменная, содержащая запрос пользователя и веедённые данные
   *      для него
   * @see PassengerOptionView#menuAnswer переменная, содержащая ответ слоя Controller на запрос
   *      пользователя
   */
  private static final String CHOISE_REGEXP = "[0-9]+";
  private Scanner sc = super.getSc();
  private Controller controller = super.getController();
  private String request;
  private String menuAnswer;
  private Logger logger = super.getLogger();

  /**
   * Консольное меню регистрации пассажира
   */
  @Override
  public void registrationMenu() {
    logger.info("Hello! Please enter your name:");
    String passengerName = sc.nextLine();
    logger.info("Please enter your surname:");
    String passengerSurname = sc.nextLine();

    request = "passengerRegistration" + " " + passengerName + " " + passengerSurname;
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
   * Консольное меню опций пассажира
   */
  @Override
  public void applicationMenu() {
    logger.info(
        "AirTaxi" + "\n" + "Press 1 to search a car" + "\n" + "Press 2 to set your payment type"
            + "\n" + "Press 3 to add credit card" + "\n" + "Press 4 to add promocode" + "\n"
            + "Press 5 to show order history" + "\n" + "Press any other number to exit");
    String choise = sc.nextLine();

    if (!choise.matches(CHOISE_REGEXP)) {
      applicationMenu();
    } else if (Integer.valueOf(choise) == 1) {
      logger.info("Enter your destination address:");
      String destinationAddress = sc.nextLine();
      request = "passengerSearchCar" + " " + destinationAddress;
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);
      if (menuAnswer.equalsIgnoreCase("You entered an incorrect delivery address")
          || menuAnswer.equalsIgnoreCase("There are no drivers near you")) {
        applicationMenu();
      } else {
        String takeOrder = sc.nextLine();
        if (!takeOrder.matches(CHOISE_REGEXP)) {
          applicationMenu();
        } else if (Integer.valueOf(takeOrder) == 1) {
          request = "passengerBeginTrip" + " " + "Minimal";
          menuAnswer = controller.doAction(request);
          logger.info(menuAnswer);
          applicationMenu();
        } else if (Integer.valueOf(takeOrder) == 2) {
          request = "passengerBeginTrip" + " " + "Middle";
          menuAnswer = controller.doAction(request);
          logger.info(menuAnswer);
          applicationMenu();
        } else if (Integer.valueOf(takeOrder) == 3) {
          request = "passengerBeginTrip" + " " + "Middle+";
          menuAnswer = controller.doAction(request);
          logger.info(menuAnswer);
          applicationMenu();
        } else if (Integer.valueOf(takeOrder) == 4) {
          request = "passengerBeginTrip" + " " + "Lux";
          menuAnswer = controller.doAction(request);
          logger.info(menuAnswer);
          applicationMenu();
        } else {
          applicationMenu();
        }
      }
    } else if (Integer.valueOf(choise) == 2) {
      logger.info("Select your payment type" + "\n" + "Press 1 to choose cash" + "\n"
          + "Press 2 to choose credit card");
      String paymentType = null;
      String choosePaymentType = sc.nextLine();
      if (!choosePaymentType.matches(CHOISE_REGEXP)) {
        applicationMenu();
      } else if (Integer.valueOf(choosePaymentType) == 1) {
        paymentType = "cash";
      } else if (Integer.valueOf(choosePaymentType) == 2) {
        paymentType = "creditcard";
      }

      request = "passengerSelectPaymentType" + " " + paymentType;
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);
      applicationMenu();
    } else if (Integer.valueOf(choise) == 3) {
      logger.info("Enter your credit card's number:");
      String cardNum = sc.nextLine();
      logger.info("Enter your credit card's expiring date:");
      String expiringDate = sc.nextLine();
      request = "passengerAddCreditCard" + " " + cardNum + " " + expiringDate;
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);
      applicationMenu();
    } else if (Integer.valueOf(choise) == 4) {
      logger.info("Enter your promocode's number:");
      String promocodeNum = sc.nextLine();
      request = "passengerAddPromocode" + " " + promocodeNum;
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);
      applicationMenu();
    } else if (Integer.valueOf(choise) == 5) {
      request = "ordersShowHistory";
      menuAnswer = controller.doAction(request);
      logger.info(menuAnswer);
      applicationMenu();
    } else {
      logger.info("Have a nice day!");
      return;
    }
  }
}