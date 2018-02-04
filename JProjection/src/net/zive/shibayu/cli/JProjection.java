package net.zive.shibayu.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * projection�i�ˉe�j�R�}���h.
 * �W���o�͂���󂯎�����f�[�^�̈����Ŏw�肳�ꂽ��̎ˉe��W���o�͂ɏo�͂���B
 * @author Yu.Shiba
 *
 */
public final class JProjection {
    /**
     * �X���[�v����.
     */
    private static final int WAIT_TIME = 100;

    /**
     * �R���X�g���N�^(�C���X�^���X���s��).
     */
    private JProjection() {
    }

    /**
     * �R�}���h�����`�F�b�N.
     * @param args �R�}���h����
     */
    private static void cmdCheck(final String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:jprojection [COL NUMBER..]");
            System.exit(1);
        }
    }

    /**
     * �W���o�͂ɓ��͂�����܂őҋ@����.
     * @throws Exception �G���[
     */
    private static void waitSystemIn() throws Exception {
        while (System.in.available() == 0) {
            Thread.sleep(WAIT_TIME);
        }
    }

    /**
     * �������ԍ��̔z��ɕϊ�����.
     * @param args ����
     * @return ��ԍ��̔z��
     */
    private static int[] convArgsToRows(final String[] args) {
        int[] rows = new int[args.length];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = Integer.parseInt(args[i]);
        }
        return rows;
    }

    /**
     * ���s.
     * @param rows ��ԍ�
     * @throws Exception �G���[
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
     * ���C������.
     * @param args �R�}���h����
     * @throws Exception �G���[
     */
    public static void main(final String[] args) throws Exception {
        cmdCheck(args);
        waitSystemIn();
        exec(convArgsToRows(args));
        System.exit(0);
    }
}
