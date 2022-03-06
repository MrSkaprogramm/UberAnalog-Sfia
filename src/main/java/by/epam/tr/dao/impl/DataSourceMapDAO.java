package by.epam.tr.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.MapDAO;
import by.epam.tr.datasource.DataSource;

/**
 * Implementation class of the DAO layer interface * @see MapDAO
 */
public class DataSourceMapDAO implements MapDAO {
  private final String datasourceFileName = "CityStreetsDataSource.txt";
  private final String datasourseDirectory = "src\\main\\java\\by\\epam\\tr\\datasource";
  private Path datasourcePath;
  private File file;

  public DataSourceMapDAO() {
    this.datasourcePath = Paths.get(datasourseDirectory).toAbsolutePath();
    this.file = new File(datasourcePath.toString(), datasourceFileName);
  }

  /**
   * Reading a data source with city streets
   */
  @Override
  public Map<String, Double> readSource() throws DAOException {
    Map<String, Double> streetsLocations = new HashMap<>();
    try (FileReader readStreetsfile = new FileReader(file);
        BufferedReader streetReader = new BufferedReader(readStreetsfile)) {
      String res = streetReader.readLine();
      while (res != null) {
        String[] streetLocation = res.split(" ");
        streetsLocations.put(streetLocation[0], Double.valueOf(streetLocation[1]));
        res = streetReader.readLine();
      }
    } catch (IOException e) {
      throw new DAOException();
    }
    return streetsLocations;
  }

  /**
   * Adding a street to the list of city streets
   */
  public void addStreet(String street) throws DAOException {
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new DAOException();
      }
    }

    try (FileWriter addStreet = new FileWriter(file, true);) {
      addStreet.write(street + "\n");
    } catch (IOException e) {
      throw new DAOException();
    }
  }

  /**
   * Search for a street in the list of streets by name
   *
   * @param street street name
   * @return Returns boolean - the value of finding a street in the list by name
   *         {@link DataSource#getStreetsLocationsFromCentre()}
   * @throws DAOException
   */
  public boolean checkLocation(String street) throws DAOException {
    Map<String, Double> streetsLocations = readSource();
    return streetsLocations.containsKey(street);
  }

  /**
   * Search for a random city street
   * 
   * @return Returns a random street {@link DataSource#getStreetsLocationsFromCentre()}
   * @throws DAOException
   */
  public String getRandomLocation() throws DAOException {
    Random generator = new Random();
    Map<String, Double> streetsLocations = readSource();
    Set<String> cityStreets = streetsLocations.keySet();
    Object[] streets = cityStreets.toArray();
    String location = (String) streets[generator.nextInt(streets.length)];
    return location;
  }

  /**
   * Search for the distance of a city street from the center
   *
   * @param street street name
   * @return Returns the distance of a city street from the center
   *         {@link DataSource#getStreetsLocationsFromCentre()}
   * @throws DAOException
   */
  public double getLocationDistance(String street) throws DAOException {
    Map<String, Double> streetsLocations = readSource();
    return streetsLocations.get(street);
  }
}
