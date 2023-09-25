package org.example;

public class Profession {
    int id;
    String titleProfession;

    public Profession(int id, String titleProfession) {
        this.id = id;
        this.titleProfession = titleProfession;
    }

    public String getTitleProfession() {
        return titleProfession;
    }
}
