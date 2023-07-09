//package com.stackroute.userservice.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQListenerConfig {
//
//    @Value("${spring.rabbitmq.queue}")
//    private String queue;
//
//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private String password;
//
//    @Value("${spring.rabbitmq.routingkey}")
//    private String routingkey;
//
//    @Bean
//    public Queue queuelistener(){
//        return new Queue(queue,true);
//    }
//
//    @Bean
//    public Exchange MessageExchangerlistner(){
//        return ExchangeBuilder.directExchange(exchange).durable(true).build();
//    }
//
//    @Bean
//    public Binding binding(Queue queue, Exchange exchange){
//        return BindingBuilder.bind(queuelistener())
//                .to(MessageExchangerlistner()).with(routingkey).noargs();
//
//    }
//
//    @Bean
//    public MessageConverter JsonMessageConverterlistener(){
//        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
//
//        return new Jackson2JsonMessageConverter(mapper);
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory()
//    {
//        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory(host);
//        cachingConnectionFactory.setUsername(username);
//        cachingConnectionFactory.setPassword(password);
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory)
//    {
//        final RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(JsonMessageConverterlistener());
//        return rabbitTemplate;
//    }
//
//}
