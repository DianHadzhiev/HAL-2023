package org.chat.hal2023;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the proper behaviour of the {@link LanguageContext LanguageContext}
 * class.
 *
 * @author Anne
 */
class LanguageContextTest {

    /**
     * Check that upon creation a {@link LanguageContext LanguageContext}
     * object the proper strategy is picked. In this version, this is
     * a {@link LanguageDutch LanguageDutch} strategy object.
     */
    @Test
    public void testConstructor() {
        LanguageContext languageContext = new LanguageContext();
        assertTrue(languageContext.getStrategy() instanceof LanguageDutch);
    }

    /**
     * Check that after calling the
     * {@link LanguageContext#setStrategy(LanguageStrategy) setStrategy} method
     * the context has changed.
     */
    @Test
    public void testChangeContext() {
        LanguageContext languageContext = new LanguageContext();
        languageContext.setStrategy(new LanguageEnglish());
        assertFalse(languageContext.getStrategy() instanceof LanguageDutch);
        assertTrue(languageContext.getStrategy() instanceof  LanguageEnglish);
    }
}