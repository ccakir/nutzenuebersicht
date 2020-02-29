package com.cakirilhan.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cakirilhan.domain.kunde.DatabaseDatei;
import com.cakirilhan.domain.kunde.Vertrag;
import com.cakirilhan.domain.kunde.repository.DatabaseDateiRepository;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.repository.VertragRepository;
import com.cakirilhan.domain.kunde.repository.VorOrtBetreuungRepository;
import com.cakirilhan.domain.kunde.repository.service.DatabaseDateiService;
import com.cakirilhan.domain.kunde.repository.service.VertragService;
import com.cakirilhan.domain.kunde.repository.service.VortOrtBetreuungService;
import com.cakirilhan.domain.kunde.service.impl.FileStorageService;
import com.cakirilhan.domain.user.service.UserRepositoryService;
import com.cakirilhan.exception.ResourceNotFoundException;
import com.cakirilhan.kunde.validator.DatabaseDateiValidator;
import com.cakirilhan.kunde.validator.VertragValidator;

@RestController
@RequestMapping("/vertrag")
public class VertragRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(Vertrag.class);

	@Autowired
	private DatabaseDateiRepository databaseDateiRepository;

	@Autowired
	private UserRepositoryService userRepositoryService;

	@Autowired
	private VertragRepository vertragRepository;

	@Autowired
	private VertragService vertragService;

	@Autowired
	private KundeRepository kundeRepository;

	@Autowired
	private OrtRepository ortRepository;

	@Autowired
	private VorOrtBetreuungRepository vorOrtBetreuungRepository;

	@Autowired
	private VortOrtBetreuungService vortOrtBetreuungService;

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private VertragValidator vertragValidator;
	
	@Autowired
	private DatabaseDateiService databaseDateiService;
	
	@Autowired
	private DatabaseDateiValidator databaseDateiValidator;

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/list-vertrag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView getAllVertrag() {
		
		logger.debug("adadadasdasdasda");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/vertrag/listVertrag");
		mav.addObject("listVertrag", vertragService.findAllOrderById());
		mav.addObject("listFile", databaseDateiRepository.findAll());
		return mav;
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/get-vertrag/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Vertrag> getVertrag(
			@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {

		Vertrag vertrag = vertragRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						"Vetrag für diese Id nicht gefunden : " + id));

		return ResponseEntity.ok().body(vertrag);

	}

	@PostMapping(value = "/add-vertrag", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView addVertrag(
			@ModelAttribute("vertragForm") Vertrag vertragDetails,
			@RequestParam("file") MultipartFile[] file,
			RedirectAttributes redirectAttributes,
			BindingResult bindingResult) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		LocalDate localDate = LocalDate.now();

		int fileSize = file.length;
		for (int i = 0; i < fileSize; i++) 
		{

			if (!file[i].getOriginalFilename().equals("")) {
				DatabaseDatei dbFile1 = new DatabaseDatei();
				
				dbFile1.setFileName(StringUtils.cleanPath(file[i]
						.getOriginalFilename()));
				
				databaseDateiValidator.validate(dbFile1, bindingResult);
				
			}
		}
		
		vertragValidator.validate(vertragDetails, bindingResult);
		
		
		
		if(bindingResult.hasErrors()) {
			
			mav.setViewName("vertrag/addVertrag");
			mav.addObject("allKunden", kundeRepository.findAll());
			mav.addObject("allOrte", ortRepository.findAll());
			mav.addObject("allVorOB",
					vortOrtBetreuungService.findAllDistinctLocationWhereCustomer());
			return mav;
		}
		

		
		
		try {

			vertragRepository.save(vertragDetails);
			redirectAttributes.addFlashAttribute("message",
					"message.successfull.add");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message",
					"message.error.delete");
			e.printStackTrace();
		}
		

		for (int i = 0; i < fileSize; i++) {

			if (!file[i].getOriginalFilename().equals("")) {

				if (fileStorageService.storeFile(file[i])) {

					DatabaseDatei dbFile = new DatabaseDatei();
					dbFile.setData(file[i].getBytes());
					dbFile.setDatum(localDate);
					dbFile.setFileName(StringUtils.cleanPath(file[i]
							.getOriginalFilename()));
					dbFile.setFileType(file[i].getContentType());
					dbFile.setVertrag(vertragDetails);

					
					databaseDateiRepository.save(dbFile);
				}

			}

		}

		
		mav.setViewName("redirect:/vertrag/list-vertrag");
		return mav;

	}

	@GetMapping("/add-vertrag")
	public ModelAndView addVertrag() {

		Map<String, Object> map = new HashMap<>();
		map.put("vertragForm", new Vertrag());
		
		map.put("allKunden", kundeRepository.findAll());
		map.put("allOrte", ortRepository.findAll());
		map.put("allVorOB",
				vortOrtBetreuungService.findAllDistinctLocationWhereCustomer());
		map.put("listFileNames", databaseDateiService.getAllFileName());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("vertrag/addVertrag");
		mav.addAllObjects(map);

		return mav;
	}

	@GetMapping(value = "/delete-vertrag/{id}")
	public ModelAndView deleteVertrag(@PathVariable("id") long id)
			throws ResourceNotFoundException {

		Optional<Vertrag> vertrag = vertragRepository.findById(id);

		System.out.println("Vertrag   :" + vertrag);

		List<DatabaseDatei> listDatei = databaseDateiRepository
				.findByVertrag(vertrag.get());

		System.out.println("Datei : " + listDatei);

		if (vertrag.isPresent()) {

			int listSize = listDatei.size();
			for (int i = 0; i < listSize; i++) {

				fileStorageService.deleteFile(listDatei.get(i).getFileName());
				databaseDateiRepository.delete(listDatei.get(i));
			}

		}

		vertragRepository.delete(vertrag.get());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/vertrag/list-vertrag");

		return mav;

	}

	@PostMapping(value = "/delete-vertrags/")
	public ModelAndView deleteVertrags(
			@RequestParam(value = "listIds") long[] listIds,
			RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/vertrag/list-vertrag");
		int ids_lengt = listIds.length;

		for (int i = 0; i < ids_lengt; i++) {

			Optional<Vertrag> vertrag = vertragRepository.findById(listIds[i]);

			List<DatabaseDatei> databaseDatei = databaseDateiRepository
					.findByVertrag(vertrag.get());

			for (int k = 0; k < databaseDatei.size(); k++) {

				fileStorageService.deleteFile(databaseDatei.get(k)
						.getFileName());
				databaseDateiRepository.delete(databaseDatei.get(k));
			}

		}
		return mav;

	}

	@GetMapping(value = "/update-vertrag/{id}")
	public ModelAndView updateVertrag(@PathVariable(value = "id") long id) {

		Vertrag vertrag = vertragRepository.findById(id).get();
		List<DatabaseDatei> databaseDatei = databaseDateiRepository
				.findByVertrag(vertrag);

		Map<String, Object> map = new HashMap<>();

		map.put("vertragForm", vertrag);
		map.put("listDatei", databaseDatei);

		map.put("listLocation", ortRepository.findAll());
		map.put("listVorOB",
				vortOrtBetreuungService.findAllDistinctLocationWhereCustomer());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("vertrag/updateVertrag");
		mav.addAllObjects(map);

		return mav;

	}

	@PostMapping(value = "/update-vertrag/{id}")
	public ModelAndView updateVertrag(@PathVariable(value = "id") long id,
			@ModelAttribute("vertragForm") Vertrag vertragDetails,
			@RequestParam("file") MultipartFile[] file,
			RedirectAttributes redirectAttributes, BindingResult bindingResult)
			throws ResourceNotFoundException, IOException {

		LocalDate localDate = LocalDate.now();
		ModelAndView mav = new ModelAndView();
		
		Vertrag vertrag = vertragRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User nt found :: " + id));

		if (bindingResult.hasErrors()) {

			redirectAttributes.addFlashAttribute("message",
					"message.successfull.update");
			mav.setViewName("redirect:/vertrag/list-vertrag");
			return mav;
		}

		int fileSize = file.length;
		

		for (int i = 0; i < fileSize; i++) {
		  
		  if (!file[i].getOriginalFilename().equals("")) { 
			  
			  fileStorageService.storeFile(file[i]);
			  
			  DatabaseDatei dbFile = new DatabaseDatei();
				dbFile.setData(file[i].getBytes());
				dbFile.setDatum(localDate);
				dbFile.setFileName(StringUtils.cleanPath(file[i]
						.getOriginalFilename()));
				dbFile.setFileType(file[i].getContentType());
				dbFile.setVertrag(vertrag);

				databaseDateiRepository.save(dbFile);
		  }
		  
		  
		 
		  }
		 

		

		
		vertrag.setInhalt(vertragDetails.getInhalt());
		vertrag.setKunde(vertragDetails.getKunde());
		vertrag.setOrt(vertragDetails.getOrt());

		vertragRepository.save(vertrag);

		redirectAttributes.addFlashAttribute("message",
				"message.successfull.update");
		mav.setViewName("redirect:/vertrag/list-vertrag");

		return mav;
	}

	@GetMapping(value = "/delete-file/{id}/{fileId}")
	public ModelAndView deleteFile(@PathVariable("fileId") long fileId,
			@PathVariable("id") long id) throws ResourceNotFoundException {

		ModelAndView mav = new ModelAndView();

		Optional<DatabaseDatei> datei = databaseDateiRepository.findById(fileId);
				

		if (datei.isPresent()) {

			fileStorageService.deleteFile(datei.get().getFileName());
			databaseDateiRepository.delete(datei.get());

		}

		mav.setViewName("redirect:/vertrag/update-vertrag/" + id);
		return mav;
	}
	@GetMapping("/downloadFile/{fileName}")
	public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        
		Resource resource = fileStorageService.loadFile(fileName);

        
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
           ex.printStackTrace();
        }

        
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

}
