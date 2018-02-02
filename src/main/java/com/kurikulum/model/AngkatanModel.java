package com.kurikulum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AngkatanModel {
	private int tahun_masuk;
	private int id_universitas;
	private int kode_fakultas;
	private int id_prodi;
	private String kode_kurikulum;
}
