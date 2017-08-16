package pl.sointeractive.interview.facebook;


import pl.sointeractive.interview.facebook.domain.Facebook;
import pl.sointeractive.interview.facebook.domain.Post;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FacebookServiceImpl implements FacebookService {

    public static final String SPLIT_TO_WORDS_REGEX = "\\W+";
    private final Map<String, Facebook> facebookRepository;

    public FacebookServiceImpl(Map<String, Facebook> facebookRepository) {
        this.facebookRepository = facebookRepository;
    }

    @Override
    public Facebook findById(String id) throws NotFoundException {
        return Optional.ofNullable(facebookRepository.get(id))
                .orElseThrow(() -> new NotFoundException("No profile of id " + id));
    }

    @Override
    public Map<String, Long> findMostCommonWords() {

        return facebookRepository
                .values()
                .stream()
                .filter(Objects::nonNull)
                .flatMap(profile -> profile.getPosts().stream())
                .filter(Objects::nonNull)
                .map(Post::getMessage)
                .flatMap(message -> Arrays.stream(message.split(SPLIT_TO_WORDS_REGEX)))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((w1, w2) -> w2.getValue().compareTo(w1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x, y) -> x,
                        LinkedHashMap::new));
    }

    @Override
    public Set<String> findPostIdsByKeyword(String word) {

        return facebookRepository
                .values()
                .stream()
                .flatMap(profile -> profile.getPosts().stream())
                .filter(post -> post.getMessage().toLowerCase().contains(word.toLowerCase()))
                .map(Post::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Facebook> findAll() {
        return facebookRepository
                .values()
                .stream()
                .sorted(
                        Comparator.comparing(Facebook::getFirstname)
                                .thenComparing(Facebook::getLastname)
                )
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
