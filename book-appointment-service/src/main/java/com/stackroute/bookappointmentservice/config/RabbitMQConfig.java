package com.stackroute.bookappointmentservice.config;

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

    @Value("${appointment.rabbitmq.queue}")
    String queueName;

    @Value("${appointment.rabbitmq.exchange}")
    String exchange;

    @Value("${appointment.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${appointment.rabbitmq.queuea}")
    String queueNamea;

    @Value("${appointment.rabbitmq.exchangea}")
    String exchangea;

    @Value("${appointment.rabbitmq.routingkeya}")
    private String routingkeya;

    @Value("${appointment.rabbitmq.queueb}")
    String queueNameb;

    @Value("${appointment.rabbitmq.exchangeb}")
    String exchangeb;

    @Value("${appointment.rabbitmq.routingkeyb}")
    private String routingkeyb;

    @Value("${appointment.rabbitmq.queuec}")
    String queueNamec;

    @Value("${appointment.rabbitmq.exchangec}")
    String exchangec;

    @Value("${appointment.rabbitmq.routingkeyc}")
    private String routingkeyc;

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
    Queue queuec() {
        return new Queue(queueNamec, true);
    }

    @Bean
    DirectExchange exchangec() {
        return new DirectExchange(exchangec);
    }

    @Bean
    Binding bindingc() {
        return BindingBuilder.bind(queuec()).to(exchangec()).with(routingkeyc);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
