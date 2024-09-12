package com.strajag.log_the_dog;

import com.strajag.log_the_dog.application.DataManager;
import com.strajag.log_the_dog.gui.GUI;

class Main {

    public static void main(String[] args) {
        try {
            new GUI(new DataManager());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}