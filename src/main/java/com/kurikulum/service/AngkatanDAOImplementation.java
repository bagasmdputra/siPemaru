package com.kurikulum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kurikulum.dao.AngkatanDAO;
import com.kurikulum.model.AngkatanModel;

@Service
public class AngkatanDAOImplementation implements AngkatanDAO {

	private final String API_BASE_URL = "http://localhost:9091/api";
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	
//	@Autowired
//	private RestTemplate restTemplate;
		
//	@Override
//	public List<FakultasModel> selectAllFakultas(int id_universitas)
//	{
//		String fakultasList =
//				restTemplate.getForObject(
//						API_BASE_URL + "/getFakultasList/"+id_universitas, String.class);
//		List<FakultasModel> listFakultas = new ArrayList<>();
//		
//		JSONObject jsonResult = new JSONObject(fakultasList);
//		JSONArray resultArr = jsonResult.getJSONObject("result").getJSONArray("fakultasList");
//		for(int i = 0; i < resultArr.length(); i++) {
//			FakultasModel univ = (new Gson()).fromJson(resultArr.getJSONObject(i).toString(), FakultasModel.class);
//			listFakultas.add(univ);
//		}
//		
//		System.out.println("List Fakultas " + fakultasList);
//				return listFakultas;
//	}
//	
//	@Override
//	public List<UniversitasModel> selectAngkatan(int kurikulum/)
//	{
//		String univList =
//				restTemplate.getForObject(
//						API_BASE_URL + "/getUniversitasList", String.class);
//		List<UniversitasModel> listUniv = new ArrayList<>();
//		
//		JSONObject jsonResult = new JSONObject(univList);
//		JSONArray resultArr = jsonResult.getJSONObject("result").getJSONArray("univList");
//		for(int i = 0; i < resultArr.length(); i++) {
//			UniversitasModel univ = (new Gson()).fromJson(resultArr.getJSONObject(i).toString(), UniversitasModel.class);
//			listUniv.add(univ);
//		}
//		
//		System.out.println("List Universitas " + univList);
//				return listUniv;
//	}

	@Override
	public List<AngkatanModel> selectAngkatanProdi(int id_universitas, int id_fakultas, int id_prodi) {
//		
//		List<AngkatanModel> angkatans = new ArrayList<AngkatanModel>();
//		for(int i = 0; i < 5; i++) {
//			AngkatanModel angkatan = new AngkatanModel();
//			angkatan.setTahun(2015+i);
//			angkatan.setKurikulum("" + (12 +i)+"CSI01"+(2+i));
//			angkatans.add(angkatan);
//		}
		
		@SuppressWarnings("unchecked")
		List<AngkatanModel> angkatans =
		restTemplate.getForObject(
		API_BASE_URL+"/getAngkatanByProdi/"+id_universitas+"/"+id_fakultas+"/"+id_prodi,
		List.class);

		return angkatans;
	}
}
