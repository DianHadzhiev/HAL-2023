package org.chat.hal2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageEnglishTest {

    @Test
    public void testGetResponseFile() {
        LanguageStrategy languageStrategy = new LanguageEnglish();
        assertEquals(languageStrategy.getResponseFile(), "DocumentatieENG.json");
    }

}