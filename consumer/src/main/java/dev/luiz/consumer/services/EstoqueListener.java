package dev.luiz.consumer.services;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class EstoqueListener {
    @KafkaListener(topics = "estoque-topico", groupId = "estoque")
    public void processarVenda(String venda) {
        System.out.printf("venda %s processada%n", venda);
    }
}