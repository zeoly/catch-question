package com.yahacode.catchq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long qid;

    String category;

    String content;

    int distinguish;

    @Column(name = "facility_value")
    int facilityValue;

    @Column(name = "group_id")
    String groupId;

    int id;

    String option;

    @Column(name = "option_count")
    int optionCount;

    @Column(name = "right_answer")
    String rightAnswer;

    @Column(name = "target_name")
    String targetName;

    public Question() {
    }

    public Question(String category, String content, int distinguish, int facilityValue, String groupId, int id,
                    String option, int optionCount, String rightAnswer, String targetName) {
        this.category = category;
        this.content = content;
        this.distinguish = distinguish;
        this.facilityValue = facilityValue;
        this.groupId = groupId;
        this.id = id;
        this.option = option;
        this.optionCount = optionCount;
        this.rightAnswer = rightAnswer;
        this.targetName = targetName;
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

    public int getFacilityValue() {
        return facilityValue;
    }

    public void setFacilityValue(int facilityValue) {
        this.facilityValue = facilityValue;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public int getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(int optionCount) {
        this.optionCount = optionCount;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
