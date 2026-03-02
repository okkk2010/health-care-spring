package inhatc.cse.inhive.stt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class SttService {

    @Value("${openai.api-key}")
    
    private String apiKey;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String transcribe(String filename) throws IOException {
        System.out.println("API KEY = " + apiKey);
        File audioFile = new File("uploads/recordings/" + filename);

        if (!audioFile.exists()) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다.");
        }

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", audioFile.getName(),
                        RequestBody.create(audioFile, MediaType.parse("audio/wav")))
                .addFormDataPart("model", "whisper-1")
                .build();

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/audio/transcriptions")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

    try (Response response = client.newCall(request).execute()) {

        String responseBody = response.body().string();

        if (!response.isSuccessful()) {
            System.out.println("OpenAI ERROR BODY: " + responseBody);
            throw new RuntimeException("OpenAI API 호출 실패: " + responseBody);
        }

        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("text").asText();
    }  
  }
}