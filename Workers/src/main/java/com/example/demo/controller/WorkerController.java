package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Workers;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.services.WorkerServiceImplementation;
import com.example.demo.util.ExcelGenerator;
import com.example.demo.util.ExcelImport;

@RestController
@RequestMapping("/worker")
public class WorkerController {

	@Autowired
	private WorkerServiceImplementation workerServiceImplementation;


	@Autowired
	private WorkerRepository repo;

	@GetMapping("/getExcel")
	public String exportExcel(HttpServletResponse response)throws IOException{
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=Employee.xlsx";
		response.setHeader(headerKey, headerValue);
		List<Workers> employees=repo.findAll();
		ExcelGenerator excelGenerator=new ExcelGenerator(employees);
		excelGenerator.generateExcelFile(response);
		return "coverted successfuly";
	}
	
	@PostMapping()
	public String postData(@RequestBody Workers workers)  {
		workerServiceImplementation.postAllData(workers);
		return "posted";
	}
	
	@PostMapping("/addExcel")
	public String importExcel() throws FileNotFoundException {
		ExcelImport importE =new ExcelImport();
		List<Workers> workers=importE.ExcelImport();
		repo.saveAll(workers);
		return "done";
		
	}
}
