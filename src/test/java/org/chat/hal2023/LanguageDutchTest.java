package org.chat.hal2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageDutchTest {

    @Test
    public void testGetResponseFile() {
        LanguageStrategy languageStrategy = new LanguageDutch();
        assertEquals(languageStrategy.getResponseFile(), "DocumentatieNL.json");
    }

}