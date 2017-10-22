package com.sample.data.migration.repo.impl;

import com.sample.data.migration.config.OrientDBFactory;
import com.sample.data.migration.model.DataTable;
import com.sample.data.migration.repo.DataMigrationRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orientechnologies.orient.core.db.OPartitionedDatabasePool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.sample.data.migration.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Kalidass Mahalingam on 10/21/2017.
 */
@Repository("dataMigrationRepository")
public class DataMigrationRepositoryImpl implements DataMigrationRepository {

    /**
     * The Query find all.
     */
    @Value("${query.dataMigration.findAll}")
    String queryFindAll;

    /**
     * The Factory.
     */
    @Autowired
    OrientDBFactory factory;

    /**
     * The Odocument converter.
     */
    @Autowired
    ODocumentConverter odocumentConverter;

    @Override
    public CompletableFuture<Boolean> startMigration() {

        return execute(queryFindAll).thenApply(dataTable -> {
            final int sizeOfList = dataTable.size();
            final int breakApart = 1;
            for (int i = 0; i < sizeOfList; i += breakApart) {
                List<DataTable> list = new ArrayList<>(dataTable.subList(i, Math.min(sizeOfList, i + breakApart)));

                //temp solution, we need to implement to save the list of data
                list.stream().forEach(data -> syncData(data));
            }

            return true;
        });
    }


    /**
     * Execute completable future.
     *
     * @param strQuery the str query
     *
     * @return the completable future
     */
    public CompletableFuture<List<DataTable>> execute(String strQuery) {
        return CompletableFuture.supplyAsync(() -> {
            OPartitionedDatabasePool dbPool = factory.createPool();
            ODatabaseDocumentTx db = dbPool.acquire();

            try {
                List<DataTable> dataTable = new ArrayList<>();

                db.activateOnCurrentThread().begin();
                OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<>(strQuery);
                List<ODocument> documents = db.command(query).execute();

                Gson gson = new GsonBuilder().create();
                for (ODocument document : documents) {
                    dataTable.add(gson.fromJson(document.toJSON(), DataTable.class));
                }
                return dataTable;
            } finally {
                db.activateOnCurrentThread().close();
                db.close();
                dbPool.close();
            }
        });

    }

    /**
     * Sync data.
     *
     * @param dataTableList the data table list
     */
    public void syncData(List<DataTable> dataTableList) {
        OPartitionedDatabasePool dbPool = factory.createPool();
        ODatabaseDocumentTx db = dbPool.acquire();
        try {
            db.activateOnCurrentThread().begin();
            ODocument oDocument = odocumentConverter.createDocument(Constants.Vertex.DESTINATION_TABLE, dataTableList);
            oDocument = oDocument.save();
            db.commit(true);
            String json = oDocument.toJSON();
            System.out.println("Sync Data = " + json);
        } finally {
            db.activateOnCurrentThread().close();
            db.close();
            dbPool.close();
        }

    }

    /**
     * Sync data.
     *
     * @param dataTable the data table
     */
    public void syncData(DataTable dataTable) {
        OPartitionedDatabasePool dbPool = factory.createPool();
        ODatabaseDocumentTx db = dbPool.acquire();
        try {
            db.activateOnCurrentThread().begin();
            ODocument oDocument = odocumentConverter.createDocument(Constants.Vertex.DESTINATION_TABLE, dataTable);
            oDocument = oDocument.save();
            db.commit(true);
            String json = oDocument.toJSON();
            System.out.println("Sync Data = " + json);
        } finally {
            db.activateOnCurrentThread().close();
            db.close();
            dbPool.close();
        }

    }

}
