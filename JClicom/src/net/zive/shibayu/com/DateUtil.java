package net.zive.shibayu.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>���t���[�e�B���e�B.</p>
 *
 * Copyright�iC�jYuu Shiba All Rights Reserved.
 */
public final class DateUtil {

    /**
     * �R���X�g���N�^(�B�؂�).
     */
    private DateUtil() {
    }

    /**
     * ������̓��t�ϊ�.
     * @param date ���t������
     * @param format �t�H�[�}�b�g
     * @return ���t
     * @throws ParseException �ϊ��G���[
     */
    public static Date strToDate(final String date, final String format)
            throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.parse(date);
    }

    /**
     * Date�^��Calendar�ɕϊ�.
     * @param date �ϊ��Ώ�
     * @return Calendar
     */
    public static synchronized Calendar toCalendar(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * ���t�̕�����ϊ�.
     * @param date ���t
     * @param format �t�H�[�}�b�g
     * @return �ϊ��㕶����
     */
    public static String dateToStr(final Date date, final String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * ������̓��t�ϊ�.
     * @param date ���t������
     * @param before �ϊ��O�t�H�[�}�b�g
     * @param after �ϊ���t�H�[�}�b�g
     * @return ���t
     * @throws ParseException �ϊ��G���[
     */
    public static String strToStr(final String date,
            final String before, final String after)
            throws ParseException {
        return dateToStr(strToDate(date, before), after);
    }

    /**
     * ���t�����Z����.
     * @param date ���t
     * @param interval ���Z��
     * @return ���Z����
     */
    public static synchronized Date dateAdd(final Date date,
            final int interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, interval);
        return c.getTime();
    }

    /**
     * ���t���R�s�[����.
     * @param date �R�s�[��
     * @return Date
     */
    public static synchronized Date clone(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTime();
    }
}
