package com.orlovsky.mooc_platform;

import com.orlovsky.mooc_platform.service.grpc.GrpcAccountService;
import com.rabbitmq.client.impl.AMQImpl;
import io.grpc.ServerBuilder;
import io.grpc.channelz.v1.Server;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.geom.QuadCurve2D;

@SpringBootApplication
public class MoocPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoocPlatformApplication.class, args);
    }
}
