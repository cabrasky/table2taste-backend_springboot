package net.cabrasky.table2taste.backend.controller;

import net.cabrasky.table2taste.backend.model.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/services")
public class ServiceController{
	
	@GetMapping
	@PreAuthorize("hasAuthority('PLACE_ORDER')")
	public ResponseEntity<Service> get() {
		return null;
	}

}
