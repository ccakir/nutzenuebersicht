package com.cakirilhan.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cakirilhan.domain.user.Nutzen;
import com.cakirilhan.domain.user.service.impl.NutzenServiceImpl;
import com.cakirilhan.user.repository.NutzenRepository;

@RestController
@RequestMapping("/nutzen")
public class NutzenRestController {

	@Autowired
	private NutzenRepository nutzenRepository;

	@Autowired
	private NutzenServiceImpl nutzenService;

	
	//ALLE NUTZEN LIST
	@GetMapping(value="/rest-list-nutzen", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Nutzen>> getAllenutzen() {

		try {
			return new ResponseEntity<List<Nutzen>>(nutzenService.findAllOrderById(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Nutzen>>(HttpStatus.BAD_REQUEST);
		}

	}

	
	
	//GET NUTZEN NACH ID
	@RequestMapping(value = "/nutzen/rest-getNutzen/{nutzenId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Optional<Nutzen> getNutzen(@PathVariable("nutzenId") Long nutzenId) {

		return nutzenRepository.findById(nutzenId);
	}
	
	
	//SUCHEN NACH DATUM
	@RequestMapping(value = "/nutzen/rest-getNutzen/{von}/{bis}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Nutzen> getVonBisDatum(
			@PathVariable(name = "von", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate von,
			@PathVariable(name = "bis", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bis) {
		int currentPage = 1;
		int pagesize = 50;
		Page<Nutzen> nutzens = nutzenService.sucheMitDatum(
				PageRequest.of(currentPage - 1, pagesize), von, bis);
		return nutzens;
	}

	//NUTZEN SPEICHERN
	@PostMapping(value = "/nutzen/saveNutzen")
	public void saveNutzen(@RequestBody Nutzen nutzen) {

		nutzenService.save(nutzen);
	}
	
	//DELETE
	@RequestMapping(value="/nutzen/delete/{nutzenId}", method=RequestMethod.GET)
	public void deleteNutzen(@PathVariable("nutzenId") long nutzenId) {
		
		nutzenService.deleteNutzen(nutzenId);
		
	}
	
	//DELETE MEHRERE NUTZEN
	@RequestMapping(value="/nutzen/deleteNutzens/{nutzenIds}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void deleteMehrereNutzen(@PathVariable("nutzenIds")long[] nutzenIds) {
		
		nutzenService.deleteMehrereNutzen(nutzenIds);
	}
	
	//UPDATE NUTZEN
	@RequestMapping(value="/nutzen/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Optional<Nutzen> updateNutzen(@RequestBody Nutzen nutzen) {
		
		nutzenService.updateNutzen(nutzen);
		
		
		return nutzenRepository.findById(nutzen.getId());
		
	}

}
