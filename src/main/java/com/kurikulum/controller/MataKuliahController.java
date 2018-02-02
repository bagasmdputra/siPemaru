package com.kurikulum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.service.KurikulumService;
import com.kurikulum.service.MataKuliahService;
import com.kurikulum.service.NotificationService;
import com.kurikulum.service.PrasyaratService;

@Controller
public class MataKuliahController
{
    @Autowired
    MataKuliahService matkulDAO;
    
    @Autowired
    KurikulumService kurikulumDAO;
    
    @Autowired
    PrasyaratService prasyaratDAO;
    
    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/matkuldetail")
    public String matkuldetail (Model model, @RequestParam(value="kode_matkul", required = false) String kode_matkul)
    {
    	MataKuliahModel matakuliah = matkulDAO.selectMataKuliah(kode_matkul);
    	if(matakuliah != null ) {
    		model.addAttribute("matakuliah", matakuliah);
    		model.addAttribute("pagetitle", "Mata Kuliah");
    		return "matkuldetail";
    	}
    	else {
    		notifyService.addErrorMessage("ERROR 404 - Halaman yang Anda tuju tidak ditemukan");
    		List<MataKuliahModel> mataKuliahList = matkulDAO.selectMataKuliahList();
    		model.addAttribute("matakuliahlist", mataKuliahList);
        	model.addAttribute("pagetitle", "Mata Kuliah");
    		return "matakuliah";
    	}
    }
    
    @RequestMapping("/matakuliah")
    public String matakuliah (Model model)
    {
    	List<MataKuliahModel> mataKuliahList = matkulDAO.selectMataKuliahList();
    	model.addAttribute("matakuliahlist", mataKuliahList);
    	model.addAttribute("pagetitle", "Mata Kuliah");
    	return "matakuliah";
    }
    
  @RequestMapping(value = "/addMatkul")
   public String addMatkul (Model model)
    {
    	model.addAttribute ("pagetitle", "Tambah Mata Kuliah");
        return "addMatkul";
    }
    
    @RequestMapping(value = "/addMatkul", method = RequestMethod.POST)
    public String addMatkulSubmit (Model model, @RequestParam(value="nama", required = false) String nama, 
    		@RequestParam(value="jumlah_sks", required = false) int jumlah_sks,
    		@RequestParam(value="deskripsi", required = false) String deskripsi)
    {
    	if((nama == null) || (jumlah_sks < 1)) {
    		model.addAttribute("flag_error", true);
    		model.addAttribute ("pagetitle", "Tambah Mata Kuliah");
    		return "addMatkul";
    	}
    	
    	MataKuliahModel lastMatkul = matkulDAO.selectLastMataKuliah();
    	int lastKode = Integer.parseInt(lastMatkul.getKode().substring(3));
    	
    	String kode = String.format("CSI%05d", (lastKode + 1));
    	
    	MataKuliahModel matkul = new MataKuliahModel();
    	matkul.setKode(kode);
    	matkul.setNama(nama);
    	matkul.setJumlahSKS(jumlah_sks);
    	matkul.setDeskripsi(deskripsi);
    	matkulDAO.addMatkul(matkul);

    	model.addAttribute("pagetitle", "Mata Kuliah");
    	model.addAttribute("flag_add", true);
    	model.addAttribute("kode", kode);
    	
    	List<MataKuliahModel> mataKuliahList = matkulDAO.selectMataKuliahList();
    	model.addAttribute("matakuliahlist", mataKuliahList);
    	model.addAttribute("sukses", "Sukses Menambahkan Mata Kuliah " + matkul.getNama());
    	notifyService.addSuccessMessage("Mata Kuliah \"" + matkul.getKode() +" \" telah berhasil ditambahkan");
        return "matakuliah";
    }
    
    
    @RequestMapping(value = "/matakuliah", method = RequestMethod.POST)
    public String addMatkulSubmit (Model model)
    {
    	model.addAttribute ("pagetitle", "Mata Kuliah");
        return "matakuliah";
    }
    
   
    
    @RequestMapping(value = "/editMatkul/{kode}", method = RequestMethod.GET)
    public String updateMatkul (Model model, @PathVariable(value="kode") String kode)
    {
    	
    	MataKuliahModel matakuliah = matkulDAO.selectMataKuliah(kode);
    	model.addAttribute("matakuliah", matakuliah);
    	model.addAttribute ("pagetitle", "Tambah Mata Kuliah");
    	
        return "editmatkul";
    }
    
    @RequestMapping(value = "/editMatkul/{kode}", method = RequestMethod.POST)
    public String updateMatkulSubmit (Model model, MataKuliahModel matakuliah)
    {
    	matkulDAO.updateMatkul(matakuliah);

    	model.addAttribute("pagetitle", "Mata Kuliah");
    	model.addAttribute("flag_update", true);
    	model.addAttribute("kode", matakuliah.getKode());
    	
    	List<MataKuliahModel> mataKuliahList = matkulDAO.selectMataKuliahList();
    	model.addAttribute("matakuliahlist", mataKuliahList);
    	model.addAttribute("sukses", "Sukses Melakukan Perubahan Mata Kuliah " + matakuliah.getNama());
    	notifyService.addSuccessMessage("Mata Kuliah \"" + matakuliah.getKode() +" \" telah berhasil diubah");
        return "matakuliah";
    }
    
    @RequestMapping("/deleteMatkul/{kode}")
    public String deleteMatkul (Model model, @PathVariable(value="kode") String kode)
    {
    	matkulDAO.deleteMatkul(kode);
    	model.addAttribute("flag_delete", true);
    	model.addAttribute("kode", kode);

    	model.addAttribute("pagetitle", "Mata Kuliah");
    	
    	List<MataKuliahModel> mataKuliahList = matkulDAO.selectMataKuliahList();
    	model.addAttribute("matakuliahlist", mataKuliahList);
        return "matakuliah";
    }
}
