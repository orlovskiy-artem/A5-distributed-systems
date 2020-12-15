package com.orlovsky.mooc_platform.rabbitmqServer;

import com.orlovsky.mooc_platform.config.EducationalMaterialServiceConfig;
import com.orlovsky.mooc_platform.dto.CourseDTO;
import com.orlovsky.mooc_platform.model.Course;
import com.orlovsky.mooc_platform.model.EducationalStep;
import com.orlovsky.mooc_platform.model.TestStep;
import com.orlovsky.mooc_platform.model.TestStepOption;
import com.orlovsky.mooc_platform.rabbitMessages.educationalMaterialServerMessages.AddAuthorMessageBody;
import com.orlovsky.mooc_platform.rabbitMessages.educationalMaterialServerMessages.AddEducationalStepMessageBody;
import com.orlovsky.mooc_platform.rabbitMessages.educationalMaterialServerMessages.AddTestStepMessageBody;
import com.orlovsky.mooc_platform.rabbitMessages.educationalMaterialServerMessages.AddTestStepOptionMessageBody;
import com.orlovsky.mooc_platform.service.EducationalMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EducationalMaterialServer {
    @Autowired
    EducationalMaterialService educationalMaterialService;

    @RabbitListener(queues = EducationalMaterialServiceConfig.QUEUE_COURSE_CREATE_NAME)
    @SendTo(EducationalMaterialServiceConfig.QUEUE_COURSE_CREATE_NAME)
    public Course consumeCreateCourseMessage(CourseDTO courseDTO) {
        log.info("Message received from {} : " + courseDTO,
                EducationalMaterialServiceConfig.QUEUE_COURSE_CREATE_NAME);
        Course course = educationalMaterialService.createEmptyCourse(courseDTO);
        log.info("Course created : " + course);
        log.info("Course was sent to rabbitmq queue to client!");
        return course;
    }

    @RabbitListener(queues = EducationalMaterialServiceConfig.QUEUE_EDUCATIONALSTEP_CREATE_NAME)
    @SendTo(EducationalMaterialServiceConfig.QUEUE_EDUCATIONALSTEP_CREATE_NAME)
    public EducationalStep consumeCreateEducationalStepMessage(AddEducationalStepMessageBody addEducationalStepMessageBody) {
        log.info("Message received from {} : " + addEducationalStepMessageBody,
                EducationalMaterialServiceConfig.QUEUE_EDUCATIONALSTEP_CREATE_NAME);
        EducationalStep educationalStep = educationalMaterialService.addEducationalStep(
                addEducationalStepMessageBody.getCourseId(),
                addEducationalStepMessageBody.getEducationalStepDTO());
        log.info("Educational step created : " + educationalStep);
        log.info("Educational step was sent to rabbitmq queue to client!");
        return educationalStep;
    }

    @RabbitListener(queues = EducationalMaterialServiceConfig.QUEUE_TESTSTEP_CREATE_NAME)
    @SendTo(EducationalMaterialServiceConfig.QUEUE_TESTSTEP_CREATE_NAME)
    public TestStep consumeCreateTestStepMessage(AddTestStepMessageBody addTestStepMessageBody) {
        log.info("Message received from {} : " + addTestStepMessageBody,
                EducationalMaterialServiceConfig.QUEUE_TESTSTEP_CREATE_NAME);
        TestStep testStep = educationalMaterialService.addTestStep(
                addTestStepMessageBody.getCourseId(),
                addTestStepMessageBody.getTestStepDTO());
        log.info("Test step created : " + testStep);
        log.info("Test step was sent to rabbitmq queue to client!");
        return testStep;
    }


    @RabbitListener(queues = EducationalMaterialServiceConfig.QUEUE_TESTSTEPOPTION_CREATE_NAME)
    @SendTo(EducationalMaterialServiceConfig.QUEUE_TESTSTEPOPTION_CREATE_NAME)
    public TestStepOption consumeCreateTestStepOptionMessage(AddTestStepOptionMessageBody addTestStepOptionMessageBody) {
        log.info("Message received from {} : " + addTestStepOptionMessageBody,
                EducationalMaterialServiceConfig.QUEUE_TESTSTEPOPTION_CREATE_NAME);
        TestStepOption testStepOption = educationalMaterialService.addTestStepOption(
                addTestStepOptionMessageBody.getCourseId(),
                addTestStepOptionMessageBody.getTestStepId(),
                addTestStepOptionMessageBody.getTestStepOptionRequestDTO());
        log.info("Test step option created : " + testStepOption);
        log.info("Test step option  was sent to rabbitmq queue to client!");
        return testStepOption;
    }

    @RabbitListener(queues = EducationalMaterialServiceConfig.QUEUE_ADD_AUTHOR_TO_COURSE)
    public void consumeAddAuthorMessage(AddAuthorMessageBody addAuthorMessageBody) {
        log.info("Message received from {} : " + addAuthorMessageBody,EducationalMaterialServiceConfig.QUEUE_ADD_AUTHOR_TO_COURSE );
        educationalMaterialService.addAuthor(
                addAuthorMessageBody.getCourseId(),addAuthorMessageBody.getAuthorId());
        log.info("Author added to the course");
    }
}
