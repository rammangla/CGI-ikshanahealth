package com.stackroute.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${user.rabbitmq.queue}")
    String queueName;

    @Value("${user.rabbitmq.exchange}")
    String exchange;

    @Value("${user.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${user.rabbitmq.queuea}")
    String queueNamea;

    @Value("${user.rabbitmq.exchangea}")
    String exchangea;

    @Value("${user.rabbitmq.routingkeya}")
    private String routingkeya;

    @Value("${user.rabbitmq.queueb}")
    String queueNameb;

    @Value("${user.rabbitmq.exchangeb}")
    String exchangeb;

    @Value("${user.rabbitmq.routingkeyb}")
    private String routingkeyb;

    @Value("${spring.rabbitmq.queue}")
    private String queuelistener;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangelistener;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkeylistener;

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
    Queue queuea() {
        return new Queue(queueNamea, true);
    }

    @Bean
    DirectExchange exchangea() {
        return new DirectExchange(exchangea);
    }

    @Bean
    Binding bindinga() {
        return BindingBuilder.bind(queuea()).to(exchangea()).with(routingkeya);
    }

    @Bean
    Queue queueb() {
        return new Queue(queueNameb, true);
    }

    @Bean
    DirectExchange exchangeb() {
        return new DirectExchange(exchangeb);
    }

    @Bean
    Binding bindingb() {
        return BindingBuilder.bind(queueb()).to(exchangeb()).with(routingkeyb);
    }

    @Bean
    Queue queuelistener(){
        return new Queue(queuelistener,true);
    }

    @Bean
    DirectExchange MessageExchangerlistner(){
        return new DirectExchange(exchangelistener);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queuelistener())
                .to(MessageExchangerlistner()).with(routingkeylistener);

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
