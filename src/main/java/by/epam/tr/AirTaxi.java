import by.epam.tr.datasource.Initialization;
import by.epam.tr.view.ApplicationView;

/**
 * Класс запуска программы
 */
public class AirTaxi {
  /**
   * @see Initialization#initStreetsDatabase() Загрузка улиц города в базу данных
   * @see Initialization#initCarsDatabase() Загрузка автомобилей в базу данных
   * @see ApplicationView#logination() Вызов меню выбора роли в приложении
   */
  public static void main(String[] args) {
		Initialization dataInit = new Initialization();
		dataInit.initStreetsDatabase();
        dataInit.initCarsDatabase();
        ApplicationView.logination();
	}
}
