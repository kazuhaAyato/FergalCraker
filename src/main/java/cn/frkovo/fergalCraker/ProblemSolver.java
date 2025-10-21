package cn.frkovo.fergalCraker;

import com.alibaba.fastjson2.JSONObject;
import com.openai.models.chat.completions.ChatCompletionContentPart;
import com.openai.models.chat.completions.ChatCompletionContentPartImage;
import com.openai.models.chat.completions.ChatCompletionContentPartText;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import java.io.IOException;
import java.util.List;

import static cn.frkovo.fergalCraker.main.*;

public class ProblemSolver implements Runnable{
    String id;
    public ProblemSolver(String id){
        this.id = id;
    }

    @Override
    public void run() {
        int qNumber = -1;
        while (true) {
            try {
                JSONObject ans = Utils.request(id);
                if(ans == null){
                    System.out.println("["+id+"] Session ended or invalid ID.");
                    return;
                }else if(qNumber == -1){
                    System.out.println("["+id+"] valid. Starting Ans");
                }
                if (qNumber == ans.getIntValue("current_index")) {
                    continue;
                }
                qNumber = ans.getIntValue("current_index");
                System.out.println("[" + id + "] New Question Detected: " + qNumber);
                String imgData = Utils.getImageData("http://10.20.100.26:5550" + ans.getJSONObject("question").getJSONArray("images").getString(0));
                ChatCompletionContentPart image = ChatCompletionContentPart.ofImageUrl(ChatCompletionContentPartImage.builder()
                        .imageUrl(ChatCompletionContentPartImage.ImageUrl.builder()
                                .url(imgData)
                                .build())
                        .build());
                ChatCompletionContentPart sysInputItem = ChatCompletionContentPart.ofText(
                        ChatCompletionContentPartText.builder().text("""
                                You Are A CAIE 9618 COMPUTER SCIENCE teacher.
                                Your Student is asking you problems,and
                                your job is to solve them one by one, exactly in english, using fluent languages IN ENGLISH ONLY.
                                You Could Use Emojis, and Make The Answer More Interesting.
                                You are also allowed to use memes and jokes to make the learning experience more enjoyable.
                                And it's better to make the whole class laugh.
                                You Must Answer The Question ONLY, DO NOT ADD ANYTHING EXTRA.
                                The Question Will be given by the Image.
                                """).build());
                ChatCompletionCreateParams createParams = ChatCompletionCreateParams.builder()
                        .model(config.getString("model"))
                        .addUserMessageOfArrayOfContentParts(List.of(sysInputItem, image))
                        .build();

                StringBuilder sb = new StringBuilder();
                client.chat().completions().create(createParams).choices().stream()
                        .flatMap(choice -> choice.message().content().stream())
                        .forEach(sb::append);
                String finalAnswer = sb.toString().trim();
                Utils.response(id, finalAnswer);
            } catch (IOException e) {
                throw   new RuntimeException(e);
            }
        }
    }
}
