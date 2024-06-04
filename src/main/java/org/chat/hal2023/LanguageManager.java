package org.chat.hal2023;

import java.util.ArrayList;

public class LanguageManager {

    /**
     * List containing all objects that need to be kept up to date with the
     * used language.
     */
    private final ArrayList<LanguageListener> listeners;

    /**
     * Object that contains the current LanguageStrategy used.
     */
    private final LanguageContext languageContext;

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

    /**
     * Remove a listener from the list of object to be updated when the
     * language is changed.
     * @param listener Listener to be removed
     */
    public void unsubscribe(LanguageListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Change the language in all the listener objects in the
     * {@link LanguageManager#listeners listeners} list.
     */
    public void updateListeners() {
        for (LanguageListener l : listeners) {
            l.updateLanguage(this.languageContext);
        }
    }

    /**
     * Switch from one language to the other. Currently only supports two
     * languages.
     */
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
