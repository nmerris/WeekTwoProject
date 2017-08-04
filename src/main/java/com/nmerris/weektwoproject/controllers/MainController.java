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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class MainController
{

    // TODO: maybe extract all the Strings and put them in their own Class?

    @Autowired
    ResumeRepository resumeRepository;


    @GetMapping("/index")
    public String indexPageGet(Model model)
    {
        model.addAttribute("indexTitle","Robo Resume");
        model.addAttribute("welcomeMessage","Welcome to Robo Resume!");
        model.addAttribute("addResumeLinkText","Add resume");
        model.addAttribute("displayAllResumesLinkText","Display all resumes");

        return "index";
    }


    @GetMapping("/addresume")
    public String addBook(Model model)
    {
        model.addAttribute("newResume", new Resume());

        // I am attempting to keep all Strings out of the HTML files
        // so that, for example, translating would be easier
        // this is how I was taught to do it in Android, and it
        // makes sense to me to do it here too.. also really good
        // practive with Thymeleaf
        model.addAttribute("addResumeHeading", "Enter resume details");
        model.addAttribute("addResumeTitle", "Robo Resume");
        model.addAttribute("firstNameFormTitle", "First Name: ");
        model.addAttribute("lastNameFormTitle", "Last Name: ");
        model.addAttribute("emailFormTitle", "Email: ");
        model.addAttribute("orgFormTitle", "Organization: ");
        model.addAttribute("dateStartFormTitle", "Start Date: ");
        model.addAttribute("dateEndFormTitle", "End Date: ");

        return "addresume";
    }


    @PostMapping("/addresume")
    public String postProduct(@Valid @ModelAttribute("newResume") Resume resume, BindingResult bindingResult)
    {
        // TODO: add validation stuff to addresume.html
        // display the same page again if any input validation errors found
        // the error messages are handled in addresume.html with Thymeleaf
//        if (bindingResult.hasErrors()) {
//            return "addresume";
//        }

        System.out.println("*********************************Resume: " + resume.getId() + "first name: " + resume.getNameFirst());

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
