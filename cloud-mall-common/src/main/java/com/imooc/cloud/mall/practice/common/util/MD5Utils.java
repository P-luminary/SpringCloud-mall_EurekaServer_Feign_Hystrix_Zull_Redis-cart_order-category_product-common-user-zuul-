package com.imooc.cloud.mall.practice.common.util;

import com.imooc.cloud.mall.practice.common.common.Constant;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//70.生成MD5方法 返回MemberServiceImpl编写随机数
public class MD5Utils {
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest((strValue+ Constant.SALT).getBytes()));
    }
}
