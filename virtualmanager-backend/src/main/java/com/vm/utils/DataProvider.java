package com.vm.utils;

import java.util.Collection;

/**
 * QuickTickets Dashboard backend API.
 */
public interface DataProvider {

    /**
     * @return A Collection of movies.
     */
    Collection<Movie> getMovies();

    /**
     * @param movieId
     *            Movie's identifier
     * @return A Movie instance for the given id.
     */
    Movie getMovie(long movieId);

}
