package com.cakirilhan.domain.kunde.mapper;

import org.mapstruct.Mapper;

import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.web.dto.KontaktDto;


@Mapper
public interface KontaktMapper {
	
	KontaktDto toKontaktDto(Kontakt kontakt);
	
	Kontakt toKontakt(KontaktDto kontaktDto);

}
