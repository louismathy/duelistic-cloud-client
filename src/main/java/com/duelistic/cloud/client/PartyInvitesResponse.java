package com.duelistic.cloud.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyInvitesResponse {
    private int count;
    private List<PartyInvite> invites = new ArrayList<>();

    public PartyInvitesResponse() {
    }

    public int getCount() {
        return count;
    }

    public List<PartyInvite> getInvites() {
        return invites;
    }
}
