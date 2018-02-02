package com.kurikulum.service;

import java.util.List;


import com.kurikulum.model.MataKuliahModel;

public interface MataKuliahService
{
	 	MataKuliahModel selectMataKuliah (String kode_matkul);
	    
	    List<MataKuliahModel> selectMataKuliahList ();
	    
	    List<MataKuliahModel> selectMataKuliahbyKurikulum (String kode_kurikulum);
	    
	    List<MataKuliahModel> selectMataKuliahNotAddedToKurikulum (String kode_kurikulum);
	    
	    void addMatkul (MataKuliahModel matkul);
	    
	    void deleteMatkul(String kode);
	    
	    void updateMatkul(MataKuliahModel matkul);
	    
	    int countMatkul();
	    
	    int countMatkulByKode(String kode);
	    
	    MataKuliahModel selectLastMataKuliah();
	    
	    List<MataKuliahModel> selectMataKuliahWajib (String kode_kurikulum);
	    
	    List<MataKuliahModel> selectMataKuliahPilihan (String kode_kurikulum);
	    
	    MataKuliahModel selectMataKuliahOnKurikulum (String kode_kurikulum, String kode_matkul);
}
