package com.yahacode.catchq;

import com.yahacode.catchq.model.Question;
import com.yahacode.catchq.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.List;

@SpringBootApplication
@RestController
@EnableScheduling
public class StartApplication {

    @Autowired
    QuestionRepository repository;

    @Autowired
    CatchTask catchTask;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @GetMapping("/search/{keyword}")
    public String search(@PathVariable String keyword) {
        List<Question> list = repository.findByContentLike("%" + keyword + "%");
        StringBuilder sb = new StringBuilder();
        for (Question question : list) {
            sb.append("题目：").append(question.getContent()).append("</br>选项：").append(question.getOption()).append(
                    "</br>答案：").append(question.getRight_answer()).append("</br></br>");
        }
        return sb.toString();
    }

    @GetMapping("/task")
    private void reset() throws UnsupportedEncodingException {
        catchTask.task();
    }
}
