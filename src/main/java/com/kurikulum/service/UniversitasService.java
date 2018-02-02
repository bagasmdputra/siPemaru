package com.kurikulum.service;

import java.util.List;

import com.kurikulum.model.UniversitasModel;

public interface UniversitasService {
	UniversitasModel selectUniv(int kode);
	List<UniversitasModel> selectAllUniv();
}
