package com.inn.cafe.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inn.cafe.POJO.Bill;


@RequestMapping(path = "/bill")
public interface BillRest {

	@PostMapping(path = "/generateReport")
	ResponseEntity<String> generateReport(@RequestBody(required = true) Map<String, Object> requestMap);

	@GetMapping(path = "/getBills")
	ResponseEntity<List<Bill>> getBills(); 
	
	@PostMapping(path = "/getPDF")
	ResponseEntity<byte[]> getPDf(@RequestBody Map<String, Object> requestMap);

	@PostMapping(path = "/delete/{id}")
	ResponseEntity<String> deleteBill(@PathVariable Integer id);
}
