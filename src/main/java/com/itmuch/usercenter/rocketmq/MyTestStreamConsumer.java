package com.itmuch.usercenter.rocketmq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyTestStreamConsumer {
    @StreamListener(MySink.MY_INPUT)
    public void receive(String msg){
        log.info("收到MySink消息了：{}",msg);
    }
}
