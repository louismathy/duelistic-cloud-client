package com.duelistic.cloud.client;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyInvite {
    private UUID inviter;
    private UUID invited;

    public PartyInvite() {
    }

    public UUID getInviter() {
        return inviter;
    }

    public UUID getInvited() {
        return invited;
    }
}
