package com.example.honorairesbatch.config;

import com.example.honorairesbatch.model.Dossier;
import com.example.honorairesbatch.processor.DossierItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private DossierItemProcessor processor;

    @Bean
    public FlatFileItemReader<Dossier> reader() {
        return new FlatFileItemReaderBuilder<Dossier>()
                .name("dossierItemReader")
                .resource(new ClassPathResource("dossiers.csv"))
                .linesToSkip(1)
                .delimited()
                .names("reference", "montantDu", "region", "agence", "phase", "estCloture")
                .targetType(Dossier.class)
                .build();
    }

    @Bean
    public JpaItemWriter<Dossier> dossierWriter() {
        return new JpaItemWriterBuilder<Dossier>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step importDossierStep() {
        return new StepBuilder("importDossierStep", jobRepository)
                .<Dossier, Dossier>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor)  // Processor qui ne fait rien
                .writer(dossierWriter())
                .build();
    }

    @Bean
    public Job importDossierJob() {
        return new JobBuilder("importDossierJob", jobRepository)
                .start(importDossierStep())  // Un seul step
                .build();
    }
}