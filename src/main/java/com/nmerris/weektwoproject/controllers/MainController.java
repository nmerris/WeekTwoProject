package com.nmerris.weektwoproject.controllers;

import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class MainController
{

    @Autowired
    ResumeRepository resumeRepository;


    @GetMapping("/index")
    public String indexPage(Model model)
    {

        model.addAttribute("welcomemessage","Welcome to this book DB");
        return "index";
    }


    @GetMapping("/addbook")
    public String addBook(Model model)
    {
        model.addAttribute("newbook", new Book());

        model.addAttribute("addbookmessage","please add book");
        return "addbook";
    }

    @PostMapping("/addbook")
    public String postProduct(@Valid @ModelAttribute("newbook") Book book, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()){
            return "addbook";
        }
        bookRepository.save(book); // save it to the db
        return "bookadditionconfirmation";
    }


    @GetMapping("/displayallbooks")
    public String displayBooks(Model model)
    {
        Iterable<Book> bookList = bookRepository.findAll();
        model.addAttribute("books",bookList);
        return "displayallbooks";


    }
    // this method is called when the user clicks on the LoadBooks link from the index page
    // there is no new page to go to, all it does is load the db with a list of predefined books
    // all this happens righ here
    @GetMapping("/loadbooks")
    public String loadExampleBooks()
    {
        // get all the books from out db
//        Iterable<Book> bookList = bookRepository.findAll();

        // save all out books to the db in one shot
        bookRepository.save(loadSampleBooks());
        return "allbooksloadedconfirmation";
    }

    private ArrayList<Book> loadSampleBooks(){

        ArrayList<Book> myBooks = new ArrayList<Book>();

        int length = Array.getLength(sampleSkus);

        for(int i = 0; i < length; i++) {
            Book newBook = new Book();

            newBook.setSku(sampleSkus[i]);
            newBook.setAuthor(sampleAuthors[i]);
            newBook.setTitle(sampleTitles[i]);
            newBook.setDescription(sampleDescription[i]);
            newBook.setPrice(samplePrice[i]);

            myBooks.add(newBook);


        }


        return myBooks;
    }


//
//    @GetMapping("/bookadditionconfirmation")
//    public String confirmBook()
//    {
//       return "bookadditionconfirmation";
//    }

}
