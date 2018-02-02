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
import com.kurikulum.dao.ProdiDAO;
import com.kurikulum.model.ProdiModel;

@Service
public class ProdiDAOImplementation implements ProdiDAO {

	private final String API_BASE_URL = "https://apap2017-univ-apps.herokuapp.com";
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ProdiModel selectProdi(int id_universitas, int id_fakultas, int id_prodi)
	{

//		ProdiModel prodi =
//		restTemplate.getForObject(
//		API_BASE_URL + "/getProdi/"+id_universitas+"/"+id_fakultas+"/"+id_prodi,
//		ProdiModel.class);
//		System.out.println("Universitas " + prodi);
//		return prodi;
		
		String prodi =
				restTemplate.getForObject(
						API_BASE_URL + "/getProdi/"+id_universitas+"/"+id_fakultas+"/"+id_prodi, String.class);
				
		JSONObject jsonResult = new JSONObject(prodi);
		ProdiModel prodiObject = (new Gson()).fromJson(jsonResult.getJSONObject("result").getJSONObject("prodi").toString(), ProdiModel.class);

				return prodiObject;

	}
	
	@Override
	public List<ProdiModel> selectAllProdi(int id_universitas, int id_fakultas)
	{
		String prodiList =
				restTemplate.getForObject(
						API_BASE_URL + "/getProdiList/"+id_universitas+"/"+id_fakultas, String.class);
		List<ProdiModel> listProdi = new ArrayList<>();
		
		JSONObject jsonResult = new JSONObject(prodiList);
		JSONArray resultArr = jsonResult.getJSONObject("result").getJSONArray("prodiList");
		for(int i = 0; i < resultArr.length(); i++) {
			ProdiModel univ = (new Gson()).fromJson(resultArr.getJSONObject(i).toString(), ProdiModel.class);
			listProdi.add(univ);
		}
				return listProdi;
	}
}
