package by.epam.tr.service;

import by.epam.tr.service.impl.DriverServiceImpl;
import by.epam.tr.service.impl.MapServiceImpl;
import by.epam.tr.service.impl.OrderServiceImpl;
import by.epam.tr.service.impl.PassengerServiceImpl;
import by.epam.tr.service.impl.PaymentServiceImpl;

/**
 * Класс - провайдер слоя Service
 */
public class ServiceProvider {
  private static final ServiceProvider serviceProvider = new ServiceProvider();
	
	private final PassengerService passengerService = new PassengerServiceImpl();
	private final MapService mapService = new MapServiceImpl();
	private final DriverService driverService = new DriverServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();
	private final PaymentService paymentService = new PaymentServiceImpl();
	
	public ServiceProvider() {}

	public PassengerService getPassengerService() {
		return passengerService;
	}

	public MapService getMapService() {
		return mapService;
	}

	public DriverService getDriverService() {
		return driverService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

    public static ServiceProvider getServiceProvider() {
      return serviceProvider;
    }
}
