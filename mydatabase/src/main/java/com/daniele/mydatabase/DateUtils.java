package com.daniele.mydatabase;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils {
	
	public static final ZonedDateTime END_OF_TIME_ZONED = 
			ZonedDateTime.of(9999, 12, 31, 0, 0, 0, 0, ZoneId.systemDefault());
	
	public static final LocalDate END_OF_DATE_TIME = 
			LocalDate.of(9999, 12, 31);

	public static Date fromLocalDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}