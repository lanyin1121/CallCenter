package com.lanyin.employee;

import java.util.Random;

public class TechnicalLead extends Employee {

	@Override
	public boolean ishandle() {
		return new Random().nextBoolean();
	}

}
