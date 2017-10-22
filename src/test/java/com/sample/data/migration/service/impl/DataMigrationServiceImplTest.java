package com.sample.data.migration.service.impl;

import com.sample.data.migration.repo.DataMigrationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

/**
 * Created by kamahalingam on 10/22/2017.
 */
public class DataMigrationServiceImplTest {
    @Mock
    DataMigrationRepository dataMigrationRepository;
    @InjectMocks
    DataMigrationServiceImpl dataMigrationServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartMigration() throws Exception {
        CompletableFuture<Boolean> result = dataMigrationServiceImpl.startMigration();
        Assert.assertEquals(null, result);
    }
}

