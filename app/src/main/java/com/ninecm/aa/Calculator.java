package com.ninecm.aa;

import java.util.Calendar;

/* 필요한 변환 혹은 계산을 담은 클래스 */
public class Calculator {
    private Calendar endCalendar;

    // Constructor
    public Calculator(int EndYear, int EndMonth, int EndDay) {
        // 유통기한으로 날짜 설정
        endCalendar = Calendar.getInstance();
        endCalendar.set(EndYear, EndMonth - 1, EndDay);
    }

    // 문자열 yyyymmdd로부터 연도를 가져옴
    public static int getYear(String date) {
        int year;
        year = Integer.parseInt(date.substring(0, 4));

        return year;
    }

    // 문자열 yyyymmdd로부터 달을 가져옴
    public static int getMonth(String date) {
        int month;
        month = Integer.parseInt(date.substring(4, 6));

        return month;
    }

    // 문자열 yyyymmdd로부터 날짜를 가져옴
    public static int getDay(String date) {
        int day;
        day = Integer.parseInt(date.substring(6, 8));

        return day;
    }

    // 날짜나 달이 1글자인지 2글자인지 판별해 포맷을 맞춤
    public static String oneToTwo(int notKnow) {
        String result = String.valueOf(notKnow);
        if (result.length() == 1) {
            result = "0" + result;
        }

        return result;
    }

    // D-Day 계산
    public int calDday() {
        // 휴대폰의 날짜를 기준으로 calendar 생성
        Calendar today = Calendar.getInstance();

        // 86400000 == 1일
        long endDate = endCalendar.getTimeInMillis() / 86400000;
        long currDate = today.getTimeInMillis() / 86400000;

        long count = endDate - currDate;
        return (int) count;
    }

    // D-Day 계산 결과에 따른 문자열 반환
    public String stringDday(int dDay) {
        String result;
        if (dDay == 0) {
            result = "유통기한이 오늘까지입니다.";
        } else if (dDay > 0) {
            result = String.valueOf(dDay);
        } else {
            result = "유통기한이 "+Math.abs(dDay)+"일 지났습니다.";
        }

        return result;
    }
}