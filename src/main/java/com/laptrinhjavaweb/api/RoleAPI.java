package com.laptrinhjavaweb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laptrinhjavaweb.Service.RoleService;
import com.laptrinhjavaweb.Service.impl.RoleServiceIMPL;
import com.laptrinhjavaweb.dto.RoleDTO;

@Controller
public class RoleAPI {
	
	private RoleService roleservice = new RoleServiceIMPL();
	@GetMapping("/role") 
	public RoleDTO getRole(@RequestParam long id) {
		return roleservice.findById(id);
	}
	

}
