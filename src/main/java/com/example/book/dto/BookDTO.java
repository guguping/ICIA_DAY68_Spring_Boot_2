package com.example.book.dto;

import com.example.book.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    Long id;
    String bookName;
    String bookAuthor;
    int bookPrice;

    public static BookDTO toDTO(BookEntity bookEntity){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setBookName(bookEntity.getBookName());
        bookDTO.setBookAuthor(bookEntity.getBookAuthor());
        bookDTO.setBookPrice(bookEntity.getBookPrice());
        return bookDTO;
    }
}

