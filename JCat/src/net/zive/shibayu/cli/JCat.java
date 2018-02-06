package net.zive.shibayu.cli;

import gnu.getopt.Getopt;
import net.zive.shibayu.cli.base.BaseFileInCli;

/**
 * cat����.
 * @author Yu.Sba
 *
 */
public class JCat extends BaseFileInCli {
    /**
     * ���ݍs.
     */
    private long cnt = 0;

    /**
     * �J�n�s.
     */
    private long startLine = -1;

    /**
     * �I���s.
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
        stb.append("<�T�v>\n");
        stb.append("�w�肳�ꂽ�t�@�C���̓��e��W���o�͂ɏo�͂���B\n\n");
        stb.append("<�R�}���h>\n");
        stb.append("jcat [FILE] [-s:�J�n�s] [-e:�I���s]\n\n");
        stb.append("<����>\n");
        stb.append("FILE:�t�@�C���p�X(���ȗ���)\n");
        stb.append("* FILE���ȗ������ꍇ�͕W�����͂���t�@�C���p�X���w�肷��B\n\n");
        stb.append("<�I�v�V����>\n");
        stb.append("-s:�J�n�s\n");
        stb.append("-e:�I���s\n");
        return stb.toString();
    }

    @Override
    protected final String usageString() {
        return "Usage:jcat [FILE] [-s:�J�n�s] [-e:�I���s]";
    }

    /**
     * ���C������.
     * @param args �R�}���h����
     */
    public static void main(final String[] args) {
        (new JCat()).exec(args);
    }
}