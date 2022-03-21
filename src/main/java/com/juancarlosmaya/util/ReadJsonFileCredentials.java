package com.juancarlosmaya.util;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadJsonFileCredentials {
    private JSONObject users;
    private static final String BASE_FILE_PATH = "src/test/resources/files/login/login.json";
    public final Logger LOGER = Logger.getLogger(ReadJsonFileCredentials.class);


    public ReadJsonFileCredentials(String email, String password)
    {
        readFile(BASE_FILE_PATH, email,password);
    }

    public void readFile(String fileName, String email, String password)
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

    public JSONObject getUsers() {
        return users;
    }
}
