package com.masv.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masv.controller.NodeController;
import com.masv.entity.Node;
import com.masv.service.TreeService;

@SpringBootTest(classes = NodeController.class)
@EnableWebMvc
public class NodeTests extends AbstractTest {
	private final String SERVER_URI = "/masv-asset";
	private static final Logger logger = LoggerFactory.getLogger(NodeTests.class);
	
	private static boolean isEmptyTree = true;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		try {
			if (isEmptyTree) {
				fullfitTree();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void fullfitTree() throws Exception {
		int[] testValues = {67,39,76,28,44,29,74,85,83,87};
		
		logger.info("Filling up tree");
		for (int i=0; i<testValues.length; i++) {
			int currentValue = testValues[i];
			try {
				String inputJson = super.mapToJson(new Node(currentValue));
				MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
						  .post(SERVER_URI)
					      .content(inputJson)
					      .contentType(MediaType.APPLICATION_JSON)
					      .accept(MediaType.APPLICATION_JSON))
						.andReturn();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.isEmptyTree = false;
	}
		
	@Test	
	public void createNode() throws Exception {		
		String inputJson = super.mapToJson(new Node(4));
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
								  .post(SERVER_URI)
							      .content(inputJson)
							      .contentType(MediaType.APPLICATION_JSON)
							      .accept(MediaType.APPLICATION_JSON))
				.andReturn();	
			   
		Node expectedNode   = new Node(4);
		String expectedOuput = super.mapToJson(expectedNode);
		assertEquals(expectedOuput, mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void getCommonAncestor() throws Exception {
		logger.info("Finding commmon ancestor... ");
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				  .get(SERVER_URI+"/common-ancestor?nodeA=29&nodeB=44")				  
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		Node expectedNode = new Node(39);
		Node resultNode = super.mapFromJson(mvcResult
				.getResponse()
				.getContentAsString(), Node.class);	

		assertEquals(expectedNode.getInfo(), resultNode.getInfo());		
	}
}
