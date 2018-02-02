package com.kurikulum.service;

import java.util.List;

import com.kurikulum.model.FakultasModel;

public interface FakultasService {
	FakultasModel selectFakultas(int id_universitas, int id_fakultas);
	List<FakultasModel> selectAllFakultas(int id_universitas);
}
