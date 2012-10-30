package no.steria.swhrs;

public interface JettyServerMBean {
    /* Attributes */
    void setUserWhitelist(String userWhitelist);
    String getUserWhitelist();
    void setUserBlacklist(String userBlacklist);
    String getUserBlacklist();
    void setOnlyAllowUsersFromWhitelist(Boolean useWhitelistOnly);
    Boolean getOnlyAllowUsersFromWhitelist();

    /* Operations */
    void restartServer() throws Exception;
    void stopServer() throws Exception;
}
