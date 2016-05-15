package model;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by David Lu on 2016-05-15.
 */
public class Team {
    public String id;
    public Integer createAt;
    public Integer updateAt;
    public Integer deleteAt;
    public String displayName;
    public String name;
    public String email;
    public String type;
    public String companyName;
    public String allowedDomains;
    public String inviteId;
    public Boolean allowOpenInvite;

    public Team(String id, Integer createAt, Integer updateAt, Integer deleteAt, String displayName, String name, String email, String type, String companyName, String allowedDomains, String inviteId, Boolean allowOpenInvite) {
        this.id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
        this.displayName = displayName;
        this.name = name;
        this.email = email;
        this.type = type;
        this.companyName = companyName;
        this.allowedDomains = allowedDomains;
        this.inviteId = inviteId;
        this.allowOpenInvite = allowOpenInvite;
    }

    public Invites invitesFromJson(JSONObject obj) {
        //TODO: Decode JSON
        return null;
    }

    public Team teamFromJson(JSONObject obj) {
        //TODO: Decode JSON
        return null;
    }

    public JSONObject teamMapToJson(Map<String, Team> map) {
        //TODO: Encode JSON
        return null;
    }

    public String etag() {
        //TODO: Etag
        return null;
    }

    private class Invites {
        public Map<String, String>[] invites;

        public Invites(Map<String, String>[] invites) {
            this.invites = invites;
        }

        public JSONObject toJson() {
            //TODO: Encode JSON
            return null;
        }
    }
}
