package com.kurikulum.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurikulum.model.KurikulumModel;
import com.kurikulum.service.KurikulumService;

@RestController
@RequestMapping("/api")
public class KurikulumRestController {
	@Autowired
	KurikulumService kurikulumService;
	
	@RequestMapping("/getKurikulum/{kode_kurikulum}")
	public KurikulumModel view (@PathVariable(value = "kode_kurikulum") String kode_kurikulum) {
	KurikulumModel kurikulum = kurikulumService.selectKurikulum(kode_kurikulum);
	return kurikulum;
	}
	
	@RequestMapping("/kurikulum/viewall")
	public List<KurikulumModel> viewall () {
		List<KurikulumModel> kurikulum = kurikulumService.selectAllKurikulum();
		return kurikulum;
	}
	
	@RequestMapping("/getKurikulum/prodi/{kode_univ}/{kode_fak}/{kode_prodi}")
	List<KurikulumModel> selectAllKurikulumbyProdi(@PathVariable(value = "kode_univ")int kode_univ, @PathVariable(value = "kode_fak")int kode_fak, @PathVariable(value = "kode_prodi") int kode_prodi){
		return kurikulumService.selectAllKurikulumbyProdi(kode_univ, kode_fak, kode_prodi);
	}

}