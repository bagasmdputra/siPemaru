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
import com.kurikulum.dao.UniversitasDAO;
import com.kurikulum.model.UniversitasModel;

@Service
public class UniversitasDAOImplementation implements UniversitasDAO {

	private final String API_BASE_URL = "https://apap2017-univ-apps.herokuapp.com";
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	public UniversitasModel selectUniv(int id)
	{
		String universitas =
				restTemplate.getForObject(
						API_BASE_URL + "/getUniversitas/"+id, String.class);
				
		JSONObject jsonResult = new JSONObject(universitas);
		UniversitasModel univ = (new Gson()).fromJson(jsonResult.getJSONObject("result").getJSONObject("universitas").toString(), UniversitasModel.class);

				return univ;
	}
	
	@Override
	public List<UniversitasModel> selectAllUniv()
	{
		String univList =
				restTemplate.getForObject(
						API_BASE_URL + "/getUniversitasList", String.class);
		List<UniversitasModel> listUniv = new ArrayList<>();
		
		JSONObject jsonResult = new JSONObject(univList);
		JSONArray resultArr = jsonResult.getJSONObject("result").getJSONArray("univList");
		for(int i = 0; i < resultArr.length(); i++) {
			UniversitasModel univ = (new Gson()).fromJson(resultArr.getJSONObject(i).toString(), UniversitasModel.class);
			listUniv.add(univ);
		}

				return listUniv;
	}
}
