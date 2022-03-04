package by.epam.tr.dao;
import java.util.Map;
import by.epam.tr.beans.Car;
import by.epam.tr.beans.Rate;
import by.epam.tr.datasource.DataSource;

/**
 * Интерфейс слоя DAO для bean
 * @see Car
 */
public interface CarDAO {
  /**
   * Возвращает список доступных машин {@link DataSource#getSuitableCarsTaxis()} для вождения в
   * такси
   */
  public Map<String, Rate> getCarsSuitableTaxis();

  /**
   * Возвращает boolean возможного наличия модели автомобиля в списке доступных машин
   */
  public boolean checkCar(String carModel);

  /**
   * Возвращает Rate модели автомобиля из списка доступных машин
   */
  public Rate getCarRate(String carModel);

  /**
   * Возвращает случайную модель автомобиля из списка доступных машин
   */
  public String getRandomCarModel();
}
