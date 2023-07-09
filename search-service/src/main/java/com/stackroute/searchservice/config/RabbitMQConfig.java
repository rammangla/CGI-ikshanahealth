package com.stackroute.searchservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queue.doctor}")
    private String queueDoctor;

    @Value("${spring.rabbitmq.queue.caretaker}")
    private String queueCaretaker;

    @Value("${spring.rabbitmq.exchange.doctor}")
    private String exchangeDoctor;

    @Value("${spring.rabbitmq.exchange.caretaker}")
    private String exchangeCaretaker;

    @Value("${spring.rabbitmq.routingkey.doctor}")
    private String routingKeyDoctor;

    @Value("${spring.rabbitmq.routingkey.caretaker}")
    private String routingKeyCaretaker;

    @Value("${spring.rabbitmq.username}")
    private  String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Bean
    Queue queueDoctor() {
        return new Queue(queueDoctor, true);
    }

    @Bean
    Exchange exchangeDoctor() {
        return ExchangeBuilder.directExchange(exchangeDoctor).durable(true).build();
    }

    @Bean
    Binding bindingDoctor() {
        return BindingBuilder
                .bind(queueDoctor())
                .to(exchangeDoctor())
                .with(routingKeyDoctor)
                .noargs();
    }

    @Bean
    Queue queueCaretaker() {
        return new Queue(queueCaretaker, true);
    }

    @Bean
    Exchange exchangeCaretaker() {
        return ExchangeBuilder.directExchange(exchangeCaretaker).durable(true).build();
    }

    @Bean
    Binding bindingCaretaker() {
        return BindingBuilder
                .bind(queueCaretaker())
                .to(exchangeCaretaker())
                .with(routingKeyCaretaker)
                .noargs();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
    @Bean
    public MessageConverter jsonMessageConverter()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}