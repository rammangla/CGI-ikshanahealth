package com.stackroute.schedularservice.config;

import com.stackroute.schedularservice.domain.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory()
    {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, Scheduler> redisTemplate()
    {
        RedisTemplate<String,Scheduler> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Scheduler>(Scheduler.class));
        return redisTemplate;
    }
}
