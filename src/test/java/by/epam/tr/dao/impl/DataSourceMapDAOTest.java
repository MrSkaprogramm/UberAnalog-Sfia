package by.epam.tr.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import by.epam.tr.dao.DAOException;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceMapDAOTest {
  private Logger logger = LogManager.getLogger(DataSourceMapDAOTest.class);
  @Mock
  Map<String, Double> streetsLocationsFromCentre;
  @InjectMocks
  private DataSourceMapDAO dataSourceMapDAO;

  public DataSourceMapDAOTest() {
    MockitoAnnotations.openMocks(this);
    this.dataSourceMapDAO = new DataSourceMapDAO();
  }

  @Test
  public void testGetRandomLocation() {
    Set<String> streets = new HashSet<String>();
    streets.add("Aivazovsky");
    streets.add("Angarsk");
    streets.add("Avtodorovskaya");

    given(streetsLocationsFromCentre.keySet()).willReturn(streets);
    try {
      assertEquals("Aivazovsky", dataSourceMapDAO.getRandomLocation());
    } catch (DAOException e) {
      logger.error("Test failed with exception");
      return;
    }
  }
}
