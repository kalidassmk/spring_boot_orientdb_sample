package com.sample.data.migration.config;

import com.orientechnologies.orient.core.db.OPartitionedDatabasePool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Kalidass Mahalingam on 10/22/2017.
 */
@Component
public class OrientDBFactory {
    /**
     * The Url.
     */
    @Value("${db.url}")
    String url;
    /**
     * The Username.
     */
    @Value("${db.username}")
    String username;
    /**
     * The Password.
     */
    @Value("${db.password}")
    String password;

    /**
     * The Max partition size.
     */
//@Value("${db.maxPartitionSize}")
    int maxPartitionSize = 1;

    /**
     * The Max pool size.
     */
//@Value("${db.maxPoolSize}")
    int maxPoolSize = 1;

    /**
     * The Auto create.
     */
    @Value("${db.autoCreate}")
    boolean autoCreate = true;

    /**
     * The Conn.
     */
    ODatabaseDocumentTx conn;

    /**
     * createPool
     *
     * @return o partitioned database pool
     */
    public OPartitionedDatabasePool createPool() {
        return new OPartitionedDatabasePool(url,username,password,maxPartitionSize,maxPoolSize);
    }
}
