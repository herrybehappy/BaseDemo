package com.ie.test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ActivitiTest {
	ProcessEngineConfiguration configuration=null;
	ProcessEngine processEngine = null;
	{
		configuration=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti_test.cfg.xml");
		processEngine=configuration.buildProcessEngine();
	}
	/**ʹ�ô��봴����������Ҫ��23�ű�*/
	@Test
	public void createTable(){
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		//�������ݿ������
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useUnicode=true&characterEncoding=utf8");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("admin");
		
		/**
		 	public static final String DB_SCHEMA_UPDATE_FALSE = "false";�����Զ���������Ҫ�����
  			public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";��ɾ�����ٴ�����
  			public static final String DB_SCHEMA_UPDATE_TRUE = "true";��������ڣ��Զ�������
		 */
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//�������ĺ��Ķ���ProcessEnginee����
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println("processEngine:"+processEngine);
	}
	//ʹ�������ļ�����23�ű�
	@Test
	public void createTable_2() {
		ProcessEngineConfiguration configuration=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		ProcessEngine engine=configuration.buildProcessEngine();
		System.out.println(engine);
	}

	/**�������̶���*/
	@Test
	public void deploymentProcessDefinition(){
		Deployment deployment = processEngine.getRepositoryService()//�����̶���Ͳ��������ص�Service
						.createDeployment()//����һ���������
						.name("helloworld���ų���")//��Ӳ��������
						.addClasspathResource("diagrams/helloworld.bpmn")//��classpath����Դ�м��أ�һ��ֻ�ܼ���һ���ļ�
						.addClasspathResource("diagrams/helloworld.png")//��classpath����Դ�м��أ�һ��ֻ�ܼ���һ���ļ�
						.deploy();//��ɲ���
		System.out.println("����ID��"+deployment.getId());//1
		System.out.println("�������ƣ�"+deployment.getName());//helloworld���ų���  
	}
	/**��������ʵ��*/
	@Test
	public void startProcessInstance(){
		//���̶����key
		String processDefinitionKey = "helloworld";
		ProcessInstance pi = processEngine.getRuntimeService()//������ִ�е�����ʵ����ִ�ж�����ص�Service
						.startProcessInstanceByKey(processDefinitionKey);//ʹ�����̶����key��������ʵ����key��Ӧhelloworld.bpmn�ļ���id������ֵ��ʹ��keyֵ������Ĭ���ǰ������°汾�����̶�������
		System.out.println("����ʵ��ID:"+pi.getId());//����ʵ��ID    101
		System.out.println("���̶���ID:"+pi.getProcessDefinitionId());//���̶���ID   helloworld:1:4
	}
	/**��ѯ��ǰ�˵ĸ�������*/
	@Test
	public void findMyPersonalTask(){
		String assignee = "����";
		List<Task> list = processEngine.getTaskService()//������ִ�е����������ص�Service
						.createTaskQuery()//���������ѯ����
						.taskAssignee(assignee)//ָ�����������ѯ��ָ��������
						.list();
		if(list!=null && list.size()>0){
			for(Task task:list){
				System.out.println("����ID:"+task.getId());
				System.out.println("��������:"+task.getName());
				System.out.println("����Ĵ���ʱ��:"+task.getCreateTime());
				System.out.println("����İ�����:"+task.getAssignee());
				System.out.println("����ʵ��ID��"+task.getProcessInstanceId());
				System.out.println("ִ�ж���ID:"+task.getExecutionId());
				System.out.println("���̶���ID:"+task.getProcessDefinitionId());
				System.out.println("########################################################");
			}
		}
	}
	/**����ҵ�����*/
	@Test
	public void completeMyPersonalTask(){
		//����ID
		String taskId = "7502";
		processEngine.getTaskService()//������ִ�е����������ص�Service
					.complete(taskId);
		System.out.println("�����������ID��"+taskId);
	}

}
