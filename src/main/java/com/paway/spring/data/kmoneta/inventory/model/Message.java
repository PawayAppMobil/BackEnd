package com.paway.spring.data.kmoneta.inventory.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Message {
    @Getter
    @Setter
    private String message;

    public Message(String message) {
        this.message = message;
    }

}
