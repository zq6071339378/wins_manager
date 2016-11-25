/**
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * Copyright 2005-2016 DataComo Communications Technology INC.
 * 
 * This source file is a part of DSP_V1.0  project. 
 * date: 2016-02-25
 *
 */
package com.datacomo.wins.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * @author zhaizhanpo
 *
 */
public class EncryptablePropertyConfigurer extends
		PropertyPlaceholderConfigurer {

	  protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
	    throws BeansException
	  {
	    try
	    {
	     //jdbc
	      String username = props.getProperty("jdbc.username");
	      if (username != null) {
	        props.setProperty("jdbc.username", 
	        		DESUtil.getInstance().decrypt(username));
	      }

	      String password = props.getProperty("jdbc.password");
	      if (password != null) {
	        props.setProperty("jdbc.password", DESUtil.getInstance().decrypt(password));
	      }

	      //sms
	      String smsPassword = props.getProperty("sms.password");
	      if (smsPassword != null) {
	        props.setProperty("sms.password",DESUtil.getInstance().decrypt(smsPassword));
	      }
	      super.processProperties(beanFactory, props);
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new BeanInitializationException(e.getMessage());
	    }
	  }
}
