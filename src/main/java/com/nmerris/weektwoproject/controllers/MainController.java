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
        model.addAttribute("addResume","Add a resume");
        return "addresume";
    }


    @PostMapping("/addresume")
    public String postProduct(@Valid @ModelAttribute("newResume") Resume resume, BindingResult bindingResult)
    {
        // display the same page again if any input validation errors found
        // the error messages are handled in addresume.html with Thymeleaf
        if (bindingResult.hasErrors()) {
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
