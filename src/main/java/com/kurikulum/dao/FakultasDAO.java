package com.kurikulum.dao;

import java.util.List;

import com.kurikulum.model.FakultasModel;

public interface FakultasDAO {
	FakultasModel selectFakultas(int id_universitas, int id_fakultas);
	List<FakultasModel> selectAllFakultas(int id_universitas);
}
