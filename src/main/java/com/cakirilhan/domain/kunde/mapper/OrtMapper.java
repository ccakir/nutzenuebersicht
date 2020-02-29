package com.cakirilhan.domain.kunde.mapper;

import org.mapstruct.Mapper;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.web.dto.OrtDto;


@Mapper
public interface OrtMapper {
	
	OrtDto toOrtDto(Ort ort);
	
	Ort toOrt(OrtDto ortDto);

}
