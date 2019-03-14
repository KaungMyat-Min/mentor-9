package com.trio.mentor9.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Paragraph extends RealmObject {
    @Required
    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
