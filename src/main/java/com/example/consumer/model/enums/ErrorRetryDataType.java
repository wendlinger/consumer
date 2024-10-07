
package com.example.consumer.model.enums;

import java.util.stream.Stream;

public enum ErrorRetryDataType {
	CREATE_PACIENTE("creating_paciente");

	private final String value;

	ErrorRetryDataType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ErrorRetryDataType fromString(final String value) {
		return Stream.of(ErrorRetryDataType.values())
				.filter(e -> e.value.equalsIgnoreCase(value))
				.findFirst()
				.orElseThrow(() -> new EnumConstantNotPresentException(ErrorRetryDataType.class, value));
	}
}
