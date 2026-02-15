package com.example.languagestranslator;

public class ModelLanguage {
    //variables for Lanugage code e,g, en and language title e.g. English
    String languageCode;
    String languageTitle;
    //constructor
    public ModelLanguage (String languageCode, String languageTitle) {
        this.languageCode = languageCode;
        this.languageTitle = languageTitle;
    }
 /*-----Getter Setters---- */
 public String getLanguageCode() {
     return languageCode;
 }
 public void setLanguageCode (String languageCode) {
    this.languageCode = languageCode;
 }
 public String getLanguageTitle() {
     return languageTitle;
 }
 public void setLanguageTitle (String languageTitle) {
    this.languageTitle = languageTitle;
 }

}
