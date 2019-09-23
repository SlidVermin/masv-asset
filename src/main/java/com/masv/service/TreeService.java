package com.masv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.masv.entity.Node;

@Service
public class TreeService {	
	private Node root = null;
	private static final Logger logger = LoggerFactory.getLogger(TreeService.class);
	
	public void createNode(Node newNode) {	
		if (root==null) {
			root = newNode;
		}
		else {
			Node currentNode = root;
			boolean nodeInserted = false;
			
			while (!nodeInserted) {
				if (newNode.getInfo() == currentNode.getInfo()) {
					break;
				}
				else if (newNode.getInfo() < currentNode.getInfo()) {
					if (currentNode.getLeftChild()==null) {
						currentNode.setLeftChild(newNode);
						nodeInserted = true;
					}
					else {
						currentNode = currentNode.getLeftChild();
					}					
				}
				else if (newNode.getInfo() > currentNode.getInfo()){
					if (currentNode.getRigthChild() == null) {
						currentNode.setRigthChild(newNode);
						nodeInserted = true;
					}
					else {
						currentNode = currentNode.getRigthChild();
					}
				}
			}
		}	
	}
	
	public void visitPreorder(Node node) {
		if(node == null) {
            return;
		}
  
        visitPreorder(node.getLeftChild());   
        visitPreorder(node.getRigthChild());  
	}
	
	public Node getTree() {
		return root;
	}
	
	public Node lowestCommonAncestor(Node a, Node b) {
		Node currentNode = root;
		
	    while ((currentNode.getInfo() - a.getInfo()) * (currentNode.getInfo() - b.getInfo()) > 0) {
	        if (a.getInfo() < currentNode.getInfo()) {
	        	currentNode = currentNode.getLeftChild();
	        }
	        else {
	        	currentNode = currentNode.getRigthChild();
	        }
	    }
	    return currentNode;
	}
	
	public Node cleanTree() {
		Node aux = root;
		root = null;		
		return aux;
	}
}
