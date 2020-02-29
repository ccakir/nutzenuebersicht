package com.cakirilhan.domain.kunde.mapper;

import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.web.dto.KundeDto;


@Service
public class KundeMapperImpl implements KundeMapper{

	@Override
	public KundeDto toKundeDto(Kunde kunde) {
		
		if(kunde == null) {
			return null;
		}
		
		KundeDto kundeDto = new KundeDto();
		
		if(kunde.getKundeId() != null) {
			kundeDto.setKundeId(kunde.getKundeId());
		}
		kundeDto.setName(kunde.getName());
		kundeDto.setAdresse(kunde.getAdresse());
		kundeDto.setOrtsname(kunde.getOrtsname());
		kundeDto.setPlz(kunde.getPlz());
		kundeDto.setLand(kunde.getLand());
		kundeDto.setWeb(kunde.getWeb());
		kundeDto.setEmail(kunde.getEmail());
		kundeDto.setTelefon(kunde.getTelefon());
		kundeDto.setFax(kunde.getFax());
		
		
		return kundeDto;
	}

	@Override
	public Kunde toKunde(KundeDto kundeDto) {

		if(kundeDto == null) {
			return null;
		}
		
		Kunde kunde = new Kunde();
		
		if(kundeDto.getKundeId()!=null) {
			kunde.setKundeId(kundeDto.getKundeId());
		}
		
		kunde.setName(kundeDto.getName());
		kunde.setAdresse(kundeDto.getAdresse());
		kunde.setOrtsname(kundeDto.getOrtsname());
		kunde.setPlz(kundeDto.getPlz());
		kunde.setLand(kundeDto.getLand());
		kunde.setWeb(kundeDto.getWeb());
		kunde.setEmail(kundeDto.getEmail());
		kunde.setTelefon(kundeDto.getTelefon());
		kunde.setFax(kundeDto.getFax());
		
		
		return kunde;
	}

}
