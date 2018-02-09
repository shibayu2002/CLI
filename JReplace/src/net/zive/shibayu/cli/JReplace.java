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
public class JReplace extends BaseFileInCli {
    /**
     * �����p�^�[��.
     */
    private Pattern pattern = null;

    /**
     * �u��������.
     */
    private String value = null;

    /**
     * �u��������o�b�t�@.
     */
    private StringBuffer targetStb = null;

    @Override
    protected final boolean checkArgs(final String[] args) {
        Getopt g = new Getopt(this.getClass().getName(), args, "p:v:");

        int c = -1;
        while ((c = g.getopt()) != -1) {
            switch (c) {
            case 'p':
                pattern = Pattern.compile(g.getOptarg());
                break;
            case 'v':
                value = g.getOptarg();
                break;
            default:
                return false;
            }
        }
        return true;
    }

    @Override
    protected final void beforeAccept() throws Exception {
        targetStb = new StringBuffer();
    }

    @Override
    protected final boolean accept(final String data) throws Exception {
        targetStb.append(data).append("\n");
        return true;
    }

    @Override
    protected final void afterAccept() throws Exception {
        Matcher m = pattern.matcher(targetStb.toString());
        String result = m.replaceAll(value);
        System.out.println(result);
    }

    @Override
    protected final String helpString() {
        StringBuilder stb = new StringBuilder();
        stb.append("<�T�v>\n");
        stb.append("jreplace�R�}���h�B\n");
        stb.append("�w�肳�ꂽ����������ɊY������l���w�肳�ꂽ�l�ɒu�����o�͂���B\n\n");
        stb.append("<�R�}���h>\n");
        stb.append("jreplace [FILE] -p ����������(���K�\��) -v �u�������� \n\n");
        stb.append("<����>\n");
        stb.append("FILE:�t�@�C��(���ȗ���)\n");
        stb.append("* FILE���ȗ������ꍇ�͕W�����͂���f�[�^������B");
        stb.append("<�I�v�V����>\n");
        stb.append("-p:����������(���K�\��)\n");
        stb.append("-v:�u��������\n");
        return stb.toString();
    }

    @Override
    protected final String usageString() {
        return "Usage:jreplace [FILE] -p ����������(���K�\��) -v �u��������";
    }

    /**
     * ���C������.
     * @param args �R�}���h����
     */
    public static void main(final String[] args) {
        (new JReplace()).exec(args);
    }
}