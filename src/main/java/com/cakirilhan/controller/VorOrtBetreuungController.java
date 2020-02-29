package com.cakirilhan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.repository.VorOrtBetreuungRepository;
import com.cakirilhan.domain.kunde.repository.service.VortOrtBetreuungService;
import com.cakirilhan.domain.user.User;
import com.cakirilhan.kunde.validator.VorOrtBetreuungValidator;
import com.cakirilhan.user.repository.UserRepositiory;

@Controller
public class VorOrtBetreuungController {

	@Autowired
	private KundeRepository kundeRepository;

	@Autowired
	private OrtRepository ortRepository;

	@Autowired
	private UserRepositiory userRepositiory;

	@Autowired
	private VorOrtBetreuungRepository vorOrtBetreuungRepository;

	@Autowired
	private VortOrtBetreuungService vortOrtBetreuungService;

	@Autowired
	private VorOrtBetreuungValidator vorOrtBetreuungValidator;

	final static String url = "/vorortbetreuung/";

	@RequestMapping(value = "/vorortbetreuung/list-vorobs", method = RequestMethod.GET)
	public String listVorOB(ModelMap model) {

		model.addAttribute("listVorOBs",
				vortOrtBetreuungService.findAllOrderById());

		return url + "listVorOB";
	}

	@RequestMapping(value = "/vorortbetreuung/add-vorOB", method = RequestMethod.GET)
	public String addVorOB(ModelMap model) {

		List<Kunde> allKunden = kundeRepository.findAll();
		List<Ort> allOrte = ortRepository.findAll();
		List<User> allUser = userRepositiory.findAll();

		model.addAttribute("vorOBForm", new VorOrtBetreuung());
		model.addAttribute("allKunden", allKunden);
		model.addAttribute("allOrte", allOrte);
		model.addAttribute("allUsers", allUser);

		return url + "addVorOB";

	}

	@RequestMapping(value = "/vorortbetreuung/add-vorOB", method = RequestMethod.POST)
	public String addVorOBs(ModelMap model,
			@ModelAttribute("vorOBForm") VorOrtBetreuung vorOBForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			ModelMap model1) {

		vorOrtBetreuungValidator.validate(vorOBForm, bindingResult);

		if (bindingResult.hasErrors()) {

			List<Kunde> allKunden = kundeRepository.findAll();
			List<Ort> allOrte = ortRepository.findAll();
			List<User> allUser = userRepositiory.findAll();
			model1.addAttribute("allKunden", allKunden);
			model1.addAttribute("allOrte", allOrte);
			model1.addAttribute("allUsers", allUser);

			Optional<Kunde> selectedKunde = kundeRepository
					.findByKundeId(vorOBForm.getKunde().getKundeId());
			Optional<Ort> selectedOrt = ortRepository.findByOrtId(vorOBForm
					.getOrt().getOrtId());
			Optional<User> selectedUser = userRepositiory.findById(vorOBForm
					.getUser().getId());

			model1.addAttribute("selectedKunde", selectedKunde.get()
					.getKundeId());
			model1.addAttribute("selectedOrt", selectedOrt.get().getOrtId());
			model1.addAttribute("selectedUser", selectedUser.get().getId());
			model1.addAttribute("fehler",
					"Diese Kombination(Ort/Kunde/Mitarbeiter) ist vorhanden.");

			return url + "addVorOB";
		}

		vorOrtBetreuungRepository.save(vorOBForm);
		redirectAttributes.addFlashAttribute("message",
				"message.successfull.add");

		return "redirect:" + url + "list-vorobs";

	}

	@RequestMapping(value = "/vorortbetreuung/delete-vorOB", method = RequestMethod.GET)
	public String deleteVorOB(
			@RequestParam(name = "id", required = false) long id,
			RedirectAttributes redirectAttributes) {

		if (!vortOrtBetreuungService.deleteVorOB(id)) {
			redirectAttributes.addFlashAttribute("message",
					"message.successfull.deleteOne");

		} else {
			redirectAttributes.addFlashAttribute("message",
					"message.error.delete");

		}

		return "redirect:" + url + "list-vorobs";
	}

	@RequestMapping(value = "/vorortbetreuung/delete-vorOB", method = RequestMethod.POST)
	public String deleteVorOBs(
			@RequestParam(name = "listVOBIds", required = false) long[] listVOBIds,
			RedirectAttributes redirectAttributes) {

		if (listVOBIds == null
				|| !vortOrtBetreuungService.deleteVorOBs(listVOBIds)) {

			redirectAttributes.addFlashAttribute("message",
					"message.error.delete");

		} else {
			redirectAttributes.addFlashAttribute("message",
					"message.successfull.delete");
		}

		return "redirect:" + url + "list-vorobs";
	}

	@RequestMapping(value = "/vorortbetreuung/update-vorOB", method = RequestMethod.GET)
	public String updateVorOB(@RequestParam long id, ModelMap model) {

		Optional<VorOrtBetreuung> selectedVorOB = vorOrtBetreuungRepository
				.findById(id);

		List<Kunde> allKunden = kundeRepository.findAll();
		List<Ort> allOrte = ortRepository.findAll();
		List<User> allUser = userRepositiory.findAll();

		model.addAttribute("allKunden", allKunden);
		model.addAttribute("allOrte", allOrte);
		model.addAttribute("allUsers", allUser);

		model.put("vorOBForm", selectedVorOB.get());

		return "/vorortbetreuung/updateVorOB";
	}
	
	@RequestMapping(value="/vorortbetreuung/update-vorOB", method=RequestMethod.POST)
	public String updateVorOB(@ModelAttribute("vorOBForm") VorOrtBetreuung vorOBForm,
			RedirectAttributes redirectAttributes, BindingResult bindingResult, ModelMap model){
		
		vorOrtBetreuungValidator.validate(vorOBForm, bindingResult);
		if(bindingResult.hasErrors()) {
			
			List<Kunde> allKunden = kundeRepository.findAll();
			List<Ort> allOrte = ortRepository.findAll();
			List<User> allUser = userRepositiory.findAll(); 
			Optional<VorOrtBetreuung> selectedVorOB = vorOrtBetreuungRepository
					.findById(vorOBForm.getId());
			model.addAttribute("allKunden", allKunden);
			model.addAttribute("allOrte", allOrte);
			model.addAttribute("allUsers", allUser);

			model.put("vorOBForm", selectedVorOB.get());
			
			model.addAttribute("message",
					"message.error.duplicateLocationSupport");
			return url + "updateVorOB";
		}
		
		vortOrtBetreuungService.updateVorOB(vorOBForm);
		
		return "redirect:" + url + "list-vorobs"; 
	}

}
