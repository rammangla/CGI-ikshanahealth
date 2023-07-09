package com.stackroute.caretakerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${caretaker.rabbitmq.queue}")
    String queueName;

    @Value("${caretaker.rabbitmq.exchange}")
    String exchange;

    @Value("${caretaker.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${caretaker.rabbitmq.queuea}")
    String queueNamea;

    @Value("${caretaker.rabbitmq.exchangea}")
    String exchangea;

    @Value("${caretaker.rabbitmq.routingkeya}")
    private String routingkeya;


    @Value("${caretaker.rabbitmq.queueb}")
    String queueNameb;

    @Value("${caretaker.rabbitmq.exchangeb}")
    String exchangeb;

    @Value("${caretaker.rabbitmq.routingkeyb}")
    private String routingkeyb;


    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }
    @Bean
    Queue queuea(){
        return new Queue(queueNamea,true);
    }

    @Bean
    DirectExchange exchangea() {
        return new DirectExchange(exchangea);
    }

    @Bean
    Binding bindinga(Queue queuea, DirectExchange exchangea){
        return BindingBuilder.bind(queuea()).to(exchangea()).with(routingkeya);
    }

    @Bean
    Queue queueb(){
        return new Queue(queueNameb,true);
    }

    @Bean
    DirectExchange exchangeb() {
        return new DirectExchange(exchangeb);
    }

    @Bean
    Binding bindingb(Queue queueb, DirectExchange exchangeb){
        return BindingBuilder.bind(queueb()).to(exchangeb()).with(routingkeyb);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
