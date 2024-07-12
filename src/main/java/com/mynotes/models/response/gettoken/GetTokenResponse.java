package com.mynotes.models.response.gettoken;


import lombok.*;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenResponse{

	private com.mynotes.models.response.gettoken.Data data;

	private boolean success;

	private String message;

	private int status;
}