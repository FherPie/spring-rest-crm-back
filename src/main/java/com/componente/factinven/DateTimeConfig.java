package com.componente.factinven;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

@Configuration
public class DateTimeConfig {
	
	@Bean
	public Converter<String, LocalDateTime> stringDateConverter() {
	    return new Converter<String, LocalDateTime>() {
	        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        @Override
	        public LocalDateTime convert(@NonNull String source) {
	            return LocalDateTime.parse(source, formatter);
	        }

			@Override
			public JavaType getInputType(TypeFactory typeFactory) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public JavaType getOutputType(TypeFactory typeFactory) {
				// TODO Auto-generated method stub
				return null;
			}
	    };
	}
	
	

}
