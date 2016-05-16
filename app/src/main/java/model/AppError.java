package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by David Lu on 2016-05-15.
 */
public class AppError {
    public String id;
    public String message;
    public String detailedError;
    public String requestId;
    public Integer statusCode;
    public String where;
    public Boolean isOAuth;
    public Map<String, String> params;

    public AppError(String id, String message, String detailedError, String requestId, Integer statusCode, String where, Boolean isOAuth, Map<String, String> params) {
        this.id = id;
        this.message = message;
        this.detailedError = detailedError;
        this.requestId = requestId;
        this.statusCode = statusCode;
        this.where = where;
        this.isOAuth = isOAuth;
        this.params = params;
    }

    public AppError(JSONObject obj) {
        try {
            this.id = obj.getString("id");
            this.message = obj.getString("message");
            this.detailedError = obj.getString("detailed_error");
            this.requestId = obj.getString("request_id");
            this.statusCode = obj.getInt("status_code");
            this.where = obj.getString("-");
            this.isOAuth = obj.getBoolean("is_oauth");
            this.params = (Map<String, String>) obj.get("-");
        } catch (JSONException e) {
            this.id = "";
            this.message = "";
            this.detailedError = "";
            this.requestId = "";
            this.statusCode = 0;
            this.where = "";
            this.isOAuth = false;
            this.params = null;
        }
    }

    public String error() {
        return where + ": " + message + ", " + detailedError;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("id", id);
            obj.put("message", message);
            obj.put("detailed_error", detailedError);
            obj.put("request_id", requestId);
            obj.put("status_code", statusCode);
            obj.put("-", where);
            obj.put("is_oauth", isOAuth);
            obj.put("-", params);
        } catch (JSONException e) {
            return null;
        }

        return obj;
    }
}
