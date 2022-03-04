package by.epam.tr.controller;

/**
 * Интерфейс команд пользователя слоя Controller
 */
public interface Command {
  public String execute(String[] requestParts);
}
