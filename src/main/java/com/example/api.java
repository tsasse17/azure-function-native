package com.example;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class api implements Function<Message<String>, String> {
    @Override
    public String apply(Message<String> inputMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Uri: ").append(inputMessage.getHeaders().get("uri"));

        String payload = inputMessage.getPayload();
        if (!payload.isBlank()) {
            stringBuilder.append("echo: ").append(payload);
        }

      //  inputMessage.getHeaders().forEach((t, u) -> stringBuilder.append(t).append(" : ").append(u));
        return stringBuilder.toString();
    }

}
