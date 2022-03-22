package com.juancarlosmaya.util;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadJsonFileCredentials {
    private JSONObject users;
    private static final String BASE_FILE_LOGIN_PATH = "src/test/resources/files/login/login.json";
    private static final String BASE_FILE_UPDATE_PATH = "src/test/resources/files/update/update.json";
    public final Logger LOGER = Logger.getLogger(ReadJsonFileCredentials.class);


    public ReadJsonFileCredentials(String str1, String str2, int type)
    {
        switch(type)
        {
            case 1:
                readEmailPassword(BASE_FILE_LOGIN_PATH, str1, str2);
                break;
            case 2:
                readNameJob(BASE_FILE_UPDATE_PATH, str1, str2);
                break;
        }
    }

    public void readEmailPassword(String fileName, String email, String password)
    {
        JSONParser jsonParser = new JSONParser();
        try
        {
            Object obj = jsonParser.parse(new FileReader(fileName));
            users =  (JSONObject) obj;
            users.replace("password",LoginKey.PASSWORD.getValue(),password);
            users.replace("email",LoginKey.EMAIL.getValue(),email);
        }
        catch (Exception e)
        {
            LOGER.error(e.getMessage(), e);
        }
    }

    public void readNameJob(String fileName, String name, String job)
    {
        JSONParser jsonParser = new JSONParser();
        try
        {
            Object obj = jsonParser.parse(new FileReader(fileName));
            users =  (JSONObject) obj;
            users.replace("name",UpdateKey.NAME.getValue(),name);
            users.replace("job",UpdateKey.JOB.getValue(),job);
        }
        catch (Exception e)
        {
            LOGER.error(e.getMessage(), e);
        }
    }

    public JSONObject getUsers() {
        return users;
    }
}
