package mattermost.com.mattermost_native_android;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import model.Result;

/**
 * Created by David Lu on 2016-05-15.
 */
public class MattermostClient {

    private final String HEADER_REQUEST_ID = "X-Request-ID";
    private final String HEADER_VERSION_ID = "X-Version-ID";
    private final String HEADER_ETAG_SERVER = "ETag";
    private final String HEADER_ETAG_CLIENT = "If-None-Match";
    private final String HEADER_FORWARDED = "X-Forwarded-For";
    private final String HEADER_REAL_IP = "X-Real-IP";
    private final String HEADER_FORWARDED_PROTO = "X-Forwarded-Proto";
    private final String HEADER_TOKEN = "tolen";
    private final String HEADER_BEARER = "BEARER";
    private final String HEADER_AUTH = "Authorization";
    private final String HEADER_REQUESTED_WITH = "X-Requested-With";
    private final String HEADER_REQUEST_WITH_XML = "XMLHttpRequest";
    private final String API_URL_SUFFIX_V1 = "/api/v1";
    private final String API_URL_SUFFIX_V3 = "/api/v3";
    private final String API_URL_SUFFIX = API_URL_SUFFIX_V3;

    private URL url;
    private URL apiUrl;
    private HttpURLConnection httpClient;
    private String authToken;
    private String authType;
    private String teamId;

    public MattermostClient(String url) {
        try {
            this.url = new URL(url);
            this.apiUrl = new URL(url + API_URL_SUFFIX);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.authToken = "";
        this.authType = "";
        this.teamId = "";
    }

    public void doPost(String url, String data) {
        try {
            httpClient = (HttpURLConnection) new URL(this.url + url).openConnection();
            httpClient.setRequestMethod("POST");
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpClient.setDoOutput(true);
        httpClient.setInstanceFollowRedirects(false);
        httpClient.setRequestProperty("charset", "utf-8");
        httpClient.setRequestProperty("Content-Length", Integer.toString(data.length()));
        httpClient.setUseCaches(false);

        DataOutputStream wr;
        try {
            wr = new DataOutputStream(httpClient.getOutputStream());
            wr.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doApiPost(String url, String data) {
        try {
            httpClient = (HttpURLConnection) new URL(this.apiUrl + url).openConnection();
            httpClient.setRequestMethod("POST");
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpClient.setDoOutput(true);
        httpClient.setInstanceFollowRedirects(false);
        httpClient.setRequestProperty("charset", "utf-8");
        httpClient.setRequestProperty("Content-Length", Integer.toString(data.length()));
        httpClient.setUseCaches(false);

        if (authToken.length() > 0) {
            httpClient.setRequestProperty(HEADER_AUTH, authType + " " + authToken);
        }

        DataOutputStream wr;
        try {
            wr = new DataOutputStream(httpClient.getOutputStream());
            wr.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doApiGet(String url, String data, String etag) {
        try {
            httpClient = (HttpURLConnection) new URL(this.apiUrl + url).openConnection();
            httpClient.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpClient.setDoOutput(true);
        httpClient.setInstanceFollowRedirects(false);
        httpClient.setRequestProperty("charset", "utf-8");
        httpClient.setRequestProperty("Content-Length", Integer.toString(data.length()));
        httpClient.setUseCaches(false);

        if (etag.length() > 0) {
            httpClient.setRequestProperty(HEADER_ETAG_CLIENT, etag);
        }

        if (authToken.length() > 0) {
            httpClient.setRequestProperty(HEADER_AUTH, authType + " " + authToken);
        }

        DataOutputStream wr;
        try {
            wr = new DataOutputStream(httpClient.getOutputStream());
            wr.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOAuthToken(String token) {
        this.authToken = token;
        this.authType = HEADER_TOKEN;
    }

    public void clearOAuthToken(String token) {
        this.authToken = "";
        this.authType = HEADER_BEARER;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void clearTeamId() {
        this.teamId = "";
    }

    public String getTeamRoute() {
        return "/teams/" + getTeamId();
    }

    public String getChannelRoute(String channelId) {
        return "/teams/" + getTeamId() + "/channels/" + channelId;
    }

    public String getChannelNameRoute(String channelName) {
        return "/teams/" + getTeamId() + "/channels/name/" + channelName;
    }

    public Result Must(Result r, Error err) {
        if (err != null) {
            err.printStackTrace();
        }
        return r;
    }

    public Result signUpTeam(String email, String displayName) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", email);
            obj.put("display_name", displayName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        doApiPost("/teams/signup", obj.toString());

        Result r = null;
        try {
            r = new Result(httpClient.getHeaderField(HEADER_REQUEST_ID), httpClient.getHeaderField(HEADER_ETAG_SERVER), httpClient.getResponseMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }
}
