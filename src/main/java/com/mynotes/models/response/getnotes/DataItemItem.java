package com.mynotes.models.response.getnotes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataItemItem{

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