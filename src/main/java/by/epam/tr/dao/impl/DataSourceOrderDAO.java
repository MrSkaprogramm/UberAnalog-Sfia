package by.epam.tr.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import by.epam.tr.beans.Order;
import by.epam.tr.dao.DAOException;
import by.epam.tr.dao.OrderDAO;
import by.epam.tr.datasource.DataSource;
import by.epam.tr.service.ServiceException;

/**
 * Implementation class of the DAO layer interface * @see OrderDAO
 */
public class DataSourceOrderDAO implements OrderDAO {
  private final String datasourceFileName = "OrdersDataSource.txt";
  private final String datasourseDirectory = "src\\main\\java\\by\\epam\\tr\\datasource";
  private Path datasourcePath;
  private File file;


  public DataSourceOrderDAO() {
    this.datasourcePath = Paths.get(datasourseDirectory).toAbsolutePath();
    this.file = new File(datasourcePath.toString(), datasourceFileName);
  }

  /**
   * Adds a new order to the data source
   *
   * @param order order
   * @return Returns boolean confirmation of addition {@link DataSource#GetOrders()}
   */

  public boolean addOrder(Order order) throws DAOException {
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new DAOException();
      }
    }

    try (FileOutputStream addOrder = new FileOutputStream(file, true);
        ObjectOutputStream addOrderObj = new ObjectOutputStream(addOrder);) {
      addOrderObj.writeObject(order);
    } catch (IOException e) {
      throw new DAOException();
    }
    return true;
  }

  /**
   * Reading the data source, saving it to the list of completed orders and passing it to the
   * Service layer
   * 
   * @return Returns a list with orders
   */
  @Override
  public List<Order> showOrdersHistory() throws ServiceException {
    List<Order> orders = new ArrayList<Order>();

    try (FileInputStream readOrder = new FileInputStream(file);) {
      ObjectInputStream readOrderObj = null;
      boolean count = true;
      while (count) {
        if (readOrder.available() != 0) {
          readOrderObj = new ObjectInputStream(readOrder);
          Order order = (Order) readOrderObj.readObject();
          orders.add(order);
        } else {
          count = false;
          readOrderObj.close();
        }
      }
    } catch (FileNotFoundException e) {
      throw new ServiceException();
    } catch (IOException e) {
      throw new ServiceException();
    } catch (ClassNotFoundException e) {
      throw new ServiceException();
    }
    return orders;
  }
}
