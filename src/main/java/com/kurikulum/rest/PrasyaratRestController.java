package com.kurikulum.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurikulum.model.PrasyaratModel;
import com.kurikulum.service.PrasyaratService;

@RestController
@RequestMapping("/api")
public class PrasyaratRestController {
	@Autowired
	PrasyaratService prasyaratService;
	

	@RequestMapping("/getPrasyarat/{kode_kurikulum}/{kode_matkul}")
	public PrasyaratModel view (@PathVariable(value = "kode_kurikulum") String kode_kurikulum, @PathVariable(value = "kode_matkul") String kode_matkul) {
	PrasyaratModel prasyarat = prasyaratService.selectMataKuliahPrasyarat(kode_kurikulum, kode_matkul);
	return prasyarat;
	}
}