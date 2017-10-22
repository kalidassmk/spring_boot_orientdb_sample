package com.sample.data.migration.controller;

import com.sample.data.migration.service.DataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Kalidass Mahalingam on 10/21/2017.
 */
@RestController
public class DataMigrationController {

    /**
     * The Data migration service.
     */
    @Autowired
    DataMigrationService dataMigrationService;

    /**
     * Start migration completable future.
     *
     * @return the completable future
     */
    @RequestMapping("/start")
    public CompletableFuture<Boolean> startMigration() {
        return dataMigrationService.startMigration();
    }
    
}
