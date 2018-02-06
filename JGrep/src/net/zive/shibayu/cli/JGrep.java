package net.zive.shibayu.cli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gnu.getopt.Getopt;
import net.zive.shibayu.cli.base.BaseFileInCli;

/**
 * jgrep�R�}���h.
 * @author Yu.Sba
 *
 */
public class JGrep extends BaseFileInCli {
    /**
     * �\����ԍ�.
     */
    private Pattern pattern = null;

    @Override
    protected final boolean checkArgs(final String[] args) {
        Getopt g = new Getopt(this.getClass().getName(), args, "p:");

        int c = -1;
        while ((c = g.getopt()) != -1) {
            switch (c) {
            case 'p':
                pattern = Pattern.compile(g.getOptarg());
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
        Matcher m = pattern.matcher(data);
        if (m.find()) {
            System.out.println(data);
        }
        return true;
    }

    @Override
    protected void afterAccept() throws Exception {
    }

    @Override
    protected final String helpString() {
        StringBuilder stb = new StringBuilder();
        stb.append("<�T�v>\n");
        stb.append("jgrep�R�}���h�B\n");
        stb.append("�w�肳�ꂽ����������ɊY������s��W���o�͂ɏo�͂���B\n\n");
        stb.append("<�R�}���h>\n");
        stb.append("jgrep [FILE] -p ����������(���K�\��) \n\n");
        stb.append("<����>\n");
        stb.append("FILE:�t�@�C��(���ȗ���)\n");
        stb.append("* FILE���ȗ������ꍇ�͕W�����͂���f�[�^������B");
        stb.append("<�I�v�V����>\n");
        stb.append("-p:����������(���K�\��)\n");
        return stb.toString();
    }

    @Override
    protected final String usageString() {
        return "Usage:jgrep [FILE] -p ����������(���K�\��)";
    }

    /**
     * ���C������.
     * @param args �R�}���h����
     */
    public static void main(final String[] args) {
        (new JGrep()).exec(args);
    }
}