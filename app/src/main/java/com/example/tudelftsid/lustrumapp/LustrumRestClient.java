package com.example.tudelftsid.lustrumapp;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.HttpEntity;

public class LustrumRestClient {
    private static final String BASE_URL = "http://api.lustrumvirgiel.nl/";
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MTA5MzkyMjIsInN1YiI6MX0.3Z-phBTmJm_B-hQDZuJFWNidD7rUXhAnHLv4xra8WiM";

    public static void authenticate() {

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

    public static void postJSON(String url, HttpEntity entity, ResponseHandlerInterface responseHandler) {
        client.post(null, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}