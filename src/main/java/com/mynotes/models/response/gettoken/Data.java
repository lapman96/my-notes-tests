package com.mynotes.models.response.gettoken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data{

	private String name;

	private String id;

	private String email;

	private String token;
}