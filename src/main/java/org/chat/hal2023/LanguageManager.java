package org.chat.hal2023;

import java.util.ArrayList;

public class LanguageManager {

    private final ArrayList<LanguageListener> listeners;

    private LanguageContext languageContext;

    public LanguageManager() {
        this.listeners = new ArrayList<>();
        this.languageContext = new LanguageContext();
    }

    /**
     * Add a new {@link LanguageListener listener} to the
     * {@link LanguageManager#listeners listeners} list.
     * @param listener the new listener to be added
     */
    public void subscribe(LanguageListener listener) {
        listener.updateLanguage(this.languageContext);
        this.listeners.add(listener);
    }

    public void unsubscribe(LanguageListener listener) {
        this.listeners.remove(listener);
    }

    public void updateListeners() {
        for (LanguageListener l : listeners) {
            l.updateLanguage(this.languageContext);
        }
    }

    public void changeLanguageStrategy() {
        if (this.languageContext.getStrategy() instanceof LanguageDutch) {
            this.languageContext.setStrategy(new LanguageEnglish());
        } else if (this.languageContext.getStrategy() instanceof  LanguageEnglish) {
            this.languageContext.setStrategy(new LanguageDutch());
        } else {
            throw new RuntimeException("Someone didn't implement what happens with a new strategy :3");
        }
        this.updateListeners();
    }
}
