package com.manytomany.mapper;

import com.manytomany.entity.AuthorEntity;
import com.manytomany.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author  entityToModel(AuthorEntity authorEntity);
}
