package com.daniele.mydatabase;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtils {
	
	public static final ZonedDateTime END_OF_TIME_ZONED = 
			ZonedDateTime.of(9999, 12, 31, 0, 0, 0, 0, ZoneId.systemDefault());
	
	public static final LocalDate END_OF_DATE_TIME = 
			LocalDate.of(9999, 12, 31);

	public static LocalDate fromLocalDate(Date sqlDate) {
		return sqlDate.toLocalDate();
	}

	public static LocalDate now() {
		return LocalDate.now();
	}

	public static LocalDate getEndOfTime() {
		return END_OF_DATE_TIME;
	}

	public static ZonedDateTime getEndOfTimeZoned() {
		return END_OF_TIME_ZONED;
	}

}