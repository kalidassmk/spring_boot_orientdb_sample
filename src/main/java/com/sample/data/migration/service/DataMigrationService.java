package com.sample.data.migration.service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Kalidass Mahalingam on 10/21/2017.
 */
public interface DataMigrationService {
    /**
     * Start migration completable future.
     *
     * @return the completable future
     */
    CompletableFuture<Boolean>  startMigration();
}
