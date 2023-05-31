package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.entity.BookEntity;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public void bookSave(BookDTO bookDTO) {
        BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
        bookRepository.save(bookEntity);
    }

    public List<BookDTO> findAll() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        // List<BookEntity> -> List<BookDTO>
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(BookEntity bookEntity : bookEntityList){
            /*
                1. Entity -> DTO 변환 메서드 호출(메서드는 BoardDTO에 정의)
                2. 호출결과를 DTO 객체로 받음.
                3. DTO객체를 DTO리스트에 추가
                4. 반복문 종료 후 DTO 리스트를 리턴
             */
            bookDTOList.add(BookDTO.toDTO(bookEntity));
        }
        return bookDTOList;
    }
}
