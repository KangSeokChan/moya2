package com.example.moya2;

public class ListViewItem_content {
    private String title;
    private String writer;
    private String content;

    public ListViewItem_content(){ }

    public ListViewItem_content(String title, String writer) {
        this.title = title;
        this.writer = writer;
    }
    public ListViewItem_content(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String title) {
        this.content = content;
    }


}
