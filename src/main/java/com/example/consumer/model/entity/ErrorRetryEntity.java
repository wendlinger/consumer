
package com.example.consumer.model.entity;


import com.example.consumer.model.enums.ErrorRetryDataType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "error_retry")
public class ErrorRetryEntity implements Serializable {

    private static final long serialVersionUID = 8068978877162956411L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "error_date_time", nullable = false)
    private LocalDateTime errorDateTime;

    @Column(name = "reprocessed_date_time")
    private LocalDateTime reprocessedTime;

    @Lob
    @Column(name = "data_object")
    private String dataObject;

    @Enumerated(EnumType.STRING)
    @Column(name = "error_data_type", nullable = false, length = 30)
    private ErrorRetryDataType errorDataType;

    @Column(name = "message")
    private String message;

    @Lob
    @Column(name = "stack_trace")
    private String stackTrace;

    @Column(name = "attempt")
    private int attempt;

}
