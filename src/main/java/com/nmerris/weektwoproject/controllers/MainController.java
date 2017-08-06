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
        Date dateStart = new Date();
        Date dateEnd = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Resume.DATE_PATTERN);
        int diffInDays;

        boolean startDateEmpty, endDateEmpty, startDateInvalid, endDateInvalid, startDateIsTenChars, endDateIsTenChars;
        startDateEmpty = endDateEmpty = startDateInvalid = endDateInvalid = startDateIsTenChars = endDateIsTenChars = false;
        int dateStartLength = resume.getDateStart().length();
        int dateEndLength = resume.getDateEnd().length();


        if(dateStartLength == 0) startDateEmpty = true;
        if(dateEndLength == 0) endDateEmpty = true;
        if(dateStartLength == 10) startDateIsTenChars = true;
        if(dateEndLength == 10) endDateIsTenChars = true;

        if(startDateIsTenChars) {
            try {
                dateStart = dateFormat.parse(resume.getDateStart());
            } catch (ParseException e) {
                startDateInvalid = true;
            }
        }

        if(endDateIsTenChars) {
            try {
                dateEnd = dateFormat.parse(resume.getDateEnd());
            } catch (ParseException e) {
                endDateInvalid = true;
            }
        }




        // both dates are valid at this point
        // date start is exactly 10 chars in MM/DD/YYYY format
        // end date is same format if anything was entered, otherwise it is empty, which is ok



        // * 1000 to convert to seconds
        // * 60 to convert to minutes
        // * 60 to convert to hours
        // * 24 to convert to days
        // absolute value in case user entered later date first
        diffInDays = (int) (Math.abs((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24)));


        System.out.println("!!!!!!!!!!!!!!!!!!!!!! date diff: " + diffInDays + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");




        // the @NotEmpty errors are automatically handled in messages.properties
        // I am handling date format errors in this method because I have yet to figure out how
        // to get @DateTimeFormat annotation for the dateStart and dateEnd fields to work
        if (bindingResult.hasErrors()) {
            System.out.println("**************************************** VALIDATION ERROR ************************");
            return "addresume";
        }


        // to get here, must have entered valid form data, so save it to db
        resumeRepository.save(resume);
        return "resumeaddedconfirmation";
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
