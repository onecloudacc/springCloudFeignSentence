package com.learn.springCloud.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rajes
 * date    Apr 18, 201712:38:33 AM
 *
 */
@FeignClient("EUREKACLIENT2VERB")
public interface verbClient {

	@RequestMapping(method=RequestMethod.GET,value="/testFeign")
	 public String callVerbApp();
	
	@RequestMapping(method=RequestMethod.GET,value="/")
	 public String callVerbApp2();
	 
	
}
