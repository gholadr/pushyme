package co.ghola.backend.service;

/**
 * Created by macbook on 2/27/16.
 *
 * Rename to APIKeys
 * 
 */
public class APIKeysSample {
    private static APIKeysSample instance = null;
    public static String PUSHY_API_KEY = "KEY";

    public static synchronized APIKeysSample getInstance(){
        if(instance == null){
            instance = new APIKeysSample();
        }
        return instance;
    }
}

