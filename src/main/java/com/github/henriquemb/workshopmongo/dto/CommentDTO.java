package com.github.henriquemb.workshopmongo.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Date date;
    private String content;
    private AuthorDTO author;

    public CommentDTO() {
    }

    public CommentDTO(Date date, String content, AuthorDTO author) {
        this.date = date;
        this.content = content;
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
