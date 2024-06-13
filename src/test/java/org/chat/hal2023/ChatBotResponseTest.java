package org.chat.hal2023;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ChatBotResponseTest {

    private static LanguageStrategy languageStrategy;

    private static ChatBotResponse chatBotResponse;

    @BeforeAll
    public static void setUp() {
        languageStrategy = Mockito.mock(LanguageStrategy.class);
        Mockito.when(languageStrategy.getResponseFile()).thenReturn("DocumentatieNL.json");
        chatBotResponse = ChatBotResponse.getInstance(languageStrategy);
    }

    @Test
    public void testResponseInstance() {
        chatBotResponse.updateLanguage(languageStrategy);
        assertEquals(languageStrategy, chatBotResponse.getLanguageStrategy());
    }

    @Test
    public void testGetResponse() {
        chatBotResponse.updateLanguage(languageStrategy);
        assertEquals("Java is een high-level, class-based, object-oriented programming language die is ontworpen om zo min mogelijk implementatie afhankelijkheden te hebben."
                , chatBotResponse.getResponse("java"));
    }

    @Test
    public void testUpdateLanguage() {
        LanguageContext languageContext = Mockito.mock(LanguageContext.class);
        LanguageStrategy languageStrategy1 = Mockito.mock(LanguageStrategy.class);
        Mockito.when(languageStrategy1.getResponseFile()).thenReturn("DocumentatieENG.json");
        Mockito.when(languageContext.getStrategy()).thenReturn(languageStrategy1);

        chatBotResponse.updateLanguage(languageStrategy1);

        assertEquals(chatBotResponse.getLanguageStrategy(), languageStrategy1);
        assertEquals("Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible."
                , chatBotResponse.getResponse("java"));
    }

    @Test
    public void testUpdateWithUnknownLanguageDocument() {
        LanguageStrategy fakeStrategy = Mockito.mock(LanguageStrategy.class);
        Mockito.when(fakeStrategy.getResponseFile()).thenReturn("thisDocumentDoesntExist");
        assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                chatBotResponse.updateLanguage(fakeStrategy);
            }
        });
    }

}