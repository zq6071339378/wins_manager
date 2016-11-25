/**
 * 
 */
package com.datacomo.wins.controller;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author zhaizhanpo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","/WEB-INF/dispatcher-servlet.xml"})
public class BasicControllerTest {

	@Autowired
    protected WebApplicationContext wac;
    
    protected MockMvc mvc;
    
    @Before
    public void setUp() {
        this.mvc=MockMvcBuilders.webAppContextSetup(wac).build();
    }
	

}
