package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.kurikulum.dao.AngkatanDAO;
import com.kurikulum.model.AngkatanModel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Primary
public class AngkatanServiceRest implements AngkatanService{

	@Autowired
	AngkatanDAO angkatanDAO;
	
	@Override
	public List<AngkatanModel> selectAngkatanProdi(int id_universitas, int id_fakultas, int id_prodi) {
		log.info("REST - select angkatan aktif");
		return angkatanDAO.selectAngkatanProdi(id_universitas, id_fakultas, id_prodi);
	}

}
