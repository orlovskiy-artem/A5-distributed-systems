package com.orlovsky.mooc_platform.rabbitmqServer;

import com.orlovsky.mooc_platform.config.CourseProgressServiceConfig;
import com.orlovsky.mooc_platform.config.EducationalMaterialServiceConfig;
import com.orlovsky.mooc_platform.dto.CourseDTO;
import com.orlovsky.mooc_platform.grpc.MakeProcessedTestStepResponse;
import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.rabbitMessages.courseProgressServerMessages.MakePassedEducationalStepMessageBody;
import com.orlovsky.mooc_platform.rabbitMessages.courseProgressServerMessages.MakeProcessedTestStepMessageBody;
import com.orlovsky.mooc_platform.rabbitMessages.courseProgressServerMessages.SignUpUserMessageBody;
import com.orlovsky.mooc_platform.service.CourseProgressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CourseProgressServer {
    @Autowired
    CourseProgressService courseProgressService;

    @RabbitListener(queues = CourseProgressServiceConfig.QUEUE_SIGN_UP_STUDENT)
    public void consumeSignUpStudentMessage(SignUpUserMessageBody signUpUserMessageBody) {
        log.info("Message received from {} : " + signUpUserMessageBody,
                CourseProgressServiceConfig.QUEUE_SIGN_UP_STUDENT);
        courseProgressService.signUpUser(
                signUpUserMessageBody.getCourseId(),
                signUpUserMessageBody.getStudentId());
        log.info("User signed up");
    }



    @RabbitListener(queues = CourseProgressServiceConfig.QUEUE_PASS_EDUCATIONALSTEP)
    public void consumeMakePassedEducationalStepMessage(MakePassedEducationalStepMessageBody messageBody) {
        log.info("Message received from {} : " + messageBody,
                EducationalMaterialServiceConfig.QUEUE_COURSE_CREATE_NAME);
        courseProgressService.makePassedEducationalStep(
                messageBody.getCourseId(),
                messageBody.getStudentId(),
                messageBody.getEducationalStepId());
        log.info("Educational step passed");
    }

    @RabbitListener(queues = CourseProgressServiceConfig.QUEUE_PROCESS_TESTSTEP)
    @SendTo(CourseProgressServiceConfig.QUEUE_PROCESS_TESTSTEP)
    public Boolean consumeMakeProcessedTestStepMessage(MakeProcessedTestStepMessageBody messageBody) {
        log.info("Message received from {} : " + messageBody,
                CourseProgressServiceConfig.QUEUE_PROCESS_TESTSTEP);
        Boolean passed = courseProgressService.makeProcessedTestStep(
                messageBody.getCourseId(),
                messageBody.getStudentId(),
                messageBody.getTestStepId(),
                messageBody.getChosenAnswer());
        log.info("Got test step processing results: {}",passed);
        log.info("Sent to rabbitmq to client");
        return passed;

    }
}
