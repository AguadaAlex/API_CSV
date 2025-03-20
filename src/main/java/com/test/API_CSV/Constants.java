package com.test.API_CSV;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    public static String MAP_PARAM_SECRETS_SERVER = "secretsServer";
    public static String MAP_PARAM_API_KEY_AUTH = "apiKeyAuth";
    public static String MAP_PARAM_APP_KEY_AUTH = "appKeyAuth";
    public static String LOG_TYPE_ERROR = "ERROR";
    public static String LOG_TYPE_INFO = "INFO";
    public static String LOG_DEFAULT_HOST = "S/D";
    public static String HASH_ALGORITM_SHA_512 = "SHA-512";
    public List<String> headerNames = Arrays.asList("ID", "NOMBRE", "PRECIO", "STOCK");
}
