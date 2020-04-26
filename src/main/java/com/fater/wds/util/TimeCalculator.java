package com.fater.wds.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeCalculator {
	public static Date getPreDoneScore(Date holdDate,Integer month) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(holdDate);
		calendar.add(Calendar.MONTH, month);
		// calendar的time转成java.util.Date格式日期
		java.util.Date utilDate = (java.util.Date) calendar.getTime();;
		utilDate = (java.util.Date) calendar.getTime();
		// java.util.Date日期转换成转成java.sql.Date格式
		Date newDate = new Date(utilDate.getTime());
		return newDate;
	}
	

}
