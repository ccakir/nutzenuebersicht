package com.cakirilhan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.Taetigkeit;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.repository.TaetigkeitRepository;
import com.cakirilhan.domain.kunde.service.impl.KundeServiceImpl;
import com.cakirilhan.domain.user.Nutzen;
import com.cakirilhan.domain.user.User;
import com.cakirilhan.domain.user.service.UserRepositoryService;
import com.cakirilhan.domain.user.service.impl.NutzenServiceImpl;
import com.cakirilhan.user.repository.NutzenRepository;
import com.cakirilhan.user.repository.UserRepositiory;

@Controller
public class NutzenController {

	@Autowired
	private NutzenRepository nutzenRepository;

	@Autowired
	private NutzenServiceImpl nutzenService;

	@Autowired
	private UserRepositoryService userService;

	@Autowired
	private KundeRepository kundeRepository;

	@Autowired
	private TaetigkeitRepository taetigkeitRepository;

	@Autowired
	private OrtRepository ortRepository;

	@Autowired
	private UserRepositiory userRepositiory;

	@Autowired
	private KundeServiceImpl kundeService;

	final static String url = "/nutzen/list-nutzen?kunde=0&size=10&mitarbeiter=0&sucheMit=0";

	@RequestMapping(value = "/nutzen/list-nutzen", method = RequestMethod.GET)
	public String listNutzen(
			Authentication authentication,
			Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam(name = "kunde", required = false) Long kunde,
			@RequestParam(name = "sucheMit", required = false) String sucheMit,
			@RequestParam(name = "mitarbeiter", required = false) Long mitarbeiter,
			HttpServletRequest request,
			@RequestParam(name = "von", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate von,
			@RequestParam(name = "bis", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bis) {

		LocalDate vonDatum = von;

		LocalDate bisDatum = bis;

		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		boolean authorized = authorities.contains(new SimpleGrantedAuthority(
				"ROLE_ADMIN"));
		List<Kunde> kundeList = kundeRepository.findAll();
		User user = userService.findByUsername(authentication.getName());

		List<User> allUser = userRepositiory.findAll();

		Optional<Kunde> selectedKunde = kundeService.findByKundeId(kunde);

		Page<Nutzen> nutzenPage = null;

		int currentPage = page.orElse(1);
		int pagesize = size.orElse(5);

		switch (sucheMit) {

		case "null":
			nutzenPage = (Page<Nutzen>) nutzenService
					.findPaginated(PageRequest.of(currentPage - 1, pagesize),
							authorized, user);
			break;

		case "kunde":
			nutzenPage = nutzenService.sucheMitKunde(
					PageRequest.of(currentPage - 1, pagesize), authorized,
					user, selectedKunde);
			break;

		case "mitarbeiter":

			nutzenPage = nutzenService.sucheMitMitarbeiter(
					PageRequest.of(currentPage - 1, pagesize), mitarbeiter);

			break;

		case "datum":

			nutzenPage = nutzenService.sucheMitDatum(
					PageRequest.of(currentPage - 1, pagesize), vonDatum,
					bisDatum);

			break;

		default:
			nutzenPage = (Page<Nutzen>) nutzenService
					.findPaginated(PageRequest.of(currentPage - 1, pagesize),
							authorized, user);

		}

		model.addAttribute("kundenList", kundeList);
		model.addAttribute("nutzenPage", nutzenPage);
		model.addAttribute("sucheNachKunde", kunde);
		model.addAttribute("sucheMit", sucheMit);
		model.addAttribute("mitarbeiter", mitarbeiter);
		model.addAttribute("authorized", authorized);
		model.addAttribute("userList", allUser);
		model.addAttribute("user", user);
		;
		int totalPages = nutzenPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);

		}

		return "nutzen/listNutzen";
	}

	@RequestMapping(value = "/nutzen/add-nutzen", method = RequestMethod.GET)
	public String addNutzen(ModelMap model, Authentication authentication) {

		User userInfo = userService.findByUsername(authentication.getName());
		List<Kunde> kundeList = kundeRepository.findAll();
		List<Taetigkeit> taetigkeitList = taetigkeitRepository.findAll();
		List<Ort> ortList = ortRepository.findAll();

		model.put("ortList", ortList);
		model.put("taetigkeitList", taetigkeitList);
		model.put("kundeList", kundeList);
		model.put("userId", userInfo.getId());
		model.put("nutzenform", new Nutzen());
		return "nutzen/addNutzen";
	}

	@RequestMapping(value = "/nutzen/add-nutzen", method = RequestMethod.POST)
	public String addNutzen(@ModelAttribute("nutzenForm") Nutzen nutzenForm,
			RedirectAttributes redirectAttributes) {

		nutzenService.save(nutzenForm);
		redirectAttributes.addFlashAttribute("message",
				"Datensatz wurde erfolgreich gespeichert.");

		return "redirect:" + url;
	}

	@RequestMapping(value = "/nutzen/update-nutzen", method = RequestMethod.GET)
	public String updateNutzen(@RequestParam long id, ModelMap model,
			Authentication authentication) {

		User userInfo = userService.findByUsername(authentication.getName());
		List<Kunde> kundeList = kundeRepository.findAll();
		List<Taetigkeit> taetigkeitList = taetigkeitRepository.findAll();
		List<Ort> ortList = ortRepository.findAll();
		Optional<Nutzen> nutzen = nutzenRepository.findById(id);

		model.put("ortList", ortList);
		model.put("taetigkeitList", taetigkeitList);
		model.put("kundeList", kundeList);
		model.put("userId", userInfo.getId());
		model.put("nutzenForm", nutzen.get());
		return "/nutzen/updateNutzen";
	}

	@RequestMapping(value = "/nutzen/update-nutzen", method = RequestMethod.POST)
	public String updateNutzen(@ModelAttribute("nutzenForm") Nutzen nutzenForm,
			RedirectAttributes redirectAttributes, ModelMap model) {

		nutzenService.updateNutzen(nutzenForm);

		redirectAttributes.addFlashAttribute("message",
				"Nutzen wurde erfolgreich geändert.");
		return "redirect:" + url;
	}

	@RequestMapping(value = "/nutzen/ansehen-nutzen", method = RequestMethod.GET)
	public String ansichtNutzen(@RequestParam long id, ModelMap model,
			Authentication authentication) {

		Optional<Nutzen> nutzen = nutzenRepository.findById(id);

		model.put("nutzenForm", nutzen.get());

		return "/nutzen/ansichtNutzen";
	}

	@RequestMapping(value = "/nutzen/copy-nutzen", method = RequestMethod.GET)
	public String copyNutzen(@RequestParam long id, ModelMap model,
			Authentication authentication) {

		User userInfo = userService.findByUsername(authentication.getName());
		List<Kunde> kundeList = kundeRepository.findAll();
		List<Taetigkeit> taetigkeitList = taetigkeitRepository.findAll();
		List<Ort> ortList = ortRepository.findAll();
		Optional<Nutzen> nutzen = nutzenRepository.findById(id);

		model.put("ortList", ortList);
		model.put("taetigkeitList", taetigkeitList);
		model.put("kundeList", kundeList);
		model.put("userId", userInfo.getId());
		model.put("nutzenForm", nutzen.get());
		model.put("newNutzenForm", new Nutzen());
		return "/nutzen/copyNutzen";
	}

	@RequestMapping(value = "/nutzen/copy-nutzen", method = RequestMethod.POST)
	public String copyNutzen(@ModelAttribute("nutzenForm") Nutzen nutzenForm,
			RedirectAttributes redirectAttributes) {

		Nutzen nutzen = new Nutzen(nutzenForm.getDatum(), nutzenForm.getUser(),
				nutzenForm.getKunde(), nutzenForm.getOrt(),
				nutzenForm.getTaetigkeit(), nutzenForm.getDetails(),
				nutzenForm.getSondernleistung(), nutzenForm.getStunde(),
				nutzenForm.isFreigabe());

		nutzenService.save(nutzen);
		redirectAttributes.addFlashAttribute("message",
				"Datensatz wurde erfolgreich gespeichert.");

		return "redirect:" + url;
	}

	@RequestMapping(value = "/nutzen/delete-nutzen", method = RequestMethod.GET)
	public String deleteNutzen(@RequestParam long id,
			RedirectAttributes redirectAttributes) {

		if (nutzenService.deleteNutzen(id)) {
			redirectAttributes.addFlashAttribute("message",
					"Datensatz wurde gelöscht.");

		} else {
			redirectAttributes.addFlashAttribute("message",
					"Ein Fehler ist aufgetreten!");

		}

		return "redirect:" + url;
	}

	@RequestMapping(value = "/nutzen/delete-nutzen", method = RequestMethod.POST)
	public String deleteNutzens(
			@RequestParam(name = "nutzenIds", required = false) long[] nutzenIds,
			RedirectAttributes redirectAttributes) {

		if (nutzenIds == null || !nutzenService.deleteMehrereNutzen(nutzenIds)) {

			redirectAttributes.addFlashAttribute("message",
					"Ein Fehler ist aufgetreten!");
		} else {
			redirectAttributes.addFlashAttribute("message",
					"Datensätze wurden gelöscht.");

		}

		return "redirect:" + url;
	}

}
