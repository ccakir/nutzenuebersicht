package com.cakirilhan.domain.kunde.mapper;

import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.web.dto.KontaktDto;


@Service
public class KontaktMapperImpl implements KontaktMapper {

	@Override
	public KontaktDto toKontaktDto(Kontakt kontakt) {
		
		if(kontakt == null) {
			return null;
		}
		
		KontaktDto kontaktDto = new KontaktDto();
		
		if(kontakt.getKontaktId()!= null) {
			kontaktDto.setKontaktId(kontakt.getKontaktId());
		}
		kontaktDto.setAnrede(kontakt.getAnrede());
		kontaktDto.setTitel(kontakt.getTitel());
		kontaktDto.setVorname(kontakt.getVorname());
		kontaktDto.setNachname(kontakt.getNachname());
		kontaktDto.setTelefon(kontakt.getTelefon());
		kontaktDto.setMobil(kontakt.getMobil());
		kontaktDto.setFax(kontakt.getFax());
		kontaktDto.setEmail(kontakt.getEmail());
		kontaktDto.setKunde(kontakt.getKunde());
		
		return kontaktDto;
	}

	@Override
	public Kontakt toKontakt(KontaktDto kontaktDto) {
		if(kontaktDto == null) {
			return null;
		}
		
		Kontakt kontakt = new Kontakt();
		
		if(kontaktDto.getKontaktId()!= null) {
			kontakt.setKontaktId(kontaktDto.getKontaktId());
		}
		kontakt.setAnrede(kontaktDto.getAnrede());
		kontakt.setTitel(kontaktDto.getTitel());
		kontakt.setVorname(kontaktDto.getVorname());
		kontakt.setNachname(kontaktDto.getNachname());
		kontakt.setTelefon(kontaktDto.getTelefon());
		kontakt.setMobil(kontaktDto.getMobil());
		kontakt.setFax(kontaktDto.getFax());
		kontakt.setEmail(kontaktDto.getEmail());
		kontakt.setKunde(kontaktDto.getKunde());
		
		return kontakt;
	}
	

}
