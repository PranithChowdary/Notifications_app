package com.example.notifications;

public class Message {
    private CharSequence text,sender;
    private long timestamp;

    public Message(CharSequence text, CharSequence sender) {
        this.text = text;
        this.sender = sender;
        this.timestamp = System.currentTimeMillis();
    }

    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public CharSequence getSender() {
        return sender;
    }

    public void setSender(CharSequence sender) {
        this.sender = sender;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
