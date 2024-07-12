package com.mynotes.data.enums;

import lombok.Getter;
@Getter
public enum NoteCategory {
    ALL("All"),
    HOME("Home"),
    WORK("Work"),
    PERSONAL("Personal");

    private final String categoryName;

    NoteCategory(String categoryName){
        this.categoryName = categoryName;
    }


}
