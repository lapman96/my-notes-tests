package com.mynotes.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.mynotes.components.NoteEditorPopup;
import com.mynotes.data.enums.NoteCategory;
import com.mynotes.elements.NoteCard;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import java.util.List;
import java.util.function.Predicate;

import static com.codeborne.selenide.Selenide.*;
import static com.mynotes.data.PagePaths.*;

@NoArgsConstructor
public class MyNotesPage extends BasePage{

    private static final String PAGE_URL = EXPAND_TESTING_BASE_UI_URL + EXPAND_TESTING_MY_NOTES_PAGE_URL;

    private final SelenideElement logoutButton = $(By.cssSelector("button[data-testid=\"logout\"]"));

    private final SelenideElement searchField = $(By.cssSelector("input[data-testid=\"search-input\"]"));

    private final SelenideElement searchButton = $(By.cssSelector("button[data-testid=\"search-btn\"]"));

    private final SelenideElement allCategoryButton = $(By.cssSelector("button[data-testid=\"category-all\"]"));

    private final SelenideElement homeCategoryButton = $(By.cssSelector("button[data-testid=\"category-home\"]"));

    private final SelenideElement workCategoryButton = $(By.cssSelector("button[data-testid=\"category-work\"]"));

    private final SelenideElement personalCategoryButton = $(By.cssSelector("button[data-testid=\"category-personal\"]"));

    private final SelenideElement addNewNoteButton = $(By.cssSelector("button[data-testid=\"add-new-note\"]"));

    private final ElementsCollection notes = $$(By.cssSelector("div[data-testid=\"note-card\"]"));

    @Override
    public MyNotesPage openPage() {
        open(PAGE_URL);
        return this;
    }

    public MyNotesPage searchNote(String searchQuery) {
        searchField.setValue(searchQuery);
        searchButton.click();
        return this;
    }

    public MyNotesPage openCategory(NoteCategory category) {
        switch (category){
            case HOME -> homeCategoryButton.click();
            case WORK -> workCategoryButton.click();
            case PERSONAL -> personalCategoryButton.click();
            default -> allCategoryButton.click();
        }
        return this;
    }

    public NoteEditorPopup addNewNote() {
        addNewNoteButton.click();
        return new NoteEditorPopup();
    }

    public WelcomePage logout() {
        logoutButton.click();
        return new WelcomePage();
    }

    public List<NoteCard> getNotes() {
        return notes.asDynamicIterable().stream()
                .map(NoteCard::new)
                .toList();
    }


    public NoteCard getNoteCardByCondition (Predicate<NoteCard> condition) {
        Selenide.sleep(500);
        allCategoryButton.should(Condition.visible);
        return getNotes().stream().filter(condition).findFirst().orElseThrow();
    }

    public boolean doesNoteCartExist(Predicate<NoteCard> condition) {
        Selenide.sleep(500);
        return getNotes().stream().anyMatch(condition);
    }
}
