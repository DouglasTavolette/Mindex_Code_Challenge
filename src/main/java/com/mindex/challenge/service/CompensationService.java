package com.mindex.challenge.service;

import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Compensation;

//This is my CompensationService interface that will be implemented to be used in the API
@Service
public interface CompensationService {
    Compensation create(Compensation comp);
    Compensation read(String empID);
} 