package com.kurikulum.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kurikulum.model.KurikulumModel;

public interface KurikulumService
{
	KurikulumModel selectKurikulum (@Param("kode_kurikulum") String kode_kurikulum);

	List<KurikulumModel> selectOwnProdiKurikulum();
	
	List<KurikulumModel> selectNotOwnProdiKurikulum();
	
    List<KurikulumModel> selectAllKurikulum();
    
    void addKurikulum (KurikulumModel kurikulum);
    
    void addKurikulumToProdi (String kode);
    
    void deleteKurikulum(String kode);
    
    void deleteKurikulumFromProdi(String kode);
    
    void updateKurikulum(KurikulumModel kurikulum);

    void addMatkulKurikulum (String kode_matkul, String kode_kurikulum,int isWajib, int jumlah_sks_prasyarat, int term);
    
    void deleteMatkulKurikulum(String kode_matkul, String kode_kurikulum);
    
    void updateMatkulKurikulum(String kode_matkul, String kode_kurikulum,int isWajib, int jumlah_sks_prasyarat, int term);

    List<KurikulumModel> selectAllKurikulumbyProdi(int kode_univ, int kode_fak, int kode_prodi);
}
