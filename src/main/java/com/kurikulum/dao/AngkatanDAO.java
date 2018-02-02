package com.kurikulum.dao;

import java.util.List;

import com.kurikulum.model.AngkatanModel;

public interface AngkatanDAO {
	List<AngkatanModel> selectAngkatanProdi(int id_universitas, int id_fakultas, int id_prodi);
}
