package com.kurikulum.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kurikulum.model.KurikulumModel;
import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.model.PrasyaratModel;
import com.kurikulum.service.KurikulumService;
import com.kurikulum.service.MataKuliahService;
import com.kurikulum.service.NotificationService;
import com.kurikulum.service.PrasyaratService;

@Controller
public class PrasyaratController {
    @Autowired
    MataKuliahService matkulDAO;
    
    @Autowired
    KurikulumService kurikulumDAO;
    	
    @Autowired
    PrasyaratService prasyaratDAO;

    @Autowired
    private NotificationService notifyService;
    
    @RequestMapping("/matkul/edit/{kodeKurikulum}/{kodeMatkul}")
	public String addPrasyarat(Model model, @PathVariable("kodeKurikulum") String kodeKurikulum, @PathVariable("kodeMatkul") String kodeMatkul) {
    	KurikulumModel kurikulum = kurikulumDAO.selectKurikulum(kodeKurikulum);
    	MataKuliahModel matkul = matkulDAO.selectMataKuliahOnKurikulum(kodeKurikulum, kodeMatkul);

    	if(kurikulum != null && matkul != null) {
    		List<MataKuliahModel> listMatkulPrasyarat = prasyaratDAO.selectPrasyarat(kodeKurikulum, kodeMatkul) ;
    		List<MataKuliahModel> listSemuaMatkul = matkulDAO.selectMataKuliahbyKurikulum(kodeKurikulum);
    		List<MataKuliahModel> listMatkul = new ArrayList<>();
    		HashSet<String> setMatkulPrasyarat = new HashSet<>();
    		
    		
    		for(MataKuliahModel matkulTemp : listMatkulPrasyarat) {
    			setMatkulPrasyarat.add(matkulTemp.getKode());
    		}
    		
    		for(MataKuliahModel matkulTemp : listSemuaMatkul) {
    			if(!setMatkulPrasyarat.contains(matkulTemp.getKode())) {
    				listMatkul.add(matkulTemp);
    			}
    		}
    		
    		
    		PrasyaratModel prasyarat = new PrasyaratModel();
    		prasyarat.setKodeKurikulum(kodeKurikulum);
    		prasyarat.setKodeMatkul(kodeMatkul);
    		model.addAttribute("kurikulum", kurikulum);
    		model.addAttribute("matkul", matkul);
    		model.addAttribute("listMatkulPrasyarat", listMatkulPrasyarat);
    		model.addAttribute("listMatkul", listMatkul);
    		model.addAttribute("prasyarat", prasyarat);
    		model.addAttribute("pagetitle", "Edit Prasyarat Matkul");
    		
        	return "matkulprasyarat";
    	}
    	else {
    		if(matkul == null) {
    			notifyService.addErrorMessage("ERROR 404 - matkul pada kurikulum yang Anda cari tidak ada pada sistem kami");
        		return "redirect:/kurikulum/detail";
    		}
    		else {
    			notifyService.addErrorMessage("ERROR 404 - Kurikukulum yang Anda cari tidak ada pada sistem kami");
        		return "redirect:/kurikulum/detail";
    		}
    	}
	}
    
    @RequestMapping(value = "/addPrasyarat", method = RequestMethod.POST)
    public String addPrasyaratToMatkulSubmit(Model model,   @ModelAttribute("prasyarat") PrasyaratModel prasyarat)
    {	
    	MataKuliahModel matkulPrasyarat = matkulDAO.selectMataKuliah(prasyarat.getKodeMatkulPrasyarat());
    	MataKuliahModel matkul = matkulDAO.selectMataKuliah(prasyarat.getKodeMatkul());
    	
    	prasyaratDAO.addPrasyaratToMatkul(prasyarat);
    	
    	notifyService.addSuccessMessage("Mata Kuliah \"" + prasyarat.getKodeMatkulPrasyarat() + " - "+ matkulPrasyarat.getNama() +" \" telah berhasil ditambahkan menjadi salah satu prasyarat Mata kuliah " + matkul.getNama());
    	
    	return String.format("redirect:/matkul/edit/%s/%s", prasyarat.getKodeKurikulum(), prasyarat.getKodeMatkul());
    }
    
    @RequestMapping("/deletePrasyarat/{kodeKurikulum}/{kodeMatkul}/{kodeMatkulPrasyarat}")
    public String deletePrasyarat(Model model, @PathVariable("kodeKurikulum") String kodeKurikulum, @PathVariable("kodeMatkul") String kodeMatkul, @PathVariable("kodeMatkulPrasyarat") String kodeMatkulPrasyarat) {
    	
    	prasyaratDAO.deletePrasyaratOnMatkul(kodeKurikulum, kodeMatkul, kodeMatkulPrasyarat);
    	notifyService.addInfoMessage("Mata Kuliah \"" + kodeMatkulPrasyarat +" \" telah berhasil didihapus dari prasyarat Mata kuliah " + kodeMatkul);
    	
    	return String.format("redirect:/matkul/edit/%s/%s", kodeKurikulum, kodeMatkul);
    }
    
    @RequestMapping(value = "/updateMinimalSKS", method = RequestMethod.POST)
    public String updateMinimalSKS(Model model, @ModelAttribute("kurikulum") KurikulumModel kurikulum, @ModelAttribute("matkul") MataKuliahModel matkul) {
    	StringTokenizer token = new StringTokenizer(kurikulum.getKode(), ",");
    	String kodeKurikulum = token.nextToken();
    	String kodeMatkul = token.nextToken();
    	
    	prasyaratDAO.updateMinimalSKS(kodeKurikulum, kodeMatkul, matkul.getSksPrasyarat());
    	notifyService.addInfoMessage("Prasyarat minimal SKS pada Mata Kuliah \"" + kodeMatkul +" \" di kurikulum " + kodeKurikulum + " telah berhasil dilakukan");
    	return String.format("redirect:/matkul/edit/%s/%s", kodeKurikulum, kodeMatkul);
    }
}
