package org.chat.hal2023;

public class LanguageContext {

    private LanguageStrategy strategy;

    public LanguageContext() {
        this.strategy = new LanguageDutch();
    }

    public LanguageStrategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(LanguageStrategy strategy) {
        this.strategy = strategy;
    }

}
