package com.pinetree.mobile.utils;

import android.content.Context;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 网络数据缓存工具类
 */
public class FilesUtils {
    //缓存目录的文件名
    private final static String APK_CACHE_FILE_NAME="PINETREE";
    
    //获取缓存
    public static String getCacheInfo(String fileName,Context context){
        File file=getFile(fileName,context);
        if(file==null)return null;
        try {
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                StringBuffer sb=new StringBuffer();
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    sb.append(lineTxt);
                }
                read.close();
                bufferedReader.close();
               // return AESTUtil.getInstance().deciphering(sb.toString());
                return sb.toString();
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    //储存缓存  内容是加密的。。用到的加密工具。自己搞一个吧。你也可以不加密
    public static void setCacheInfo(String fileName,String str,Context context){
        File file=getFile(fileName,context);
        //这是对内容进行加密。可加密，可不加密
       // content= AESTUtil.getInstance().encrypt(content);
        if(file==null)return;
        try {
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(new FileOutputStream(file));
                outputStreamWriter.write(str);
                outputStreamWriter.close();
        }catch (Exception e){

        }
    }
    public static File getFile(String fileName,Context context){
        File file=getFileDir("cache",context);
        if(file==null)return null;
        return new File(file,fileName);
    }

    public static File getFileDir(String fileDirName,Context context){
        File apkFile=null;
        if(existSDCard()){
            apkFile=new File(context.getExternalCacheDir().getAbsolutePath()+ File.separator+APK_CACHE_FILE_NAME+ File.separator+fileDirName);
            if(apkFile==null)return apkFile;
            if(!apkFile.exists()||!apkFile.isDirectory()){
                if(!apkFile.mkdirs())return null;
            }
        }else {
            apkFile=new File(context.getCacheDir().getAbsolutePath()+ File.separator+APK_CACHE_FILE_NAME+ File.separator+fileDirName);
            if(apkFile==null)return apkFile;
            if(!apkFile.exists()||!apkFile.isDirectory()){
                if(!apkFile.mkdirs())return null;
            }
        }
        return apkFile;
    }
    private static boolean existSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    public static void deleteDirectory(File dirFile) {
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return;
        }
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                deleteFile(files[i]);
            } //删除子目录
            else {
                deleteDirectory(files[i]);
            }
        }
        dirFile.delete();
    }
    public static void deleteFile(File file) {
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }
}
