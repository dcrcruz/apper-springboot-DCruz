package com.apper.accountservice;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

@Service
public class IdGeneratorService {
    // Lab 1: Generate verification code
    public static String generateRandomCharacters(int length) {

        return RandomStringUtils.randomAlphanumeric(length);
    }

    // Lab 1: Generate account ID
    public static String getNextId() {

        return UUID.randomUUID().toString();
    }
}
