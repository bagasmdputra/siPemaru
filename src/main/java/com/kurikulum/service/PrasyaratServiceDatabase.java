package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurikulum.dao.PrasyaratMapper;
import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.model.PrasyaratModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrasyaratServiceDatabase implements PrasyaratService
{
	
    @Autowired
    private PrasyaratMapper prasyaratMapper;

	@Override
	public PrasyaratModel selectMataKuliahPrasyarat(String kode_kurikulum, String kode_matkul) {
		log.info ("select matkul prasyarat with kode kurikulum {} and kode matkul {} \n {}", kode_kurikulum, kode_matkul, prasyaratMapper.selectPrasyarat(kode_kurikulum, kode_matkul));
		return prasyaratMapper.selectMataKuliahPrasyarat(kode_kurikulum, kode_matkul);
	}

	@Override
	public List<MataKuliahModel> selectPrasyarat(String kode_kurikulum, String kode_matkul) {
		// TODO Auto-generated method stub
		log.info("select prasyarat with kode_kurikulum {} and kode_matkul {}", kode_kurikulum, kode_matkul);
		return prasyaratMapper.selectPrasyarat(kode_kurikulum, kode_matkul);
	}

	@Override
	public void addPrasyaratToMatkul(PrasyaratModel prasyarat) {
		// TODO Auto-generated method stub
		log.info("add prasyarat to mata kuliah");
		prasyaratMapper.addPrasyaratToMatkul(prasyarat);
	}

	@Override
	public void deletePrasyaratOnMatkul(String kodeKurikulum, String kodeMatkul, String kodeMatkulPrasyarat) {
		// TODO Auto-generated method stub
		log.info("delete prasyarat on matkul with kodeKurikulum {} and kodeMatkul {} and kodeMatkulPrasyarat {}", kodeKurikulum, kodeMatkul, kodeMatkulPrasyarat);
		prasyaratMapper.deletePrasyaratOnMatkul(kodeKurikulum, kodeMatkul, kodeMatkulPrasyarat);
	}

	@Override
	public void updateMinimalSKS(String kodeKurikulum, String kodeMatkul, int sksPrasyarat) {
		// TODO Auto-generated method stub
		log.info("update minimal SKS with kodeKurikulum {} and kodeMatkul {} and sksPrasyarat {}", kodeKurikulum, kodeMatkul, sksPrasyarat);
		prasyaratMapper.updateMinimalSKS(kodeKurikulum, kodeMatkul, sksPrasyarat);
	}


}
