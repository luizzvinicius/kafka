package dev.luiz.producer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class SaleController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SaleController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<String> veda(@RequestBody String idVenda) {
        kafkaTemplate.send("estoque-topico", idVenda);
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("{'message': 'a venda %s está sendo processada'}", idVenda));
    }
}