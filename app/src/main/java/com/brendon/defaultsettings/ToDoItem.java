package com.brendon.defaultsettings;

import java.util.Date;

/**
 * Created by Brendon on 9/22/16.
 */

public class ToDoItem {

    String text;
    Date created;

    public ToDoItem (String text) {

        this.text = text;
        this.created = new Date();

    }

    public String getText() {
        return text;
    }

    public Date getCreated() {
        return created;
    }
}
