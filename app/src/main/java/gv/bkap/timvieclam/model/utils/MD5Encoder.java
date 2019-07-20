package gv.bkap.timvieclam.model.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {
    public static String encode(String input) {
        String result = null;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
