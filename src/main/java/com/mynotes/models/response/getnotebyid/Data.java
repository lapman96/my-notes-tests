package com.mynotes.models.response.getnotebyid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data{

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("user_id")
	private String userId;

	private String description;

	@JsonProperty("created_at")
	private String createdAt;

	private String id;

	private boolean completed;

	private String title;

	private String category;
}