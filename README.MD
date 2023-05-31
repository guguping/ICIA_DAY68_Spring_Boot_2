## Book Project 1일차

### 기본페이지 : index.html

- 책등록(/save),책목록(/books) 링크 있음

### 책등록페이지 : save.html

- 책제목(bookName),저자(bookAuthor),가격(bookPrice) 입력받고 저장 버튼 클릭하면 /save 주소로 post 요청 보냄
- BookController의 save 메서드는 입력값을 BookDTO 객체로 받아서 BookService의 save 메서드로 전달.

### List 방복문 / 태그 안에 model 값 넣기

```html

<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>list</title>
</head>
<body>
<h2>list</h2>
<table>
    <tr>
        <th>번호</th>
        <th>책이름</th>
        <th>책저자</th>
        <th>책가격</th>
    </tr>
    <tr th:each="album:${bookList}">
        <th th:text="${album.getId()}"></th>
        <th th:text="${album.getBookName()}"></th>
        <th th:text="${album.getBookAuthor()}"></th>
        <th th:text="${album.getBookPrice()}"></th>
    </tr>
</table>
```

### 주의사항

```html

<html lang="ko" xmlns:th="http://www.thymeleaf.org"> 
```

- html 부분에 xmlns 주소를 꼭 넣어야한다

# 2일차 수업내용

- BookEntity 와 BookDTO 에 각각
- Entity / DTO로 변환해주는 static 메소드가 있음 !!
- 만들어진 메소드는 BookService에서 사용되었음 !!

### html에 css 링크 추가하기

```html

<link rel="stylesheet" th:href="@{/css/main.css}">
```

- staic 폴더에 css경로르 추가하고 css를 만든다
- 경로 설정에는 th:href를 사용하고 @{}로 주소값을 넣어야한다

### 태그에 링크로 controller에 값 전달하기 (rest api 방식)

```html
<a th:href="@{|/book/delete/${album.id}|}">삭제</a>
```

- 마찬가지로 th:href 가 사용되고 추가로 @{} 안에 버티컬 바를 사용한다
- 버틸컬 바가 없으면 주소가 제대로 전달되지 않기 때문에 주의가 필요하다
- controller에서 전달 받기 위해서는 @RequestParam이 아닌 @PathVariable 를 사용한다

### update 해보기

- Spring Date JPA 에서는 따로 Update 메소드를 제공하지 않는다
  그렇기 때문에 save를 사용하는데 save에서는 제공 받은 값에 id값이 있다면
  새로 저장하는것이 아닌 해당 id값에 내용을 덮어 씌운다

단점

- 값을 덮어 씌우는 방식이기 때문에 모든 값을 받지 않으면
- 전값이 null로 덮어 씌워지는 단점이 있다

### test 메서드 만들어보기

```java
// Assertions 클래스가 가지고 있는 모든 static 메서드를 가져오겠다.

import static org.assertj.core.api.Assertions.*;
```
- 테스트에 필요한 메서드를 편하게 사용하기 위해 import를 추가함
- 테스트 데이터로 저장을해도 Transactional , Rollback 을 사용하지 않으면 db에 저장되기 때문에
- 꼭 Transactional , Rollback 을 사용해야한다 (테스트 이후 다시 Rollback함)

#### 저장 test 사용 예시

```java

@SpringBootTest
public class BookTest {
    @Autowired
    private BookService bookService;

    @Test
    @Transactional // 스프링의 Transactional 사용
    @Rollback(value = true) // 디폴트는 true 이지만 적었음
    @DisplayName("저장테스트")
    public void bookSaveTest() {
        BookDTO bookDTO = newBook(); // 테스트용 데이터 준비
        Long savedId = bookService.bookSave(bookDTO); // 저장을 위한 메서드 호출 후 id값 가져옴
        // id 로 조회
        BookDTO findDTO = bookService.findById(savedId);
        // 테스트용 데이터의 제목과 조회한 데이터의 제목이 일치하는지 확인
        assertThat(bookDTO.getBookName()).isEqualTo(findDTO.getBookName());
    }
}
```

#### 삭제 test 사용 예시
```java

@SpringBootTest
public class BookTest {
    @Autowired
    private BookService bookService;

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("삭제테스트")
    public void bookDeleteTest() {
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
}
```

#### 수정 test 사용 예시
```java

@SpringBootTest
public class BookTest {
    @Autowired
    private BookService bookService;

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("수정 테스트")
    public void bookUpdateTest() {
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
```