package org.crm;

import org.crm.utils.DateTimeUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class test {

    @Test
    public void testMD5(){

            String pwd = "abc123456";
            try {
                // 得到一个信息摘要器
                MessageDigest digest = MessageDigest.getInstance("md5");
                byte[] result = digest.digest(pwd.getBytes());
                StringBuffer buffer = new StringBuffer();
                // 把每一个byte 做一个与运算 0xff;
                for (byte b : result) {
                    // 与运算
                    int number = b & 0xff;// 加盐
                    String str = Integer.toHexString(number);
                    if (str.length() == 1) {
                        buffer.append("0");
                    }
                    buffer.append(str);
                }

                // 标准的md5加密后的结果
                System.out.println(buffer);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                ;
            }
        }

    @Test
    public void generateUUID(){
        for (int i = 0; i < 20 ; i++) {

            System.out.println(UUID.randomUUID().toString().replace("-", ""));
        }
    }

    @Test
    public void testFloor(){
        System.out.println(Math.floorDiv(50, 10)+1);
    }
    @Test
    public void testUtils() throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("te st.xlsx", "UTF-8"));
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        System.out.println(DateTimeUtil.getSysTime());
        System.out.println(DateTimeUtil.getSysTimeForUpload());
    }
}
