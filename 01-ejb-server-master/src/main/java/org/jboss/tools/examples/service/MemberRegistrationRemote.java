package org.jboss.tools.examples.service;

import javax.ejb.Remote;

import org.jboss.tools.examples.model.Member;

@Remote
public interface MemberRegistrationRemote {

	public void register(Member member) throws Exception;
}
