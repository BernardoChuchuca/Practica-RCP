package org.jboss.tools.examples.service;


import javax.ejb.Local;

import org.jboss.tools.examples.model.Member;

public interface MemberRegistrationRemote{
	public void register(Member member) throws Exception;
}