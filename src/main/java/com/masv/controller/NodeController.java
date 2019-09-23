package com.masv.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masv.entity.Node;
import com.masv.service.TreeService;

@RestController
@RequestMapping("/masv-asset")
public class NodeController  {
	
	@Autowired
	TreeService service;
	
	private static final Logger logger = LoggerFactory.getLogger(NodeController.class);
	
	@GetMapping()
	public ResponseEntity<Node> listTree() {
		return new ResponseEntity<Node>(service.getTree(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Node> createNode(@Valid @RequestBody Node node) {
		service.createNode(node);	
		return new ResponseEntity<Node>(node, HttpStatus.CREATED);
	}
	
	@PostMapping("/nodes")	
	public ResponseEntity<Node> createNodes(@Valid @RequestBody int[] nodeValues) {
		Arrays.stream(nodeValues).forEach(value-> service.createNode(new Node(value)));
		return new ResponseEntity<Node>(service.getTree(), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/nodes")
	public ResponseEntity<Node> deleteNodes(){
		Node result = service.cleanTree();
		return new ResponseEntity<Node>(result, HttpStatus.OK);
	}
	
	@GetMapping("/common-ancestor")
	public ResponseEntity<Node> getLowestCommonAncestor(@RequestParam("nodeA") int nodeA, 
												  @RequestParam("nodeB") int nodeB) {
		Node commonAncestor = service.lowestCommonAncestor(new Node(nodeA), new Node(nodeB));
		return new ResponseEntity<Node>(commonAncestor, HttpStatus.OK);
	}
	
}
