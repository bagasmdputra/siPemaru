package com.kurikulum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MataKuliahModel {
	private String kode;
	private String nama;
	private Integer jumlahSKS;
	private String deskripsi;
	private Integer sksPrasyarat;
	private Integer isWajib;
	private Integer term;
	
}
