package com.kurikulum.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KurikulumModel {
	private String kode;
	private String nama;
	private int tahun;
	private int sksWajib;
	private int sksPilihan;
	private List<MataKuliahModel> matkulList;
}
