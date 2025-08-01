package com.ctw.workstation.simple;

import org.jboss.logging.Logger;

public class ExternalMessageServiceImpl implements ExternalMessageService {


    private static final Logger log = Logger.getLogger(ExternalMessageServiceImpl.class);



    @Override
    public String sayHelloFromOuterSpace() {
        return "Hello from outer space";
    }

    @Override
    public String sayHelloFromOuterSpace(String name) {

        return "Hello from outer space "+name;
    }

    @Override
    public void fazAlgo(){
        log.info("Algo executed");
        throw new IllegalStateException("Algo executed");
    }

}
