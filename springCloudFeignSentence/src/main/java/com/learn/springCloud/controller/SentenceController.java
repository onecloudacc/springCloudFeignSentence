package com.learn.springCloud.controller;

import java.net.URI;
import java.util.List;

import org.assertj.core.api.UriAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.springCloud.client.AdjectiveClient;
import com.learn.springCloud.client.SubjectClient;
import com.learn.springCloud.client.TopicClient;
import com.learn.springCloud.client.verbClient;
import com.learn.springCloud.model.Topic;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author rajes
 * date    Apr 7, 20171:27:33 AM
 *
 */
@RestController
@Component
public class SentenceController {
	private static final Logger log = LoggerFactory.getLogger(SentenceController.class);

@Autowired
 private verbClient verb;
@Autowired
private SubjectClient subject;

@Autowired
 AdjectiveClient adjective;

@Autowired
 private TopicClient topicClient;


	 @RequestMapping("/sentence")
	  public  void getSentence() {
	    verb.callVerbApp();
	  }
	 
	 
	 @RequestMapping("/sentences")
	  public @ResponseBody String getSentences() {
	return 	getVerb() + "  "+ getSubject()  + "  " +getAdjective() ;
	  }
	 
	 /**
	 * @return
	 */
	 @HystrixCommand(fallbackMethod="getFallBackVerb")
	public String getVerb() {
		// TODO Auto-generated method stub
		return 	verb.callVerbApp2();
	}


	/**
	 * @return
	 */
	 @HystrixCommand(fallbackMethod="getFallBackSubject")
	 public String getSubject() {
		// TODO Auto-generated method stub
		return 	subject.callSubjectApp();
	}


	/**
	 * @return
	 */
	 
	 @HystrixCommand(fallbackMethod="getFallBackAdjective")
	 public String getAdjective() {
	  return adjective.callAdjectiveApp();
	}


	
	//Fall back methods
	
	 public String getFallBackVerb() {
		// TODO Auto-generated method stub
		return "fallbackVerb";
	}
	
	
	 public String getFallBackSubject() {
		// TODO Auto-generated method stub
		return "fallbackSubject";
	}
	
	
	 public String getFallBackAdjective() {
		// TODO Auto-generated method stub
		return "fallbackAdjective";
	}
	
	
	@RequestMapping("/")
	  public  void getSentence2() {
	    verb.callVerbApp2();
	  }
	 
	 @RequestMapping("/getTopics")
	  public void getTopics() {
		Topic topicConsumed= topicClient.callTopicService();
		log.info("id= "+topicConsumed.getId()+"\n name= "+topicConsumed.getName()+ "\n description= "+topicConsumed.getDescription());
	  }
	 
	 
}
