package com.cakirilhan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.mapper.OrtMapperImpl;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.repository.service.OrtService;
import com.cakirilhan.kunde.validator.OrtValidator;
import com.cakirilhan.web.dto.OrtDto;


@Controller
public class OrtController {
	
	@Autowired
	OrtRepository ortRepository;
	
	@Autowired
	OrtService ortService;
	
	@Autowired
	OrtValidator ortValidator;
	
	@Autowired
	private OrtMapperImpl ortMapper;
	
	
	@RequestMapping(value= "/ort/list-ort", method = RequestMethod.GET)
	public String listOrts(Model model) {
		model.addAttribute("listOrt", ortRepository.findAll());
		return "ort/list-orte";
	}
	
	@RequestMapping(value= "/ort/addOrt", method = RequestMethod.GET)
	public String addOrt(Model model) {
		
		model.addAttribute("ortForm", new Ort());
		return "ort/addOrt";
	}
	
	@PostMapping("/ort/addOrt")
	public String addOrt(@ModelAttribute("ortForm") Ort ortForm, BindingResult bindingResult){
		OrtDto ortDto = ortMapper.toOrtDto(ortForm);
		ortValidator.validate(ortDto, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "ort/addOrt";
		}
		
		Ort ort = ortMapper.toOrt(ortDto);
		ortService.save(ort);
		
		return "redirect:/ort/list-ort";
	}

	@RequestMapping(value = "/ort/delete-ort" , method = RequestMethod.GET)
	public String deleteOrt(@RequestParam long id, ModelMap model) {
		
		if(ortService.deleteOrt(id)) {
		
		return "redirect:/ort/list-ort";
		} else {
			model.put("message", "Ein Fehler ist aufgetreten!");
			return "redirect:/ort/list-ort";
		}
	}
	
	
	@RequestMapping(value="/ort/delete-orts" , method = RequestMethod.POST)
	public String deleteOrts(@RequestParam(value="listOrtIds" , required = false) long[] listOrtIds , Model model) {
		
		if(listOrtIds != null){
			ortService.deleteOrts(listOrtIds);
		}
		
		
		return "redirect:/ort/list-ort";
	}
	
	
	@RequestMapping(value="ort/update-ort", method = RequestMethod.GET)
	public String updateOrt(@RequestParam long id, ModelMap model) {
		Optional<Ort> ort = ortRepository.findById(id);
		
		model.put("ortUpdateForm", ort);
		
		return "ort/updateOrt";
	}
	
	@RequestMapping(value="ort/update-ort", method = RequestMethod.POST)
	public String updateOrt(ModelMap model, @ModelAttribute("ortUpdateForm")Ort ortUpdateForm, BindingResult result) {
		OrtDto ortDto = ortMapper.toOrtDto(ortUpdateForm);
		
		ortValidator.validate(ortDto, result);
		
		if(result.hasErrors()) {
			return "ort/updateOrt";
		}
		Ort ort = ortMapper.toOrt(ortDto);
		ortService.updateOrt(ort);
		
		return "redirect:/ort/list-ort";
		
	}
}
