package org.amdatu.examples.greeter.impl;

import org.amdatu.examples.greeter.Greeter;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Start;
import org.apache.felix.dm.annotation.api.Stop;

@Component
public class GreeterImpl implements Greeter {

	@Override
	public String sayHello() {
		return "Hello Amdatu!";
	}
	
	@Start
	public void start() {
		System.out.println("GreeterImpl.start()");
	}
	
	@Stop
	public void stop() {
		System.out.println("GreeterImpl.stop()");
	}
	
}
