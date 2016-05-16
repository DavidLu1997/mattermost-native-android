package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by David Lu on 2016-05-15.
 */
public class Utils {

    // NewId is a globally unique identifier.  It is a [A-Z0-9] string 26
    // characters long.  It is a UUID version 4 Guid that is zbased32 encoded
    // with the padding stripped off.
    private final String encode = "ybndrfg8ejkmcpqxot1uwisza345h769";
    public static String newId() {
        //TODO: base32 encoding
        return "";
    }

    public static String newRandomString(int length) {
        //TODO: Random string of length
        return "";
    }

    public static Long getMillis() {
        return System.currentTimeMillis();
    }

    public static JSONObject mapToJson(Map<String, String> map) {
        return new JSONObject(map);
    }

    public static Map<String, String> mapFromJson(JSONObject obj) {
        Map<String, String> map = new HashMap<String, String>();

        Iterator<String> keysIt = obj.keys();

        String key;
        while(keysIt.hasNext()) {
            key = keysIt.next();
            try {
                map.put(key, obj.getString(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    public static Boolean isLower(String s) {
        return s.toLowerCase().equals(s);
    }

    public static Boolean isValidEmail(String email) {
        if (!isLower(email)) {
            return false;
        } else {
            return email.contains("@");
        }
    }

    public final String[] reservedName = {
            "www",
            "web",
            "admin",
            "support",
            "notify",
            "test",
            "demo",
            "mail",
            "team",
            "channel",
            "internal",
            "localhost",
            "dockerhost",
            "stag",
            "post",
            "cluster",
            "api",
            "oauth",
    };

    //TODO: Rest of utils
}
