package pl.sointeractive.interview.facebook;


import pl.sointeractive.interview.facebook.domain.Facebook;

import java.util.Map;
import java.util.Set;

public interface FacebookService {
    /**
     * Zwraca obiekt reprezentujący profil Facebooka na podstawie id
     * w czasie logarytmicznym
     */
    Facebook findById(String id) throws NotFoundException;

    /**
     * Zwraca mapę której kluczem jest słowo a wartością liczba jego
     * wystąpień - pod uwagę brane są wszystkie posty
     */
    Map<String, Long> findMostCommonWords();

    /**
     * Zwraca zbiór id Postów zawierających słowo word
     */
    Set<String> findPostIdsByKeyword(String word);

    /**
     * Zwraca zbiór obiektów reprezentujących profile Facebooka
     * posortowane po firstname, lastname
     */
    Set<Facebook> findAll();

}
