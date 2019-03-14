package com.trio.mentor9.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Chapter extends RealmObject {

    @Required
    private String title;

    @Required
    private String subject;

    @Required
    private int grade;

    @Required
    private int chapterNo;

    RealmList<Paragraph> mParagraphs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public RealmList<Paragraph> getParagraphs() {
        return mParagraphs;
    }

    public void setParagraphs(RealmList<Paragraph> paragraphs) {
        mParagraphs = paragraphs;
    }
}
