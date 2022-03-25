package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhysicalInfoTest {

    private PhysicalInfo personA;
    private PhysicalInfo personB;
    private ArrayList<Double> kgToLosePerWeek;

    @BeforeEach
    void runBefore() {
        this.personA = new PhysicalInfo(53, 160, 25, true);
        this.personB = new PhysicalInfo(80, 180, 35, false);
        kgToLosePerWeek = new ArrayList<>();
        kgToLosePerWeek.add(0.2);
        kgToLosePerWeek.add(0.5);
        kgToLosePerWeek.add(0.8);
        kgToLosePerWeek.add(1.0);
    }

    @Test
    void testConstructorForFemale() {
        assertEquals(53, personA.getWeight());
        assertEquals(160, personA.getHeight());
        assertEquals(true, personA.getGender());
        assertEquals(25, personA.getAge());
        assertEquals(0, personA.getCaloriesNeeded());
    }

    @Test
    void testConstructorForMale() {
        assertEquals(80, personB.getWeight());
        assertEquals(180, personB.getHeight());
        assertEquals(false, personB.getGender());
        assertEquals(35, personB.getAge());
        assertEquals(0, personB.getCaloriesNeeded());
    }


    @Test
    void testCalculateCaloriesNeededForMaintainWeightForFemale() {

        assertEquals(1244, personA.calculateCaloriesNeededForMaintainWeight());
    }

    @Test
    void testCalculateCaloriesNeededForMaintainWeightForMale() {
        assertEquals(1755, personB.calculateCaloriesNeededForMaintainWeight());
    }

    @Test
    void testCalculateCaloriesToLoseWeightForFemale() {
        assertEquals(1024, personA.calculateCaloriesToLoseWeight(1));
        assertEquals(364, personA.calculateCaloriesToLoseWeight(3));
        assertEquals(694, personA.calculateCaloriesToLoseWeight(2));
        assertEquals(144, personA.calculateCaloriesToLoseWeight(4));
    }

    @Test
    void testCalculateCaloriesToLoseWeightForMale() {
        assertEquals(1535, personB.calculateCaloriesToLoseWeight(1));
        assertEquals(1205, personB.calculateCaloriesToLoseWeight(2));
        assertEquals(875, personB.calculateCaloriesToLoseWeight(3));
        assertEquals(655, personB.calculateCaloriesToLoseWeight(4));
    }

    @Test
    void testToString() {
        assertEquals("Your physical information is  :" +
        "\n weight is 53.0" +
        "\n height is 160" +
        "\n age is 25" +
        "\n gender is female" +
        "\n you need 0.0 calories", personA.toString());
    }

    @Test
    void testToStringMale() {
        personB.toString();
        assertEquals("Your physical information is  :" +
                "\n weight is 80.0" +
                "\n height is 180" +
                "\n age is 35" +
                "\n gender is male" +
                "\n you need 0.0 calories", personB.toString());
    }

    @Test
    void testPhysicalInfoTest() {
        personA.setPhysicalInfo(90,160,22,false);
        assertEquals(22,personA.getAge());
        assertEquals(90,personA.getWeight());
        assertEquals(160,personA.getHeight());
        assertEquals(false, personA.getGender());
    }


}

