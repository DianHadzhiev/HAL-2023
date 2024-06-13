package org.chat.hal2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LanguageManagerTest {

    private LanguageContext languageContext;

    private LanguageManager languageManager;

    @BeforeEach
    public void setUp() {
        languageContext = Mockito.mock(LanguageContext.class);
        languageManager = LanguageManager.getInstance(languageContext);
    }

    @Test
    public void testManagerInstance() {
        assertEquals(languageManager.getLanguageContext(), languageContext);
    }

    @Test
    public void testSubscribe() {
        LanguageListener languageListener = Mockito.mock(LanguageListener.class);

        languageManager.subscribe(languageListener);

        assertEquals(languageManager.getListeners().get(0), languageListener);

        languageManager.unsubscribe(languageListener);

        assertTrue(languageManager.getListeners().isEmpty());
    }

    @Test
    public void testChangeLanguageStrategy() {
        Mockito.when(languageContext.getStrategy()).thenReturn(new LanguageDutch());

        languageManager.changeLanguageStrategy();

        Mockito.verify(languageContext, Mockito.times(1)).getStrategy();
        Mockito.verify(languageContext, Mockito.times(1)).setStrategy(Mockito.any(LanguageEnglish.class));
    }

}