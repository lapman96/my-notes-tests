package com.mynotes.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.mynotes.models.dto.Note;
import com.mynotes.pageobjects.MyNotesPage;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@NoArgsConstructor
public class NoteEditorPopup {
    private final SelenideElement categoryDropdown = $(By.id("category"));

    private final SelenideElement completedCheckBox = $(By.id("completed"));

    private final SelenideElement titleInputField = $(By.id("title"));

    private final SelenideElement descriptionInputField = $(By.id("description"));

    private final SelenideElement createButton = $(By.cssSelector("button[data-testid=\"note-submit\"]"));

    private final SelenideElement cancelButton = $(By.className("btn-secondary"));

    private final SelenideElement xButton = $(By.cssSelector("button[aria-label=\"Close\"]"));

    public MyNotesPage createNote(Note note) {
        if(note.isCompleted() != completedCheckBox.has(Condition.checked)) {
            completedCheckBox.click();
        }

        categoryDropdown.selectOptionByValue(note.getCategory().getCategoryName());
        titleInputField.setValue(note.getTitle());
        descriptionInputField.setValue(note.getDescription());
        createButton.click();

        return new MyNotesPage();
    }

    public MyNotesPage closePopupByXButton() {
        xButton.click();
        return new MyNotesPage();
    }

    public MyNotesPage closePopupByCancelButton() {
        cancelButton.click();
        return new MyNotesPage();
    }
}
