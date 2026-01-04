package com.duelistic.cloud.client;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyUser {
    private UUID uniqueId;
    private PartyRank rank;

    public PartyUser() {
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public PartyRank getRank() {
        return rank;
    }
}
