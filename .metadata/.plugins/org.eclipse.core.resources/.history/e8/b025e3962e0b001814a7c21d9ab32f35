package net.zive.shibayu.cli;

import gnu.getopt.Getopt;
import net.zive.shibayu.cli.base.BaseFileInCli;

/**
 * projection（射影）コマンド.
 * @author Yu.Sba
 *
 */
public class JProjection extends BaseFileInCli {
    /**
     * 表示列番号.
     */
    private int[] cols = null;

    @Override
    protected final boolean checkArgs(final String[] args) {
        Getopt g = new Getopt(this.getClass().getName(), args, "c:");

        int c = -1;
        while ((c = g.getopt()) != -1) {
            switch (c) {
            case 'c':
                setColsFromArg(g.getOptarg());
                return true;
            default:
                return false;
            }
        }
        return false;
    }

    @Override
    protected void beforeAccept() throws Exception {
    }

    @Override
    protected final boolean accept(final String data) throws Exception {
        String[] strs = data.split(",");
        StringBuilder stb = new StringBuilder();
        for (int i : cols) {
            if (stb.length() > 0) {
                stb.append(",");
            }
            stb.append(strs[i - 1]);
        }
        System.out.println(stb.toString());
        return true;
    }

    @Override
    protected void afterAccept() throws Exception {
    }

    @Override
    protected final String helpString() {
        StringBuilder stb = new StringBuilder();
        stb.append("<概要>\n");
        stb.append("projection（射影）コマンド。\n");
        stb.append("指定されたCSVデータからコマンドラインで指定された列を抜き出し標準出力に出力する。\n\n");
        stb.append("<コマンド>\n");
        stb.append("jprojection [FILE] -c:カンマ区切り列番号 \n\n");
        stb.append("<引数>\n");
        stb.append("FILE:CSVファイル(※省略可)\n");
        stb.append("* FILEを省略した場合は標準入力からCSVデータを受取る。");
        stb.append("<オプション>\n");
        stb.append("-c:カンマ区切り列番号（例.-c 1,2,5を指定した場合、FILEの1,2,5列目を出力する。）\n");
        return stb.toString();
    }

    @Override
    protected final String usageString() {
        return "Usage:jprojection [FILE] -c カンマ区切り列番号";
    }

    /**
     * 列番号の配列を作成する.
     * @param arg 引数
     */
    private void setColsFromArg(final String arg) {
        String[] tmp = arg.split(",");
        cols = new int[tmp.length];
        for (int i = 0; i < cols.length; i++) {
            cols[i] = Integer.parseInt(tmp[i]);
        }
    }

    /**
     * メイン処理.
     * @param args コマンド引数
     */
    public static void main(final String[] args) {
        (new JProjection()).exec(args);
    }
}
