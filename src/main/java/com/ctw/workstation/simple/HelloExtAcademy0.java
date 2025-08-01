package com.ctw.workstation.simple;

public class HelloExtAcademy0 {

    ExternalMessageService externalMessageService;

    public HelloExtAcademy0(ExternalMessageService externalMessageService) {
        this.externalMessageService = externalMessageService;
    }

    public String sayHello(String name){
        if(name!=null){
            return externalMessageService.sayHelloFromOuterSpace(name);
        }
        else{
            String hello = null;
            try {
                hello = externalMessageService.sayHelloFromOuterSpace();
            } catch (Exception e) {
                return "Sorry";
            }
            externalMessageService.fazAlgo();

            return hello;
        }
    }

}
