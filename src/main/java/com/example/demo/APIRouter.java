package com.example.demo;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import Controller.CustomerServices;
import model.Customer;

@CrossOrigin
@RestController
@RequestMapping("api")
public class APIRouter {

	@PostMapping("customer/insert")
	String createCutsomer(@RequestParam(value = "custName", required = true) String custName,
			@RequestParam(value = "custProfession", required = true) String custProfession) throws SQLException {

		return CustomerServices.createCust(custName, custProfession);
	}

	@PostMapping("customer/update")
	String updateCutsomer(@RequestParam(value = "custId", required = true) String custId,
			@RequestParam(value = "custName", required = false, defaultValue = "") String custName,
			@RequestParam(value = "custProfession", required = false, defaultValue = "") String custProfession)
			throws SQLException {
		return CustomerServices.updateCutsomer(custId, custName, custProfession);
	}

	@PostMapping("/customer/getAll")
	String getCustomer(@RequestParam(value = "custName", required = true) String custName) {
		return CustomerServices.showCust(custName);
	}

	@PostMapping("/customer/delete")
	String deleteCustomer(@RequestParam(value = "custId", required = true) String custId) throws SQLException {
		
		return CustomerServices.deleteCustomer(custId);
	}

}
