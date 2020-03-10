package Testing;

import model.Validation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {

    Validation val;
    String onlyLetters;
    String onlyNumbers;
    String combination;

    @Before
    public void setUp() throws Exception {
        val = new Validation();
        onlyLetters = "qwerty";
        onlyNumbers = "123456";
        combination = "q2e4t6";
    }

    @Test
    public void onlyLetters() {
        Assert.assertTrue(val.onlyLetters(onlyLetters));
        Assert.assertFalse(val.onlyLetters(onlyNumbers));
        Assert.assertFalse(val.onlyLetters(combination));
    }

    @Test
    public void onlyNumbers() {
        Assert.assertFalse(val.onlyNumbers(onlyLetters));
        Assert.assertTrue(val.onlyNumbers(onlyNumbers));
        Assert.assertFalse(val.onlyNumbers(combination));
    }

    @Test
    public void onlyLettersAndNumbers() {
        Assert.assertTrue(val.onlyLettersAndNumbers(onlyLetters));
        Assert.assertTrue(val.onlyLettersAndNumbers(onlyNumbers));
        Assert.assertTrue(val.onlyLettersAndNumbers(combination));
    }
}