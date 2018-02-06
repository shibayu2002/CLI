package net.zive.shibayu.cli;

import gnu.getopt.Getopt;
import net.zive.shibayu.cli.base.BaseFileInCli;

/**
 * cat処理.
 * @author Yu.Sba
 *
 */
public class JCat extends BaseFileInCli {
    /**
     * 現在行.
     */
    private long cnt = 0;

    /**
     * 開始行.
     */
    private long startLine = -1;

    /**
     * 終了行.
     */
    private long endLine = -1;

    @Override
    protected final boolean checkArgs(final String[] args) {
        Getopt g = new Getopt(this.getClass().getName(), args, "s:e:");

        int c = -1;
        while ((c = g.getopt()) != -1) {
            switch (c) {
            case 's':
                startLine = Integer.parseInt(g.getOptarg());
                break;
            case 'e':
                endLine = Integer.parseInt(g.getOptarg());
                break;
            default:
                return false;
            }
        }
        return true;
    }

    @Override
    protected final void beforeAccept() throws Exception {
        cnt = 0;
    }

    @Override
    protected final boolean accept(final String data) throws Exception {
        if (cnt >= startLine - 1) {
            System.out.println(data);
        }
        cnt = cnt + 1;
        if ((endLine != -1) && (cnt >= endLine)) {
            return false;
        }
        return true;
    }

    @Override
    protected void afterAccept() throws Exception {
    }

    @Override
    protected final String helpString() {
        StringBuilder stb = new StringBuilder();
        stb.append("<概要>\n");
        stb.append("指定されたファイルの内容を標準出力に出力する。\n\n");
        stb.append("<コマンド>\n");
        stb.append("jcat [FILE] [-s:開始行] [-e:終了行]\n\n");
        stb.append("<引数>\n");
        stb.append("FILE:ファイルパス(※省略可)\n");
        stb.append("* FILEを省略した場合は標準入力からファイルパスを指定する。\n\n");
        stb.append("<オプション>\n");
        stb.append("-s:開始行\n");
        stb.append("-e:終了行\n");
        return stb.toString();
    }

    @Override
    protected final String usageString() {
        return "Usage:jcat [FILE] [-s:開始行] [-e:終了行]";
    }

    /**
     * メイン処理.
     * @param args コマンド引数
     */
    public static void main(final String[] args) {
        (new JCat()).exec(args);
    }
}
