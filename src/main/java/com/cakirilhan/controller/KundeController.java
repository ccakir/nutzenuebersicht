package com.cakirilhan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.kunde.mapper.KundeMapperImpl;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.service.VortOrtBetreuungService;
import com.cakirilhan.domain.kunde.service.impl.KundeServiceImpl;
import com.cakirilhan.kunde.validator.KundeValidator;
import com.cakirilhan.web.dto.KundeDto;

@RestController
public class KundeController {

	@Autowired
	KundeRepository kundeRepository;
	@Autowired
	KundeServiceImpl kundeService;

	@Autowired
	KundeMapperImpl kundeMapper;

	@Autowired
	KundeValidator kundeValidator;
	
	@Autowired
	private VortOrtBetreuungService VortOrtBetreuungService;
	
	@RequestMapping(value="/kunde/view-customer-active-location/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView customerLocationView(@PathVariable("id") long id) {
		
		
		
		ModelAndView mav = new ModelAndView("kunde/viewCustomerLocation");
		mav.addObject("listLocations", VortOrtBetreuungService.findOrtWithLocation(id) );
		return mav;
	}

	@RequestMapping(value = "/kunde/list-kunden", method = RequestMethod.GET)
	public ModelAndView listKunden(ModelMap model) {

		model.put("listKunden", kundeRepository.findAll());
		model.put("message", "Alle Kunden");
		ModelAndView mav = new ModelAndView("kunde/listKunden");
		return mav;
	}

	@RequestMapping(value = "/kunde/addKunde", method = RequestMethod.GET)
	public ModelAndView addKunde(Model model) {

		model.addAttribute("kundeForm", new Kunde());
		
		ModelAndView mav = new ModelAndView("kunde/addKunde");
		return mav;
	}

	@RequestMapping(value = "/kunde/addKunde", method = RequestMethod.POST)
	public ModelAndView addKunde(@ModelAttribute("kundeForm") Kunde kundeForm,
			BindingResult bindingResult) {

		KundeDto kundeDto = kundeMapper.toKundeDto(kundeForm);
		kundeValidator.validate(kundeDto, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("kunde/addKunde");
			return mav;
		}

		Kunde kunde = kundeMapper.toKunde(kundeDto);
		kundeService.saveKunde(kunde);
		ModelAndView mav = new ModelAndView("redirect:/kunde/list-kunden");
		return mav;
	}
	
	@RequestMapping(value = "/kunde/update-kunde" , method = RequestMethod.GET)
	public ModelAndView updateKunde(@RequestParam long id, ModelMap model) {
		
		Optional<Kunde> kunde = kundeService.findByKundeId(id);
		
		model.put("updateKundeForm", kunde);
		ModelAndView mav = new ModelAndView("kunde/updateKunde");
		return mav;
	}
	@RequestMapping(value = "/kunde/update-kunde" , method = RequestMethod.POST)
	public ModelAndView updateKunde(@ModelAttribute("updateKundeForm") Kunde updateKundeForm , ModelMap model , BindingResult bindingResult) {
		
		KundeDto kundeDto = kundeMapper.toKundeDto(updateKundeForm);
		kundeValidator.validate(kundeDto, bindingResult);
		
		if(bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("kunde/updateKunde");
			return mav;
		}
		
		Kunde kunde = kundeMapper.toKunde(kundeDto);
		
		kundeService.updateKunde(kunde);
		ModelAndView mav = new ModelAndView("redirect:/kunde/list-kunden");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/kunde/delete-kunde" , method = RequestMethod.GET)
	public ModelAndView deleteKunde( ModelMap model, @RequestParam long id) {
		
		if(kundeService.deleteKunde(id)) {
			
			model.put("message1", "Der Eintrag wurde gelöscht.");
		} 
		else {
			model.put("message1", "Ein Fehler ist aufgetreten!");
			
		}
		ModelAndView mav = new ModelAndView("redirect:/kunde/list-kunden");
		return mav;
		
	}
	
	@RequestMapping(value = "/kunde/delete-kunde" , method = RequestMethod.POST)
	public ModelAndView deleteKunde(@RequestParam(value="listKundeIds", required = false) long[] listKundeIds, ModelMap model){
		
		if(listKundeIds != null && kundeService.deleteKunde(listKundeIds)) {
			model.put("message", "Die Einträgen wurden gelöscht.");
		} else {
			model.put("message", "Ein Fehler ist aufgetreten!");
		}
		
		ModelAndView mav = new ModelAndView("redirect:/kunde/list-kunden");
		return mav;
	}
	

}
