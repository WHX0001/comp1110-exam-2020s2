package comp1110.exam;

import java.util.Random;

/**
 * COMP1110 Final Exam, Question 5
 *
 * 5 Marks
 *
 * This class represents a type of car, which is uniquely identified by a
 * combination of make, model, and year, for example: "Hyundai i30 2019".
 */
public class Q5Car {
    /**
     * The make (manufacturer) of this car e.g. "Toyota".
     */
    String make;

    /**
     * The model name of this car e.g. "Corolla".
     */
    String model;

    /**
     * The model year (version) of this car e.g. "2018".
     */
    int year;

    public Q5Car(String make, String model, int year) {
        if (make == null) throw new IllegalArgumentException("make may not be null!");
        this.make = make;
        if (model == null) throw new IllegalArgumentException("model may not be null!");
        this.model = model;
        this.year = year;
    }

    /**
     * @return a hash code value for this car
     * In implementing this method, you may *not* use the default Java
     * implementations in Object.hashCode() or Objects.hash().
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        // FIXME complete this method
        int h = 0;
        String str = this.make+this.model+this.year;
        for(int i = 0; i<str.length(); i++){
            h = 11*h + str.charAt(i);
        }
        return h;
    }

    /**
     * @return true if this car is equal to the provided object
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        // FIXME complete this method
        if(object == this){
            return true;
        }
        if(!(object instanceof Q5Car)){
            return false;
        }
        Q5Car q5Car = (Q5Car) object;

        boolean makeEquals = (this.make == null && q5Car.make == null)
                || (this.make != null && this.make.equals(q5Car.make));
        boolean modelEquals = (this.model == null && q5Car.model == null)
                || (this.model != null && this.model.equals(q5Car.model));
        boolean yearEquals = (this.year == q5Car.year);

        return makeEquals && modelEquals && yearEquals;
    }

    @Override
    public String toString() {
        return make + " " + model + " " + year;
    }

    // DO NOT DELETE OR CALL THIS METHOD
    int passThroughHash() {
        return super.hashCode();
    }
}
