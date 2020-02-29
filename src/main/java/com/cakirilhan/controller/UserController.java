package com.cakirilhan.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.repository.service.OrtService;
import com.cakirilhan.domain.user.User;
import com.cakirilhan.domain.user.mapper.UserMapperImpl;
import com.cakirilhan.domain.user.service.SecurityService;
import com.cakirilhan.domain.user.service.UserRepositoryService;
import com.cakirilhan.user.repository.UserRepositiory;
import com.cakirilhan.user.validator.PasswordMatchesValidator;
import com.cakirilhan.user.validator.UserValidator;
import com.cakirilhan.web.dto.PasswordDto;
import com.cakirilhan.web.dto.UserDto;



@Controller
public class UserController {
    @Autowired
    private UserRepositoryService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private UserRepositiory userRepositiory;
    
    @Autowired
    UserMapperImpl userMapper;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private OrtService ortService;
    
    @Autowired
    private OrtRepository ortRepository;
    
   @Autowired
   private PasswordMatchesValidator passwordMatchesValidator;
   
   
    @GetMapping("/user/addUser")
    public String addUser(ModelMap model) {
    	
    	model.put("userForm", new User());
    	model.put("locationList", ortRepository.findAll());
    	
    return "user/addUser";	
    }
    
    @PostMapping("/user/addUser")
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes) {
    	
    	UserDto userDto = userMapper.toUserDto(userForm);
    	userValidator.validate(userDto, bindingResult);
    	
    	if(bindingResult.hasErrors()) {
    		model.put("locationList", ortRepository.findAll());
    		model.put("selectedLocation", userDto.getOrt());
    		model.put("message", "message.error.delete");
    		return "/user/addUser";
    	}
    	
    	User user = userMapper.toUser(userDto);
    	userService.save(user);
    	redirectAttributes.addFlashAttribute("message", "message.successfull.add");
    	
		return "redirect:/user/list-user";
    }

    @GetMapping("/registration")
    public String registration(ModelMap model) {
        model.put("userForm", new User());
        List<Ort> orts = ortRepository.findAll();
        model.put("ortList", orts);
    	
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    	
    	UserDto userDto = userMapper.toUserDto(userForm);
    	userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
        	 List<Ort> ort = ortRepository.findAll();
         	model.addAttribute("ortList", ort);
         	model.addAttribute("ortId", userDto.getOrt());
            return "user/registration";
        }
        User user = userMapper.toUser(userDto);
        userService.save(user);

       //securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/user/list-user";
    }

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Username und Password sind ungültig.");

        if (logout != null)
            model.addAttribute("message", "Sie haben sich erfolgreich ausgeloggt!");

        return "user/login";
    }
    
    @RequestMapping(value = "/user/list-user" , method = RequestMethod.GET)
    public String UserList(ModelMap model) {
    	
    	model.put("users", userRepositiory.findAll());
    	
		return "user/listUsers";
    	
    }
    
    @RequestMapping(value = "/user/delete-user" , method = RequestMethod.GET)
    public String deleteUser(@RequestParam long id, RedirectAttributes redirectAttributes) {
    	
    	userService.deleteUser(id);
    	redirectAttributes.addFlashAttribute("message", "message.successfull.deleteOne");
    	
		return "redirect:/user/list-user";
    }
    
    @RequestMapping(value = "user/delete-users" , method = RequestMethod.POST)
	public String deleteKunde(@RequestParam(value="listUserIds", required = false) long[] listUserIds, RedirectAttributes redirectAttributes){
		
		if(listUserIds != null && userService.deleteUsers(listUserIds)) {
			redirectAttributes.addFlashAttribute("message", "message.successfull.delete");
		} else {
			redirectAttributes.addFlashAttribute("message", "message.error.delete");
		}
		
		return "redirect:/user/list-user";
	}
    
    @RequestMapping(value = "user/update-user" , method = RequestMethod.GET)
    public String  showUpdateUser(@RequestParam long id, ModelMap model) {
    	
    	Optional<User> user = userRepositiory.findById(id);
    	List<Ort> ort = ortRepository.findAll();
    	
    	model.put("ortList", ort);
    	model.put("userForm", user);
    	model.put("ortId", user.get().getOrt());
    	model.put("formRole", user.get().getRole());
    	
    	return "user/updateUser";
    }
    
    @RequestMapping(value = "user/update-user" , method = RequestMethod.POST)
    public String updateUser(ModelMap model, @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    	
    	UserDto userDto = userMapper.toUserDto(userForm);
    	userValidator.validate(userDto, bindingResult);
    	if(bindingResult.hasErrors()) {
    		
    		return "user/updateUser";
    		    		
    	}
    	User user = userMapper.toUser(userDto);
    	userService.updateUser(user);
    	
    	return "redirect:/user/list-user";
    }
    
    @RequestMapping(value ="/user/mein-konto" , method = RequestMethod.GET)
    public String meinKonto(ModelMap model, Authentication authentication) {
    	
    	
    	User userInfo = userService.findByUsername(authentication.getName());
    	
    	model.put("userInfo", userInfo);
    	
    	return "user/meinKonto";
    }
    
    @RequestMapping(value="/user/password-change", method = RequestMethod.GET)
    public String passwordChange(ModelMap model) {
    	
    	model.put("kennwortChange", new PasswordDto());
    	
    	return "user/kennwort";
    }
    
    @RequestMapping(value="/user/password-change", method = RequestMethod.POST)
    public String passwordChange(ModelMap model, @ModelAttribute("kennwortChange") PasswordDto kennwortChange, BindingResult bindingResult, Principal principal){
    	
    	passwordMatchesValidator.validate(kennwortChange, bindingResult);
    	
    	if(bindingResult.hasErrors()) {
    		
    		return "user/kennwort";
    	}
    	
    	User newUser = userService.findByUsername(principal.getName());
    	
    	String newPassword = bCryptPasswordEncoder.encode(kennwortChange.getNewPassword());
    	newUser.setPassword(newPassword);
    	userService.updatePassword(newUser);
    	
    	
    	return "redirect:/user/mein-konto";
    	
    }
    
  

    
    
   
    
}