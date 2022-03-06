package by.epam.tr;

import by.epam.tr.datasource.Initialization;
import by.epam.tr.view.ApplicationView;

/**
 * Класс запуска программы
 */
public class AirTaxi {
  /**
   * @see Initialization#initStreetsDatabase() Uploading city streets to the database
   * @see Initialization#initCarsDatabase() Uploading cars to the database
   * @see ApplicationView#logination() Calling the role selection menu in the application
   */
  public static void main(String[] args) {
    Initialization dataInit = new Initialization();
    dataInit.initStreetsDatabase();
    dataInit.initCarsDatabase();
    ApplicationView.logination();
  }
}
