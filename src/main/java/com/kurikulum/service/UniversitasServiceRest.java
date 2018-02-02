package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.kurikulum.dao.UniversitasDAO;
import com.kurikulum.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Primary
public class UniversitasServiceRest implements UniversitasService{

	@Autowired
	UniversitasDAO universitasDAO;
	
	@Override
	public UniversitasModel selectUniv(int kode) {
		log.info("REST - select univ "+ kode);
		return universitasDAO.selectUniv(kode);
	}

	@Override
	public List<UniversitasModel> selectAllUniv() {
		log.info("REST - select all Univ");
		return universitasDAO.selectAllUniv();
	}

}
