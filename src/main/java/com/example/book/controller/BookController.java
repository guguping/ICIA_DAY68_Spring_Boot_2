package com.example.book.controller;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/save")
    public String save(){
        return "save";
    }
    @PostMapping("/save")
    public void bookSave(@ModelAttribute BookDTO bookDTO){
        bookService.bookSave(bookDTO);
    }
}
