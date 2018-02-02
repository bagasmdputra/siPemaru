package com.kurikulum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	private String username;
	private int universitas;
	private int fakultas;
	private int prodi;
}
