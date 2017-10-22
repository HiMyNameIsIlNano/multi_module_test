package com.daniele.mylogger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class LoggerFactory {
	
	private static Map<String, Logger> loggerMap = new ConcurrentHashMap<>();
	
	public static Logger getLogger(String clazz) {
		loggerMap.putIfAbsent(clazz, Logger.getLogger(clazz));
		return loggerMap.get(clazz);
	}
}