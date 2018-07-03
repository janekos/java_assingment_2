package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model md) {
    	md.addAttribute("dentists", dentistVisitService.listVisits());
        return "form";
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model md) {
        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult.getAllErrors());
        	md.addAttribute("dentists", dentistVisitService.listVisits());
        	return "form";
        }

        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime());
        return "redirect:/results";
    }
    
    @GetMapping("/appointments")
    public String showAppointmentsTable(DentistVisitDTO dentistVisitDTO, Model md) {
    	md.addAttribute("visits", dentistVisitService.listVisits());
        return "appointments";
    }
    
    @PostMapping("/appointments/edit")
    public String updateAppointmentsTable(@RequestParam("id") String id, @RequestParam("name") String dentistName, @RequestParam("time") String visitTime, Model md) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    	dentistVisitService.updateVisit(Long.parseLong(id), dentistName, LocalDateTime.parse(visitTime, formatter));
        return "appointments";
    }
    
    @PostMapping("/appointments/delete")
    public String deleteFromAppointmentsTable(@RequestParam("id") String id) {
    	dentistVisitService.deleteVisit(Long.parseLong(id));
        return "appointments";
    }

}
