package cn.frkovo.fergalCraker;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import java.io.IOException;
import java.util.logging.Logger;

public class main {
    public static OpenAIClient client;
    public static void main(String[] args) throws IOException {
        client = OpenAIOkHttpClient.builder()
        .apiKey(args[1])
        .baseUrl(args[0]).build();
        Booter booter = new Booter();

    }

}
