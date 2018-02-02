package com.kurikulum.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kurikulum.model.UniversitasModel;
import com.kurikulum.service.UniversitasService;

@Controller
public class PageController
{
    @Autowired
    UniversitasService universitasDAO;
    
    
    @RequestMapping("/")
    public String viewHomepage (Model model)
    {
    	model.addAttribute ("pagetitle", "Home");
  		List<UniversitasModel> listUniv = universitasDAO.selectAllUniv();
    	
    	model.addAttribute("universitas", new UniversitasModel());
    	model.addAttribute("listUniv", listUniv);
    	System.out.println("MASUK UNIV");
    	
        return "homepage";
    }
    
    @RequestMapping("/login")
    public String login (Principal principal, Model model)
    {
    	if(principal != null) {
    		return "redirect:/";
    	}
    	model.addAttribute ("pagetitle", "Login");
        return "login";
    }
}