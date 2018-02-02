package com.kurikulum.service;

import java.util.List;

import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.model.PrasyaratModel;

public interface PrasyaratService
{
	PrasyaratModel selectMataKuliahPrasyarat (String kode_kurikulum, String kode_matkul);
	  
    List<MataKuliahModel> selectPrasyarat (String kode_kurikulum, String kode_matkul);
    
    void addPrasyaratToMatkul(PrasyaratModel prasyarat);

	void deletePrasyaratOnMatkul(String kodeKurikulum, String kodeMatkul, String kodeMatkulPrasyarat);

	void updateMinimalSKS(String kodeKurikulum, String kodeMatkul, int sksPrasyarat);
}
