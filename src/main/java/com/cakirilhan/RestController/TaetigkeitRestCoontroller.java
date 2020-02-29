package com.cakirilhan.RestController;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cakirilhan.domain.kunde.Taetigkeit;
import com.cakirilhan.domain.kunde.repository.TaetigkeitRepository;
import com.cakirilhan.domain.kunde.repository.service.TaetigkeitService;
import com.google.common.base.Preconditions;



@RestController
@RequestMapping("/rest/taetigkeit")
public class TaetigkeitRestCoontroller {
	
	@Autowired
	private TaetigkeitRepository taetigkeitRepository;
	
	@Autowired
	private TaetigkeitService taetigkeitService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Taetigkeit>> getList() {
		
		return new ResponseEntity<List<Taetigkeit>>(taetigkeitRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Taetigkeit create(@RequestBody Taetigkeit resource) {
		Preconditions.checkNotNull(resource);
		//taetigkeitRepository.save(resource);
		return taetigkeitRepository.save(resource);
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") long id, @RequestBody Taetigkeit resource) {
		System.out.println("Id : "+resource.getId());
		Preconditions.checkNotNull(resource);
		
		Preconditions.checkNotNull(taetigkeitRepository.findById(resource.getId()));
		taetigkeitService.updateTaetigkeit(resource);
	}
	

}
