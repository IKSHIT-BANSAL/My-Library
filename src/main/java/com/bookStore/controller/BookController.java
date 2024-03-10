package com.bookStore.controller;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookListService;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }
    @GetMapping("/Available_book")
    public ModelAndView getAllBook(){
        List<Book> list=service.getAllBooks();
//        ModelAndView m=new ModelAndView();
//        m.setViewName("BookList");      //page name to show
//        m.addObject("book",list);
        return new ModelAndView("BookList","book",list);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        service.save(b);
        return "redirect:/Available_book";
    }
    @GetMapping("/myBooks")
    public String getMyBooks(){
        return "myBooks";
    }
    @RequestMapping("/myList/{id}")
    public String getMyList(@PathVariable("id") int id){
        Book b=service.getBookById(id);
        MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookListService.saveMyBook(mb);
        return "redirect:/myBooks";
    }
}
