package Business.Service;

public class ServiceFactory {
	public static class WashStationType {
		public static final String TUNELWASHSTASIONSERVICE = "TunelWashStationService";
		public static final String TOUCHLESSWASHSTATIONSERVICE = "TouchlessWashStationService";
		public static final String STEAMWASHSTATIONSERVICE = "SteamWashStationService";
		public static final String MANUALWASHSTATIONSERVICE = "ManualWashStationService";
	}

	private static java.util.Map<String, Service> services = new java.util.HashMap<String, Service>();

	static {
		services.put(WashStationType.TUNELWASHSTASIONSERVICE,
				new TunelWashStationService());
		services.put(WashStationType.TOUCHLESSWASHSTATIONSERVICE,
				new TouchlessWashStationService());
		services.put(WashStationType.STEAMWASHSTATIONSERVICE,
				new SteamWashStationService());
		services.put(WashStationType.MANUALWASHSTATIONSERVICE,
				new ManualWashStationService());
	}

	public static Service getInstance(final String s)
			throws CloneNotSupportedException {
		return (Service) services.get(s).clone();
	}
}
