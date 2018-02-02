package net.zive.shibayu.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * catコマンド.
 * 引数で指定されたファイルの中身を標準出力に出力する。
 * @author Yu.Shiba
 *
 */
public final class JCat {
    /**
     * コンストラクタ(インスタンス化不可).
     */
    private JCat() {
    }

    /**
     * コマンド引数チェック.
     * @param args コマンド引数
     */
    private static void cmdCheck(final String[] args) {
        if (args.length != 1) {
            System.out.println("Usage:jcat FILE");
            System.exit(1);
        }
    }

    /**
     * 実行.
     * @param path ファイルパス
     * @throws Exception エラー
     */
    private static void exec(final String path) throws Exception {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String str = null;
        try {
            while ((str = br.readLine()) != null) {
                System.out.println(str);
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
        exec(args[0]);
        System.exit(0);
    }
}
