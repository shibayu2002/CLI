package net.zive.shibayu.cli;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.data.xy.DefaultHighLowDataset;

import net.zive.shibayu.com.date.DateUtil;

/**
 * candleChart（射影）コマンド.
 * 標準出力から受け取ったデータから蝋燭足チャートを生成する。
 * @author Yu.Shiba
 *
 */
public final class JCandleChart {
    /** 画像ファイル縦幅. */
    private static final int IMG_HEIGHT = 300;
    /** 画像ファイル横幅. */
    private static final int IMG_WIDTH =
            (int) (IMG_HEIGHT * 1.6);  // 黄金比
    /** ラインサイズ. */
    private static final int LINE_SIZE = 5;
    /** 金額の最大値. */
    private static final double MAX_PRICE = 150.0;

    /** 始値の列番号. */
    private static final int OPEN_NUM = 1;
    /** 高値の列番号. */
    private static final int HIGH_NUM = 2;
    /** 安値の列番号. */
    private static final int LOW_NUM = 3;
    /** 終値の列番号. */
    private static final int CLOSE_NUM = 4;

    /**
     * スリープ時間.
     */
    private static final int WAIT_TIME = 100;

    /**
     * コンストラクタ(インスタンス化不可).
     */
    private JCandleChart() {
    }

    /**
     * コマンド引数チェック.
     * @param args コマンド引数
     */
    private static void cmdCheck(final String[] args) {
        if (args.length != 1) {
            System.out.println("Usage:jcandleChart FILE");
            System.exit(1);
        }
    }

    /**
     * 標準出力に入力があるまで待機する.
     * @throws Exception エラー
     */
    private static void waitSystemIn() throws Exception {
        while (System.in.available() == 0) {
            Thread.sleep(WAIT_TIME);
        }
    }

    /**
     * 引数を列番号のDefaultHighLowDatasetに変換する.
     * @param list 標準出力リスト
     * @return DefaultHighLowDataset
     * @throws Exception エラー
     */
    private static DefaultHighLowDataset createDataset(
                final List<String[]> list) throws Exception {
        int size = list.size();
        Date[] date = new Date[size];
        double[] high = new double[size];
        double[] low = new double[size];
        double[] open = new double[size];
        double[] close = new double[size];
        double[] volume = new double[size];

        for (int i = 0; i < size; i++) {
            String[] row = list.get(i);
            date[i] = DateUtil.strToDate(row[0], "hh:mm");
            open[i] = Double.parseDouble(row[OPEN_NUM]);
            high[i] = Double.parseDouble(row[HIGH_NUM]);
            low[i] = Double.parseDouble(row[LOW_NUM]);
            close[i] = Double.parseDouble(row[CLOSE_NUM]);
            volume[i] = 1;
        }

        DefaultHighLowDataset data =
                new DefaultHighLowDataset("USD/JPY", date,
                        high, low, open, close, volume);

        return data;
    }

    /**
     * 実行.
     * @param file 出力ファイル
     * @throws Exception エラー
     */
    private static void exec(final String file) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String line = null;
        List<String[]> list = new LinkedList<String[]>();
        try {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                list.add(row);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            br.close();
        }

        DefaultHighLowDataset dataset = createDataset(list);
        CandlestickRenderer renderer = new CandlestickRenderer();

        renderer.setCandleWidth(LINE_SIZE);
        renderer.setUpPaint(Color.BLUE);
        renderer.setDownPaint(Color.RED);

        renderer.setUseOutlinePaint(true);
        renderer.setBaseOutlinePaint(Color.BLACK);
        renderer.setBasePaint(Color.BLACK);
        renderer.setBaseOutlineStroke(new BasicStroke(1));

        renderer.setDrawVolume(true);   // 出来高表示
        renderer.setVolumePaint(Color.GREEN);

        renderer.setBaseItemLabelsVisible(false);
        DateAxis dateAxis = new DateAxis();
        dateAxis.setVisible(false);
        dateAxis.setTickLabelsVisible(false);

        NumberAxis priceAxis = new NumberAxis();
        priceAxis.setAutoRange(false);
        priceAxis.setRange(0, MAX_PRICE);
        priceAxis.setTickLabelsVisible(false);
        XYPlot plot = new XYPlot(dataset, dateAxis, priceAxis, renderer);
        JFreeChart chart = new JFreeChart(plot);

        ChartUtilities.saveChartAsPNG(
                new File(file), chart, IMG_WIDTH, IMG_HEIGHT);
    }

    /**
     * メイン処理.
     * @param args コマンド引数
     * @throws Exception エラー
     */
    public static void main(final String[] args) throws Exception {
        cmdCheck(args);
        waitSystemIn();
        exec(args[0]);
        System.exit(0);
    }
}
