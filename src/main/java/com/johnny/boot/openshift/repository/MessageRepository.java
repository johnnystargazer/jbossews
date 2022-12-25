package com.johnny.boot.openshift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.johnny.boot.openshift.domain.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>,
		JpaSpecificationExecutor<Message> {

}
