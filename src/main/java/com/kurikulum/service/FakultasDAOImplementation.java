package com.kurikulum.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.kurikulum.dao.FakultasDAO;
import com.kurikulum.model.FakultasModel;

@Service
public class FakultasDAOImplementation implements FakultasDAO {

	private final String API_BASE_URL = "https://apap2017-univ-apps.herokuapp.com";
	
	
//	@Override
//	public List<UniversitasModel> selectAllUniv() 
//	{
//		try {
//			String result = restTemplate.build().getForObject(
//					String.format("%s/getUniversitasList", API_BASE_URL),
//					String.class);
//			
//			JSONObject jsonResult = new JSONObject(result);
//			
//			List<UniversitasModel> listUniv = new ArrayList<>();
//			
//			JSONArray resultArr = jsonResult.getJSONObject("result").getJSONArray("univList");
//			for(int i = 0; i < resultArr.length(); i++) {
//				UniversitasModel univ = (new Gson()).fromJson(resultArr.getJSONObject(i).toString(), UniversitasModel.class);
//				listUniv.add(univ);
//			}
//			return  listUniv;
//		}catch (Exception e){
//			e.printStackTrace();
//			return new ArrayList<>();
//		}
//	}
//	
//	@Override
//	public UniversitasModel selectUniv(int id_univ) 
//	{
//		String result = restTemplate.build().getForObject(
//				String.format("%s/getUniversitas/%s", API_BASE_URL, id_univ),
//				String.class);
//		return  null;
//	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	public FakultasModel selectFakultas(int id_universitas, int id_fakultas)
	{
//		FakultasModel fakultas =
//		restTemplate.getForObject(
//		API_BASE_URL + "/getFakultas/"+id_universitas+"/"+id_fakultas,
//		FakultasModel.class);
//		System.out.println("Fakultas " + fakultas);
//		return fakultas;
		
		String fakultas =
				restTemplate.getForObject(
						API_BASE_URL + "/getFakultas/"+id_universitas+"/"+id_fakultas, String.class);
				
		JSONObject jsonResult = new JSONObject(fakultas);
		FakultasModel fak = (new Gson()).fromJson(jsonResult.getJSONObject("result").getJSONObject("fakultas").toString(), FakultasModel.class);
				return fak;
	}
	
	@Override
	public List<FakultasModel> selectAllFakultas(int id_universitas)
	{
		String fakultasList =
				restTemplate.getForObject(
						API_BASE_URL + "/getFakultasList/"+id_universitas, String.class);
		List<FakultasModel> listFakultas = new ArrayList<>();
		
		JSONObject jsonResult = new JSONObject(fakultasList);
		JSONArray resultArr = jsonResult.getJSONObject("result").getJSONArray("fakultasList");
		for(int i = 0; i < resultArr.length(); i++) {
			FakultasModel univ = (new Gson()).fromJson(resultArr.getJSONObject(i).toString(), FakultasModel.class);
			listFakultas.add(univ);
		}
				return listFakultas;
	}
}
