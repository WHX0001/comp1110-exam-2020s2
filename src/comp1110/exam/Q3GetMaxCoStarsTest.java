package comp1110.exam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * COMP1110 Exam, Question 3.2
 */
public class Q3GetMaxCoStarsTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    String[] names = new String[]{
            "Malcolm X",
            "Boyz n da Hood",
            "Higher Learning"
    };
    int[] years = new int[]{
            1992,
            1991,
            1995
    };
    String[] directors = new String[]{
            "Spike Lee",
            "John Singleton",
            "John Singleton"
    };

    String[][] actors = new String[][]{
            new String[]{"Denzel Washington", "Angela Bassett", "Spike Lee"},
            new String[]{"Ice Cube", "Cuba Gooding, Jr.", "Angela Bassett", "Laurence Fishburne"},
            new String[]{"Jennifer Connolly", "Ice Cube", "Laurence Fishburne"}
    };

    // FIXME add one or more JUnit unit tests for the getMaxCoStars() method of the Q3Hollywood class
    @Test
    public void testGetMaxCoStars(){
        Q3Hollywood q3Hollywood = new Q3Hollywood();
        q3Hollywood.films.add(new Q3Hollywood.Film(names[0], years[0], directors[0], Set.of(actors[0])));
        assertEquals("getMaxCoStars() returned incorrect value", 2, q3Hollywood.getMaxCoStars());
        q3Hollywood.films.add(new Q3Hollywood.Film(names[1], years[1], directors[1], Set.of(actors[1])));
        assertEquals("getMaxCoStars() returned incorrect value", 5, q3Hollywood.getMaxCoStars());
        q3Hollywood.films.add(new Q3Hollywood.Film(names[2], years[2], directors[2], Set.of(actors[2])));
        assertEquals("getMaxCoStars() returned incorrect value", 5, q3Hollywood.getMaxCoStars());
    }
}
