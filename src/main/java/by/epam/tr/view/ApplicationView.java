package by.epam.tr.view;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.tr.controller.Controller;
import by.epam.tr.controller.TaxiController;

/**
 * Класс слоя View приложения
 */
public abstract class ApplicationView {
  /**
   * @see ApplicationView#CHOISE_REGEXP Регулярное выражение для проверки ввода пользователя при
   *      выборе
   * @see ApplicationView#sc Сканнер ввода пользователем данных в консоль
   * @see ApplicationView#controller объект Controller для связи со слоем Controller
   * @see ApplicationView#request переменная, содержащая запрос пользователя и веедённые данные для
   *      него
   * @see ApplicationView#menuAnswer переменная, содержащая ответ слоя Controller на запрос
   *      пользователя
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
   * Стартовое консольное меню выбора роли
   */
  public static void logination() {
    logger.info(
        "You're welcome in AirTaxi service!\nChoose your role:\nPress 1 if you are a Passenger "
            + "\nor 2 if you are a driver\nor any other number if you want to get out");
    String userRole = sc.nextLine();
    if (!userRole.matches(CHOISE_REGEXP)) {
      logination();
    } else if (Integer.valueOf(userRole) == 1) {
            PassengerOptionView passengerOption = new PassengerOptionView();
            passengerOption.registrationMenu();
          } else if (Integer.valueOf(userRole) == 2) {
        DriverOptionView driverOption = new DriverOptionView();
        driverOption.registrationMenu();
    }else {
      logger.info("Have a nice day!");
        return;
    }
  }
  
  public abstract void registrationMenu();

  public abstract void applicationMenu();
}
