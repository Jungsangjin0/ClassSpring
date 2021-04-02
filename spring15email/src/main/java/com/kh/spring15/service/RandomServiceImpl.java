package com.kh.spring15.service;

import java.text.Format;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl implements RandomService{

	@Autowired
	private Random rand;
	
	@Autowired
	private Format fmt;
	
	@Override
	public String generateNumber() {
		int n = rand.nextInt(1000000);
		return fmt.format(n);
	}
	
}
