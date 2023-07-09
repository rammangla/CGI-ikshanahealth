package com.stackroute.schedularservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("scheduler")
public class Scheduler {

    @Id
    private  String emailId;
    private Map<String, String> slots=new LinkedHashMap<String, String>();

}

