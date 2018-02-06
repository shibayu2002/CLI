package net.zive.shibayu.cli;

import java.util.Date;

/**
 * <p>����.</p>
 *
 * Copyright�iC�jYuu Shiba All Rights Reserved.
 */
public class Price {
    /** �����. */
    private Date dateTime;
    /** �����R�[�h. */
    private String code;
    /** �n�l. */
    private double open;
    /** ���l. */
    private double high;
    /** ���l. */
    private double low;
    /** �I�l. */
    private double close;
    /** �o����. */
    private double volume;

    /**
     * �R���X�g���N�^.
     */
    public Price() {
    }

    /**
     * �R���X�g���N�^.
     * @param aDateTime �����
     * @param aCode �����R�[�h
     * @param aOpen �n�l
     * @param aHigh ���l
     * @param aLow ���l
     * @param aClose �I�l
     * @param aVolume �o����
     */
    public Price(final Date aDateTime, final String aCode,
            final double aOpen, final double aHigh,
            final double aLow, final double aClose,
            final double aVolume) {
        this.dateTime = aDateTime;
        this.code = aCode;
        this.open = aOpen;
        this.high = aHigh;
        this.low = aLow;
        this.close = aClose;
        this.volume = aVolume;
    }

    /**
     * �����Getter.
     * @return �����
     */
    public final Date getDateTime() {
        return dateTime;
    }

    /**
     * �����R�[�hGetter.
     * @return �����R�[�h
     */
    public final String getCode() {
        return code;
    }

    /**
     * �n�lGetter.
     * @return �n�l
     */
    public final double getOpen() {
        return open;
    }

    /**
     * ���lGetter.
     * @return ���l
     */
    public final double getHigh() {
        return high;
    }

    /**
     * �I�lGetter.
     * @return �I�l
     */
    public final double getLow() {
        return low;
    }

    /**
     * �I�lGetter.
     * @return �I�l
     */
    public final double getClose() {
        return close;
    }

    /**
     * �o����Getter.
     * @return �o����
     */
    public final double getVolume() {
        return volume;
    }

    @Override
    public final String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append(dateTime).append("\t");
        stb.append(code).append("\t");
        stb.append(open).append("\t");
        stb.append(high).append("\t");
        stb.append(low).append("\t");
        stb.append(close).append("\t");
        stb.append(volume).append("\t");
        return stb.toString();
    }
}