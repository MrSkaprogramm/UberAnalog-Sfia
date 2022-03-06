package by.epam.tr.controller.impl;

import by.epam.tr.controller.Command;
import by.epam.tr.service.PaymentService;
import by.epam.tr.service.ServiceProvider;
import by.epam.tr.view.PassengerOptionView;

/**
 * Класс - контроллер для опции добавления промокода на поездку пассажира
 * 
 * @see PassengerOptionView#applicationMenu()
 */
public class PassengerAddPromocodeCommand implements Command {
  /**
   * Initializing Service Layer objects using ServiceProvider
   * 
   * @see PassengerAddPromocodeCommand#PROMOCODE_NUM_REGEX Регулярное выражение для проверки номера
   *      промокода
   */
  private static final String PROMOCODE_NUM_REGEX = "[0-9a-zA-Z]+";
  private ServiceProvider provider = ServiceProvider.getServiceProvider();
  private PaymentService paymentService = provider.getPaymentService();

  /**
   * Checks the data and registers the passenger's promo code in the system or outputs a response
   * about incorrect data
   */
  public String execute(String[] requestParts) {
    String promocodeNum = requestParts[1];
    double discount = Math.random();

    boolean result = false;
    if (promocodeNum.matches(PROMOCODE_NUM_REGEX)) {
      result = paymentService.addPromocode(promocodeNum, discount);
    } else {
      return "You entered incorrect data. Check it and try again";
    }

    if (result) {
      return "You have successfully added a promocode";
    } else {
      return "You entered incorrect data. Check it and try again";
    }
  }
}
