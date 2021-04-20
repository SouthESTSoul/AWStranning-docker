package com.guhao.controller;

import com.guhao.entity.Visitor;
import com.guhao.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
public class VisitorController {

    @Autowired
    private VisitorRepository repository;
    @Autowired
    private RedisTemplate redisTemplate;

	
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        String ip=request.getRemoteAddr();
        Visitor visitor=repository.findByIp(ip);
        if(visitor==null){
            visitor=new Visitor();
            visitor.setIp(ip);
            visitor.setTimes(1);
        }else {
            visitor.setTimes(visitor.getTimes()+1);
        }
        repository.save(visitor);

        ValueOperations<String, Visitor> operations=redisTemplate.opsForValue();
        operations.set("demo",visitor,10, TimeUnit.SECONDS);
        String result;
        if (redisTemplate.hasKey("demo")){
            result="The visitor has been recorded in redis.";
        }else{
            result="the visitor has not been recorded in redis.";
        }

        return "I have been seen ip "+visitor.getIp()+" "+visitor.getTimes()+" times. \n" +result;
    }
}