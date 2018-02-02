package com.kurikulum.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.service.MataKuliahService;

@RestController
@RequestMapping("/api")
public class MataKuliahRestController {
	@Autowired
	MataKuliahService matkulService;
	

	@RequestMapping("/getMataKuliah/kurikulum/{kode}")
	public List<MataKuliahModel> view (@PathVariable(value = "kode") String kode) {
	List<MataKuliahModel> student = matkulService.selectMataKuliahbyKurikulum(kode);
	
	return student;
	}
	
	@RequestMapping("/getMataKuliah/{kode}")
	public MataKuliahModel matkulByKurikulum(@PathVariable(value = "kode") String kode) {
		MataKuliahModel student = matkulService.selectMataKuliah(kode);
		return student;
	}
	
	@RequestMapping("/matkul/viewall")
	public List<MataKuliahModel> viewall () {
		List<MataKuliahModel> students = matkulService.selectMataKuliahList();
		return students;
	}
}