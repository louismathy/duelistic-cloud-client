package com.duelistic.cloud.client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Party {
    private UUID partyId;
    private List<PartyUser> users = new ArrayList<>();

    public Party() {
    }

    public UUID getPartyId() {
        return partyId;
    }

    public List<PartyUser> getUsers() {
        return users;
    }
}
