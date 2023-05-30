## Book Project
### 기본페이지 : index.html
- 책등록(/save),책목록(/books) 링크 있음

### 책등록페이지 : save.html
- 책제목(bookName),저자(bookAuthor),가격(bookPrice) 입력받고 저장 버튼 클릭하면 /save 주소로 post 요청 보냄
- BookController의 save 메서드는 입력값을 BookDTO 객체로 받아서 BookService의 save 메서드로 전달.