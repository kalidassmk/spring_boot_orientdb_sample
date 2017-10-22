package com.sample.data.migration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.orientechnologies.orient.core.db.OPartitionedDatabasePool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Kalidass Mahalingam on 10/21/2017.
 */
@Configuration
public class OrientDbConfiguration {

    /**
     * The Url.
     */
    @Value("url")
    String url;
    /**
     * The Username.
     */
    @Value("username")
    String username;
    /**
     * The Password.
     */
    @Value("password")
    String password;

    /**
     * Gets o partitioned database pool.
     *
     * @return the o partitioned database pool
     */
    @Bean
    OPartitionedDatabasePool getOPartitionedDatabasePool() {
        return factory().createPool();
    }

    /**
     * Gets object mapper.
     *
     * @return the object mapper
     */
    @Bean
    ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }

    /**
     * Gets o database document tx.
     *
     * @return the o database document tx
     */
    @Bean
    ODatabaseDocumentTx getODatabaseDocumentTx() {
        return factory().createPool().acquire();
    }

    /**
     * Factory orient db factory.
     *
     * @return the orient db factory
     */
    @Bean
    OrientDBFactory factory() {
        return new OrientDBFactory();
    }

}