package pl.sointeractive.interview.facebook;

public class FacebookServiceFactory {

    public static FacebookService createService() {
        return new FacebookServiceImpl(new FacebookProfilesLoader().loadProfiles());

    }
}
