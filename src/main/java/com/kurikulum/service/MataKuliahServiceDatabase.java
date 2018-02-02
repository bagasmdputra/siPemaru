
package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurikulum.dao.MataKuliahMapper;
import com.kurikulum.model.MataKuliahModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MataKuliahServiceDatabase implements MataKuliahService
{
	
    @Autowired
    private MataKuliahMapper matkulMapper;

	@Override
	public MataKuliahModel selectMataKuliah(String kode_matkul) {
		log.info ("select matkul with kode matkul {}", kode_matkul);
		return matkulMapper.selectMataKuliah(kode_matkul);
	}

	@Override
	public List<MataKuliahModel> selectMataKuliahList() {
		// TODO Auto-generated method stub
		log.info("select mata kuliah list");
		return matkulMapper.selectMataKuliahList();
	}

	@Override
	public void addMatkul(MataKuliahModel matkul) {
		log.info("add mata kuliah");
		matkulMapper.addMatkul(matkul);
	}

	@Override
	public void deleteMatkul(String kode) {
		// TODO Auto-generated method stub
		log.info("delete mata kuliah with kode mata kuliah {}", kode);
		matkulMapper.deleteMatkul(kode);
		
	}

	@Override
	public void updateMatkul(MataKuliahModel matkul) {
		// TODO Auto-generated method stub
		log.info("update mata kuliah");
		matkulMapper.updateMatkul(matkul);
		
	}

	@Override
	public List<MataKuliahModel> selectMataKuliahbyKurikulum(String kode_kurikulum) {
		// TODO Auto-generated method stub
		log.info("select mata kuliah by kurikulum with kode_kurikulum {}", kode_kurikulum);
		return matkulMapper.selectMataKuliahbyKurikulum(kode_kurikulum);
	}

	@Override
	public int countMatkul() {
		log.info("count {}", matkulMapper.countMatkul());
		return matkulMapper.countMatkul();
	}

	@Override
	public int countMatkulByKode(String kode) {
		log.info("count {}", matkulMapper.countMatkulByKode(kode));
		return matkulMapper.countMatkulByKode(kode);
	}

	@Override
	public MataKuliahModel selectLastMataKuliah() {
		log.info("select last mata kuliah");
		return matkulMapper.selectLastMataKuliah();
	}

	@Override
	public List<MataKuliahModel> selectMataKuliahWajib(String kode_kurikulum) {
		// TODO Auto-generated method stub
		log.info("select mata kuliah wajib with kode_kurikulum {}", kode_kurikulum);
		return matkulMapper.selectMataKuliahWajib(kode_kurikulum);
	}

	@Override
	public List<MataKuliahModel> selectMataKuliahPilihan(String kode_kurikulum) {
		// TODO Auto-generated method stub
		log.info("select mata kuliah pilihan with kode_kurikulum", kode_kurikulum);
		return matkulMapper.selectMataKuliahPilihan(kode_kurikulum);
	}

	@Override
	public MataKuliahModel selectMataKuliahOnKurikulum(String kode_kurikulum, String kode_matkul) {
		// TODO Auto-generated method stub
		log.info("select mata kuliah on kurikulum with kode_kurikulum {} and kode_matkul {}", kode_kurikulum, kode_matkul);
		return matkulMapper.selectMataKuliahOnKurikulum(kode_kurikulum, kode_matkul);
	}

	@Override
	public List<MataKuliahModel> selectMataKuliahNotAddedToKurikulum(String kode_kurikulum) {
		// TODO Auto-generated method stub
		log.info("select mata kuliah not added to kurikulum with kode_kurikulum {}", kode_kurikulum);
		return matkulMapper.selectMataKuliahNotAddedToKurikulum(kode_kurikulum);
	}
}
