package net.zive.shibayu.cli;

import gnu.getopt.Getopt;
import net.zive.shibayu.cli.base.BaseFileInCli;

/**
 * projection�i�ˉe�j�R�}���h.
 * @author Yu.Sba
 *
 */
public class JProjection extends BaseFileInCli {
    /**
     * �\����ԍ�.
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
        stb.append("<�T�v>\n");
        stb.append("projection�i�ˉe�j�R�}���h�B\n");
        stb.append("�w�肳�ꂽCSV�f�[�^����R�}���h���C���Ŏw�肳�ꂽ��𔲂��o���W���o�͂ɏo�͂���B\n\n");
        stb.append("<�R�}���h>\n");
        stb.append("jprojection [FILE] -c:�J���}��؂��ԍ� \n\n");
        stb.append("<����>\n");
        stb.append("FILE:CSV�t�@�C��(���ȗ���)\n");
        stb.append("* FILE���ȗ������ꍇ�͕W�����͂���CSV�f�[�^������B");
        stb.append("<�I�v�V����>\n");
        stb.append("-c:�J���}��؂��ԍ��i��.-c 1,2,5���w�肵���ꍇ�AFILE��1,2,5��ڂ��o�͂���B�j\n");
        return stb.toString();
    }

    @Override
    protected final String usageString() {
        return "Usage:jprojection [FILE] -c �J���}��؂��ԍ�";
    }

    /**
     * ��ԍ��̔z����쐬����.
     * @param arg ����
     */
    private void setColsFromArg(final String arg) {
        String[] tmp = arg.split(",");
        cols = new int[tmp.length];
        for (int i = 0; i < cols.length; i++) {
            cols[i] = Integer.parseInt(tmp[i]);
        }
    }

    /**
     * ���C������.
     * @param args �R�}���h����
     */
    public static void main(final String[] args) {
        (new JProjection()).exec(args);
    }
}
