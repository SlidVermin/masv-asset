package com.masv.entity;

import javax.persistence.Id;
import javax.persistence.Transient;

public class Node{

	@Id
	private int info;
	
	@Transient
	private Node leftChild;
	@Transient
	private Node rigthChild;
	
	public Node() {
		
	}
	
	
	public Node(int info, Node leftChild, Node rigthChild) {
		super();
		this.info = info;
		this.leftChild = leftChild;
		this.rigthChild = rigthChild;
	}

	public Node(int info) {
		super();
		this.info = info;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRigthChild() {
		return rigthChild;
	}

	public void setRigthChild(Node rigthChild) {
		this.rigthChild = rigthChild;
	}

	@Override
	public String toString() {
		return "Node [info=" + info + ", leftChild=" + leftChild + ", rigthChild=" + rigthChild + "]";
	}	
	
	
}
