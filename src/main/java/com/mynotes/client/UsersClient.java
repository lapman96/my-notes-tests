package com.mynotes.client;

import com.mynotes.models.request.GetTokenRequestFormParams;
import com.mynotes.models.response.gettoken.GetTokenResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static com.mynotes.data.enums.HttpHeaders.CONTENT_TYPE;
import static com.mynotes.data.EndpointsRoutes.login;

public class UsersClient extends BaseClient {

    public String getToken(GetTokenRequestFormParams getTokenRequestParams) {
        postWithFormParams(login(), getTokenRequestParams,
                new Header(CONTENT_TYPE.getHeaderName(), ContentType.URLENC.withCharset("UTF-8")));
        return getResponse().as(GetTokenResponse.class).getData().getToken();
    }
}
