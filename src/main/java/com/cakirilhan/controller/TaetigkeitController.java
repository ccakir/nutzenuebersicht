package com.cakirilhan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cakirilhan.domain.kunde.Taetigkeit;
import com.cakirilhan.domain.kunde.repository.TaetigkeitRepository;
import com.cakirilhan.domain.kunde.repository.service.TaetigkeitService;
import com.cakirilhan.domain.kunde.service.impl.TaetigkeitServiceImpl;

@Controller
public class TaetigkeitController {

	@Autowired
	private TaetigkeitRepository taetigkeitRepository;

	@Autowired
	private TaetigkeitServiceImpl taetigkeitService;

	@RequestMapping(value = "/taetigkeit/list-taetigkeiten", method = RequestMethod.GET)
	public String listTaetigkeiten(ModelMap model) {

		List<Taetigkeit> taetigkeits = taetigkeitRepository.findAll();
		model.put("taetigkeiten", taetigkeits);
		return "taetigkeit/listTaetigkeit";
	}

	@RequestMapping(value = "/taetigkeit/add-taetigkeit", method = RequestMethod.GET)
	public String addTaetigkeit(ModelMap model) {

		Taetigkeit taetigkeit = new Taetigkeit();
		model.put("taetigkeitForm", taetigkeit);
		return "taetigkeit/addTaetigkeit";

	}

	@RequestMapping(value = "/taetigkeit/add-taetigkeit", method = RequestMethod.POST)
	public String addTaetigkeit(
			@ModelAttribute("taetigkeitForm") Taetigkeit taetigkeitForm,
			RedirectAttributes redirectAttributes) {

		taetigkeitRepository.save(taetigkeitForm);
		redirectAttributes.addFlashAttribute("message",
				"Sie haben eine Tätigkeit erfolgreich geschpeichert.");

		return "redirect:/taetigkeit/list-taetigkeiten";
	}

	@RequestMapping(value = "/taetigkeit/delete-taetigkeit", method = RequestMethod.GET)
	public String deleteTaetigkeit(@RequestParam long id,
			RedirectAttributes redirectAttributes) {

		if (taetigkeitService.deleteTaetigkeit(id)) {

			redirectAttributes.addFlashAttribute("message",
					"Tätigkeit wurde gelöscht.");
		} else {
			redirectAttributes.addFlashAttribute("message",
					"Beim Löschen ein Fehler aufgetreten!");
		}

		return "redirect:/taetigkeit/list-taetigkeiten";
	}

	@RequestMapping(value = "/taetigkeit/delete-taetigkeiten", method = RequestMethod.POST)
	public String deleteTaetigkeiten(
			@RequestParam(name = "taetigkeitIds", required = false) long[] taetigkeitIds,
			RedirectAttributes redirectAttributes) {

		if (taetigkeitService.deleteTaetigkeiten(taetigkeitIds)) {

			redirectAttributes.addFlashAttribute("message",
					"Tätigkeiten wurden gelöscht.");
		} else {
			redirectAttributes.addFlashAttribute("message",
					"Beim Löschen ein Fehler aufgetreten!");
		}

		return "redirect:/taetigkeit/list-taetigkeiten";
	}
	
	@RequestMapping(value="taetigkeit/update-taetigkeit", method=RequestMethod.GET)
	public String updateTaetigkeit(ModelMap model,@RequestParam long id) {
		
		Optional<Taetigkeit> taetigkeit = taetigkeitRepository.findById(id);
		
		model.put("taetigkeitForm", taetigkeit.get());
		return "taetigkeit/updateTaetigkeit";
	}
	
	@RequestMapping(value="taetigkeit/update-taetigkeit", method=RequestMethod.POST)
	public String updateTaetigkeit(@ModelAttribute("taetigkeitForm") Taetigkeit taetigkeitForm, RedirectAttributes redirectAttributes) {
		
		taetigkeitService.updateTaetigkeit(taetigkeitForm);
		redirectAttributes.addFlashAttribute("message", "Taetigkeit wurde erfolgreich geändert.");
		return "redirect:/taetigkeit/list-taetigkeiten";
	}
}
