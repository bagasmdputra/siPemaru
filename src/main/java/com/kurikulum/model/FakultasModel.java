package com.kurikulum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultasModel {
	private int id_univ;
	private int id_fakultas;
	private String nama_fakultas;
}
