package model;

import java.util.Map;

/**
 * Created by David Lu on 2016-05-15.
 */
public class User {
    public final String ROLE_SYSTEM_ADMIN = "system_admin";
    public final Integer USER_AWAY_TIMEOUT = 5 * 60 * 1000;
    public final Integer USER_OFFLINE_TIMEOUT = 1 * 60 * 1000;
    public final String USER_OFFLINE = "offline";
    public final String USER_AWAY = "away";
    public final String USER_ONLINE = "online";
    public final String USER_NOTIFY_ALL = "all";
    public final String USER_NOTIFY_MENTION = "mention";
    public final String USER_NOTIFY_NONE = "none";
    public final String DEFAULT_LOCALE = "en";
    public final String USER_AUTH_SERVICE_EMAIL = "email";
    public final String USER_AUTH_SERVICE_USERNAME = "username";
    public final Integer MIN_PASSWORD_LENGTH = 5;

    public String Id;
    public Integer createAt;
    public Integer updateAt;
    public Integer deleteAt;
    public String userName;
    public String password;
    public String authData;
    public String authService;
    public String email;
    public Boolean emailVerified;
    public String nickName;
    public String firstName;
    public String lastName;
    public String roles;
    public Integer lastActivityAt;
    public Integer lastPingAt;
    public Boolean allowMarketing;
    public Map<String, String> props;
    public Map<String, String> notifyProps;
    public Map<String, String> themeProps;
    public Integer lastPasswordUpdate;
    public Integer lastPictureUpdate;
    public Integer failedAttempts;
    public String locale;
    public Boolean mfaActive;
    public String mfaSecret;

    public User(String id, Integer createAt, Integer updateAt, Integer deleteAt, String userName, String password, String authData, String authService, String email, Boolean emailVerified, String nickName, String firstName, String lastName, String roles, Integer lastActivityAt, Integer lastPingAt, Boolean allowMarketing, Map<String, String> props, Map<String, String> notifyProps, Map<String, String> themeProps, Integer lastPasswordUpdate, Integer lastPictureUpdate, Integer failedAttempts, String locale, Boolean mfaActive, String mfaSecret) {
        Id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
        this.userName = userName;
        this.password = password;
        this.authData = authData;
        this.authService = authService;
        this.email = email;
        this.emailVerified = emailVerified;
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.lastActivityAt = lastActivityAt;
        this.lastPingAt = lastPingAt;
        this.allowMarketing = allowMarketing;
        this.props = props;
        this.notifyProps = notifyProps;
        this.themeProps = themeProps;
        this.lastPasswordUpdate = lastPasswordUpdate;
        this.lastPictureUpdate = lastPictureUpdate;
        this.failedAttempts = failedAttempts;
        this.locale = locale;
        this.mfaActive = mfaActive;
        this.mfaSecret = mfaSecret;
    }
}
