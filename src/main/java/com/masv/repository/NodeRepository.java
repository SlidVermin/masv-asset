package com.masv.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masv.entity.Node;

@Repository
public interface NodeRepository extends CrudRepository<Node, Integer> {

}
