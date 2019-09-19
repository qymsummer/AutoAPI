package com.course.utils;

import java.io.*;

public class DataIdFile {
    private static String path="C:\\Data\\Datafile.txt";
    public static void witerDataId(String token){
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

    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            buffer.append("\n");
            line = reader.readLine();
        }
        reader.close();
        is.close();
    }
    public static String readFile() throws IOException {
        StringBuffer sb = new StringBuffer();
        TokenFile.readToBuffer(sb, path);
        return sb.toString();
    }
}
