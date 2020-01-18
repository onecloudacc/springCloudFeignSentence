package com.learn.springCloud.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learn.springCloud.model.Topic;

/**
 * @author rajes
 * date    Apr 24, 20172:02:23 AM
*
 */
@FeignClient(name="tst", url="${SpringCloudFeignSentence.ribbon.listOfServers}")
public interface TopicClient {
	@RequestMapping(method=RequestMethod.GET,value="/staticTopicss")
	public Topic callTopicService();
	

}
