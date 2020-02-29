package com.cakirilhan.controller;

import java.util.List;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.mapper.KontaktMapperImpl;
import com.cakirilhan.domain.kunde.repository.KontaktRepository;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.service.impl.KontaktServiceImpl;
import com.cakirilhan.kunde.validator.KontaktValidator;
import com.cakirilhan.web.dto.KontaktDto;

@RestController
public class KontaktController {

	@Autowired
	KontaktRepository kontaktRepository;

	@Autowired
	KundeRepository kundeRepository;

	@Autowired
	KontaktServiceImpl kontaktService;

	@Autowired
	private KontaktMapperImpl kontaktMapper;

	@Autowired
	private KontaktValidator kontaktValidator;
	
	

	@RequestMapping(value = "/contact/list-kontakte/{letter}", method = RequestMethod.GET)
	public ModelAndView listContactByLetter(
			@PathVariable("letter") String letter) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("kontakt/listKontakt");
		mav.addObject("kontakte", kontaktService.findContactsByLastname(letter));
		return mav;

	}

	

	@RequestMapping(value = "contact/list-kontakte", method = RequestMethod.GET)
	public ModelAndView listKontakte() {

		ModelAndView mav = new ModelAndView("kontakt/listKontakt");
		mav.addObject("kontakte", kontaktRepository.findAll());

		return mav;
	}

	@RequestMapping(value = "/contact/add-kontakt", method = RequestMethod.GET)
	public ModelAndView addKontakt() {
		

		ModelAndView mav = new ModelAndView("kontakt/addKontakt");
		mav.addObject("kundeList", kundeRepository.findAll());
		mav.addObject("kontaktForm", new Kontakt());
		return mav;
	}

	@RequestMapping(value = "/contact/add-kontakt", method = RequestMethod.POST)
	public ModelAndView addKontakt(
			@ModelAttribute("kontaktForm") Kontakt kontaktForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		KontaktDto kontaktDto = kontaktMapper.toKontaktDto(kontaktForm);
		kontaktValidator.validate(kontaktDto, bindingResult);

		if (bindingResult.hasErrors()) {
			
			ModelAndView mav = new ModelAndView("kontakt/addKontakt");
			mav.addObject("selectedKunde", kontaktForm.getKunde().getKundeId());
			mav.addObject("kundeList", kundeRepository.findAll());
			
			return mav;
		}

		Kontakt newKontakt = kontaktMapper.toKontakt(kontaktDto);
		kontaktService.saveKontakt(newKontakt);

		ModelAndView mav = new ModelAndView("redirect:/contact/list-kontakte");
		redirectAttributes.addFlashAttribute("message", "message.successfull.add");
		
		return mav;
	}

	@RequestMapping(value = "/contact/update-kontakt/{id}", method = RequestMethod.GET)
	public ModelAndView updateKontakt(@PathVariable long id, ModelMap model) {

		Kontakt kontakt = kontaktRepository.findByKontaktId(id);
		List<Kunde> kunde = kundeRepository.findAll();
		model.put("updateKontakt", kontakt);
		model.put("kundeList", kunde);

		ModelAndView mav = new ModelAndView("kontakt/updateKontakt");

		return mav;
	}

	@RequestMapping(value = "/contact/update-kontakt", method = RequestMethod.POST)
	public ModelAndView updateKontakt(
			@ModelAttribute("updateKontakt") Kontakt updateKontakt,
			BindingResult bindingResult,RedirectAttributes redirectAttributes) {

		KontaktDto kontaktDto = kontaktMapper.toKontaktDto(updateKontakt);

		kontaktValidator.validate(kontaktDto, bindingResult);

		
		
		if (bindingResult.hasErrors()) {

			ModelAndView mav = new ModelAndView("kontakt/updateKontakt");
			mav.addObject("kundeList", kundeRepository.findAll());
			return mav;
		}

		Kontakt kontakt = kontaktMapper.toKontakt(kontaktDto);
		kontaktService.updateKontakt(kontakt);

		ModelAndView mav = new ModelAndView("redirect:/contact/list-kontakte");
		redirectAttributes.addFlashAttribute("message", "message.successfull.update");
		return mav;
	}

	@RequestMapping(value = "/contact/delete-kontakt/{id}", method = RequestMethod.GET)
	public ModelAndView deleteKontakt(@PathVariable long id,
			RedirectAttributes redirectAttributes) {

		if (kontaktService.deleteKontakt(id)) {

			redirectAttributes.addFlashAttribute("message",
					"message.successfull.deleteOne");

		} else {
			redirectAttributes.addFlashAttribute("message",
					"message.error.delete");

		}

		ModelAndView mav = new ModelAndView("redirect:/contact/list-kontakte");
		return mav;
	}

	@RequestMapping(value = "/contact/delete-kontakte", method = RequestMethod.POST)
	public ModelAndView deleteKontakte(
			@RequestParam(name = "kontaktIds", required = false) long[] kontaktIds,
			RedirectAttributes redirectAttributes) {

		if (kontaktIds == null || !kontaktService.deleteKontakte(kontaktIds)) {
			redirectAttributes.addFlashAttribute("message",
					"message.error.delete");

		} else {
			redirectAttributes.addFlashAttribute("message",
					"message.successfull.delete");
		}

		ModelAndView mav = new ModelAndView("redirect:/contact/list-kontakte");
		return mav;
	}

}
