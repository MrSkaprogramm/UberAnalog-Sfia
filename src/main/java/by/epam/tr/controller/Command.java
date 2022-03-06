package by.epam.tr.controller;

/**
 * Interface for user commands of the Controller layer
 */
public interface Command {
  public String execute(String[] requestParts);
}
