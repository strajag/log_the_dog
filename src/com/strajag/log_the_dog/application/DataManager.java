package com.strajag.log_the_dog.application;

import com.strajag.log_the_dog.Tools;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DataManager {

    public File ownersFolder;
    public String dogsFolderNameWithSlashes;
    public String infoFileNameWithSlash;
    public String imageFileNameWithSlash;
    public List<Owner> ownerList;
    public List<Dog> dogList;

    public DataManager() throws Exception {
        ownersFolder = new File("./data/owners");
        dogsFolderNameWithSlashes = "/dogs/";
        infoFileNameWithSlash = "/info";
        imageFileNameWithSlash = "/image";
        ownerList = new ArrayList<>();
        dogList = new ArrayList<>();
        readAllData();
    }

    private void readAllData() throws Exception {
        List<String> lines;
        for (Owner owner : ownerList) { owner.dogList.clear(); }
        ownerList.clear();
        dogList.clear();

        Owner owner;
        Dog dog;
        File imageFile;
        File[] ownerFolders = ownersFolder.listFiles();
        File[] dogFolders;
        if (ownerFolders == null) { throw new Exception("Failed to list ownerFolders - " + ownersFolder); }
        for (File ownerFolder : ownerFolders) {
            lines = Files.readAllLines(Path.of(ownerFolder + infoFileNameWithSlash));
            owner = new Owner();
            owner.firstName = lines.get(0);
            owner.lastName = lines.get(1);
            owner.phoneNumber = lines.get(2);
            owner.address = lines.get(3);
            owner.email = lines.get(4);
            owner.notes = lines.get(5);
            owner.timeCreated = Long.parseLong(ownerFolder.getName());
            imageFile = new File(ownerFolder + imageFileNameWithSlash);
            if (imageFile.exists()) { owner.image = ImageIO.read(imageFile); } else { owner.image = null; }
            ownerList.add(owner);

            dogFolders = new File(ownerFolder + dogsFolderNameWithSlashes).listFiles();
            if (dogFolders == null) { throw new Exception("Failed to list dogFolders - " + ownerFolder + dogsFolderNameWithSlashes); }
            for (File dogFolder : dogFolders) {
                lines = Files.readAllLines(Path.of(dogFolder + infoFileNameWithSlash));
                dog = new Dog();
                dog.owner = owner;
                dog.name = lines.get(0);
                dog.trainingReason = lines.get(1);
                dog.notes = lines.get(2);
                dog.trainingPrice = Integer.parseInt(lines.get(3));
                dog.timeArrived = Long.parseLong(lines.get(4));
                dog.timeTrainingEnds = Long.parseLong(lines.get(5));
                dog.timeCreated = Long.parseLong(dogFolder.getName());
                imageFile = new File(dogFolder + imageFileNameWithSlash);
                if (imageFile.exists()) { dog.image = ImageIO.read(imageFile); } else { dog.image = null; }
                owner.dogList.add(dog);
                dogList.add(dog);
            }
        }
    }

    public void addOwner(Owner owner) throws Exception {
        File ownerFolder = new File(ownersFolder + "/" + System.currentTimeMillis());
        try {
            while (ownerFolder.exists()) { ownerFolder = new File(ownerFolder.getParent() + "/" + System.currentTimeMillis()); }
            if (!ownerFolder.mkdir()) { throw new Exception("Failed to create ownerFolder - " + ownerFolder); }
            File dogFolder = new File(ownersFolder + "/" + ownerFolder.getName() + dogsFolderNameWithSlashes);
            if (!dogFolder.mkdir()) { throw new Exception("Failed to create dogFolder - " + dogFolder); }

            File imageFile = new File(ownerFolder + imageFileNameWithSlash);
            if (owner.image != null) { if (!ImageIO.write(owner.image, "png", imageFile)) { throw new Exception("Failed to create imageFile - " + imageFile); } }

            List<String> lines = Arrays.asList(owner.firstName, owner.lastName, owner.phoneNumber, owner.address, owner.email, owner.notes);
            Files.write(Path.of(ownerFolder + infoFileNameWithSlash), lines);
            owner.timeCreated = Long.parseLong(ownerFolder.getName());
            ownerList.add(owner);
        } catch (Exception exception) {
            if (ownerFolder.exists()) { Tools.deleteFolder(ownerFolder); }
            throw exception;
        }
    }

    public void editOwner(Owner owner) throws Exception {
        if (!ownerList.contains(owner)) { throw new Exception("Owner does not exist - " + owner); }

        File imageFile = new File(ownersFolder + "/" + owner.timeCreated + imageFileNameWithSlash);
        if (owner.image != null) {
            if (!ImageIO.write(owner.image, "png", imageFile)) { throw new Exception("Failed to create imageFile - " + imageFile);  }
        } else {
            if (imageFile.exists()) { if (!imageFile.delete()) { throw new Exception("Failed to delete imageFile - " + imageFile); } }
        }

        List<String> lines = Arrays.asList(owner.firstName, owner.lastName, owner.phoneNumber, owner.address, owner.email, owner.notes);
        Files.write(Path.of(ownersFolder + "/" + owner.timeCreated + infoFileNameWithSlash), lines);
    }

    public void removeOwner(Owner owner) throws Exception {
        if (!ownerList.contains(owner)) { throw new Exception("Owner does not exist - " + owner); }
        Tools.deleteFolder(new File(ownersFolder + "/" + owner.timeCreated));
        for (Dog dog : owner.dogList) { dogList.remove(dog); }
        owner.dogList.clear();
        ownerList.remove(owner);
    }

    public void removeOwner(int index) throws Exception { removeOwner(ownerList.get(index)); }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addDog(Dog dog) throws Exception {
        if (!ownerList.contains(dog.owner)) { throw new Exception("Owner does not exist - " + dog.owner); }
        File dogFolder = new File(ownersFolder + "/" + dog.owner.timeCreated + dogsFolderNameWithSlashes + System.currentTimeMillis());
        try {
            while (dogFolder.exists()) { dogFolder = new File(dogFolder.getParent() + "/" + System.currentTimeMillis()); }
            if (!dogFolder.mkdir()) { throw new Exception("Failed to create dogFolder - " + dogFolder); }

            File imageFile = new File(dogFolder + imageFileNameWithSlash);
            if (dog.image != null) { if (!ImageIO.write(dog.image, "png", imageFile)) { throw new Exception("Failed to create imageFile - " + imageFile); } }

            List<String> lines = Arrays.asList(dog.name, dog.trainingReason, dog.notes, Integer.toString(dog.trainingPrice), Long.toString(dog.timeArrived), Long.toString(dog.timeTrainingEnds));
            Files.write(Path.of(dogFolder + infoFileNameWithSlash), lines);
            dog.timeCreated = Long.parseLong(dogFolder.getName());
            dog.owner.dogList.add(dog);
            dogList.add(dog);
        } catch (Exception exception) {
            if (dogFolder.exists()) { Tools.deleteFolder(dogFolder); }
            throw exception;
        }
    }

    public void editDog(Dog dog) throws Exception {
        if (!ownerList.contains(dog.owner)) { throw new Exception("Owner does not exist - " + dog.owner); }
        if (!dogList.contains(dog)) { throw new Exception("Dog does not exist - " + dog); }
        if (!dog.owner.dogList.contains(dog)) { throw new Exception("Dog does not exist - " + dog); }

        File imageFile = new File(ownersFolder + "/" + dog.owner.timeCreated + dogsFolderNameWithSlashes + dog.timeCreated + imageFileNameWithSlash);
        if (dog.image != null) {
            if (!ImageIO.write(dog.image, "png", imageFile)) { throw new Exception("Failed to create imageFile - " + imageFile); }
        } else {
            if (imageFile.exists()) { if (!imageFile.delete()) { throw new Exception("Failed to delete imageFile - " + imageFile); } }
        }

        List<String> lines = Arrays.asList(dog.name, dog.trainingReason, dog.notes, Integer.toString(dog.trainingPrice), Long.toString(dog.timeArrived), Long.toString(dog.timeTrainingEnds));
        Files.write(Path.of(ownersFolder + "/" + dog.owner.timeCreated + dogsFolderNameWithSlashes + dog.timeCreated + infoFileNameWithSlash), lines);
    }

    public void removeDog(Dog dog) throws Exception {
        if (!dog.owner.dogList.contains(dog)) { throw new Exception("Dog does not exist - " + dog); }
        Tools.deleteFolder(new File(ownersFolder + "/" + dog.owner.timeCreated + dogsFolderNameWithSlashes + dog.timeCreated));
        dog.owner.dogList.remove(dog);
        dogList.remove(dog);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void remove(Object object) throws Exception {
        if (object instanceof Owner) {
            removeOwner((Owner) object);
        } else if (object instanceof Dog) {
            removeDog((Dog) object);
        } else { throw new Exception("Failed to remove object - the object is neither a owner nor a dog"); }
    }
}
