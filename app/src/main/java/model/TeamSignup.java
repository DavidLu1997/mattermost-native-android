package model;

/**
 * Created by David Lu on 2016-05-15.
 */
public class TeamSignup {
    public Team team;
    public User user;
    public String[] invites;
    public String data;
    public String hash;

    public TeamSignup(Team team, User user, String[] invites, String data, String hash) {
        this.team = team;
        this.user = user;
        this.invites = invites;
        this.data = data;
        this.hash = hash;
    }
}
