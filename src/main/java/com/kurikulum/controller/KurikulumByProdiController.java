package com.kurikulum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.kurikulum.model.AngkatanModel;
import com.kurikulum.model.FakultasModel;
import com.kurikulum.model.ProdiModel;
import com.kurikulum.model.UniversitasModel;
import com.kurikulum.service.AngkatanService;

import com.kurikulum.service.FakultasService;
import com.kurikulum.service.KurikulumService;
import com.kurikulum.service.MataKuliahService;
import com.kurikulum.service.NotificationService;
import com.kurikulum.service.PrasyaratService;
import com.kurikulum.service.ProdiService;
import com.kurikulum.service.UniversitasService;

@Controller
public class KurikulumByProdiController {
    @Autowired
    MataKuliahService matkulDAO;
    
    @Autowired
    KurikulumService kurikulumDAO;
    	
    @Autowired
    PrasyaratService prasyaratDAO;

    @Autowired
    UniversitasService universitasDAO;
    
    @Autowired
    FakultasService fakultasDAO;
    
    @Autowired
    ProdiService prodiDAO;
    
    @Autowired
    AngkatanService angkatanDAO;

    @Autowired
    private NotificationService notifyService;
    
    
    @RequestMapping("/kurikulumByProdi")
    public String kurikulum (Model model,  
    		@RequestParam(value="univ", required = false) String univ, 
    		@RequestParam(value="fak", required = false) String fak, 
    		@RequestParam(value="prodi", required = false) String prodi)
    {
    	
    		
    		List<UniversitasModel> listUniv = universitasDAO.selectAllUniv();
        	
        	model.addAttribute("pagetitle", "Cari Kurikulum");

        	model.addAttribute("universitas", new UniversitasModel());
        	model.addAttribute("listUniv", listUniv);
        	
        	
        	if(univ !=null) {
        		int kode_univ = Integer.parseInt(univ);
	        		
	        	List<FakultasModel> listFakultas = fakultasDAO.selectAllFakultas(kode_univ);
	        	UniversitasModel univSelected = universitasDAO.selectUniv(kode_univ);
	        	

	        	model.addAttribute("universitas", univSelected);

	        	model.addAttribute("univSelected", univSelected);
	        	model.addAttribute("fakultas", new FakultasModel());
	        	model.addAttribute("listFakultas", listFakultas);
	        	
        		if(fak!=null) {
        			int kode_fak = Integer.parseInt(fak);
        			
        			List<ProdiModel> listProdi = prodiDAO.selectAllProdi(kode_univ, kode_fak);
        			FakultasModel fakSelected = fakultasDAO.selectFakultas(kode_univ, kode_fak);
        			
        			model.addAttribute("fakSelected",fakSelected);
        			model.addAttribute("listProdi", listProdi);
        		
        	}
    	}
    	 
    	return "kurikulumByProdiPage";
    }
    
    @RequestMapping("/angkatanaktif")
    public String kurikulumResult (Model model,  
    		@RequestParam(value="univ", required = false) String univ, 
    		@RequestParam(value="fak", required = false) String fak, 
    		@RequestParam(value="prod", required = false) String prod) {
    	
    	if(univ != null && fak !=null && prod != null) {
    		int universitas = Integer.parseInt(univ);
    		int fakultas = Integer.parseInt(fak);
    		int prodi = Integer.parseInt(prod);
    	
    	List<AngkatanModel> angkatan = angkatanDAO.selectAngkatanProdi(universitas, fakultas, prodi);
    	if(!angkatan.isEmpty()) {
	    	model.addAttribute("angkatan",angkatan);
	    	
	    	model.addAttribute("universitas", universitasDAO.selectUniv(universitas));
	    	model.addAttribute("prodi", prodiDAO.selectProdi(universitas, fakultas, prodi));
    	}else {
    		notifyService.addErrorMessage("ERROR 404 - Kami tidak menemukan data apapun dari Prodi yang Anda Masukkan");
    		return "redirect:/kurikulumByProdi";
    	}
    	model.addAttribute("pagetitle", "Angkatan Aktif");
    	
    	return "search-byprodi";
    	}
    	notifyService.addErrorMessage("ERROR - Masukan Anda salah/kurang");
    	return "redirect:/kurikulumByProdi";
    }
    
}
