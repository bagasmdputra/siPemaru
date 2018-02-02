package com.kurikulum.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrasyaratModel {
	private String kodeKurikulum;
	private String namaKurikulum;
	private String kodeMatkul;
	private String namaMatkul;
	private String kodeMatkulPrasyarat;
	private String namaMatkulPrasyarat;
	private List<MataKuliahModel> prasyaratList;
}
