package models;

public class Message {
    private String author;
    private String content;

    public Message() {
    }

    public Message(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}