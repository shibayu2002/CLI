package net.zive.shibayu.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>日付ユーティリティ.</p>
 *
 * Copyright（C）Yuu Shiba All Rights Reserved.
 */
public final class DateUtil {

    /**
     * コンストラクタ(隠ぺい).
     */
    private DateUtil() {
    }

    /**
     * 文字列の日付変換.
     * @param date 日付文字列
     * @param format フォーマット
     * @return 日付
     * @throws ParseException 変換エラー
     */
    public static Date strToDate(final String date, final String format)
            throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.parse(date);
    }

    /**
     * Date型をCalendarに変換.
     * @param date 変換対象
     * @return Calendar
     */
    public static synchronized Calendar toCalendar(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 日付の文字列変換.
     * @param date 日付
     * @param format フォーマット
     * @return 変換後文字列
     */
    public static String dateToStr(final Date date, final String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 文字列の日付変換.
     * @param date 日付文字列
     * @param before 変換前フォーマット
     * @param after 変換後フォーマット
     * @return 日付
     * @throws ParseException 変換エラー
     */
    public static String strToStr(final String date,
            final String before, final String after)
            throws ParseException {
        return dateToStr(strToDate(date, before), after);
    }

    /**
     * 日付を加算する.
     * @param date 日付
     * @param interval 加算日
     * @return 加算結果
     */
    public static synchronized Date dateAdd(final Date date,
            final int interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, interval);
        return c.getTime();
    }

    /**
     * 日付をコピーする.
     * @param date コピー元
     * @return Date
     */
    public static synchronized Date clone(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTime();
    }
}
