package com.stackroute.doctorservice.config;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${doctor.rabbitmq.queue}")
    String queueName;

    @Value("${doctor.rabbitmq.exchange}")
    String exchange;

    @Value("${doctor.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${doctor.rabbitmq.queuea}")
    String queueNamea;

    @Value("${doctor.rabbitmq.exchangea}")
    String exchangea;

    @Value("${doctor.rabbitmq.routingkeya}")
    private String routingkeya;

    @Value("${doctor.rabbitmq.queue2}")
    String queueName2;

    @Value("${doctor.rabbitmq.exchange2}")
    String exchange2;

    @Value("${doctor.rabbitmq.routingkey2}")
    private String routingkey2;

    @Value("${spring.rabbitmq.queue}")
    private String queuelistener;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangelistener;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkeylistener;


    @Bean
     Queue queue(){
        return new Queue(queueName,true);
    }

    @Bean
    DirectExchange exchange() {
         return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
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
    Queue queue2(){
        return new Queue(queueName2,true);
    }

    @Bean
    DirectExchange exchange2() {
        return new DirectExchange(exchange2);
    }

    @Bean
    Binding binding2(Queue queue2, DirectExchange exchange2){
        return BindingBuilder.bind(queue2()).to(exchange2()).with(routingkey2);
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
    Jackson2JsonMessageConverter jsonMessageConverter(){
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
