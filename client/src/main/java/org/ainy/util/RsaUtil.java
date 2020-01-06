package org.ainy.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author YUANDONG
 * @Description Rsa工具
 * @Date 2019-10-09 18:05
 */
public class RsaUtil {

    /**
     * 从文件输入流中加载公钥
     *
     * @param path 公钥文件位置
     * @return 公钥
     * @throws Exception Exception
     */
    public static String loadPublicKeyByFile(String path) throws Exception {

        try {
            BufferedReader br = new BufferedReader(new FileReader(path + "/publicKey.keystore"));
            String readLine;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }
}
