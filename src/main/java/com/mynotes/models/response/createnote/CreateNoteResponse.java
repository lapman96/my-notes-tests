package com.mynotes.models.response.createnote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteResponse {

	private Data data;

	private boolean success;

	private String message;

	private int status;
}