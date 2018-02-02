package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.kurikulum.dao.FakultasDAO;
import com.kurikulum.model.FakultasModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Primary
public class FakultasServiceRest implements FakultasService{

	@Autowired
	FakultasDAO fakultasDAO;
	
	@Override
	public FakultasModel selectFakultas(int id_universitas, int id_fakultas) {
		log.info("REST - select univ "+ id_universitas + " "+id_fakultas);
		return fakultasDAO.selectFakultas(id_universitas,id_fakultas);
	}

	@Override
	public List<FakultasModel> selectAllFakultas(int id_universitas) {
		log.info("REST - select all Univ");
		return fakultasDAO.selectAllFakultas(id_universitas);
	}

}
