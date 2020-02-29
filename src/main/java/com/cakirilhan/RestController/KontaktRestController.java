package com.cakirilhan.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.domain.kunde.repository.KontaktRepository;
import com.cakirilhan.domain.kunde.service.impl.KontaktServiceImpl;

@RestController
public class KontaktRestController {
	
	@Autowired
	private KontaktRepository kontaktRepository;
	@Autowired
	private KontaktServiceImpl kontaktService;

	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/kontakten", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Kontakt> getKontakte() {
		List<Kontakt> list = kontaktRepository.findAll();
		return list;
	}
	
	@RequestMapping(value="/kontakte/{kId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Kontakt getKontakt(@PathVariable("kId") long kId) {
		return kontaktRepository.findByKontaktId(kId);
	}
	
	@RequestMapping(value="/kontakt", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void addKontakt(@RequestBody(required = false) Kontakt kontakt) {
		
		kontaktService.saveKontakt(kontakt);
	}

}
