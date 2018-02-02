package com.kurikulum.dao;

import java.util.List;

import com.kurikulum.model.ProdiModel;

public interface ProdiDAO {
	ProdiModel selectProdi(int id_universitas, int id_fakultas, int id_prodi);
	List<ProdiModel> selectAllProdi(int id_universitas, int id_fakultas);
}
