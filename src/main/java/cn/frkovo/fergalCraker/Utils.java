package cn.frkovo.fergalCraker;

import com.alibaba.fastjson2.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Utils {
        public static String getImageData(String URL) throws IOException {
        java.net.URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.connect();
        InputStream stream = conn.getInputStream();
        byte[] data = stream.readAllBytes();
        stream.close();
        return "data:" + conn.getHeaderField("Content-Type") + ";base64," + java.util.Base64.getEncoder().encodeToString(data);
    }
    public static JSONObject request(String id) throws IOException {
        URL url = new URL("http://10.20.100.26:5550/api/session?view=student&code=" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoInput(true);
        conn.connect();
        if (conn.getResponseCode() != 200) {
            return null;
        }
        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        return JSONObject.parseObject(sb.toString());
    }

    public static void response(String id, String text) throws IOException {
        URL url = new URL("http://10.20.100.26:5550/api/session/responses");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        JSONObject object = new JSONObject();
        object.put("answer", text);
        object.put("code", id);
        object.put("name", "Frank");
        conn.getOutputStream().write(object.toString().getBytes());
        conn.connect();
        System.out.println(conn.getResponseCode());

    }
}
