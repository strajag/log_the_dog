package com.strajag.log_the_dog.application;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Owner {

    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address;
    public String email;
    public String notes;
    public long timeCreated;
    public BufferedImage image;
    public List<Dog> dogList = new ArrayList<>();

    @Override
    public String toString() { return firstName + " " + lastName; }
}
