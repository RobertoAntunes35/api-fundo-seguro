package br.com.debtscredits.debtscreditsapi.modules.Transations.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.debtscredits.debtscreditsapi.modules.Transations.dto.TransationConfirmationDTO;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class TransationConfirmationSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @org.springframework.beans.factory.annotation.Value("${app-config.rabbit.exchange.credits}")
    private String creditsTopicExchange;

    @org.springframework.beans.factory.annotation.Value("${app-config.rabbit.routingKey.transation-confirmation}")
    private String transationConfirmationRoutingKey;

    public void sendTransationConfirmationMessage(TransationConfirmationDTO message) {
        try {
            log.info("Sending Message {} ", new ObjectMapper().writeValueAsString(message));
            rabbitTemplate.convertAndSend(creditsTopicExchange, transationConfirmationRoutingKey,message);
            log.info("Message was sent successfully!");
        } catch (Exception ex) {
            log.info("Message wasn't send.", ex);

        }
    }
}
