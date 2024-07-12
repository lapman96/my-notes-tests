package com.mynotes.data.enums;

import lombok.Getter;

@Getter
public enum HttpHeaders {
    ACCEPT("Accept"),
    ACCEPT_CHARSET("Accept-Charset"),
    ACCEPT_ENCODING("Accept-Encoding"),
    AUTHORIZATION("Authorization"),
    CACHE_CONTROL("Cache-Control"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_TYPE("Content-Type"),
    COOKIE("Cookie"),
    DATE("Date"),
    LOCATION("Location"),
    REFERER("Referer"),
    USER_AGENT("User-Agent"),
    X_AUTH_TOKEN("X-Auth-Token");

    private final String headerName;

    HttpHeaders(String headerName) {
        this.headerName = headerName;
    }
}
