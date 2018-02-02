package com.kurikulum.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kurikulum.model.KurikulumModel;
import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.service.KurikulumService;
import com.kurikulum.service.MataKuliahService;
import com.kurikulum.service.NotificationService;

@Controller
public class KurikulumController
{
    @Autowired
    KurikulumService kurikulumDAO;

    @Autowired
    MataKuliahService matakuliahDAO;

    @Autowired
    private NotificationService notifyService;
    
    @RequestMapping("/kurikulum")
    public String viewKurikulum (Model model, @RequestParam(value="all", required = false) String all)
    {	
    	List<KurikulumModel> kurikulums = kurikulumDAO.selectOwnProdiKurikulum();
    	if(all!=null) {	
    		kurikulums = kurikulumDAO.selectNotOwnProdiKurikulum();
        	model.addAttribute("active", "2");
	    	}else {	    	
	    	model.addAttribute("active", "1");
	    	}
    	model.addAttribute("kurikulums", kurikulums);
    	model.addAttribute("pagetitle", "Kurikulum");
    	return "kurikulum";
    }
        
    @RequestMapping("/kurikulum/detail")
    public String detailKurikulum (Model model, @RequestParam(value="kode_kurikulum", required = false) String kode_kurikulum)
    {
    	KurikulumModel kurikulum = kurikulumDAO.selectKurikulum(kode_kurikulum);
    	if(kurikulum != null) {
    		
    		if(cekOwned(kurikulum.getKode())) {
    			model.addAttribute("isOwned", true);
    		};
    		
    		List<MataKuliahModel> listMatkulWajib = matakuliahDAO.selectMataKuliahWajib(kode_kurikulum);
        	List<MataKuliahModel> listMatkulPilihan = matakuliahDAO.selectMataKuliahPilihan(kode_kurikulum);
    		
        	model.addAttribute("kurikulum", kurikulum);
        	model.addAttribute("listMatkulWajib", listMatkulWajib);
        	model.addAttribute("listMatkulPilihan", listMatkulPilihan);
        	model.addAttribute("pagetitle", "Detail Kurikulum");
        	
        	return "kurikulum-detail";
    	}
    	else {
    		notifyService.addErrorMessage("ERROR 404 - Kurikukulum yang Anda cari tidak ada pada sistem kami");
    		return "redirect:/kurikulum";
    	}
    	
    }
    
    public boolean cekOwned(String kode_kurikulum) {
    	List<KurikulumModel> listKur = kurikulumDAO.selectOwnProdiKurikulum();
		for(int i=0; i<listKur.size();i++) {
			if(listKur.get(i).getKode().equals(kode_kurikulum)) {
				return true;
			}
		}
		
		return false;
    }
    
    @RequestMapping("/kurikulum/edit")
    public String editKurikulum (Model model, @RequestParam(value="kode_kurikulum", required = false) String kode_kurikulum)
    {
    	KurikulumModel kurikulum = kurikulumDAO.selectKurikulum(kode_kurikulum);
    	if(kurikulum != null) {
    		if(!cekOwned(kurikulum.getKode())) {
    			notifyService.addErrorMessage("Anda tidak memiliki akses ke halaman ini");
    			return "redirect:/kurikulum";
    		};
    		model.addAttribute("kurikulum", kurikulum);
        	model.addAttribute("pagetitle", "Edit Kurikulum");
        	return "kurikulum-edit";
    	}
    	else {
    		notifyService.addErrorMessage("ERROR 404 - Kurikukulum yang Anda cari tidak ada pada sistem kami");
    		return "redirect:/kurikulum";
    	}	
    }
    
    @RequestMapping(value = "/kurikulum/edit", method = RequestMethod.POST)
    public String editKurikulumSubmit (Model model, @ModelAttribute("kurikulum") KurikulumModel kurikulum)
    {
    	notifyService.addSuccessMessage("Update Anda tentang \"" + kurikulum.getKode() +" - "+ kurikulum.getNama() +"\" telah berhasil");
		kurikulumDAO.updateKurikulum(kurikulum);
    	return "redirect:/kurikulum/detail?kode_kurikulum=" + kurikulum.getKode();
    	
    }
    
    @RequestMapping("/kurikulum/add/matkul")
    public String editMatkul (Model model, @RequestParam(value="kode_kurikulum", required = false) String kode_kurikulum)
    {

    	KurikulumModel kurikulum = kurikulumDAO.selectKurikulum(kode_kurikulum);
    	if(kurikulum != null) {
    		
    		if(!cekOwned(kurikulum.getKode())) {
    			return "redirect:/kurikulum";
    		};
    		
    		model.addAttribute("matkuls", matakuliahDAO.selectMataKuliahNotAddedToKurikulum(kode_kurikulum));
    		model.addAttribute("mataKuliah", new MataKuliahModel());
        	model.addAttribute("kurikulum", kurikulum);
        	model.addAttribute("pagetitle", "Tambah Mata Kuliah");
        	
        	return "kurikulum-edit2";
    	}
    	else {
    		notifyService.addErrorMessage("ERROR 404 - Kurikukulum yang Anda cari tidak ada pada sistem kami");
    		return "redirect:/kurikulum";
    	} 	
    }
    
    @RequestMapping(value = "/kurikulum/add/matkul", method = RequestMethod.POST)
    public String editMatkulSubmit (Model model, @ModelAttribute("kurikulum") MataKuliahModel mataKuliah, @RequestParam(value="kode_kurikulum", required = false) String kode_kurikulum)
    {
    	
		kurikulumDAO.addMatkulKurikulum(mataKuliah.getKode(), kode_kurikulum, mataKuliah.getIsWajib(), mataKuliah.getSksPrasyarat(), mataKuliah.getTerm());
		
		notifyService.addSuccessMessage("Mata Kuliah \"" + mataKuliah.getKode() +" \" telah berhasil ditambahkan");
		return "redirect:/kurikulum/detail?kode_kurikulum="+kode_kurikulum;
    	
    }
    
    
    @RequestMapping("/kurikulum/matkul-wajib")
    public String insertMatkulWajib (Model model, @RequestParam(value="kode_kurikulum", required = false) String kode_kurikulum)
    {
    	KurikulumModel kurikulum = kurikulumDAO.selectKurikulum(kode_kurikulum);
    	if(kurikulum != null) {
        	List<MataKuliahModel> listMatkulWajib = matakuliahDAO.selectMataKuliahWajib(kode_kurikulum);
        	List<MataKuliahModel> listMatkulPilihan = matakuliahDAO.selectMataKuliahPilihan(kode_kurikulum);
    		
        	model.addAttribute("kurikulum", kurikulum);
        	model.addAttribute("listMatkulWajib", listMatkulWajib);
        	model.addAttribute("listMatkulPilihan", listMatkulPilihan);
        	model.addAttribute("pagetitle", "Detail Kurikulum");
        	
        	return "kurikulum";
    	}
    	else {
    		notifyService.addErrorMessage("ERROR 404 - Kurikukulum yang Anda cari tidak ada pada sistem kami");
    		return "redirect:/kurikulum";
    	}
    }
    
    @RequestMapping("/kurikulum/matkul-pilihan")
    public String insertMatkulPilihan (Model model, @RequestParam(value="kode_kurikulum", required = false) String kode_kurikulum)
    {
    	KurikulumModel kurikulum = kurikulumDAO.selectKurikulum(kode_kurikulum);
    	if(kurikulum != null) {
        	List<MataKuliahModel> listMatkulWajib = matakuliahDAO.selectMataKuliahWajib(kode_kurikulum);
        	List<MataKuliahModel> listMatkulPilihan = matakuliahDAO.selectMataKuliahPilihan(kode_kurikulum);
    		
        	model.addAttribute("kurikulum", kurikulum);
        	model.addAttribute("listMatkulWajib", listMatkulWajib);
        	model.addAttribute("listMatkulPilihan", listMatkulPilihan);
        	model.addAttribute("pagetitle", "Detail Kurikulum");
        	
        	return "kurikulum";
    	}
    	else {
    		notifyService.addErrorMessage("ERROR 404 - Kurikukulum yang Anda cari tidak ada pada sistem kami");
    		return "redirect:/kurikulum";
    	}
    	
    }
    
    @RequestMapping("/kurikulum/add")
    public String addKurikulum (Model model)
    {	
    	KurikulumModel kurikulum = new KurikulumModel();
    	kurikulum.setTahun(2017);
    	model.addAttribute("pagetitle", "Tambah Kurikulum");
    	model.addAttribute("kurikulum", kurikulum);
    	return "kurikulum-add";
    }
    
    @RequestMapping(value = "/kurikulum/add", method = RequestMethod.POST)
    public String addKurikulumSubmit (Model model, @ModelAttribute("kurikulum") KurikulumModel kurikulum)
    {
    	model.addAttribute("flag_add", true);
    	model.addAttribute("kode", kurikulum);
    	
    	//menyimpan dan menambah kurikulum
		kurikulumDAO.addKurikulum(kurikulum);
		
		//buat munculin alert
		notifyService.addSuccessMessage("kurikulum \""+kurikulum.getKode()+" - "+kurikulum.getNama()+"\" berhasil ditambahkan");
    	return "redirect:/kurikulum/detail?kode_kurikulum=" + kurikulum.getKode();


    }
    
    @RequestMapping("/kurikulum/matkuldelete/{kode_kurikulum}/{kode_matkul}")
    public String deleteMatkulInKurikulum (Model model, @PathVariable(value="kode_kurikulum") String kode_kurikulum, @PathVariable(value="kode_matkul") String kode_matkul)
    {
    	kurikulumDAO.deleteMatkulKurikulum(kode_matkul, kode_kurikulum);
    	notifyService.addInfoMessage("Mata Kuliah "+ kode_matkul +" berhasil dihapus dari kurikulum "+ kode_kurikulum);
    	return "redirect:/kurikulum/detail?kode_kurikulum="+kode_kurikulum;
    }    
    
    @RequestMapping("/kurikulum/delete/{kode}")
    public String deleteKurikulumFromProdi (Model model, @PathVariable(value="kode") String kode)
    {
    	
    	if(!cekOwned(kode)) {
    		notifyService.addErrorMessage("Anda tidak memiliki akses untuk melakukan aksi ini");
			return "redirect:/kurikulum";
		};
    	kurikulumDAO.deleteKurikulumFromProdi(kode);
    	notifyService.addInfoMessage("Kurikulum "+ kode+" berhasil dihapus dari kepemilikan prodi Anda");
    	return "redirect:/kurikulum";
    }

    @RequestMapping("/kurikulum/deletepermanent/{kode}")
    public String deleteKurikulumpermanent (Model model, @PathVariable(value="kode") String kode)
    {
    	
    	if(cekOwned(kode)) {
    		notifyService.addErrorMessage("Anda tidak memiliki akses untuk melakukan aksi ini");
			return "redirect:/kurikulum";
		};
    	kurikulumDAO.deleteKurikulum(kode);
    	notifyService.addInfoMessage("Kurikulum "+ kode+" berhasil dihapus dari kepemilikan prodi Anda");
    	return "redirect:/kurikulum";
    }
    
    @RequestMapping("/kurikulum/addToProdi/{kode}")
    public String addToProdiKurikulum (Model model, @PathVariable(value="kode") String kode)
    {
    	
    	kurikulumDAO.addKurikulumToProdi(kode);
    	notifyService.addSuccessMessage("Kurikulum "+ kode+" berhasil ditambahkan menjadi salah satu kurikulum prodi Anda");
    	return "redirect:/kurikulum";
    }
}