package com.mynotes.components;

import com.codeborne.selenide.SelenideElement;
import com.mynotes.pageobjects.MyNotesPage;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@NoArgsConstructor
public class DeleteNotePopup {

    private final SelenideElement deleteButton = $(By.cssSelector("button[data-testid=\"note-delete-confirm\"]"));

    private final SelenideElement cancelButton = $(By.cssSelector("button[data-testid=\"note-delete-confirm\"]"));

    public MyNotesPage clickDeleteButton() {
        deleteButton.click();
        return new MyNotesPage();
    }

    public MyNotesPage closePopup() {

        cancelButton.click();
        return new MyNotesPage();
    }
}
