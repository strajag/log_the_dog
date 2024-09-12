package com.strajag.log_the_dog.application;

import java.awt.image.BufferedImage;

public class Dog {

    public Owner owner;
    public String name;
    public String trainingReason;
    public String notes;
    public int trainingPrice;
    public long timeArrived;
    public long timeTrainingEnds;
    public long timeCreated;
    public BufferedImage image;

    @Override
    public String toString() { return name; }
}

