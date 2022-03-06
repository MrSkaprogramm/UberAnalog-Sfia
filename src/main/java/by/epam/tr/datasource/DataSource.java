package by.epam.tr.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import by.epam.tr.beans.Driver;
import by.epam.tr.beans.Passenger;
import by.epam.tr.beans.Rate;
import by.epam.tr.utils.DriversSortByStreet;

/**
 * Database implementation class by collection
 */
public class DataSource {
  /**
   * @see DataSource#streetsLocationsFromCentre City streets and distance from the center
   * @see DataSource#suitableCarsTaxis Cars allowed for Taxi rides
   * @see DataSource#drivers List of drivers
   * @see DataSource#passengers Passenger List
   * @see DataSource#orders List of orders
   */
  private Map<String, Double> streetsLocationsFromCentre = new HashMap<String, Double>();
  private Map<String, Rate> suitableCarsTaxis = new HashMap<String, Rate>();
  private Set<Driver> drivers = new TreeSet<Driver>(new DriversSortByStreet());
  private Set<Passenger> passengers = new TreeSet<Passenger>();
	
	public DataSource() {}

    public Map<String, Double> getStreetsLocationsFromCentre() {
      return streetsLocationsFromCentre;
  }

    public void setStreetsLocationsFromCentre(Map<String, Double> streetsLocationsFromCentre) {
      this.streetsLocationsFromCentre = streetsLocationsFromCentre;
  }

    public Map<String, Rate> getSuitableCarsTaxis() {
      return suitableCarsTaxis;
    }

    public void setSuitableCarsTaxis(Map<String, Rate> suitableCarsTaxis) {
      this.suitableCarsTaxis = suitableCarsTaxis;
    }

    public Set<Driver> getDrivers() {
      return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
      this.drivers = drivers;
    }

    public Set<Passenger> getPassengers() {
      return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
      this.passengers = passengers;
    }
}
