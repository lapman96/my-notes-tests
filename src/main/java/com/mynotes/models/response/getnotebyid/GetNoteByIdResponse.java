package com.mynotes.models.response.getnotebyid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetNoteByIdResponse {

	private Data data;

	private boolean success;

	private String message;

	private int status;
}