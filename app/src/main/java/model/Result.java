package model;

/**
 * Created by David Lu on 2016-05-15.
 */
public class Result {
    public String requestID;
    public String eTag;
    public String data;

    public Result(String requestID, String eTag, String data) {
        this.requestID = requestID;
        this.eTag = eTag;
        this.data = data;
    }
}
