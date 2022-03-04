package by.epam.tr.utils;
import java.util.Comparator;
import by.epam.tr.beans.Driver;

/**
 * Класс сортировки водителей в зависимости от улицы
 */
public class DriversSortByStreet implements Comparator<Driver> {
  /**
   * Сравнивает двух водителей по улице
   * @return Возвращает результат сравнения
   */
  public int compare(Driver o1, Driver o2) {
    return o1.getMap().getDriverLocation().compareTo(o2.getMap().getDriverLocation());
  }
}
