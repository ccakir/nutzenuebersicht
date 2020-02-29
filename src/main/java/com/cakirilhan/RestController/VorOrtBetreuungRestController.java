package com.cakirilhan.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.kunde.repository.VorOrtBetreuungRepository;
import com.cakirilhan.domain.kunde.service.impl.VortOrtBetreuungImpl;


@RestController
public class VorOrtBetreuungRestController {
	
	@Autowired
	private VorOrtBetreuungRepository vorOrtBetreuungRepository;
	
	@Autowired
	private VortOrtBetreuungImpl vortOrtBetreuungService;
	
	//VORORTBETREUUNG LIST
	@RequestMapping(value="/vob/list", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<VorOrtBetreuung>> getAlle() {
		
		return new ResponseEntity<List<VorOrtBetreuung>> (vorOrtBetreuungRepository.findAll(), HttpStatus.OK);
	}
	
	
	//VORORTBETREUUNG SPEICHERN
	@RequestMapping(value="/vob/saveVOB", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void saveVOB(@RequestBody VorOrtBetreuung vorOB) {
		
		vortOrtBetreuungService.saveVorOrtBetreuung(vorOB);
	}
	
	
	//DELETE vorOB
	@RequestMapping(value="/vob/delete/{id}", method=RequestMethod.GET)
	public void deleteVOB(@PathVariable("id") long id) {
		vortOrtBetreuungService.deleteVorOB(id);
	}
	
	//DELETE MEHRERE vorOB
	@RequestMapping(value="/vob/deletes/{ids}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void deleteVOBS(@PathVariable("ids") long[] ids) {
		
		
		
		vortOrtBetreuungService.deleteVorOBs(ids);
		
	}

}
