package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.entity.BookEntity;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public void bookSave(BookDTO bookDTO) {
        BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
        bookRepository.save(bookEntity);
    }
}
