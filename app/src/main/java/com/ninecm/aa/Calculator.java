package com.ninecm.aa;

import java.util.Calendar;

public class Calculator {
    private Calendar endCalendar;

    public Calculator(int EndYear, int EndMonth, int EndDay) {
        endCalendar = Calendar.getInstance();
        endCalendar.set(EndYear, EndMonth, EndDay);
    }

    public int getYear(String date) {
        int year;
        year = Integer.parseInt(date.substring(0, 4));

        return year;
    }

    public int getMonth(String date) {
        int month;
        month = Integer.parseInt(date.substring(6, 8));

        return month;
    }

    public int getDay(String date) {
        int day;
        day = Integer.parseInt(date.substring(10, 12));

        return day;
    }

    public String oneToTwo(int notKnow) {
        String result = String.valueOf(notKnow);
        if (result.length() == 1) {
            result = "0" + result;
        }

        return result;
    }

    public int calDday() {
        Calendar today = Calendar.getInstance();
        // 86400000 == 1일
        long endDate = endCalendar.getTimeInMillis() / 86400000;
        long currDate = today.getTimeInMillis() / 86400000;

        long count = endDate - currDate;
        return (int) count + 1;
    }
}
