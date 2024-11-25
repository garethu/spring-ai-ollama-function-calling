package com.yootiful.functioncalling.controller;

import com.yootiful.functioncalling.model.Question;
import com.yootiful.functioncalling.service.AdviceService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private final ChatClient chatClient;

    public BookingController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a customer chat support agent of an train booking system.
                                                Respond in a friendly, helpful, and joyful manner.
                                                You are interacting with customers through an online chat system.                                   
                                                If they ask for their booking details and they have entered in the booking id, their first name and last name, then provide the booking details.
                                                If you cannot find the booking details, tell them that you cannot provide the information with the details provided.
                                                If they would like to cancel their booking, cancel the booking provided you have confirmed their booking details with a yes or no response. do NOT cancel without an 'YES' response.
                                            """)
                .build();
    }

    @PostMapping("/booking/question")
    public ResponseEntity<String> generate(@RequestBody Question question) {
        try {
            String response = chatClient.prompt()
                    .user(question.text())
                    .functions("getBookingDetails")
                    .call()
                    .content()
                    .toString();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
}

