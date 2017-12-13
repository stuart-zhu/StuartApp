package com.stuart.stuartapp.demo.jktk.entity;

import android.text.TextUtils;

/**
 * Created by stuart on 2017/12/1.
 */

public class JKTK {
    private int subject; // 科目类别

    private String question; // 问题

    private String option1; // 选项一
    private String option2; // 选项二
    private String option3; // 选项三
    private String option4; // 选项四
    private String answer;

    private String explain;
    private String pic;
    private String type;

    private String chapter;

    public JKTK() {

    }

    public JKTK(int subject, String question, String option1, String option2, String option3, String option4, String answer, String explain, String pic, String type, String chapter) {
        this.subject = subject;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.explain = explain;
        this.pic = pic;
        this.type = type;
        this.chapter = chapter;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public boolean isSingleChoose() {
        return TextUtils.isEmpty(getOption1())
                &&
                TextUtils.isEmpty(getOption2())
                &&
                TextUtils.isEmpty(getOption3())
                &&
                TextUtils.isEmpty(getOption4())
                ;
    }
    @Override
    public String toString() {
        return "JKTK{" +
                "subject=" + subject +
                ", question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", answer='" + answer + '\'' +
                ", explain='" + explain + '\'' +
                ", pic='" + pic + '\'' +
                ", type='" + type + '\'' +
                ", chapter='" + chapter + '\'' +
                '}';
    }
}
