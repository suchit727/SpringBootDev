package com.manytomany.dto;

import com.manytomany.entity.AuthorEntity;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

@Getter
public class BookDto{

    private String bookName;
    private Collection<AuthorEntity> authorList;

    public BookDto(String bookName) {
        super();

        this.bookName = bookName;
       /* this.authorList = authorList;*/
    }
    public BookDto(String bookName,Collection<AuthorEntity> authorList) {
        super();
        this.bookName = bookName;
         this.authorList = authorList;
    }
}
