package com.manytomany.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @SequenceGenerator(name = "seq_author_id", initialValue = 1, sequenceName = "seq_author_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_author_id")
    private Long id;
    @Column(name = "author_name")
    private String authorName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "authorList")
    private List<BookEntity> bookEntities;
}
