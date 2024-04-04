package com.billing.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
//import java.sql.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Converter
public class HistoryConverter implements AttributeConverter<Map<Date, java.math.BigDecimal>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<Date, java.math.BigDecimal> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Error converting Map to JSON string", ex);
        }
    }
    @Override
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    public Map<Date, java.math.BigDecimal> convertToEntityAttribute(String jsonb) {
        try {

            return objectMapper.readValue(jsonb, new TypeReference<HashMap<Date, java.math.BigDecimal>>() {});
        } catch (IOException ex) {
            throw new IllegalArgumentException("Error converting JSON string to Map", ex);
        }
    }
}