package model;

/**
 * Created by David Lu on 2016-05-15.
 */
public class Version {
    public final String[] versions = {
            "3.0.0",
            "2.2.0",
            "2.1.0",
            "2.0.0",
            "1.4.0",
            "1.3.0",
            "1.2.1",
            "1.2.0",
            "1.1.0",
            "1.0.0",
            "0.7.1",
            "0.7.0",
            "0.6.0",
            "0.5.0",

    };
    public final String CurrentVersion = versions[0];
    private String buildNumber;
    private String buildDate;
    private String buildHash;
    private String buildEnterpriseReady;
    private String[] versionsWithoutHotFixes;

    public Integer[] splitVersion(String version) {
        String[] split = version.split(".");
        //TODO: Split it
        return null;
    }

    public Version() {

    }
}
