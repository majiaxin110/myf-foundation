package dev.mtage.util.web.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.mtage.util.web.SimpleResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;


/**
 * @author mtage
 * @since 2020/2/8 12:10
 */
public class BaseTest {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    @BeforeAll
    public static void ansi() {
        System.setProperty("spring.output.ansi.enabled", "ALWAYS");
    }

    protected void checkResultAndOut(SimpleResult<?> result) throws JsonProcessingException {
        Assertions.assertTrue(result.getSuccess());
        System.out.println(objectMapper.writeValueAsString(result));
    }

    protected void checkListAndOut(List<?> result) throws JsonProcessingException {
        Assertions.assertTrue(result.size() > 0);
        System.out.println(objectMapper.writeValueAsString(result));
    }
}
