package com.ctw.workstation.config;

import io.quarkus.logging.Log;
import io.quarkus.runtime.Quarkus;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class CommodoreTestConfig implements QuarkusTestResourceLifecycleManager {
    @Override
    public Map<String, String> start() {
        Log.info("Strating CommodoreTestConfig");
        return Map.of();
    }

    @Override
    public void stop() {

        Log.info("Stoping CommodoreTestConfig");
    }

    @Override
    public void init(Map<String, String> initArgs) {
        Log.info("Init CommodoreTestConfig");
    }
}
