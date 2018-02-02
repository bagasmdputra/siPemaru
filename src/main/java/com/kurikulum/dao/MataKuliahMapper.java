package com.kurikulum.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kurikulum.model.MataKuliahModel;

@Mapper
public interface MataKuliahMapper
{
    @Select("select * from mata_kuliah where mata_kuliah.kode = #{kode_matkul}")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"),
    		@Result(property="deskripsi", column="deskripsi")
    })
    MataKuliahModel selectMataKuliah (@Param("kode_matkul") String kode_matkul);
    
    @Select("select * from mata_kuliah")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"), 
    		@Result(property="deskripsi", column="deskripsi")
    })
    List<MataKuliahModel> selectMataKuliahList ();
    
    @Select("select * from mata_kuliah, matkul_kurikulum where mata_kuliah.kode = #{kode_matkul} and mata_kuliah.kode = matkul_kurikulum.kode_matkul and matkul_kurikulum.kode_kurikulum = #{kode_kurikulum}")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"),
    		@Result(property="deskripsi", column="deskripsi"),
    		@Result(property="sksPrasyarat", column="jumlah_sks_prasyarat"),
    		@Result(property="isWajib", column="isWajib"),
    		@Result(property="term", column="term")
    })
    MataKuliahModel selectMataKuliahOnKurikulum (@Param("kode_kurikulum") String kode_kurikulum, @Param("kode_matkul") String kode_matkul);
    
    @Select("select count(*) from mata_kuliah")
    int countMatkul();
    
    @Select("select count(*) from mata_kuliah where kode LIKE #{kode}")
    int countMatkulByKode(@Param("kode") String kode);
    
    
    @Insert("INSERT INTO mata_kuliah (kode, nama, jumlah_sks, deskripsi) VALUES (#{kode}, #{nama}, #{jumlahSKS}, #{deskripsi} )")
    void addMatkul (MataKuliahModel matkul);
    
    @Delete("DELETE FROM mata_kuliah WHERE kode = #{kode}")
    void deleteMatkul(@Param("kode") String kode);
    
    @Select("select * from mata_kuliah ORDER BY kode desc LIMIT 1")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"), 
    		@Result(property="deskripsi", column="deskripsi")
    })
    MataKuliahModel selectLastMataKuliah();
    
    @Update("UPDATE mata_kuliah SET kode = #{kode}, nama = #{nama}, jumlah_sks = #{jumlahSKS}, deskripsi = #{deskripsi} WHERE kode = #{kode}")
    void updateMatkul(MataKuliahModel matkul);
    
    @Select("select * from matkul_kurikulum join mata_kuliah on matkul_kurikulum.kode_matkul = mata_kuliah.kode "
    		+ "where matkul_kurikulum.kode_kurikulum = #{kode_kurikulum}")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"), 
    		@Result(property="deskripsi", column="deskripsi"),
    		@Result(property="sksPrasyarat", column="jumlah_sks_prasyarat")
    })
    List<MataKuliahModel> selectMataKuliahbyKurikulum (@Param("kode_kurikulum") String kode_kurikulum);
    
    @Select("select * from matkul_kurikulum join mata_kuliah on matkul_kurikulum.kode_matkul = mata_kuliah.kode "
    		+ "where matkul_kurikulum.kode_kurikulum != #{kode_kurikulum}")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"), 
    		@Result(property="deskripsi", column="deskripsi"),
    		@Result(property="sksPrasyarat", column="jumlah_sks_prasyarat"),
    		@Result(property="term", column="term")
    })
    List<MataKuliahModel> selectMataKuliahNotAddedToKurikulum (@Param("kode_kurikulum") String kode_kurikulum);
    
    @Select("select * from matkul_kurikulum join mata_kuliah on matkul_kurikulum.kode_matkul = mata_kuliah.kode "
    		+ "where matkul_kurikulum.kode_kurikulum = #{kode_kurikulum} AND matkul_kurikulum.isWajib")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"), 
    		@Result(property="deskripsi", column="deskripsi"),
    		@Result(property="sksPrasyarat", column="jumlah_sks_prasyarat")
    })
    List<MataKuliahModel> selectMataKuliahWajib (@Param("kode_kurikulum") String kode_kurikulum);
    
    @Select("select * from matkul_kurikulum join mata_kuliah on matkul_kurikulum.kode_matkul = mata_kuliah.kode "
    		+ "where matkul_kurikulum.kode_kurikulum = #{kode_kurikulum} AND NOT matkul_kurikulum.isWajib")
    @Results(value = {
    		@Result(property="kode", column="kode"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"), 
    		@Result(property="deskripsi", column="deskripsi"),
    		@Result(property="sksPrasyarat", column="jumlah_sks_prasyarat")
    })
    List<MataKuliahModel> selectMataKuliahPilihan (@Param("kode_kurikulum") String kode_kurikulum);
}
