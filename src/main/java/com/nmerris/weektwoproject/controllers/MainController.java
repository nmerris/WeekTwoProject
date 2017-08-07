package com.nmerris.weektwoproject.controllers;

import com.nmerris.weektwoproject.models.Resume;
import com.nmerris.weektwoproject.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controls all routes for this app.
 * Append '?lang=es_US' to any URL to switch to Spanish mode.
 * Append '?lang=en_US' to switch to English more.
 * If no lang parameter is specified, it will default to English.
 * All page text is located in the messages.properties resource bundle, so language switching happens automatically.
 * Locale handling Beans are located in WeektwoprojectApplication.java
 *
 * @see com.nmerris.weektwoproject.WeektwoprojectApplication
 *
 * @author Nathan Merris
 */
@Controller
public class MainController
{

    @Autowired
    ResumeRepository resumeRepository;

    // home page
    @GetMapping(value = {"/", "/index"})
    public String indexPageGet()
    {
        // TODO add a link in index that changes the language to us spanish

        return "index";
    }

    // add a single resume using a basic form (GET)
    @GetMapping("/addresume")
    public String addBook(Model model)
    {
        // newResume is the object name that will be used in addresume.html
        model.addAttribute("newResume", new Resume());

        return "addresume";
    }


    /**
     * User is routed back to here if invalid form data is entered.
     * If valid form data is entered, user is routed to a confirmation page.
     * Annotations in Resume.java are used to validate first and last names, and email.
     * Date validation is done in this method: start date is required and validated, end date is optional, but if
     * user enters anything in end date field, it will be validated.  The difference in days between start and end
     * dates are calculated here.
     *
     * @param resume the Resume object whose data is being entered
     * @param bindingResult holds annotation errors specified in Resume.java
     * @param model the model passed back to addresume.html (if necessary)
     * @return the same addresume.html page if there are any form errors, or resumeaddedconfirmation.html if not
     * @see Resume
     */
    @PostMapping("/addresume")
    public String postProduct(@Valid @ModelAttribute("newResume") Resume resume, BindingResult bindingResult, Model model)
    {
        Date dateStart = new Date();

        // a new Date is initialized to todays date by default
        Date dateEnd = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Resume.DATE_PATTERN);

        // must setLenient or it will take bogus dates like 0/0/0
        dateFormat.setLenient(false);

        // difference in days from start to end dates
        int diffInDays = 0;

        boolean startDateInvalid, endDateInvalid;
        startDateInvalid = endDateInvalid = false;
        int dateEndLength = resume.getDateEnd().length();


        // catch all start date errors, including empty dates
        try {
            dateStart = dateFormat.parse(resume.getDateStart());
        } catch (ParseException e) {
            // set a flag so our template knows if it should display an error message
            // Thymeleaf boolean variables are ignored if not present, so no error message is displayed if
            // dateStartInvalid is not present in the template
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

        // if ANY errors are present, redisplay the addresume page
        if (bindingResult.hasErrors() || startDateInvalid || endDateInvalid) {
            return "addresume";
        }


        // both dates are valid at this point
        // date start is exactly 10 chars in MM/DD/YYYY format
        // end date is same format if anything was entered, otherwise it is empty, which is ok

        // TODO: would be nice to make sure that end date is after start date
        // get difference and convert from milliseconds to days, rounding to the nearest int
        diffInDays = (int) (Math.abs((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24)));
        resume.setEmploymentDuration(diffInDays);

        // save it to db
        resumeRepository.save(resume);

        // display a confirmation page
        return "resumeaddedconfirmation";
    }


    @GetMapping("/displayallresumes")
    public String displayBooks(Model model)
    {
        // TODO: make it look nicer!
        // get every resume from db
        Iterable<Resume> resumeList = resumeRepository.findAll();

        // add it to the page model
        model.addAttribute("resumes", resumeList);
        return "displayallresumes";
    }


}
