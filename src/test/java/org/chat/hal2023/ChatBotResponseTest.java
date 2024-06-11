package org.chat.hal2023;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.util.io.IOUtil.closeQuietly;

public class ChatBotResponseTest {
    private LanguageManager mockLanguageManager;
    private LanguageStrategy mockLanguageStrategy;
    private LanguageContext mockLanguageContext;
    private ChatBotResponse chatBotResponse;

    @BeforeEach
    public void setUp() {
        mockLanguageManager = mock(LanguageManager.class);
        mockLanguageStrategy = mock(LanguageStrategy.class);
        mockLanguageContext = mock(LanguageContext.class);

        when(mockLanguageStrategy.getResponseFile()).thenReturn("responsesForTest.JSON");
        when(mockLanguageContext.getStrategy()).thenReturn(mockLanguageStrategy);

        chatBotResponse = ChatBotResponse.getInstance(mockLanguageManager);
        chatBotResponse.updateLanguage(mockLanguageContext);
    }

    @Test
    public void testInitialization() {
        verify(mockLanguageManager).subscribe(any(LanguageListener.class));
        assertNotNull(chatBotResponse);
    }

    // Ensure condition, decision, and multiple condition coverage
    @Test
    public void testGetResponse() {
        // Prepare the JSON response file content
        InputStream inputStream = getClass().getResourceAsStream("/test_responses.json");
        JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(inputStream)).getAsJsonArray();
        closeQuietly(inputStream);

        // Mock the updateJSONStream to set our prepared JSON array
        chatBotResponse.updateJSONStream("/test_responses.json");

        // Test with a message that matches a keyword
        String response = chatBotResponse.getResponse("keyword1");
        assertEquals("response1", response);

        // Test with a message that doesn't match any keyword
        response = chatBotResponse.getResponse("unknown");
        assertEquals("No documentation found for: unknown", response);

        // Test with multiple words where one matches the keyword
        response = chatBotResponse.getResponse("this is keyword1");
        assertEquals("response1", response);

        // Test with a message containing keyword in different case
        response = chatBotResponse.getResponse("Keyword1");
        assertEquals("response1", response);

        // Test with multiple words where none match the keyword
        response = chatBotResponse.getResponse("this is unknown");
        assertEquals("No documentation found for: this is unknown", response);
    }

    // Ensure condition, decision, and multiple condition coverage for language update
    @Test
    public void testUpdateLanguage() {
        chatBotResponse.updateLanguage(mockLanguageContext);
        verify(mockLanguageStrategy).getResponseFile();
        assertNotNull(chatBotResponse);
    }

}