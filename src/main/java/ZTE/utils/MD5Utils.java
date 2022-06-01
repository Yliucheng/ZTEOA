package ZTE.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * MD5加密工具类
     * @param str 明文字符串
     * @return 16位加密字符串
     */
    public static String getMD5Result(String str){
        if (str==null||"".equals(str)){
            return "";
        }
        System.out.println("加密前："+str);
        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes();
            md.update(bytes);
            bigInteger = new BigInteger(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bigInteger.toString(16);
    }
}
