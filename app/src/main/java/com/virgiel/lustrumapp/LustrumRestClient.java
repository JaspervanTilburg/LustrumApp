package com.virgiel.lustrumapp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class LustrumRestClient {

    public static final String FILE_NAME = "lustrum_virgiel_token.txt";
    public static final String BASE_URL = "https://api.lustrumvirgiel.nl/";
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        LustrumRestClient.token = token;
    }

    public static boolean hasToken() {
        return token != null;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getWithHeader(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Accept", "*/*");
        client.addHeader("Content-Type", "application/json");
        client.addHeader("Authorization", "Bearer " + token);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void authenticate(String username, String password, ResponseHandlerInterface responseHandler) {
        JSONObject auth = new JSONObject();
        JSONObject subParams = new JSONObject();
        StringEntity entity = null;
        try {
            subParams.put("email", username);
            subParams.put("password", password);
            auth.put("auth", subParams);
            entity = new StringEntity(auth.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(null, getAbsoluteUrl("auth"), entity, "application/json", responseHandler);
    }

    public static void postLike(int user_id, ResponseHandlerInterface responseHandler) {
        JSONObject auth = new JSONObject();
        StringEntity entity = null;
        try {
            auth.put("user_id", user_id);
            entity = new StringEntity(auth.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(null, getAbsoluteUrl("swipe"), entity, "application/json", responseHandler);
    }

    public static void postPreferences(String interest, int yearBegin, int yearEnd, ResponseHandlerInterface responseHandler) {
        JSONObject auth = new JSONObject();
        StringEntity entity = null;
        try {
            auth.put("interested_in", interest);
            auth.put("interested_year_begin", yearBegin);
            auth.put("interested_year_end", yearEnd);
            entity = new StringEntity(auth.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(null, getAbsoluteUrl("preferences"), entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}