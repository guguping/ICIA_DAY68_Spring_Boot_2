## Book Project
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

### 수업내용
- BookEntity 와 BookDTO 에 각각
- Entity / DTO로 변환해주는 static 메소드가 있음 !!
- 만들어진 메소드는 BookService에서 사용되었음 !!