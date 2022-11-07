package comp1110.exam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * COMP1110 Exam, Question 3.1
 * <p>
 * This class represents a library of films.
 * Each film has a unique name, a year of production, a single director,
 * and one or more actors.
 * Each person (actor or director) is uniquely identified by their name, and a
 * person may be either an actor, or a director, or both, for one or more films.
 */
public class Q3Hollywood {
    ArrayList<Film> films = new ArrayList<>();
    static class Film{
        String filmName;
        int year;
        String director;
        Set<String> actors = new HashSet<>();

        public Film(String filmName, int year, String director, Set<String> actors) {
            this.filmName = filmName;
            this.year = year;
            this.director = director;
            this.actors = actors;
        }
    }
    /**
     * Add a new film to this library. If the given film name exists, do not
     * modify this library.
     *
     * @param filmName the name of the film
     * @param year     the year that the film was produced
     * @param director the name of the director of the film
     * @param actors   the names of the actors of the film
     * @return true if the film was added to this library, or false if the
     * film was not added (because a film with the given name already exists)
     */
    public boolean addFilm(String filmName, int year, String director, Set<String> actors) {
        // FIXME complete this method
        for(Film film:films){
            if(film.filmName.equals(filmName)){
                return false;
            }
        }
        films.add(new Film(filmName, year, director, actors));
        return true;
    }


    /**
     * Remove the film with the given name from the library.
     * If no film with the given name exists, do not modify this library.
     *
     * @param filmName the name of the film to be removed
     * @return true if the removal was successful, otherwise false
     */
    public boolean deleteFilm(String filmName) {
        // FIXME complete this method
        for(Film film:films){
            if(film.filmName.equals(filmName)){
                films.remove(film);
                return true;            }
        }
        return false;
    }

    /**
     * @return the total number of films in this library
     */
    public int getFilmCount() {
        // FIXME complete this method
        return films.size();
    }

    /**
     * Get the names of all films that were directed by a particular person.
     * If the given person has not directed any film, return an empty set.
     *
     * @param directorName the name of a director
     * @return the names of all films for which the given person was director
     */
    public Set<String> getFilmsDirectedBy(String directorName) {
        // FIXME complete this method
        Set<String> res= new HashSet<>();
        for(Film film:films){
            if(film.director.equals(directorName)){
                res.add(film.filmName);
            }
        }
        return res;
    }

    /**
     * Get the names of all films in which the given person has been either
     * an actor or a director.
     * If the given person has not acted or directed in any film, return an
     * empty set.
     *
     * @param personName the name of a person
     * @return the names of all films for which the given person was either
     * an actor or a director
     */
    public Set<String> getFilms(String personName) {
        // FIXME complete this method
        Set<String> res= new HashSet<>();
        for(Film film:films){
            if(film.director.equals(personName)||film.actors.contains(personName)){
                res.add(film.filmName);
            }
        }
        return res;
    }

    /**
     * Gets the total number of unique actors across all films in this library.
     * Each actor is only counted once, even if they have acted in multiple
     * films. Directors are not included in this count unless they have also
     * been actors.
     * For example, if there are four films in the library:
     * "Malcolm X", 1992, director: "Spike Lee", actors: "Denzel Washington", "Angela Bassett", "Spike Lee"
     * "Boyz n da Hood", 1991, director: "John Singleton", actors: "Ice Cube", "Cuba Gooding, Jr.", "Angela Bassett", "Laurence Fishburne"
     * "Higher Learning", 1995, director: "John Singleton", actors: "Jennifer Connolly", "Ice Cube", "Laurence Fishburne"
     * "Waiting to Exhale", 1995, director: "Forest Whitaker", actors: "Whitney Houston", "Angela Bassett"
     * then getNumActors() == 8.
     *
     * @return the number of unique actors across all films
     */
    public int getNumActors() {
        // FIXME complete this method
        Set<String> res= new HashSet<>();
        for(Film film:films) {
            for (String actor : film.actors){
                res.add(actor);
            }
        }
        return res.size();
    }

    /**
     * Checks whether two people are both actors in the same film.
     *
     * @return true if person1 and person2 have both been actors in the same film
     */
    public boolean areCoStars(String person1, String person2) {
        // FIXME complete this method
        for(Film film:films) {
            if(film.actors.contains(person1) && film.actors.contains(person2)){
                return true;
            }
        }
        return false;
    }

    /**
     * @return the first year in which the given person was either
     * an actor or director in any film, or -1 if they were not an actor
     * or director for any film
     */
    public int getFirstFilmYear(String personName) {
        // FIXME complete this method
        Set<Integer> years = new HashSet<>();
        for(Film film:films) {
            if(film.actors.contains(personName) || film.director.equals(personName)){
                years.add(film.year);
            }
        }
        return years.stream().min(Integer::compare).get();
    }

    /**
     * Gets the greatest number of films that any person has been either an
     * actor or director in.
     * If a person is both an actor and director in the same film, that film
     * only counts once.
     * <p>
     * For example, if there are four films in the library:
     * "Malcolm X", 1992, director: "Spike Lee", actors: "Denzel Washington", "Angela Bassett", "Spike Lee"
     * "Boyz n da Hood", 1991, director: "John Singleton", actors: "Ice Cube", "Cuba Gooding, Jr.", "Angela Bassett", "Laurence Fishburne"
     * "Higher Learning", 1995, director: "John Singleton", actors: "Jennifer Connolly", "Ice Cube", "Laurence Fishburne"
     * "Waiting to Exhale", 1995, director: "Forest Whitaker", actors: "Whitney Houston", "Angela Bassett"
     * then getMaxFilms() == 3 (for the actor Angela Bassett)
     *
     * @return the maximum number of films for any person
     */
    public int getMaxFilms() {
        // FIXME complete this method
        Set<String> peopleList= new HashSet<>();
        for(Film film:films){
            peopleList.add(film.director);
            for(String actor: film.actors){
                peopleList.add(actor);
            }
        }
        int max = 0;
        for(String person: peopleList){
            int count = 0;
            for(Film film: films){
                if(film.actors.contains(person)||film.director.equals(person)){
                    count++;
                }
            }
            max = Integer.max(max, count);
        }
        return max;
    }

    /**
     * Gets the maximum number of unique co-stars of any actor.
     * Two actors are co-stars if they are both actors in the same film.
     * Only unique co-stars are counted i.e. if two actors appear together
     * in multiple films, this only adds one towards the total number
     * of co-stars.
     * <p>
     * For example, if there are three films in the library:
     * "Malcolm X", 1992, director: "Spike Lee", actors: "Denzel Washington", "Angela Bassett", "Spike Lee"
     * "Boyz n da Hood", 1991, director: "John Singleton", actors: "Ice Cube", "Cuba Gooding, Jr.", "Angela Bassett", "Laurence Fishburne"
     * "Higher Learning", 1995, director: "John Singleton", actors: "Jennifer Connolly", "Ice Cube", "Laurence Fishburne"
     * then getMaxCoStars() == 5 for the actor Angela Bassett. Note that
     * although Ice Cube and Laurence Fishburne are co-stars in two films,
     * this only counts once for both and so they only have four co-stars each.
     *
     * @return the maximum number of co-stars for any actor
     */
    public int getMaxCoStars() {
        // FIXME complete this method
        Set<String> actorsList= new HashSet<>();
        for(Film film:films){
            for(String actor: film.actors){
                actorsList.add(actor);
            }
        }
        int max = 0;
        for(String actor: actorsList){
            Set<String> coActorsList= new HashSet<>();
            for(Film film:films){
                if(film.actors.contains(actor)){
                    coActorsList.addAll(film.actors);
                }
            }
            coActorsList.remove(actor);
            max = Integer.max(max, coActorsList.size());
        }
        return max;
    }
}
