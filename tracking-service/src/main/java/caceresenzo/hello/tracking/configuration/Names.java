package caceresenzo.hello.tracking.configuration;

public interface Names {
	
	public static final String APPLICATION = "tracking-service";
	
	public static final String EXCHANGE = "user-created";
	
	public static final String QUEUE = APPLICATION + "." + EXCHANGE;
	
}