import com.alibaba.fastjson2.JSONObject;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletionContentPart;
import com.openai.models.chat.completions.ChatCompletionContentPartImage;
import com.openai.models.chat.completions.ChatCompletionContentPartText;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseInputImage;
import com.openai.models.responses.ResponseInputItem;

import java.io.IOException;
import java.util.List;



public class main {
    public static void main(String[] args) throws IOException {
                String imgData = "1";
                JSONObject reqObject = new JSONObject();
                ChatCompletionContentPart image =  ChatCompletionContentPart.ofImageUrl(ChatCompletionContentPartImage.builder()
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
                    .model("Qwen/Qwen3-VL-30B-A3B-Instruct")
                    .maxCompletionTokens(4096)
                    .addUserMessageOfArrayOfContentParts(List.of(sysInputItem,image))
                    .build();
                StringBuilder builder = new StringBuilder();
                OpenAIClient client = OpenAIOkHttpClient.builder()
                    .apiKey("sk-xrqspcsmczyadmfilcihtkaldgrncazbxptdcfmdkpcpjyke")
                    .baseUrl("https://api.siliconflow.cn/v1").build();
                client.chat().completions().create(createParams).choices().stream()
                .flatMap(choice -> choice.message().content().stream())
                .forEach(System.out::println);
                String finalAnswer = builder.toString().trim();
                System.out.println(finalAnswer);
    }
}
