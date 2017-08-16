package pl.sointeractive.interview.facebook;

import org.junit.Before;
import org.junit.Test;
import pl.sointeractive.interview.facebook.domain.Facebook;
import pl.sointeractive.interview.facebook.domain.Post;

import java.util.*;

import static org.junit.Assert.*;

public class FacebookServiceImpTest {

    private Facebook profile1;
    private Facebook profile2;
    private Map<String, Facebook> facebookProfilesDataBase;
    private FacebookService facebookService;

    @Before
    public void setUp() {
        facebookProfilesDataBase = new HashMap<>();

        Post post1 = Post.builder()
                .id("1")
                .message("Ala ma kota. ala !!!MA KotA. ZOSIA ma KOTA")
                .build();

        Post post2 = Post.builder()
                .id("2")
                .message("Maja ma kota. maja MA... KotA. MAJA ma KOTA")
                .build();

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

        profile1 = Facebook.builder()
                .id("1")
                .firstname("Jan")
                .lastname("Nowakowski")
                .posts(posts)
                .build();


        facebookProfilesDataBase.put("1", profile1);

        Post post3 = Post.builder()
                .id("3")
                .message("Ala ma psa. ala ^MA Psa...!! Zosia ma PSA")
                .build();

        Post post4 = Post.builder()
                .id("4")
                .message("")
                .build();

        List<Post> posts2 = new ArrayList<>();
        posts.add(post3);
        posts.add(post4);

        profile2 = Facebook.builder()
                .id("2")
                .firstname("Jan")
                .lastname("Kowalski")
                .posts(posts2)
                .build();


        facebookProfilesDataBase.put("2", profile2);
        facebookService = new FacebookServiceImpl(facebookProfilesDataBase);

    }

    @Test
    public void shouldReturnProfile() throws NotFoundException {
        assertEquals(facebookProfilesDataBase.get("1"), facebookService.findById("1"));
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailOnNonExistingProfile() throws NotFoundException {
        facebookService.findById("nonExistingId");
    }

    @Test
    public void findAllTest() {
        final Set<Facebook> all = facebookService.findAll();
        assertEquals(profile2, all.toArray()[0]);
        assertEquals(profile1, all.toArray()[1]);
    }


    @Test
    public void shouldReturnEmptySet() {
        FacebookService facebookService = new FacebookServiceImpl(new HashMap<>());

        final Set<Facebook> all = facebookService.findAll();
        assertTrue(all.isEmpty());

    }


    @Test
    public void findPostIdsByKeywordTest() {
        final Set<String> alaKeywords = facebookService.findPostIdsByKeyword("ala");
        assertTrue(alaKeywords.contains("1"));
        assertFalse(alaKeywords.contains("2"));
        assertTrue(alaKeywords.contains("3"));

        final Set<String> uppercaseAlaKeywords = facebookService.findPostIdsByKeyword("ALA");
        assertTrue(alaKeywords.contains("1"));

        final Set<String> noSuchKeywords = facebookService.findPostIdsByKeyword("Monika");
        assertTrue(noSuchKeywords.isEmpty());
    }

    @Test
    public void findMostCommonWordsTest() {
        final Map<String, Long> mostCommonWords = facebookService.findMostCommonWords();
        assertEquals("ma", mostCommonWords.keySet().toArray()[0]);
        assertEquals((long) 9, mostCommonWords.values().toArray()[0]);
    }

    @Test
    public void shouldReturnEmptyMap() {
        FacebookService facebookService = new FacebookServiceImpl(new HashMap<>());
        final Map<String, Long> mostCommonWords = facebookService.findMostCommonWords();
        assertTrue(mostCommonWords.isEmpty());

    }

}
