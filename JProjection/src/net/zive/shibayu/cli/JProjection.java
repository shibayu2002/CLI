package net.zive.shibayu.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * projection（射影）コマンド.
 * 標準出力から受け取ったデータの引数で指定された列の射影を標準出力に出力する。
 * @author Yu.Shiba
 *
 */
public final class JProjection {
    /**
     * スリープ時間.
     */
    private static final int WAIT_TIME = 100;

    /**
     * コンストラクタ(インスタンス化不可).
     */
    private JProjection() {
    }

    /**
     * コマンド引数チェック.
     * @param args コマンド引数
     */
    private static void cmdCheck(final String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:jprojection [COL NUMBER..]");
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
     * 引数を列番号の配列に変換する.
     * @param args 引数
     * @return 列番号の配列
     */
    private static int[] convArgsToRows(final String[] args) {
        int[] rows = new int[args.length];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = Integer.parseInt(args[i]);
        }
        return rows;
    }

    /**
     * 実行.
     * @param rows 列番号
     * @throws Exception エラー
     */
    private static void exec(final int[] rows) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                String[] strs = line.split(",");
                StringBuilder stb = new StringBuilder();
                for (int i : rows) {
                    if (stb.length() > 0) {
                        stb.append(",");
                    }
                    stb.append(strs[i - 1]);
                }
                System.out.println(stb.toString());
            }
        } catch (IOException e) {
            throw e;
        } finally {
            br.close();
        }
    }

    /**
     * メイン処理.
     * @param args コマンド引数
     * @throws Exception エラー
     */
    public static void main(final String[] args) throws Exception {
        cmdCheck(args);
        waitSystemIn();
        exec(convArgsToRows(args));
        System.exit(0);
    }
}
