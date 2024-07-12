package com.mynotes.models.response.getnotes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetNotesResponse {

	private List<DataItemItem> data;

	private boolean success;

	private String message;

	private int status;
}
