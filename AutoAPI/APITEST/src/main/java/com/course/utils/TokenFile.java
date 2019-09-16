package com.course.utils;

import java.io.*;

public class TokenFile {
    private static String path="C:\\Data\\Tokenfile.txt";
    public static void witerToken(String token){
        try{
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
            printWriter.write(token);
            printWriter.flush();
            printWriter.close();
        } catch(Exception e) {

        }
    }


    /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile() throws IOException {
        StringBuffer sb = new StringBuffer();
        TokenFile.readToBuffer(sb, path);
        return sb.toString();
    }
}