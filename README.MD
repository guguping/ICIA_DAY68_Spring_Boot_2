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

### 2일차 수업내용
- BookEntity 와 BookDTO 에 각각
- Entity / DTO로 변환해주는 static 메소드가 있음 !!
- 만들어진 메소드는 BookService에서 사용되었음 !!

### html에 css 링크 추가하기 
```html
<link rel="stylesheet" th:href="@{/css/main.css}">
```
- staic 폴더에 css경로르 추가하고 css를 만든다
- 경로 설정에는 th:href를 사용하고 @{}로 주소값을 넣어야한다

### 태그에 링크로 controller에 값 전달하기 (파라미터 형식으로 전달)
```html
<a th:href="@{|/book/delete/${album.id}|}">삭제</a>
```
- 마찬가지로 th:href 가 사용되고 추가로 @{} 안에 버티컬 바를 사용한다
- 버틸컬 바가 없으면 주소가 제대로 전달되지 않기 때문에 주의가 필요하다