package com.example.consumer.consumer;

import com.example.consumer.model.dto.PacienteDTO;
import com.example.consumer.model.entity.ErrorRetryEntity;
import com.example.consumer.model.enums.ErrorRetryDataType;
import com.example.consumer.repository.ErrorRetryRepository;
import com.example.consumer.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.time.LocalDateTime;

import static com.example.consumer.util.ConfigKafkaUtil.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProtocolTopicConsumer {
    @Value("${spring.kafka.maxAttempt}")
    private int maxAttempts;
    @Value("${spring.kafka.waitingTime}")
    private int waitingTime;
    private int attempts = 0;

    private final PacienteService pacienteService;
    private final ErrorRetryRepository errorRetryRepository;

    @KafkaListener(topics = TOPIC,
            groupId = GROUP_ID,
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(PacienteDTO pacienteDTO, final Acknowledgment ack) {
        PacienteDTO paciente = pacienteDTO;
        log.info("Start consume PACIENTE_RESPONSE_TOPIC Paciente={}", paciente.getNome());
        try {
            pacienteService.save(paciente);
            log.info("Finished consume, Paciente=[{}]", paciente.getNome());
            ack.acknowledge();
        } catch (Exception e) {
            if (attempts < maxAttempts &&
                    (e.getCause() instanceof HttpClientErrorException
                            || e.getCause() instanceof HttpServerErrorException
                            || e.getCause() instanceof JDBCConnectionException)) {
                log.info("Attempt={}, Paciente=[{}]", attempts, paciente.getNome());
                ack.nack(Duration.ofSeconds(waitingTime));
                attempts++;
            } else {
                buildErrorRetry(paciente, e);
                attempts = 0;
                log.error("[{}] Error to consume PACIENTE_RESPONSE_TOPIC, Paciente=[{}]", e.getMessage());
                ack.acknowledge();
            }
        }
    }

    private void buildErrorRetry(final PacienteDTO paciente, final Throwable ex) {
        log.debug("Saving error at creating, Paciente [{}]: buildErrorEntry", paciente.getNome());
        try {
            final ErrorRetryEntity errorRetry = new ErrorRetryEntity();
            errorRetry.setDataObject(paciente.toString());
            errorRetry.setErrorDataType(ErrorRetryDataType.CREATE_PACIENTE);
            errorRetry.setErrorDateTime(LocalDateTime.now());

            if (ex != null) {
                final StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                errorRetry.setMessage(ex.getMessage());
                errorRetry.setStackTrace(sw.toString());
            }

            errorRetryRepository.save(errorRetry);
        } catch (final Exception e) {
            log.error("Paciente [{}] Error={} ", paciente.getNome(), e.getMessage());
        }
    }

}
