package mattermost.com.mattermost_native_android;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            this.apiUrl = new URL(url + API_URL_SUFFIX);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.authToken = "";
        this.authType = "";
        this.teamId = "";
    }

    public InputStream doPost(String url, String data, String contentType) {
        try {
            httpClient = (HttpURLConnection) new URL(this.url + url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpClient.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        httpClient.setDoOutput(true);
        httpClient.setInstanceFollowRedirects(false);
        httpClient.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpClient.setRequestProperty("charset", "utf-8");
        httpClient.setRequestProperty("Content-Length", Integer.toString(data.length()));
        httpClient.setUseCaches(false);

        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(httpClient.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (wr != null) {
            try {
                wr.write(data.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            return httpClient.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

    private class Result {
        public String requestID;
        public String eTag;
        public String data;
    }
}
