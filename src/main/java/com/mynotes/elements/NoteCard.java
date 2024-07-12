package com.mynotes.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.mynotes.components.DeleteNotePopup;
import com.mynotes.components.NoteEditorPopup;
import org.openqa.selenium.By;

public class NoteCard extends BaseElement{

    public NoteCard(SelenideElement selenideElement) {
        super(selenideElement);
    }

    private final SelenideElement noteTitle = rootElement.$(By.cssSelector("div[data-testid=\"note-card-title\"]"));

    private final SelenideElement noteDescription = rootElement.$(By.cssSelector("p[data-testid=\"note-card-description\"]"));

    private final SelenideElement noteUpdatedAtDate = rootElement.$(By.cssSelector("p[data-testid=\"note-card-updated-at\"]"));

    private final SelenideElement completedCheckbox = rootElement.$(By.cssSelector("input[data-testid=\"toggle-note-switch\"]"));

    private final SelenideElement editButton = rootElement.$(By.cssSelector("button[data-testid=\"note-edit\"]"));

    private final SelenideElement deleteButton = rootElement.$(By.cssSelector("button[data-testid=\"note-delete\"]"));

    public String getTitle() {
        return noteTitle.getText();
    }

    public String getDescription() {
        return noteDescription.getText();
    }

    public String getUpdatedAtDate() {
        return noteUpdatedAtDate.getText();
    }

    public boolean isCompleted() {
        return completedCheckbox.has(Condition.checked);
    }

    public NoteEditorPopup editNote() {
        editButton.click();
        return new NoteEditorPopup();
    }

    public DeleteNotePopup deleteNote() {
        deleteButton.click();
        return new DeleteNotePopup();
    }
}
