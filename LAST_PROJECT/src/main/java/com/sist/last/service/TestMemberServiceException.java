package com.sist.last.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMemberServiceException extends RuntimeException {
	
	final Logger LOG = LoggerFactory.getLogger(TestMemberServiceException.class);
	
	public TestMemberServiceException() {}
	
	public TestMemberServiceException(String msg) {
		super(msg);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		LOG.debug(">TestMemberServiceException>"+msg);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
}
