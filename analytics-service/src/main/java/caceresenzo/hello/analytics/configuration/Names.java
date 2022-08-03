package caceresenzo.hello.analytics.configuration;

public interface Names {
	
	public static final String APPLICATION = "analytics-service";
	
	public static final String EXCHANGE = "user-created";
	
	public static final String QUEUE = APPLICATION + "." + EXCHANGE;
	
}