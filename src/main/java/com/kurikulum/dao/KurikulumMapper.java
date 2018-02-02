package com.kurikulum.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kurikulum.model.KurikulumModel;
import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.model.UserModel;


@Mapper
public interface KurikulumMapper
{
	@Select("select * from user where username = #{username}")
    @Results(value = {
    		@Result(property="username", column="username"),
    		@Result(property="universitas", column="id_universitas"),
    		@Result(property="fakultas", column="id_fakultas"), 
    		@Result(property="prodi", column="id_prodi"),
    		
    })
    UserModel selectUser (@Param("username") String username);	
	
	@Select("select count(*) from kurikulum where kode like #{kode_kurikulum}")
	int countKurikulumWithSameKode(String kode_kurikulum);
	
	@Select("select count(*) from matkul_kurikulumwhere kode_kurikulum = #{kode_kurikulum} and isWajib = #{isWajib}")
	int countMatkulInKurikulum(String kode_kurikulum,int isWajib);
	
	  	@Select("select * from kurikulum where kode = #{kode_kurikulum}")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="tahun", column="tahun"), 
	    		@Result(property="sksWajib", column="sks_wajib"),
	    		@Result(property="sksPilihan", column="sks_pilihan"),
	    		@Result(property="matkulList", column="kode", 
	    		javaType = List.class, many=@Many(select="selectMataKuliah"))
	    })
	    KurikulumModel selectKurikulum (@Param("kode_kurikulum") String kode_kurikulum);

	  	
	  	
	    @Select("select * from matkul_kurikulum join mata_kuliah on matkul_kurikulum.kode_matkul = mata_kuliah.kode "
	    		+ "where matkul_kurikulum.kode_kurikulum = #{kode_kurikulum}")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="jumlahSKS", column="jumlah_sks"), 
	    		@Result(property="deskripsi", column="deskripsi"),
	    		@Result(property="sksPrasyarat", column="jumlah_sks_prasyarat"),
	    		@Result(property="term", column="term")
	    })
	    List<MataKuliahModel> selectMataKuliah (@Param("kode_kurikulum") String kode_kurikulum);


	    
	    @Select("select * from matkul_kurikulum join mata_kuliah on matkul_kurikulum.kode_matkul = mata_kuliah.kode "
	    		+ "where matkul_kurikulum.kode_kurikulum = #{kode_kurikulum} and matkul_kurikulum.isWajib = #{isWajib}")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="isWajib", column="isWajib"),
	    		@Result(property="jumlahSKS", column="jumlah_sks"), 
	    		@Result(property="deskripsi", column="deskripsi"),
	    		@Result(property="sksPrasyarat", column="jumlah_sks_prasyarat"),
	    		@Result(property="term", column="term")
	    })
	    List<MataKuliahModel> selectMataKuliahSpesifik (@Param("kode_kurikulum") String kode_kurikulum,@Param("isWajib") int isWajib);
	    
	    @Select("select kode, nama, tahun, sks_wajib, sks_pilihan from kurikulum")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="tahun", column="tahun"), 
	    		@Result(property="sksWajib", column="sks_wajib"),
	    		@Result(property="sksPilihan", column="sks_pilihan")
	    })
	    List<KurikulumModel> selectAllKurikulum();
	    
	    @Select("select kode, nama, tahun, sks_wajib, sks_pilihan from kurikulum "
	    		+ "WHERE kode NOT IN ("
	    		+ "SELECT kode "
	    		+ "from kurikulum_instansi join kurikulum "
	    		+ "on kurikulum_instansi.kode_kurikulum = kurikulum.kode "
	    		+ "where kode_universitas = #{kode_universitas} "
	    		+ "and kode_fakultas = #{kode_fakultas} "
	    		+ "and kode_prodi = #{kode_prodi})")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="tahun", column="tahun"), 
	    		@Result(property="sksWajib", column="sks_wajib"),
	    		@Result(property="sksPilihan", column="sks_pilihan")
	    })
	    List<KurikulumModel> selectAllNotOwnKurikulum(@Param("kode_universitas") int kode_universitas, @Param("kode_fakultas") int kode_fakultas, @Param("kode_prodi") int kode_prodi);
	    
	    @Select("select kode, nama, tahun, sks_wajib, sks_pilihan"
	    		+" 				from kurikulum_instansi join kurikulum \"\r\n" + 
	    		"	    		+ \"on kurikulum_instansi.kode_kurikulum = kurikulum.kode\"\r\n" + 
	    		"	    		+ \"where kode_universitas = #{kode_univ} \"\r\n" + 
	    		"	    		+ \"and kode_fakultas = #{kode_fak} \"\r\n" + 
	    		"	    		+ \"and kode_prodi = #{kode_prodi}")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="tahun", column="tahun"), 
	    		@Result(property="sksWajib", column="sks_wajib"),
	    		@Result(property="sksPilihan", column="sks_pilihan")
	    })
	    List<KurikulumModel> selectAllOwnKurikulum(@Param("kode_universitas") String kode_universitas, @Param("kode_fakultas") String kode_fakultas, @Param("kode_prodi") String kode_prodi);
	    
	    
	    @Insert("INSERT INTO kurikulum (kode, nama, tahun, sks_wajib, sks_pilihan) VALUES (#{kode}, #{nama}, #{tahun}, #{sksWajib}, #{sksPilihan} )")
	    void addKurikulum (KurikulumModel kurikulum);
	    
	    @Insert("INSERT INTO kurikulum_instansi (kode_kurikulum, kode_prodi, kode_fakultas, kode_universitas) VALUES (#{kode_kurikulum}, #{kode_prodi}, #{kode_fakultas}, #{kode_universitas})")
	    void addKurikulumInstansi (@Param("kode_universitas") int kode_universitas, @Param("kode_fakultas") int kode_fakultas, @Param("kode_prodi") int kode_prodi, @Param("kode_kurikulum") String kode_kurikulum);
	    
	    @Insert("INSERT INTO matkul_kurikulum (kode_matkul, kode_kurikulum, isWajib, jumlah_sks_prasyarat, term) VALUES (#{kode_matkul}, #{kode_kurikulum}, #{isWajib}, #{jumlah_sks_prasyarat}, #{term} )")
	    void addMtakulKurikulum (@Param("kode_matkul") String kode_matkul,@Param("kode_kurikulum") String kode_kurikulum,@Param("isWajib") int isWajib, @Param("jumlah_sks_prasyarat") int jumlah_sks_prasyarat, @Param("term") int term);
	    
	    @Delete("DELETE FROM kurikulum WHERE kode = #{kode}")
	    void deleteKurikulum(@Param("kode") String kode);
	    
	    @Delete("DELETE FROM kurikulum_instansi WHERE kode_universitas = #{kode_universitas} and kode_fakultas = #{kode_fakultas} and kode_prodi = #{kode_prodi} and kode_kurikulum = #{kode_kurikulum}")
	    void deleteKurikulumInstansi(@Param("kode_universitas") int kode_universitas, @Param("kode_fakultas") int kode_fakultas, @Param("kode_prodi") int kode_prodi, @Param("kode_kurikulum") String kode_kurikulum);
	    
	    @Delete("DELETE FROM matkul_kurikulum WHERE kode_kurikulum = #{kode_kurikulum} AND kode_matkul = #{kode_matkul}")
	    void deleteMatkulKurikulum(@Param("kode_matkul") String kode_matkul, @Param("kode_kurikulum") String kode_kurikulum);
	    
	    
	    @Update("UPDATE kurikulum SET kode = #{kode}, nama = #{nama}, tahun = #{tahun}, sks_wajib = #{sksWajib}, sks_pilihan = #{sksPilihan} WHERE kode = #{kode}")
	    void updateKurikulum(KurikulumModel kurikulum);
	    
	    @Insert("UPDATE matkul_kurikulum SET kode_matkul = #{kode_matkul}, kode_kurikulum = #{kode_kurikulum}, isWajib = #{isWajib}, jumlah_sks_prasyarat = #{jumlah_sks_prasyarat}, term = #{term}) VALUES (, , ,  )")
	    void updateMatkulKurikulum (@Param("kode_matkul") String kode_matkul,@Param("kode_kurikulum") String kode_kurikulum,@Param("isWajib") int isWajib, @Param("jumlah_sks_prasyarat") int jumlah_sks_prasyarat, @Param("term") int term);
	    
	    
	    @Select("SELECT kode, nama, tahun, sks_wajib, sks_pilihan "
	    		+ "from kurikulum_instansi join kurikulum "
	    		+ "on kurikulum_instansi.kode_kurikulum = kurikulum.kode "
	    		+ "WHERE kode_universitas = #{kode_univ} "
	    		+ "and kode_fakultas = #{kode_fak} "
	    		+ "and kode_prodi = #{kode_prodi}")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="tahun", column="tahun"), 
	    		@Result(property="sksWajib", column="sks_wajib"),
	    		@Result(property="sksPilihan", column="sks_pilihan")
	    })
	    List<KurikulumModel> selectAllKurikulumbyProdi(@Param("kode_univ") int kode_univ, @Param("kode_fak") int kode_fak, @Param("kode_prodi") int kode_prodi);

	    @Select("select * from kurikulum where kode like #{prefix} ORDER BY kode DESC LIMIT 1")
	    @Results(value = {
	    		@Result(property="kode", column="kode"),
	    		@Result(property="nama", column="nama"),
	    		@Result(property="tahun", column="tahun"), 
	    		@Result(property="sksWajib", column="sks_wajib"),
	    		@Result(property="sksPilihan", column="sks_pilihan"),
	    		@Result(property="matkulList", column="kode", 
	    		javaType = List.class, many=@Many(select="selectMataKuliah"))
	    })
	    KurikulumModel selectLatestKurikulum (@Param("prefix") String prefix);
	      
}
