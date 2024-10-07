CREATE TABLE consumer.paciente (
    codigo NUMBER PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    cpf VARCHAR2(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL
);

CREATE TABLE consumer.error_retry (
    id NUMBER PRIMARY KEY,
    error_date_time TIMESTAMP NOT NULL,
    reprocessed_date_time TIMESTAMP,
    data_object CLOB,
    error_data_type VARCHAR2(100),
    message VARCHAR2(500),
    stack_trace CLOB,
    attempt NUMBER NOT NULL
);