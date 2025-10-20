package cn.frkovo.fergalCraker;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.logging.Logger;

public class main {
    public static OpenAIClient client;
    public static JSONObject config;
    public static void main(String[] args) throws IOException {
        File file = new File("config.json");
        if(!file.exists()){
            System.out.println("Config File Not Found! Creating One...");
            config = new JSONObject();
            config.put("base_url","https://api.siliconflow.cn/v1");
            config.put("api_key","YOUR_API_KEY_HERE");
            config.put("name","Frank");
            config.put("model","Qwen/Qwen3-VL-30B-A3B-Instruct");
            try {
                file.createNewFile();
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(JSON.toJSONString(config, JSONWriter.Feature.PrettyFormat).getBytes());
                stream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Config File Created! Please Edit config.json and Run Again.");
            return;
        }else {
            config = JSON.parseObject(new String(Files.readAllBytes(file.toPath())));
        }
        client = OpenAIOkHttpClient.builder()
        .apiKey(config.getString("api_key"))
        .baseUrl(config.getString("base_url")).build();
        while(true){
            for(int i = 1000;i<=9999;i++){
                new ProblemSolver(String.valueOf(i)).run();
            }
        }
    }
}
