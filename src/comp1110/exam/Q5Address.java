package comp1110.exam;

import java.util.Random;

/**
 * COMP1110 Final Exam, Question 5
 *
 * 5 Marks
 *
 * This class represents a street address within the Australian Capital Territory,
 * for example "108 North Road 2601".
 */
public class Q5Address {
    /**
     * The street number of the address (must be greater than zero) e.g. 108.
     */
    int streetNumber;

    /**
     * The street name is a string of ASCII characters e.g. "North Road"
     */
    String streetName;

    /**
     * The postcode is a number from 0 to 9999.
     */
    int postCode;

    public Q5Address(int streetNumber, String streetName, int postCode) {
        if (streetName == null) throw new IllegalArgumentException("Street name may not be null!");
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postCode = postCode;
    }

    /**
     * @return a hash code value for this object.
     * In implementing this method, you may *not* use the default Java
     * implementations in Object.hashCode() or Objects.hash().
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        // FIXME complete this method
        int res = 0;
        for(int i = 0; i< this.streetName.length(); i++){
            res += streetName.charAt(i);
        }
        res += (this.streetNumber+this.postCode);
        return res;
    }

    /**
     * @return true if this address is equal to the provided object
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        // FIXME complete this method
        if(object == this){
            return true;
        }
        if(!(object instanceof Q5Address)){
            return false;
        }
        Q5Address other = (Q5Address) object;
        boolean streetNameEquals = (this.streetName == null && other.streetName == null)
                || (this.streetName != null && this.streetName.equals(other.streetName));
        boolean streetNumberEquals = (this.streetNumber == other.streetNumber);
        boolean postCodeEquals = (this.postCode==other.postCode);
        return streetNameEquals && streetNumberEquals &postCodeEquals;
    }

    @Override
    public String toString() {
        return streetNumber + " " + streetName + " " + postCode;
    }

    // DO NOT DELETE OR CALL THIS METHOD
    int passThroughHash() {
        return super.hashCode();
    }
}
