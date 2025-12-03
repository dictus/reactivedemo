package com.spr.reactivedemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
@Slf4j
public class MergeExecutorService {


    private final Executor executor;
    private final MergeTask mergeTask;

    public MergeExecutorService(
            @Qualifier("customExecutor") Executor executor,
            MergeTask mergeTask) {
        this.executor = executor;
        this.mergeTask = mergeTask;
    }

    public void executeMerge2() {
        executor.execute(() ->
                mergeTask.runMerge()
                        .doOnError(err -> log.error("Merge failed", err))
                        .subscribe(
                                v -> {
                                },
                                err -> {
                                },
                                () -> log.info("Merge completed successfully")
                        )
        );
    }

    public String executeMerge() {
        executor.execute(() ->
                mergeTask.runMerge()
                        .doOnError(err -> log.error("Merge failed", err))
                        .doOnSuccess(v -> log.info("Merge completed successfully"))
                        .subscribe()
        );
        return "Success";
    }

}
