package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kurikulum.dao.KurikulumMapper;
import com.kurikulum.model.KurikulumModel;
import com.kurikulum.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KurikulumServiceDatabase implements KurikulumService
{
    @Autowired
    private KurikulumMapper kurikulumMapper;

    

    @Override
	public KurikulumModel selectKurikulum(String kode_kurikulum) {
		log.info ("select kurikulum with kode_kurikulum {} {}", kode_kurikulum);
	
		return kurikulumMapper.selectKurikulum(kode_kurikulum);
	}

	@Override
	public List<KurikulumModel> selectAllKurikulum() {
		log.info ("select all kurikulum");
		return kurikulumMapper.selectAllKurikulum();
	}

	@Override
	public void addKurikulum(KurikulumModel kurikulum) {
		String kode_kurikulum = kodeKurikulumGenerator(kurikulum.getTahun(), kurikulum.getKode());
		log.info ("ADD kurikulum with kode_kurikulum {} {}", kode_kurikulum);
		kurikulum.setKode(kode_kurikulum);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserModel user = kurikulumMapper.selectUser(auth.getName());
	
		kurikulumMapper.addKurikulumInstansi(user.getUniversitas(), user.getFakultas(), user.getProdi(), kode_kurikulum);
		kurikulumMapper.addKurikulum(kurikulum);
	}
	
	public String kodeKurikulumGenerator(int tahun, String inisial) {
		log.info("generate kode kurikulum with tahun {} and inisial {}", tahun, inisial);
		String kode_kurikulum = Integer.toString(tahun%100) + inisial;
				
		int urutan = kurikulumMapper.countKurikulumWithSameKode("%"+kode_kurikulum+"%") + 1;
		String kode_kurikulum_baru  = kode_kurikulum + String.format("%03d", urutan);
		int cekSama = kurikulumMapper.countKurikulumWithSameKode("%"+kode_kurikulum_baru+"%");
		while(cekSama >= 1){
			urutan++;
			kode_kurikulum_baru = kode_kurikulum + String.format("%03d", urutan);
			cekSama = kurikulumMapper.countKurikulumWithSameKode("%"+kode_kurikulum_baru+"%");
		}
		
		return kode_kurikulum_baru;
	}

	@Override
	public void deleteKurikulum(String kode) {
		log.info("delete kurikulum with kode kurikulum {}", kode);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserModel user = kurikulumMapper.selectUser(auth.getName());
		
		kurikulumMapper.deleteKurikulumInstansi(user.getUniversitas(), user.getFakultas(), user.getProdi(), kode);
		kurikulumMapper.deleteKurikulum(kode);
	}

	@Override
	public void updateKurikulum(KurikulumModel kurikulum) {
		// TODO Auto-generated method stub
		log.info ("select matkul wajib =  {} pilihan = {}",kurikulumMapper.selectMataKuliahSpesifik(kurikulum.getKode(), 1), kurikulumMapper.selectMataKuliahSpesifik(kurikulum.getKode(), 0));
		
		log.info ("select kurikulum with kode_kurikulum {} {}", kurikulum.getKode(), kurikulum.getNama(), kurikulum.getSksPilihan(), kurikulum.getSksWajib());
		kurikulumMapper.updateKurikulum(kurikulum);
	}

	@Override
	public List<KurikulumModel> selectAllKurikulumbyProdi(int kode_univ, int kode_fak, int kode_prodi) {
		// TODO Auto-generated method stub
		log.info("select all kurikulum by prodi with kode_univ {} and kode_fak {} and kode_prodi {}", kode_univ, kode_fak, kode_prodi);
		return kurikulumMapper.selectAllKurikulumbyProdi(kode_univ, kode_fak, kode_prodi);
	}

	@Override
	public void deleteMatkulKurikulum(String kode_matkul, String kode_kurikulum) {
		// TODO Auto-generated method stub
		log.info("delete matkul kurikulum with kode_matkul {} and kode_kurikulum {}", kode_matkul, kode_kurikulum);
		kurikulumMapper.deleteMatkulKurikulum(kode_matkul, kode_kurikulum);
	}



	@Override
	public void addMatkulKurikulum(String kode_matkul, String kode_kurikulum, int isWajib, int jumlah_sks_prasyarat,
			int term) {
		
		kurikulumMapper.addMtakulKurikulum(kode_matkul, kode_kurikulum, isWajib, jumlah_sks_prasyarat, term);
	}

	@Override
	public void updateMatkulKurikulum(String kode_matkul, String kode_kurikulum, int isWajib, int jumlah_sks_prasyarat,
			int term) {
		
		kurikulumMapper.addMtakulKurikulum(kode_matkul, kode_kurikulum, isWajib, jumlah_sks_prasyarat, term);
	}

	@Override
	public List<KurikulumModel> selectOwnProdiKurikulum() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserModel user = kurikulumMapper.selectUser(auth.getName());

		log.info ("SELECT own KURIIKULUM  {}", kurikulumMapper.selectAllKurikulumbyProdi(1,1,2));
		return kurikulumMapper.selectAllKurikulumbyProdi( user.getUniversitas(), user.getFakultas(), user.getProdi());
	}

	@Override
	public void addKurikulumToProdi(String kode) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserModel user = kurikulumMapper.selectUser(auth.getName());
		
		log.info ("ADD own KURIIKULUM instansi") ;
		kurikulumMapper.addKurikulumInstansi(user.getUniversitas(), user.getFakultas(), user.getProdi(), kode);
	}

	@Override
	public void deleteKurikulumFromProdi(String kode) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserModel user = kurikulumMapper.selectUser(auth.getName());
		
		log.info ("ADD own KURIIKULUM instansi") ;
		kurikulumMapper.deleteKurikulumInstansi(user.getUniversitas(), user.getFakultas(), user.getProdi(),kode);
		
	}

	@Override
	public List<KurikulumModel> selectNotOwnProdiKurikulum() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		UserModel user = kurikulumMapper.selectUser(auth.getName());

		log.info ("SELECT not own KURIIKULUM" );
		return kurikulumMapper.selectAllNotOwnKurikulum( user.getUniversitas(), user.getFakultas(), user.getProdi());
		
	}


}
