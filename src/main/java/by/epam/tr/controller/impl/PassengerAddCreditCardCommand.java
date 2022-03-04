package by.epam.tr.controller.impl;

import by.epam.tr.controller.Command;
import by.epam.tr.service.PaymentService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Класс - контроллер для опции добавления кредитной карты пассажира
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerAddCreditCardCommand implements Command {
  /**
   * @see PassengerAddCreditCardCommand#CARD_NUM_REGEX регулярное выражение для проверки номера
   *      кредитной карты
   */
  private static final String CARD_NUM_REGEX = "[0-9]+";
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private PaymentService paymentService = provider.getPaymentService();

  public String execute(String[] requestParts) {
    String creditCardNum = requestParts[1];
    String expiringDate = requestParts[2];

    boolean result = false;
    if (creditCardNum.matches(CARD_NUM_REGEX)) {
      result = paymentService.addCreditCard(Integer.valueOf(creditCardNum), expiringDate);
    }else {
      return "You entered incorrect data. Check it and try again";
    }
    
    if (result) {
      return "You have successfully added a credit card";
    } else {
      return "You entered incorrect data. Check it and try again";
    }
  }
}
