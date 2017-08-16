package pl.sointeractive.interview.facebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sointeractive.interview.facebook.domain.Facebook;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FacebookProfilesLoader {


    private static final String PATH_TO_PROFILES = "facebook-profiles/";
    private static final List<String> profilesFiles = Arrays.asList("f1.json", "f2.json", "f3.json", "f4.json", "f5.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Facebook> loadProfiles() {
        Map<String, Facebook> mapOfProfiles = new TreeMap<>();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        profilesFiles.stream()
                .map(file -> classLoader.getResource(PATH_TO_PROFILES + file))
                .filter(Objects::nonNull)
                .map(resource -> new File(resource.getFile()))
                .map(this::mapToFacebook)
                .filter(Objects::nonNull)
                .forEach(facebook -> mapOfProfiles.put(facebook.getId(), facebook));

        return mapOfProfiles;
    }

    private Facebook mapToFacebook(File file) {
        try {
            return mapper.readValue(file, Facebook.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


