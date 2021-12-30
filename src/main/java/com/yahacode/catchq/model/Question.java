package com.yahacode.catchq.model;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long qid;

    String category;

    String content;

    int distinguish;

    int facility_value;

    String group_id;

    int id;

    String option;

    int option_count;

    String right_answer;

    String target_name;

    public Question() {
    }

    public Question(String category, String content, int distinguish, int facility_value, String group_id, int id,
                    String option, int option_count, String right_answer, String target_name) {
        this.category = category;
        this.content = content;
        this.distinguish = distinguish;
        this.facility_value = facility_value;
        this.group_id = group_id;
        this.id = id;
        this.option = option;
        this.option_count = option_count;
        this.right_answer = right_answer;
        this.target_name = target_name;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(int distinguish) {
        this.distinguish = distinguish;
    }

    public int getFacility_value() {
        return facility_value;
    }

    public void setFacility_value(int facility_value) {
        this.facility_value = facility_value;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getOption_count() {
        return option_count;
    }

    public void setOption_count(int option_count) {
        this.option_count = option_count;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public String getTarget_name() {
        return target_name;
    }

    public void setTarget_name(String target_name) {
        this.target_name = target_name;
    }
}
