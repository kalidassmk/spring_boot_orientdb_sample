package com.sample.data.migration.service.impl;

import com.sample.data.migration.repo.DataMigrationRepository;
import com.sample.data.migration.service.DataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Kalidass Mahalingam on 10/21/2017.
 */
@Service("dataMigrationService")
public class DataMigrationServiceImpl implements DataMigrationService {

    /**
     * The Data migration repository.
     */
    @Autowired
    DataMigrationRepository dataMigrationRepository;

    @Override
    public CompletableFuture<Boolean> startMigration() {
        return dataMigrationRepository.startMigration();
    }
}
