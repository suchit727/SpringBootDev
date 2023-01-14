package com.manytomany.dto;

import lombok.Getter;
@Getter
public class AuthorDto {

    private String authorName;

    public AuthorDto(String authorName) {
      super();
        this.authorName = authorName;

    }
}
