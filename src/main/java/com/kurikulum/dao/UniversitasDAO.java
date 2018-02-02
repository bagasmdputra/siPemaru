package com.kurikulum.dao;

import java.util.List;

import com.kurikulum.model.UniversitasModel;

public interface UniversitasDAO {

	UniversitasModel selectUniv(int id_univ);

	List<UniversitasModel> selectAllUniv();
	
}
