import java.util.Map;
import java.util.Random;
import java.util.Set;
import by.epam.tr.beans.Rate;
import by.epam.tr.dao.CarDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Класс реализации интерфейса слоя DAO
 * @see CarDAO
 */
public class DataSourceCarDAO implements CarDAO {
  /**
   * @param dataSource объект класса DataSource
   */
  private DataSource dataSource = new DataSource();
  private Map<String, Rate> suitableCarTaxis = dataSource.getSuitableCarsTaxis();

  /**
   * @return Возвращает список доступных машин {@link DataSource#getSuitableCarsTaxis()} для
   *         вождения в такси
   */
  public Map<String, Rate> getCarsSuitableTaxis() {
    return dataSource.getSuitableCarsTaxis();
  }

  /**
   * @param carModel Модель машины
   * @return Возвращает boolean возможного наличия модели автомобиля в списке доступных машин
   *         {@link DataSource#getSuitableCarsTaxis()}
   */
  public boolean checkCar(String carModel) {
    return suitableCarTaxis.containsKey(carModel);
  }

  /**
   * @param carModel Модель машин
   * @return Возвращает Rate модели автомобиля из списка доступных машин
   *         {@link DataSource#getSuitableCarsTaxis()}
   */
  public Rate getCarRate(String carModel) {
    return suitableCarTaxis.get(carModel);
  }

  /**
   * @return Возвращает случайную модель автомобиля из списка доступных машин
   *         {@link DataSource#getSuitableCarsTaxis()}
   */
  public String getRandomCarModel() {
    Random generator = new Random();
    Set<String> carModelsSet = suitableCarTaxis.keySet();
    Object[] carModels = carModelsSet.toArray();
    String driverCarModel = (String) carModels[generator.nextInt(carModels.length)];
    return driverCarModel;
  }
}
