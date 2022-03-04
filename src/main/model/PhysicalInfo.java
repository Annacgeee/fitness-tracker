package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


public class PhysicalInfo implements Writable {
    private boolean gender;
    private int age;
    private double weight;
    private int height;

    private double caloriesNeeded;
    private static int caloriesNeededToLoseOneKG = 7700;// to lose 1kg, people must have 7700 calories deficit
    private ArrayList<Double> kgToLosePerWeek; // create a list to store week lose weight goal in kg


    //REQUIRES:weight, height, age must >0，gender has to be F or M
    //MODIFIES: this
    //EFFECTS: this is a constructor, construct users weight,height,age,gender according to user input
    //
    public PhysicalInfo(double w, int h, int age, boolean gender) {
        this.weight = w;
        this.height = h;
        this.age = age;
        this.gender = gender;
        this.caloriesNeeded = 0;
        kgToLosePerWeek = new ArrayList<>();
        kgToLosePerWeek.add(0.2);
        kgToLosePerWeek.add(0.5);
        kgToLosePerWeek.add(0.8);
        kgToLosePerWeek.add(1.0);
    }

    public PhysicalInfo(double w, int h, int age, boolean gender, double caloriesNeeded) {
        this.weight = w;
        this.height = h;
        this.age = age;
        this.gender = gender;
        this.caloriesNeeded = caloriesNeeded;
        kgToLosePerWeek = new ArrayList<>();
        kgToLosePerWeek.add(0.2);
        kgToLosePerWeek.add(0.5);
        kgToLosePerWeek.add(0.8);
        kgToLosePerWeek.add(1.0);
    }


    public int getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getAge() {
        return this.age;
    }

    public boolean getGender() {
        return this.gender;
    }

    public double getCaloriesNeeded() {
        return this.caloriesNeeded;
    }

    //EFFECTS:return string representation of physical info
    public String toString() {
        String genderStr = gender ? "female" : "male";
        return "Your physical information is "
                + " :" +  "\n weight is "
                + weight + "\n height is "
                + height + "\n age is "
                + age + "\n gender is "
                + genderStr + "\n you need "
                + caloriesNeeded + " calories";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("weight", weight);
        json.put("height", height);
        json.put("age", age);
        json.put("gender", gender);
        json.put("caloriesNeeded", caloriesNeeded);
        return json;
    }

    //REQUIRES: user has valid input
    // MODIFIES: this
    // EFFECT: calculate how many calories does the user need(for maintain weight) according to user's weight height and
    //         age, gender (different gender use different calculation formula)
    public double calculateCaloriesNeededForMaintainWeight() {
        if (this.getGender() == true) {
            return this.caloriesNeeded = 10 * this.weight + 6.25 * this.height - 5 * this.age - 161;
        }
        return this.caloriesNeeded = 10 * this.weight + 6.25 * this.height - 5 * this.age + 5;
    }



    //REQUIRES: user has valid input
    //MODIFIES: this
    //EFFECT: calculate how many calories does the user need (for lose weight) according to user's physical info input.
    //        different weekly lose weight goal should result in largely different calories needed.
    //        the more user want to lose in a week the lower calories needed per day
    public double calculateCaloriesToLoseWeight(int mySelect) {
        if (this.getGender() == true) {
            return this.caloriesNeeded = (10 * this.weight + 6.25 * this.height - 5 * this.age - 161)
                    - ((caloriesNeededToLoseOneKG * kgToLosePerWeek.get(mySelect - 1)) / 7);
        } else {
            return this.caloriesNeeded = (10 * this.weight + 6.25 * this.height - 5 * this.age + 5)
                    - ((caloriesNeededToLoseOneKG * kgToLosePerWeek.get(mySelect - 1)) / 7);
        }
    }



}
