package com.nmerris.weektwoproject.controllers;

import javax.validation.Valid;
import com.nmerris.weektwoproject.models.Resume;
import com.nmerris.weektwoproject.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class MainController
{

    @Autowired
    ResumeRepository resumeRepository;


    @GetMapping("/index")
    public String indexPageGet()
    {
        // TODO add a link in index that changes the language to us spanish

        return "index";
    }


    @GetMapping("/addresume")
    public String addBook(Model model)
    {
        model.addAttribute("newResume", new Resume());

        return "addresume";
    }


    @PostMapping("/addresume")
    public String postProduct(@Valid @ModelAttribute("newResume") Resume resume, BindingResult bindingResult, Model model)
    {



        model.addAttribute("dateStartNotTenChars", true);
        return "addresume";
//
//
//        // start date is formatted MM/DD/YYYY so any entry that does not have exactly 10 characters is definitely invalid
//        // note that an EMPTY start date is handled by @NotEmpty in Resume model, so don't need to check here
//        // so here we need to check if it's between 1-9 or greater than 10, if so, set a boolean flag
////        if((resume.getDateStart().length() > 1 && resume.getDateStart().length() < 10) || resume.getDateStart().length() > 10) {
////            model.addAttribute("dateStartNotTenChars", true);
////            return "addresume";
////        }
////        // set the flag to false for good measure
////        else {
////            model.addAttribute("dateStartNotTenChars", false);
////        }
//
//        // user entered exactly 10 chars for start date, now check validity
//        Date dateStart = new Date();
//        Date dateEnd = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat(Resume.DATE_PATTERN);
//        int diffInDays;
//
//        try {
//            dateStart = dateFormat.parse(resume.getDateStart());
//        } catch (ParseException e) {
//            // user entered an invalid start date
//            // set a boolean so that addresume.html will know to display an error
//            // it will be in the correct language because it will be read from messages.properties
//
//        }
//
//        // * 1000 to convert to seconds
//        // * 60 to convert to minutes
//        // * 60 to convert to hours
//        // * 24 to convert to days
//        // absolute value in case user entered later date first
////        diffInDays = (int) (Math.abs((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24)));
//
//
//
//
//
//
//        // the @NotEmpty errors are automatically handled in messages.properties
//        // I am handling date format errors here because I have yet to figure out how
//        // to get @DateTimeFormat annotation for the dateStart and dateEnd fields to work
//        if (bindingResult.hasErrors()) {
//            System.out.println("**************************************** VALIDATION ERROR ************************");
//            return "addresume";
//        }
//
//
//        // to get here, must have entered valid form data, so save it to db
//        resumeRepository.save(resume);
//        return "resumeaddedconfirmation";
    }


    @GetMapping("/displayallresumes")
    public String displayBooks(Model model)
    {
        // get every resume from db
        Iterable<Resume> resumeList = resumeRepository.findAll();
        model.addAttribute("allResumes", resumeList);
        return "displayallresumes";
    }


}
