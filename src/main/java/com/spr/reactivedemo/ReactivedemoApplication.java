package com.spr.reactivedemo;

import com.spr.reactivedemo.module.AgentDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactivedemoApplication {

	public static void main(String[] args) {

		AgentDTO agentDTO = new AgentDTO();
		agentDTO.setAgentCounter(10);
		System.out.println(agentDTO.getAgentName());

		SpringApplication.run(ReactivedemoApplication.class, args);
	}

}
