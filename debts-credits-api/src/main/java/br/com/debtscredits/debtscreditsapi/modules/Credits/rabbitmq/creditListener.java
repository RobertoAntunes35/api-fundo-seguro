package br.com.debtscredits.debtscreditsapi.modules.Credits.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import br.com.debtscredits.debtscreditsapi.modules.Credits.dto.CreditsDTO;
import br.com.debtscredits.debtscreditsapi.modules.Credits.service.CreditsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class creditListener {
    
    @Autowired
    private CreditsService creditsService;

    @RabbitListener(queues = "${app-config.rabbit.queue.credits}")
    public void receiveCreditsMessage(CreditsDTO creditsDTO) throws JsonProcessingException {
        log.info("Recebendo Messagem: {}", new ObjectMapper().writeValueAsString(creditsDTO));
        creditsService.updateCreditsTransation(creditsDTO);
    }
}

// public void recieveProductStockMessage(CreditsDTO credits) throws JsonProcessingException {
    // log.info("Recebendo mensagem: {}", new ObjectMapper().writeValueAsString(credits));
//     creditsService.updateCreditsTransation(credits);
// }