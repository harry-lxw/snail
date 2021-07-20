package com.winter;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class MD5Test {

    @Test
    public void encode(){
        String password = "123456";
        System.out.printf("加密后密码："+DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    @Test
    public void encodeSalt(){
        String password = "180530";
        //MD5 对密码进行初加密
        String md5= DigestUtils.md5DigestAsHex(password.getBytes());
        //对加密后的密码截取加密盐 (可以自己定义加密部分)
        System.out.printf("\n md5:"+md5);
        String temp =md5.substring(8,30);
        System.out.printf("\n salt:"+temp);
        // 对加密后的密码和盐进行二次加密,得到最终的密码
        //保存密码和盐 到数据库
        //如果是修改密码的话,操作一样,先验证密码,然后再对新密码加密取盐再加密,保存新的密码和新的盐
        String md501 =DigestUtils.md5DigestAsHex((temp+md5).getBytes());
        System.out.printf("\n md501:"+md501);
    }
}
