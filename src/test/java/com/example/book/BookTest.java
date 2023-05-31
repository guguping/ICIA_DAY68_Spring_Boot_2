package com.example.book;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

// Assertions 클래스가 가지고 있는 모든 static 메서드를 가져오겠다.
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BookTest {
    @Autowired
    private BookService bookService;

    // 도서 등록 테스트
    /*
    1. 신규 도서 데이터 생성
    2. save 메서드 호출해서 저장 처리
    3. 저장한 데이터의 id값을 가져온다
    4. 해당 id로 db에서 조회를 한다
    5. 1번에서 만든 객체의 책제목 값과 4번에서 조회한 객체의 책제목 값이 일치하는지를 판단
    6. 일치한다면 테스트 성공 , 일치하지 않으면 테스트 실패
     */
    private BookDTO newBook(){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName("test book");
        bookDTO.setBookAuthor("test author");
        bookDTO.setBookPrice(10000);
        return bookDTO;
    }

    @Test
    @Transactional // 스프링의 Transactional 사용
    @Rollback(value = true) // 디폴트는 true 이지만 적었음
    @DisplayName("저장테스트")
    public void bookSaveTest(){
        BookDTO bookDTO = newBook(); // 테스트용 데이터 준비
        Long savedId = bookService.bookSave(bookDTO); // 저장을 위한 메서드 호출 후 id값 가져옴
        // id 로 조회
        BookDTO findDTO = bookService.findById(savedId);
        // 테스트용 데이터의 제목과 조회한 데이터의 제목이 일치하는지 확인
        assertThat(bookDTO.getBookName()).isEqualTo(findDTO.getBookName());
    }
    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("삭제테스트")
    public void bookDeleteTest(){
        /*
        1. 새로운 데이터 저장
        2. 저장된 데이터의 id를 가져옴
        3. 해당 id로 삭제 처리
        4. 해당 id로 조회했을 때 null 이면 삭제 테스트 성공
         */
        BookDTO bookDTO = newBook();
        Long savedId = bookService.bookSave(bookDTO);
        bookService.delete(savedId);
        assertThat(bookService.findById(savedId)).isNull();
    }
    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("수정 테스트")
    public void bookUpdateTest(){
        /*
        1. 새로운 데이터 저장
        2. 수정용 데이터 준비 및 수정 처리(제목만 변경)
        3. 데이터 조회
        4. 2번에서 수정한 제목과 3번에서 조회한 제목이 일치하면 수정 성공
         */
        BookDTO bookDTO = newBook();
        Long savedId = bookService.bookSave(bookDTO);

        // 저장을 위해 받아온 테스트 정보에는 id값이 없기 때문에 저장 후 받아온 id값을 넣어줘야한다
        bookDTO.setId(savedId);

        bookDTO.setBookName("수정했습니다");
        bookService.update(bookDTO);

        BookDTO dto = bookService.findById(savedId);
        assertThat(dto.getBookName()).isEqualTo(bookDTO.getBookName());
    }
}
