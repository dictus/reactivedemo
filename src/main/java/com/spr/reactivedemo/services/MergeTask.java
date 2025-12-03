package com.spr.reactivedemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MergeTask {


    private final DatabaseClient client;

    public MergeTask(DatabaseClient client) {
        this.client = client;
    }

    public Mono<Void> runMerge() {
        log.info("Starting MERGE operation...");

        String sql = """
                MERGE INTO employees tgt
                        USING (VALUES (:id, :name)) AS src(emp_id, emp_name)
                           ON (tgt.emp_id = src.emp_id)
                        WHEN MATCHED THEN
                            UPDATE SET emp_name = src.emp_name
                        WHEN NOT MATCHED THEN
                            INSERT (emp_id, emp_name)
                            VALUES (src.emp_id, src.emp_name)
                """;

        return client.sql(sql)
                .bind("id", 101)
                .bind("name", "Rupam")
                .fetch()
                .rowsUpdated()
                .doOnSuccess(count -> log.info("MERGE affected {} rows", count))
                .doOnError(err -> log.error("MERGE failed", err))
                .then();
    }
}
