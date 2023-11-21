package br.com.debtscredits.debtscreditsapi.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;



@Configuration
public class rabbitConfig {
    @Value("${app-config.rabbit.exchange.credits}")
    private String creditsTopicExchange;

    @Value("${app-config.rabbit.routingKey.credits}")
    private String creditsRoutingKey;

    @Value("${app-config.rabbit.routingKey.transation-confirmation}")
    private String transationConfirmationRoutingKey;
     
     @Value("${app-config.rabbit.queue.credits}")
    private String creditsMq;

    @Value("${app-config.rabbit.queue.credits}")
    private String transationConfirmationMq;

    @Bean
    public TopicExchange creditTopicExchange() {
        return new TopicExchange(creditsTopicExchange);
    }
    // Stock
    @Bean
    public Queue creditsMq() {
        return new Queue(creditsMq, true);
    }

    // Sales
    @Bean
    public Queue transationConfirmationMq() {
        return new Queue(transationConfirmationMq, true);
    }

    @Bean
    public Binding  creditsMqBinding (TopicExchange topicExchange) {
        return BindingBuilder
            .bind(creditsMq())
            .to(topicExchange)
            .with(creditsRoutingKey);
    }

    @Bean
    public Binding  transationConfirmationMqBinding (TopicExchange topicExchange) {
        return BindingBuilder
            .bind(transationConfirmationMq())
            .to(topicExchange)
            .with(transationConfirmationRoutingKey);
    }

    @Bean
    public MessageConverter jMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
