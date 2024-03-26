package com.rently.rentlyAPI.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
public class EmailDtoTest {

    @Test
    public void testEmailDtoInitialization() {
        String to = "recipient@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        EmailDto emailDto = EmailDto.builder()
                .to(to)
                .subject(subject)
                .body(body)
                .build();

        assertNotNull(emailDto);
        assertEquals(to, emailDto.getTo());
        assertNull(emailDto.getCc());
        assertEquals(subject, emailDto.getSubject());
        assertEquals(body, emailDto.getBody());
    }
}
