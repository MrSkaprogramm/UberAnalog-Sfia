package by.epam.tr.utils;

import java.util.Comparator;
import by.epam.tr.beans.Driver;

/**
 * Sorting class of drivers depending on the street
 */
public class DriversSortByStreet implements Comparator<Driver> {
  /**
   * Compares two drivers on the street
   * 
   * @return Returns the result of the comparison
   */
  public int compare(Driver o1, Driver o2) {
    return o1.getMap().getDriverLocation().compareTo(o2.getMap().getDriverLocation());
  }
}
