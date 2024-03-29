package br.com.start.configuration;

import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class ApiConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper =  new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		mapper.getConfiguration().setAmbiguityIgnored(true);
		
		return mapper;
	}
	
	 @Bean
	    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
	        return builder -> {
	            
	            // formatter
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	            DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    		
	            // deserializers
	            builder.deserializers(new LocalDateDeserializer(dateFormatter));
	            builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
	            
	            // serializers
	            builder.serializers(new LocalDateSerializer(dateFormatter));
	            builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
	        };
	    }

}
