package com.cakirilhan.domain.kunde.mapper;

import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.web.dto.OrtDto;

@Service
public class OrtMapperImpl implements OrtMapper{

	@Override
	public OrtDto toOrtDto(Ort ort) {
		
		if(ort == null) {
			return null;
		}
		
		OrtDto ortDto = new OrtDto();
		
		if(ort.getOrtId()!= null) {
		ortDto.setOrtId(ort.getOrtId());
		}
		ortDto.setOrtname(ort.getOrtname());
		ortDto.setAdresse(ort.getAdresse());
		ortDto.setPlz(ort.getPlz());
		ortDto.setLand(ort.getLand());
		
		return ortDto;
	}

	@Override
	public Ort toOrt(OrtDto ortDto) {

		if(ortDto == null) {
			return null;
		}
		
		Ort ort = new Ort();
		
		if(ortDto.getOrtId()!= null) {
			ort.setOrtId(ortDto.getOrtId());
		}
		
		ort.setOrtname(ortDto.getOrtname());
		ort.setAdresse(ortDto.getAdresse());
		ort.setPlz(ortDto.getPlz());
		ort.setLand(ortDto.getLand());
		return ort;
	}

}
