package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.entity.BookEntity;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        for (BookEntity bookEntity : bookEntityList) {
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

    public BookDTO findById(Long id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if (optionalBookEntity.isPresent()) {
            System.out.println("있다");
            // optional 객체에서 꺼내기
            // BookEntity bookEntity = optionalBookEntity.get();
            // BookEntity -> BookDTO변환
            // BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            // return bookDTO;
            return BookDTO.toDTO(optionalBookEntity.get());
        } else {
            System.out.println("없다");
            return null;
        }
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public void update(BookDTO bookDTO) {
        bookRepository.save(BookEntity.toUpdate(bookDTO));
    }
}
