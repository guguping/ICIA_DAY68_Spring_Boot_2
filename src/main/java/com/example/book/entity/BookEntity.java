package com.example.book.entity;

import com.example.book.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book_table")
@ToString
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 50)
    String bookName;
    @Column(length = 50)
    String bookAuthor;
    @Column
    int bookPrice;

    public static BookEntity toSaveEntity(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        return bookEntity;
    }
    public static BookEntity toUpdate(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        return bookEntity;
    }
}
