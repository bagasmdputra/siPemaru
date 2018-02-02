package com.kurikulum.service;

import java.util.List;

import com.kurikulum.model.AngkatanModel;

public interface AngkatanService {
	List<AngkatanModel> selectAngkatanProdi(int id_universitas, int id_fakultas, int id_prodi);
}
