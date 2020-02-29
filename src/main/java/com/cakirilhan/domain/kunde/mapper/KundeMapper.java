package com.cakirilhan.domain.kunde.mapper;

import org.mapstruct.Mapper;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.web.dto.KundeDto;


@Mapper
public interface KundeMapper {
	
	KundeDto toKundeDto(Kunde kunde);
	
	Kunde toKunde(KundeDto kundeDto);

}
