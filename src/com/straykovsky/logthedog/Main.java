package com.straykovsky.logthedog;

import com.straykovsky.logthedog.application.DataManager;
import com.straykovsky.logthedog.gui.GUI;

class Main {

    public static void main(String[] args) {
        try {
            new GUI(new DataManager());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}