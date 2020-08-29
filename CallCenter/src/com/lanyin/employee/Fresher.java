package com.lanyin.employee;

import java.util.Random;

public class Fresher extends Employee {

	@Override
	public boolean ishandle() {
		return new Random().nextBoolean();
	}

}
