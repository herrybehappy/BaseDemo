package com.ie.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ActivitiTest {
	ProcessEngineConfiguration configuration=null;
	ProcessEngine processEngine = null;
	{
		configuration=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti_test.cfg.xml");
		processEngine=configuration.buildProcessEngine();
	}
	
	@Test
	public void createTable(){
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/basedemo?useUnicode=true&characterEncoding=utf8");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("");
	
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println("processEngine:"+processEngine);
	}

	@Test
	public void deploymentProcessDefinition(){
		Deployment deployment = processEngine.getRepositoryService()
						.createDeployment()
						.name("exam")
						.addClasspathResource("diagrams/exam.bpmn")
						.addClasspathResource("diagrams/exam.png")
						.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
	}

	@Test
	public void startProcessInstance(){

		String processDefinitionKey = "exam";
		ProcessInstance pi = processEngine.getRuntimeService()
						.startProcessInstanceByKey(processDefinitionKey);
		System.out.println("ID:"+pi.getId());
		System.out.println("ID:"+pi.getProcessDefinitionId());
	}

}
