package com.lanyin.employee;

import java.util.Random;

public abstract class Employee {
	
	private String name;
	
	private boolean free = true;
	
	/**
	 * 
	 * @return boolean
	 */
	public abstract boolean ishandle();
	
	/**
	 * 
	 */
	public void process() {
		System.out.println(Thread.currentThread().getName()+" "+this.getName()+" processing");
		try {
			Thread.sleep(new Random().nextInt(150));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.setFree(true);
		System.out.println(Thread.currentThread().getName()+" "+this.getName()+" process done");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

}
