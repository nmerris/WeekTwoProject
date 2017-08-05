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
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class MainController
{

    @Autowired
    ResumeRepository resumeRepository;


    @GetMapping("/index")
    public String indexPageGet(Model model)
    {
        // TODO add a link in index that changes the language to us spanish
        ResourceBundle rb = setDefaultLocaleToUsSpanish();

        model.addAttribute("indexTitle",rb.getString("indexTitle"));
        model.addAttribute("welcomeMessage",rb.getString("welcomeMessage"));
        model.addAttribute("addResumeLinkText",rb.getString("addResumeLinkText"));
        model.addAttribute("displayAllResumesLinkText",rb.getString("displayAllResumesLinkText"));

        return "index";
    }


    @GetMapping("/addresume")
    public String addBook(Model model)
    {
        ResourceBundle rb = setDefaultLocaleToUsSpanish();


        model.addAttribute("newResume", new Resume());


        model.addAttribute("addResumeHeading", rb.getString("addResumeHeading"));
        model.addAttribute("addResumeTitle", rb.getString("addResumeTitle"));
        model.addAttribute("firstNameFormTitle", rb.getString("firstNameFormTitle"));
        model.addAttribute("lastNameFormTitle", rb.getString("lastNameFormTitle"));
        model.addAttribute("emailFormTitle", rb.getString("emailFormTitle"));
        model.addAttribute("orgFormTitle", rb.getString("orgFormTitle"));
        model.addAttribute("dateStartFormTitle", rb.getString("dateStartFormTitle"));
        model.addAttribute("dateEndFormTitle", rb.getString("dateEndFormTitle"));

        return "addresume";
    }


    @PostMapping("/addresume")
    public String postProduct(@Valid @ModelAttribute("newResume") Resume resume, BindingResult bindingResult, Model model)
    {
        ResourceBundle rb = setDefaultLocaleToUsSpanish();

        if (bindingResult.hasErrors()) {
            System.out.println("**************************************** VALIDATION ERROR ************************");

            model.addAttribute("firstNameFormTitle", rb.getString("firstNameFormTitle"));

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


    private ResourceBundle setDefaultLocaleToUsSpanish() {
        Locale.setDefault(new Locale("es", "US"));
        System.out.println("*********************************** default locale is now: " + Locale.getDefault());
        return ResourceBundle.getBundle("Messages");
    }


}
