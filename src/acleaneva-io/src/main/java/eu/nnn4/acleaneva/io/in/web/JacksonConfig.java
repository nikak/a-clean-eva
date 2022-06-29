package eu.nnn4.acleaneva.io.in.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final LocalDateTime FIXED_DATE = LocalDateTime.now();
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
//                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .serializers(LOCAL_DATETIME_SERIALIZER)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .failOnUnknownProperties(false)
                ;
    }
}

//https://codingnconcepts.com/spring-boot/jackson-json-request-response-mapping/
//spring.jackson.deserialization.FAIL_ON_IGNORED_PROPERTIES = false
//DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false

/*
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        converter.setObjectMapper(objectMapper);
        converters.add(converter);
    }
}

@Configuration
class RepositoryRestAdapterConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS );
    }
}
*/

