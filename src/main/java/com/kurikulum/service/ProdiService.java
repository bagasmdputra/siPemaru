package com.kurikulum.service;

import java.util.List;

import com.kurikulum.model.ProdiModel;

public interface ProdiService {
	ProdiModel selectProdi(int id_universitas, int id_fakultas, int id_prodi);
	List<ProdiModel> selectAllProdi(int id_universitas, int id_fakultas);
}
