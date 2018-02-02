package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.kurikulum.dao.ProdiDAO;
import com.kurikulum.model.ProdiModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Primary
public class ProdiServiceRest implements ProdiService{

	@Autowired
	ProdiDAO prodiDAO;
	
	@Override
	public ProdiModel selectProdi(int id_universitas, int id_fakultas, int id_prodi) {
		log.info("REST - select prodi "+ id_prodi);
		return prodiDAO.selectProdi(id_universitas, id_fakultas, id_prodi);
	}

	@Override
	public List<ProdiModel> selectAllProdi(int id_universitas, int id_fakultas) {
		log.info("REST - select all prodi");
		return prodiDAO.selectAllProdi(id_universitas, id_fakultas);
	}

}
