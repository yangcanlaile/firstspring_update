package test.com.placeholder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class DESUtils {

    private static Key key;
    private static String KEY_STR = "myKey";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(new SecureRandom(KEY_STR.getBytes("UTF8")));
            key = generator.generateKey();
            generator = null;

        } catch (Exception e) {

        }

    }

    //对字符串进行加密，返回BASE64编码的加密字符串
    public static String getEncryptString(String str) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] strBytes = str.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64Encoder.encode(encryptStrBytes);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    //对字符串进行解密
    public static String getDecryptString(String str) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {

            byte[] strBytes = base64Decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            return new String(decryptStrBytes,"UTF8");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        String str = "yangcanlaile";
        System.out.println("请输入要加密的字符:" + str);
        String encrytStr = getEncryptString(str);
        System.out.println("加密后的字符" + encrytStr);
        String decrytStr = getDecryptString(encrytStr);
        System.out.println("解密后的字符：" + decrytStr);

    }

}
