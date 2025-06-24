package ru.yandex.practicum.user;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class PurchasesInformation {
    private Date lastPurchase;
    private long purchaseCounts = 0;
}