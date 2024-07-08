package com.raksmey.event_reciever.controller;


import com.raksmey.event_reciever.domain.entity.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final RabbitTemplate rabbitTemplate;

    public WebhookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody Event event) {
        rabbitTemplate.convertAndSend("eventQueue", event);
        return ResponseEntity.ok().body("Event Received");
    }
}
