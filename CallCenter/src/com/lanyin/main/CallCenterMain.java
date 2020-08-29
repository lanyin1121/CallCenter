package com.lanyin.main;

import com.lanyin.service.CallCenterService;

public class CallCenterMain {

	public static void main(String[] args) {
		CallCenterService service = new CallCenterService();
		
		for(int i = 1; i <= 10; i++) {
			Thread t = new TestThread(service, "Custom_"+i);
			t.start();
		}
	}

	
	
}

class TestThread extends  Thread {
	
	private CallCenterService service;
	
	TestThread(CallCenterService service, String name){
		this.service = service;
		this.setName(name);
	}
	
	public void run() {
		System.out.println(this.getName()+" incoming");
		service.process();
	}
}
