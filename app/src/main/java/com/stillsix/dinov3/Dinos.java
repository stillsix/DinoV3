package com.stillsix.dinov3;

public class Dinos {
    public int ID;
    public int icon;
    public String title;
    public int diet_icon;
    public int element_icon;
    public int era_icon;

    public Dinos(){
        super();
    }

    public Dinos(int ID, int icon, String title, int diet_icon, int element_icon, int era_icon) {
        super();
        this.ID = ID;
        this.icon = icon;
        this.title = title;
        this.diet_icon = diet_icon;
        this.element_icon = element_icon;
        this.era_icon = era_icon;
    }

    @Override
    public String toString() {
        return this.title ;
    }
}
