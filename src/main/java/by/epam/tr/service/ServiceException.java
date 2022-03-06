package by.epam.tr.service;

/**
 * Exception class that combines all exceptions of the Service layer
 */
public class ServiceException extends Exception {
  private static final long serialVersionUID = -1276822170861281157L;

  public ServiceException() {
    super();
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(Exception e) {
    super(e);
  }

  public ServiceException(String message, Exception e) {
    super(message, e);
  }
}
