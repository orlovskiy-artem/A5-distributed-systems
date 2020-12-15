package com.orlovsky.mooc_platform.rabbitmqServer;

import com.orlovsky.mooc_platform.config.AccountServiceConfig;
import com.orlovsky.mooc_platform.dto.AuthorDTO;
import com.orlovsky.mooc_platform.dto.StudentDTO;
import com.orlovsky.mooc_platform.model.Author;
import com.orlovsky.mooc_platform.model.Student;
import com.orlovsky.mooc_platform.rabbitMessages.accountServerMessages.DeleteAuthorMessageBody;
import com.orlovsky.mooc_platform.rabbitMessages.accountServerMessages.DeleteStudentMessageBody;
import com.orlovsky.mooc_platform.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountServer {
    @Autowired
    AccountService accountService;

    @RabbitListener(queues = AccountServiceConfig.QUEUE_STUDENT_CREATE_NAME)
    @SendTo(AccountServiceConfig.QUEUE_STUDENT_CREATE_NAME)
    public Student consumeCreateStudentMessage(StudentDTO studentDTO) {
        log.info("Message received from student_create_queue : " +studentDTO );
        Student student = accountService.signUpStudent(studentDTO);
        log.info("Student signed up : " + student);
        log.info("Student was sent to rabbitmq queue to client!");
        return student;
    }

    @RabbitListener(queues = AccountServiceConfig.QUEUE_AUTHOR_CREATE_NAME)
    @SendTo(AccountServiceConfig.QUEUE_AUTHOR_CREATE_NAME)
    public Author consumeCreateAuthorMessage(AuthorDTO authorDTO) {
        log.info("Message received from student_create_queue : " + authorDTO );
        Author author = accountService.signUpAuthor(authorDTO);
        log.info("Author signed up : " + author);
        log.info("Author was sent to rabbitmq queue to client!");
        return author;
    }

    @RabbitListener(queues = AccountServiceConfig.QUEUE_STUDENT_DELETE_NAME)
    public void consumeDeleteStudentMessage(DeleteStudentMessageBody deleteStudentMessageBody) {
        log.info("Message received from {} : " +deleteStudentMessageBody,
                AccountServiceConfig.QUEUE_STUDENT_DELETE_NAME);
        accountService.deleteStudentById(deleteStudentMessageBody.getStudentId());
        log.info("Student was deleted");
    }

    @RabbitListener(queues = AccountServiceConfig.QUEUE_AUTHOR_DELETE_NAME)
    public void consumeDeleteAuthorMessage(DeleteAuthorMessageBody deleteAuthorMessageBody) {
        log.info("Message received from {} : " + deleteAuthorMessageBody,
                AccountServiceConfig.QUEUE_AUTHOR_CREATE_NAME);
        accountService.deleteAuthorById(deleteAuthorMessageBody.getAuthorId());
        log.info("Author was deleted");
    }
}
