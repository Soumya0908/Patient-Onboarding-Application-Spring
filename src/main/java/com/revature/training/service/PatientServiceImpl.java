package com.revature.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.training.model.Patient;
import com.revature.training.repository.PatientRepository;




@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	
	
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public boolean addPatient(Patient patient) {
		patientRepository.save(patient);
		return true;
	}
	
	
	@Transactional
	@Override
	public boolean deletePatient(int patientId) {
		 patientRepository.deleteById(patientId);
		return false;
	}


	@Transactional(propagation = Propagation.NESTED)
	@Override
	public boolean updatePatientDetails(Patient patient) {
		patientRepository.save(patient);
		return true;
	}

	

	@Override
	public boolean patientLogin(int patientId, String patientPassword) {
		
		Optional<Patient> patientData = patientRepository.findByPatientIdAndPassword(patientId, patientPassword);
		return patientData.isPresent();
	}
	
	
	
	@Transactional(readOnly = true)
	@Override
	public Patient getPatientById(long patientId) {
		
		Optional<Patient> patientData = patientRepository.findById((int) patientId);
		Patient patient = patientData.get();
		return patient;
	}


	@Override
	public List<Patient> getAllPatients() {
		
		return (List<Patient>) patientRepository.findAll();
	}


	@Override
	public boolean isPatientExists(long patientId) {
		Optional<Patient> patientData = patientRepository.findById((int) patientId);
		return patientData.isPresent();

	}



}