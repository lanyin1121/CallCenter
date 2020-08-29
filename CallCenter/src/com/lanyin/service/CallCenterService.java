package com.lanyin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lanyin.employee.Employee;
import com.lanyin.employee.Fresher;
import com.lanyin.employee.ProductManager;
import com.lanyin.employee.TechnicalLead;

public class CallCenterService {
	
	private List<Employee> fresherList = new ArrayList<Employee>();
	
	private List<Employee> technicalLeadList = new ArrayList<Employee>();
	
	private List<Employee> productManagerList = new ArrayList<Employee>();
	
	public CallCenterService() {
		this.loadEmployee();
		
	}
	
	private void loadEmployee() {
		for(int i = 1; i <= 3; i++) {
			Employee fresher = new Fresher();
			fresher.setName("fresher_"+i);
			fresherList.add(fresher);
		}
		
		Employee technicalLead = new TechnicalLead();
		technicalLead.setName("technicalLead");
		technicalLeadList.add(technicalLead);
		
		Employee productManager = new ProductManager();
		productManager.setName("productManager");
		productManagerList.add(productManager);
		
	}
	
	public void process() {
		Employee e = this.getFreeEmployee();
		Employee handleEmployee = this.getHandleEmployee(e);
		handleEmployee.process();
	}
	
	private Employee getHandleEmployee(Employee e) {
		boolean ishandle = e.ishandle();
		System.out.println(Thread.currentThread().getName()+" "+e.getName()+" can handle "+ishandle);
		if(ishandle) {
			return e;
		}else {
			e.setFree(true);
			Employee nextEmployee = this.getNextLevelEmployee(e.getClass());
			return this.getHandleEmployee(nextEmployee);
		}
	}
	
	private Employee getFreeEmployee() {
		return this.getFreeEmployee(Arrays.asList(fresherList, technicalLeadList, productManagerList));
	}
	
	private synchronized Employee getFreeEmployee(List<List<Employee>> dataList) {
		for(List<Employee> list : dataList) {
			for(Employee e : list) {
				System.out.println(Thread.currentThread().getName()+" "+e.getName()+" is Free "+e.isFree());
				if(e.isFree()) {
					e.setFree(false);
					return e;
				}
			}
		}
		try {
			Thread.sleep(50L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.getFreeEmployee(dataList);
		
	}
	
	private Employee getNextLevelEmployee(Class<? extends Employee> c) {
		if(c.equals(Fresher.class) ) {
			return this.getFreeEmployee(Arrays.asList(technicalLeadList, productManagerList));
		}else {
			return this.getFreeEmployee(Arrays.asList(productManagerList));
		}
	}
	
}
