/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llh.drools.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kie.api.io.Resource;
import org.drools.compiler.kproject.models.KieModuleModelImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author lorenzolince
 */
@Configuration
public class DroolsConfiguration {
  
    private static String pathRules="./rules";
    
//    @Bean
//    public KieContainer kieContainer() {
//        return KieServices.Factory.get().getKieClasspathContainer();
//    }
    
     @Bean
    public KieContainer kieContainerLocal() {
         KieContainer kieContainer = null;
           InputStream is=null;
        try {
          
            KieServices kieServices = KieServices.Factory.get();
            File currentDirFile = new File(pathRules);
             KieFileSystem kFileSystem = kieServices.newKieFileSystem();
           for(File file : currentDirFile.listFiles()){
               if(file.getName().equals("kmodule.xml")){
               is = new FileInputStream(file);
               } else {
              Resource resource = kieServices.getResources().newFileSystemResource(file);  
              kFileSystem.write("src/main/resources/rules/" + file.getName(),
                        resource); 
               }
           
           }
            KieModuleModel kieModel = KieModuleModelImpl.fromXML(is);
            kFileSystem.writeKModuleXML(kieModel.toXML());
            KieBuilder kbuilder = kieServices.newKieBuilder(kFileSystem);
            kbuilder.buildAll();
            kieServices.getRepository().addKieModule(kbuilder.getKieModule());
            kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        } catch (Exception ex) {
            Logger.getLogger(DroolsConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kieContainer;
    }
}
