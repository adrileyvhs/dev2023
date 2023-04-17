package com.dev2023.Entities.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    PEDING_PAGAMENTO(1),
    PROCESSING(2),
    SHIPPED(3),
    DELIVERED(4),
    CANECEL(10);

    private int code;

    public int getCode() {
        return code;
    }

    public static OrderStatus valeueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Codigo Invalido");
    }
}

