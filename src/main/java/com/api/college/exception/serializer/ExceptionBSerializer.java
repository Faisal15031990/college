package com.api.college.exception.serializer;

import java.io.IOException;

import org.springframework.util.CollectionUtils;

import com.api.college.exception.ExceptionResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ExceptionBSerializer extends StdSerializer<ExceptionResponse> {
    protected ExceptionBSerializer() {
        super(ExceptionResponse.class);
    }

    @Override
    public void serialize(ExceptionResponse exception, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("errorCode", exception.getErrorCode());
        jsonGenerator.writeObjectField("message", exception.getMessage());
        if (!CollectionUtils.isEmpty(exception.getBody())) {
            jsonGenerator.writeObjectField("data", exception.getBody());
        }
        jsonGenerator.writeEndObject();
    }
}
