//package com.example.iqproject;
//
//
//public class ListViewItem_comment {
//    private String writer;
//    private String content;
//
//    public ListViewItem_comment(){ }
//
//    public ListViewItem_comment(String writer, String content) {
//        this.writer = writer;
//        this.content = content;
//    }
//    public String getWriter() {
//        return writer;
//    }
//
//    public void setWriter(String writer) {
//        this.writer = writer;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//
//}

package com.example.moya2;

public class ListViewItem_comment {
    private String comment_writer;
    private String comment_content;

    public ListViewItem_comment(){ }

    public ListViewItem_comment(String writer, String content) {
        this.comment_writer = writer;
        this.comment_content = content;
    }

    public void setWriter(String writer) {
        this.comment_writer = writer;
    }

    public void setComment(String content) {
        this.comment_content = content;
    }

    public String getWriter() {
        return comment_writer;
    }

    public String getComment() {
        return comment_content;
    }
}
