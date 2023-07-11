package com.gcash.service;

import lombok.Data;

@Data
public class Account {
    private String id;

    public void setBalance(Double initialBalance) {
        return;
    }

    public Double getBalance(Double newBalance) {
        return newBalance;
    }
}

//   REMOVE GETTER SETTER
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    private Double balance;
//}
