package com.kurikulum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdiModel {
	private int id_univ;
	private int id_fakultas;
	private int id_prodi;
	private String nama_prodi;
}
