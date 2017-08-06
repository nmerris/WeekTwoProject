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
        // must setLenient or it will take bogus dates like 0/0/0
        dateFormat.setLenient(false);
        int diffInDays;

        boolean startDateInvalid, endDateInvalid;
        startDateInvalid = endDateInvalid = false;
        int dateEndLength = resume.getDateEnd().length();


        // catch all start date errors, including empty dates
        try {
            dateStart = dateFormat.parse(resume.getDateStart());
        } catch (ParseException e) {
            model.addAttribute("dateStartInvalid", true);
            startDateInvalid = true;
        }

        // only catch end date errors if user entered at least one char
        if(dateEndLength > 0) {
            try {
                dateEnd = dateFormat.parse(resume.getDateEnd());
            } catch (ParseException e) {
                model.addAttribute("dateEndInvalid", true);
                endDateInvalid = true;
            }
        }

        // if both dates are valid, calculate the diff in days
        if(!startDateInvalid && !endDateInvalid) {
            // TODO: would be nice to make sure that end date is after start date
            diffInDays = (int) (Math.abs((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24)));

            System.out.println("!!!!!!!!!!!!!!!!!!!!!! date diff: " + diffInDays + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }

        // the @NotEmpty errors are automatically handled in messages.properties
        // @NotEmpty errors will show up here in the bindingResult
        // I am handling date format errors in this method because I have yet to figure out how
        // to get @DateTimeFormat annotation for the dateStart and dateEnd fields to work
        // if ANY errors are present, redisplay the addresume page
        if (bindingResult.hasErrors() || startDateInvalid || endDateInvalid) {
            System.out.println("**************************************** VALIDATION ERROR ************************");
            return "addresume";
        }


        // both dates are valid at this point
        // date start is exactly 10 chars in MM/DD/YYYY format
        // end date is same format if anything was entered, otherwise it is empty, which is ok


        // save it to db
        resumeRepository.save(resume);
        // display a confirmation page
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
