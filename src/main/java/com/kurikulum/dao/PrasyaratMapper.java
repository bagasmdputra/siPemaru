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

import com.kurikulum.model.MataKuliahModel;
import com.kurikulum.model.PrasyaratModel;

@Mapper
public interface PrasyaratMapper
{
   
    @Select("select  kode_kurikulum, kurikulum.nama as nama_kurikulum, kode_matkul, mata_kuliah.nama as nama_matkul "
    		+ "from prasyarat "
    		+ "join mata_kuliah "
    		+ "on prasyarat.kode_matkul = mata_kuliah.kode "
    		+ "join kurikulum "
    		+ "on prasyarat.kode_kurikulum = kurikulum.kode "
    		+ "where prasyarat.kode_matkul = #{kode_matkul} and prasyarat.kode_kurikulum = #{kode_kurikulum}")
    @Results(value = {
    		@Result(property="kodeKurikulum", column="kode_kurikulum"),
    		@Result(property="namaKurikulum", column="nama_kurikulum"),
    		@Result(property="kodeMatkul", column="kode_matkul"), 
    		@Result(property="namaMatkul", column="nama_matkul"),
    		@Result(property="prasyaratList", column="{kode_kurikulum = kode_kurikulum, kode_matkul = kode_matkul}",
    		javaType = List.class, many=@Many(select="selectPrasyarat"))
    })
    PrasyaratModel selectMataKuliahPrasyarat (@Param("kode_kurikulum") String kode_kurikulum, @Param("kode_matkul") String kode_matkul);
  
    @Select("select *"
    		+ "from prasyarat "
    		+ "join mata_kuliah "
    		+ "on prasyarat.kode_matkul_prasyarat = mata_kuliah.kode "
    		+ "where prasyarat.kode_matkul = #{kode_matkul} and prasyarat.kode_kurikulum = #{kode_kurikulum}")
    @Results(value = {
    		@Result(property="kode", column="kode_matkul_prasyarat"),
    		@Result(property="nama", column="nama"),
    		@Result(property="jumlahSKS", column="jumlah_sks"), 
    		@Result(property="deskripsi", column="deskripsi")
    })
    List<MataKuliahModel> selectPrasyarat (@Param("kode_kurikulum") String kode_kurikulum, @Param("kode_matkul") String kode_matkul);
    
    @Insert("INSERT INTO prasyarat (kode_matkul, kode_matkul_prasyarat, kode_kurikulum) VALUES (#{kodeMatkul}, #{kodeMatkulPrasyarat}, #{kodeKurikulum})")
    void addPrasyaratToMatkul(PrasyaratModel prasyarat);
    
    @Delete("DELETE FROM prasyarat WHERE kode_matkul = #{kodeMatkul} AND kode_matkul_prasyarat = #{kodeMatkulPrasyarat} AND kode_kurikulum = #{kodeKurikulum}")
    void deletePrasyaratOnMatkul(@Param("kodeKurikulum") String kodeKurikulum, @Param("kodeMatkul") String kodeMatkul, @Param("kodeMatkulPrasyarat") String kodeMatkulPrasyarat);
    
    @Update("UPDATE matkul_kurikulum SET jumlah_sks_prasyarat = #{sksPrasyarat} WHERE kode_kurikulum = #{kodeKurikulum} AND kode_matkul = #{kodeMatkul}")
	void updateMinimalSKS(@Param("kodeKurikulum") String kodeKurikulum, @Param("kodeMatkul") String kodeMatkul, @Param("sksPrasyarat") int sksPrasyarat);
}
