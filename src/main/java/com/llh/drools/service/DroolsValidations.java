/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.drools.service;

import java.util.List;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lorenzolince
 */
@Service
public class DroolsValidations {

  	private final KieContainer kieContainerLocal;

	@Autowired
	public DroolsValidations(KieContainer kieContainer) {
		this.kieContainerLocal = kieContainer;
	}

    public <T> T valid(Object objeto, String session) {
        KieSession kieSession = kieContainerLocal.newKieSession(session);
        kieSession.insert(objeto);
        kieSession.fireAllRules();
        kieSession.dispose();
        return (T) objeto;
    }

    public <T> T valid(Object objeto, Object globalParameters, String session) {

        KieSession kieSession = kieContainerLocal.newKieSession(session);
        kieSession.insert(objeto);
        kieSession.setGlobal("parameter", globalParameters);
        kieSession.fireAllRules();
        kieSession.dispose();
        return (T) objeto;
    }

    public <T> T valid(List<Object> listaObjeto, String session) {
        KieSession kieSession = kieContainerLocal.newKieSession(session);
        for (Object ob : listaObjeto) {
            kieSession.insert(ob);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
        return (T) listaObjeto;
    }

    public <T> T valid(List<Object> listaObjeto, Object globalParameters, String session) {

        KieSession kieSession = kieContainerLocal.newKieSession(session);
        for (Object ob : listaObjeto) {
            kieSession.insert(ob);
        }
        kieSession.setGlobal("parameter", globalParameters);
        kieSession.fireAllRules();
        kieSession.dispose();
        return (T) listaObjeto;
    }
}
