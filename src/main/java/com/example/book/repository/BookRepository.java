package com.example.book.repository;

import com.example.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity , Long> { // 이 Repository 에서 사용할 entity 와 id 타입
    // 해당 양식은 JpaRepository의 사용 양식이기 때문에 꼭 맞춰야한다 ) extends = 상속
    

}
