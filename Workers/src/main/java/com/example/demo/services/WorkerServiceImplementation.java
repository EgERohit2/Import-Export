package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Workers;
import com.example.demo.repository.WorkerRepository;

@Service
public class WorkerServiceImplementation implements WorkerService{

	@Autowired
	private WorkerRepository workerRepository;
	
	@Override
	public List<Workers> getAllData() {
		return null;
	}

	@Override
	public Workers postAllData(Workers workers) {
		return workerRepository.save(workers);
		
	}


	
	
}
