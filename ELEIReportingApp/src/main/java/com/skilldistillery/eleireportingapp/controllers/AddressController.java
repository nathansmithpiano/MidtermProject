package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.AddressDAO;
import com.skilldistillery.eleireportingapp.entities.Address;

@Controller
public class AddressController {
	
	@Autowired
	private AddressDAO addressDao;
	
	@RequestMapping(path = {"addresses.do" } )
	public String addresses(Model model) {
		model.addAttribute("addressList", addressDao.findAll());
		return "addresses";
	}
	
	@RequestMapping(path = {"address.do" })
	public String address(Model model, @RequestParam("id") int id) {
		Address address = addressDao.findById(id);
		model.addAttribute("address", address);
		return "address";
	}

}
