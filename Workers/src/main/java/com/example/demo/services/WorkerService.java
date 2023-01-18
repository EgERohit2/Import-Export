package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Workers;

public interface WorkerService {

	public List<Workers> getAllData();
	
	public Workers postAllData(Workers workers);
}
