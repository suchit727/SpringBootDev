package com.manytomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manytomany.model.Author;
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
@Table(name = "book")
public class BookEntity {

    @Id
    @SequenceGenerator(name = "seq_book_id", initialValue = 1, sequenceName = "seq_book_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book_id")
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_author"
            , joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<AuthorEntity> authorList;


}
