package com.apper.accountservice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PutAccountResponse {
    private LocalDateTime lastUpdate;
}
