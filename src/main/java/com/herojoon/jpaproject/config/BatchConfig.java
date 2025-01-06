package com.herojoon.jpaproject.config;

import com.herojoon.jpaproject.service.DataGoKrService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataGoKrService dataGoKrService;
    private final JdbcTemplate jdbcTemplate;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataGoKrService dataGoKrService, JdbcTemplate jdbcTemplate) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataGoKrService = dataGoKrService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public Job importJob(JobExecutionListener listener, Step step0, Step step1) {
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step0)
                .next(step1)
                //.end()
                .build();
    }

    @Bean
    public Step step0() {
        return stepBuilderFactory.get("step0")
                .tasklet((contribution, chunkContext) -> {
                    // Tasklet logic for step0
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(apiTasklet())
                .build();
    }

    @Bean
    public Tasklet apiTasklet() {
        return (contribution, chunkContext) -> {
            dataGoKrService.callAndProcessApi();
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobExecutionListenerSupport() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                System.out.println("Job started: " + jobExecution.getJobInstance().getJobName());
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    System.out.println("Job completed successfully");
                } else {
                    System.out.println("Job failed with status: " + jobExecution.getStatus());
                }
            }
        };
    }

    private void insertStepExecution(String stepName, String status, Timestamp createTime) {
        if (createTime == null) {
            createTime = new Timestamp(System.currentTimeMillis());
        }
        jdbcTemplate.update("INSERT INTO BATCH_STEP_EXECUTION (STEP_NAME, STATUS, CREATE_TIME) VALUES(?, ?, ?)",
                stepName, status, createTime);
    }
}
