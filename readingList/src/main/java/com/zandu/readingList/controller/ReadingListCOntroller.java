package com.zandu.readingList.controller;

import com.zandu.readingList.model.Book;
import com.zandu.readingList.model.Reader;
import com.zandu.readingList.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
@ConfigurationProperties(prefix="amazon")
public class ReadingListCOntroller {

    private String associateId;

    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListCOntroller(
            ReadingListRepository readingListRepository){
        this.readingListRepository=readingListRepository;
    }

    public void setAssociateId(String associateId){
        this.associateId=associateId;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String readersBooks(Reader reader, Model model){
        List<Book> readingList=readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);
        }
        return "readingList";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }

}
