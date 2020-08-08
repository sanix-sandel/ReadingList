package com.zandu.readingList.controller;

import com.zandu.readingList.model.Book;
import com.zandu.readingList.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public BookController(ReadingListRepository readingListRepository){
        this.readingListRepository=readingListRepository;
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks( @PathVariable("reader") String reader, Model model){
        List<Book> readingList=readingListRepository.findByReader(reader);
        if(readingList !=null){
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToBook(@PathVariable("reader")String reader, )

}
