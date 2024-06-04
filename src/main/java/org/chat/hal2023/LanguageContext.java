package org.chat.hal2023;

/**
 * The context class of the Strategy-Pattern. This class contains and changes
 * the strategy (or language) that is currently used in the program.
 *
 * @author Anne
 */
public class LanguageContext {

    private LanguageStrategy strategy;

    public LanguageContext() {
        this.strategy = new LanguageDutch();
    }

    /**
     * Getter for the {@link LanguageContext#strategy strategy} field.
     * @return the current strategy
     */
    public LanguageStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * Setter for the {@link LanguageContext#strategy strategy} field.
     * @param strategy The new strategy
     */
    public void setStrategy(LanguageStrategy strategy) {
        this.strategy = strategy;
    }

}
