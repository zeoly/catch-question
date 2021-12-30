package com.yahacode.catchq;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.yahacode.catchq.model.Question;
import com.yahacode.catchq.model.Result;
import com.yahacode.catchq.repo.QuestionRepository;
import com.yahacode.catchq.utils.AccountConsts;
import com.yahacode.catchq.utils.UrlConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class CatchTask {

    private static final Logger log = LoggerFactory.getLogger(CatchTask.class);

    @Autowired
    QuestionRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    int id;

    static Result result;

    static String token;

    @Scheduled(cron = "0 */1 * * * ?")
    public void task() throws UnsupportedEncodingException {
        reset();

        String content = "{\"type\": 1,\"rule\": 1,\"key\": \"" + AccountConsts.KEY + "\"}";

        HttpEntity<String> request = new HttpEntity<>(content, getHeaders());
        Result result = restTemplate.postForEntity(UrlConsts.GET_QUESTION, request, Result.class).getBody();

        this.result = result;
        if (result.getData() == null) {
            String token = login();
            log.info("login success, token {}", token);
            return;
        }
        this.id = result.getData().getId();

        int counter = 0;
        for (Question q : result.getData().getQuestion()) {
            Question db = repository.findById(q.getId());
            if (db == null) {
                Question question = new Question(q.getCategory(), q.getContent(), q.getDistinguish(),
                        q.getFacility_value(), q.getGroup_id(), q.getId(), q.getOption(), q.getOption_count(),
                        q.getRight_answer(), q.getTarget_name());
                repository.save(question);
                counter++;
            } else {
//                System.out.println(q.getId() + " 已存在: " + q.getContent());
            }
        }
        log.info("catch success, id: {}, size: {}, add: {}", this.id, result.getData().getQuestion().size(), counter);
    }

    private void reset() throws UnsupportedEncodingException {
        Map<String, String> answer = new HashMap();
        if (result == null || result.getData() == null) return;
        for (Question q : result.getData().getQuestion()) {
            answer.put(String.valueOf(q.getId()), "");
        }
        String answerJson = JSONObject.toJSONString(answer);
        String answerBase64 = Base64.getEncoder().encodeToString(answerJson.getBytes("utf-8"));

        String str = this.id + "CQKXDS6_ANSWER" + AccountConsts.PHONE;
        String base64 = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        String key = DigestUtils.md5DigestAsHex(base64.getBytes());
        log.info("get answer key: {}", key);

        String content = "{\"answer\": \"" + answerBase64 + "\",\"answer_id\": " + this.id + ",\"key\": \"" + key +
                "\"}";
        HttpEntity<String> request = new HttpEntity<>(content, getHeaders());
        JSONObject result = restTemplate.postForEntity(UrlConsts.GET_ANSWER, request, JSONObject.class).getBody();
        log.info("get answer finish: {}", result.get("msg"));
    }

    public String login() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json; charset=UTF-8");

        String content = "{\"type\": 2,  \"role\": 1,  \"phone\": \"" + AccountConsts.PHONE + "\",  \"password\": " +
                "\"" + AccountConsts.PASSWORD + "\"}";

        HttpEntity<String> request = new HttpEntity<>(content, headers);
        JsonNode result = restTemplate.postForEntity(UrlConsts.LOGIN, request, JsonNode.class).getBody();
        String token = result.get("token").asText();
        this.token = token;
        return token;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", token);
        headers.add("content-type", "application/json; charset=UTF-8");
        return headers;
    }
}
