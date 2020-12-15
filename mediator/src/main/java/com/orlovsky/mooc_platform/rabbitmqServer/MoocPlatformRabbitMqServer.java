package com.orlovsky.mooc_platform.rabbitmqServer;

import com.orlovsky.mooc_platform.config.AccountServiceConfig;
import com.orlovsky.mooc_platform.dto.AuthorDTO;
import com.orlovsky.mooc_platform.dto.StudentDTO;
import com.orlovsky.mooc_platform.model.Author;
import com.orlovsky.mooc_platform.model.Student;
import com.orlovsky.mooc_platform.service.AccountService;
import com.orlovsky.mooc_platform.service.CourseProgressService;
import com.orlovsky.mooc_platform.service.EducationalMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MoocPlatformRabbitMqServer {
    @Autowired
    AccountService accountService;
    @Autowired
    EducationalMaterialService educationalMaterialService;
    @Autowired
    CourseProgressService courseProgressService;


}
