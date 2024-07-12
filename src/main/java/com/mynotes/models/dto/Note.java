package com.mynotes.models.dto;

import com.mynotes.data.enums.NoteCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private NoteCategory category;

    private boolean isCompleted;

    private String title;

    private String description;
}
