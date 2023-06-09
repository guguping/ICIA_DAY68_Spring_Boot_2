package com.example.book.controller;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/books")
    public String findAll(Model model){
        List<BookDTO> bookDTOList = bookService.findAll();
        model.addAttribute("bookList",bookDTOList);
        return "list";
    }
    @GetMapping("/book/{id}")
    public String findById(@PathVariable Long id , Model model) {
        BookDTO bookDTO = new BookDTO();
        bookDTO = bookService.findById(id);
        model.addAttribute("book",bookDTO);
        return "detail";
    }
    @GetMapping("/book/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
    @GetMapping("/book/update/{id}")
    public String updateForm(@PathVariable Long id,Model model){
        BookDTO bookDTO = bookService.findById(id);
        model.addAttribute("book",bookDTO);
        return "update";
    }
    @PostMapping("/book/update")
    public String update(@ModelAttribute BookDTO bookDTO) {
        bookService.update(bookDTO);
        return "redirect:/books";
    }
}
