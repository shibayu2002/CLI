package net.zive.shibayu.cli;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
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

import gnu.getopt.Getopt;
import net.zive.shibayu.cli.base.BaseFileInCli;
import net.zive.shibayu.com.DateUtil;

/**
 * �`���[�g�쐬�R�}���h.
 * @author Yu.Sba
 *
 */
public class Chart extends BaseFileInCli {
    /** �摜�t�@�C���c��. */
    private static final int IMG_HEIGHT = 600;
    /** �摜�t�@�C������. */
    private static final int IMG_WIDTH =
            (int) (IMG_HEIGHT * 1.6);  // ������
    /** ���C���T�C�Y. */
    private static final int LINE_SIZE = 10;
    /** padding. */
    private static final double PADDING = 0.2;

    /** �n�l�̗�ԍ�. */
    private static final int OPEN_NUM = 1;
    /** ���l�̗�ԍ�. */
    private static final int HIGH_NUM = 2;
    /** ���l�̗�ԍ�. */
    private static final int LOW_NUM = 3;
    /** �I�l�̗�ԍ�. */
    private static final int CLOSE_NUM = 4;

    /**
     * �o�̓t�@�C���p�X.
     */
    private File file = null;

    /**
     * ���̓f�[�^.
     */
    private List<Price> list = new LinkedList<Price>();

    @Override
    protected final boolean checkArgs(final String[] args) {
        Getopt g = new Getopt(this.getClass().getName(), args, "o:");

        int c = -1;
        while ((c = g.getopt()) != -1) {
            switch (c) {
            case 'o':
                file = new File(g.getOptarg().trim());
                return true;
            default:
                return false;
            }
        }
        return false;
    }

    @Override
    protected final void beforeAccept() throws Exception {
        list.clear();
    }

    @Override
    protected final boolean accept(final String data) throws Exception {
        String[] row = data.split(",");
        Price p = new Price(
                DateUtil.strToDate(row[0], "yyyy/MM/dd HH:mm"),
                "USD/JPY",
                Double.parseDouble(row[OPEN_NUM]),
                Double.parseDouble(row[HIGH_NUM]),
                Double.parseDouble(row[LOW_NUM]),
                Double.parseDouble(row[CLOSE_NUM]),
                1.0);
        list.add(p);
        return true;
    }

    @Override
    protected final void afterAccept() throws Exception {
        DefaultHighLowDataset dataset = createDataset(list);
        CandlestickRenderer renderer = new CandlestickRenderer();

        renderer.setCandleWidth(LINE_SIZE);
        renderer.setUpPaint(Color.BLUE);
        renderer.setDownPaint(Color.RED);

        renderer.setUseOutlinePaint(true);
        renderer.setBaseOutlinePaint(Color.BLACK);
        renderer.setBasePaint(Color.BLACK);
        renderer.setBaseOutlineStroke(new BasicStroke(1));

        renderer.setDrawVolume(false);   // �o�����\���Ȃ�
        renderer.setVolumePaint(Color.GREEN);

        renderer.setBaseItemLabelsVisible(false);
        DateAxis dateAxis = new DateAxis();
        dateAxis.setVisible(true);
        dateAxis.setTickLabelsVisible(true);

        NumberAxis priceAxis = getNumberAxis(list);
        XYPlot plot = new XYPlot(dataset, dateAxis, priceAxis, renderer);
        JFreeChart chart = new JFreeChart(plot);

        ChartUtilities.saveChartAsPNG(file,
                chart, IMG_WIDTH, IMG_HEIGHT);
    }

    @Override
    protected final String helpString() {
        StringBuilder stb = new StringBuilder();
        stb.append("<�T�v>\n");
        stb.append("chart�R�}���h�B\n");
        stb.append("�w�肳�ꂽCSV�f�[�^����`���[�g���쐬����B\n\n");
        stb.append("<�R�}���h>\n");
        stb.append("Usage:chart [FILE] -o output path \n\n");
        stb.append("<����>\n");
        stb.append("FILE:CSV�t�@�C��(���ȗ���)\n");
        stb.append("* FILE���ȗ������ꍇ�͕W�����͂���CSV�f�[�^������B");
        stb.append("<�I�v�V����>\n");
        stb.append("-o:�o�̓t�@�C���p�X\n\n");
        stb.append("<���M����>\n");
        stb.append("���݃T�|�[�g���Ă���`���[�g�͘X�C���`���[�g�݂̂ł��B\n");
        stb.append("�E�X�C���`���[�g�̓��̓f�[�^�`��\n");
        stb.append(" ����,�n�l,���l,���l,�I�l�B\n");
        return stb.toString();
    }

    @Override
    protected final String usageString() {
        return "Usage:chart [FILE] -o output path";
    }

    /**
     * �������ԍ���DefaultHighLowDataset�ɕϊ�����.
     * @param list �W���o�̓��X�g
     * @return DefaultHighLowDataset
     * @throws Exception �G���[
     */
    private static DefaultHighLowDataset createDataset(
                final List<Price> list) throws Exception {
        int size = list.size();
        Date[] date = new Date[size];
        double[] high = new double[size];
        double[] low = new double[size];
        double[] open = new double[size];
        double[] close = new double[size];
        double[] volume = new double[size];

        for (int i = 0; i < size; i++) {
            Price p = list.get(i);
            date[i] = p.getDateTime();
            open[i] = p.getOpen();
            high[i] = p.getHigh();
            low[i] = p.getLow();
            close[i] = p.getClose();
            volume[i] = p.getVolume();
        }

        DefaultHighLowDataset data =
                new DefaultHighLowDataset(list.get(0).getCode(),
                        date, high, low, open, close, volume);

        return data;
    }

    /**
     * �`���[�g�̏c���̖ڐ���ݒ肵�ĕԂ�.
     * @param prices �������X�g
     * @return �`���[�g�̏c��
     */
    private NumberAxis getNumberAxis(final List<Price> prices) {
        double min = prices.get(0).getHigh();
        double max = prices.get(0).getLow();
        for (Price price : prices) {
            if (price.getLow() < min) {
                min = price.getLow();
            }
            if (price.getHigh() > max) {
                max = price.getHigh();
            }
        }
        min = min - PADDING;
        max = max + PADDING;
        NumberAxis priceAxis = new NumberAxis();
        priceAxis.setAutoRange(false);
        priceAxis.setRange(min, max);
        priceAxis.setTickLabelsVisible(true);
        return priceAxis;
    }

    /**
     * ���C������.
     * @param args �R�}���h����
     */
    public static void main(final String[] args) {
        (new Chart()).exec(args);
    }
}