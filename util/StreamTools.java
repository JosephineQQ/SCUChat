package com.otz.SCUchat.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by OTZ on 2/22/2016.
 */
public class StreamTools {
    //把流inputstream转换成string 常用工具类
    public static String readStream(InputStream in) throws Exception{
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int len=-1;
        byte[] buffer=new byte[1024];
        while((len=in.read(buffer))!=-1){

            baos.write(buffer,0,len);
        }
        in.close();
        String content=new String(baos.toByteArray());
        return null;
    }

}
