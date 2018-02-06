package net.zive.shibayu.cli;

import java.util.Date;

/**
 * <p>時価.</p>
 *
 * Copyright（C）Yuu Shiba All Rights Reserved.
 */
public class Price {
    /** 基準日時. */
    private Date dateTime;
    /** 銘柄コード. */
    private String code;
    /** 始値. */
    private double open;
    /** 高値. */
    private double high;
    /** 安値. */
    private double low;
    /** 終値. */
    private double close;
    /** 出来高. */
    private double volume;

    /**
     * コンストラクタ.
     */
    public Price() {
    }

    /**
     * コンストラクタ.
     * @param aDateTime 基準日時
     * @param aCode 銘柄コード
     * @param aOpen 始値
     * @param aHigh 高値
     * @param aLow 安値
     * @param aClose 終値
     * @param aVolume 出来高
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
     * 基準日時Getter.
     * @return 基準日時
     */
    public final Date getDateTime() {
        return dateTime;
    }

    /**
     * 銘柄コードGetter.
     * @return 銘柄コード
     */
    public final String getCode() {
        return code;
    }

    /**
     * 始値Getter.
     * @return 始値
     */
    public final double getOpen() {
        return open;
    }

    /**
     * 高値Getter.
     * @return 高値
     */
    public final double getHigh() {
        return high;
    }

    /**
     * 終値Getter.
     * @return 終値
     */
    public final double getLow() {
        return low;
    }

    /**
     * 終値Getter.
     * @return 終値
     */
    public final double getClose() {
        return close;
    }

    /**
     * 出来高Getter.
     * @return 出来高
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
