package net.zive.shibayu.cli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * cat�R�}���h.
 * �����Ŏw�肳�ꂽ�t�@�C���̒��g��W���o�͂ɏo�͂���B
 * @author Yu.Shiba
 *
 */
public final class JCat {
    /**
     * �R���X�g���N�^(�C���X�^���X���s��).
     */
    private JCat() {
    }

    /**
     * �R�}���h�����`�F�b�N.
     * @param args �R�}���h����
     */
    private static void cmdCheck(final String[] args) {
        if (args.length != 1) {
            System.out.println("Usage:jcat FILE");
            System.exit(1);
        }
    }

    /**
     * ���s.
     * @param path �t�@�C���p�X
     * @throws Exception �G���[
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
     * ���C������.
     * @param args �R�}���h����
     * @throws Exception �G���[
     */
    public static void main(final String[] args) throws Exception {
        cmdCheck(args);
        exec(args[0]);
        System.exit(0);
    }
}
