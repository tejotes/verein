package org.amdatu.examples.greeter.tester;

import java.util.concurrent.TimeUnit;

import org.amdatu.examples.greeter.Greeter;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.apache.felix.dm.annotation.api.Start;

public class GreeterTester {
	
	@ServiceDependency
	private volatile Greeter m_greeter;
	
	private volatile Thread m_printer;
	
	@Start
	public void activate() {
		System.out.println("GreeterTester.start()");
		System.out.println("hello: "+m_greeter.sayHello());
		
		m_printer = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					System.out.println("Greeting: "+m_greeter.sayHello());
				}

				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				
			}
		});

		m_printer.start();
	}
	
	
	

}
