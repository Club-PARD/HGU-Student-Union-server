package com.server.HGUStudentUnion_server.common;

//import io.netty.handler.codec.DecoderException;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Locale;
//
//import org.apache.commons.codec.binary.Hex;
//import org.hibernate.PropertyValueException;
//
//public class DataEnDecryption {
//    public static String base64Encoder(String value){
//        byte[] byteKey = value.getBytes();
//        return Base64.getEncoder().encodeToString(byteKey);
//    }
//    public static String dataEnDecrypt(String cipherKey, String data, int cipherMode){
//        String result = null;
//        try{
//            Cipher cipher = Cipher.getInstance("AES");
//            byte[] initializationVector = new byte[16];
//            int idx = 0;
//            for(byte b : cipherKey.getBytes(StandardCharsets.UTF_8)){
//                initializationVector[idx++ % 16] ^= b;
//            }
//            SecretKeySpec keySpec = new SecretKeySpec(initializationVector, "AES");
//            cipher.init(cipherMode, keySpec);
//            if(cipherMode == Cipher.DECRYPT_MODE){
//                result = new String(cipher.doFinal(Hex.decodeHex(data)), StandardCharsets.UTF_8);
//            }
//            else if(cipherMode == Cipher.ENCRYPT_MODE){
//                result = new String(Hex.encodeHex(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)))).toUpperCase(Locale.ROOT);
//            } else {
////                throw new PropertyValueException("Mode 변수값이 잘못되었습니다.", "CipherMode", Integer.toString(cipherMode));
//            }
//
//        }
//        catch (NoSuchAlgorithmException | NoSuchPaddingException | DecoderException | InvalidKeyException |
//               org.apache.commons.codec.DecoderException | IllegalBlockSizeException | BadPaddingException error){
//            error.printStackTrace();
//        }
//        return result;
//    }
//}
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.Locale;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DataEnDecryption {


    // 암호화
    public static String encrypt(String text, String secretKey, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(secretKey.getBytes(), "AES"),
                    new IvParameterSpec(iv.getBytes()));

            return new String(Base64.getEncoder().encode(cipher.doFinal(text.getBytes("UTF-8"))));
        } catch(Exception e) {
            return text;
        }
    }

    // 복호화
    public static String decrypt(String encryptedText,String secretKey, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(secretKey.getBytes(), "AES"),
                    new IvParameterSpec(iv.getBytes()));

            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText.getBytes("UTF-8"))));
        } catch(Exception e) {
            return encryptedText;
        }
    }
}