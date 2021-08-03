package badcode;

import java.util.Arrays;

public class RegisterBusiness {

    public Integer register(SpeakerRepository repository, Speaker speaker) {
        Integer speakerId;

        validateSpeaker(speaker);

        // Main process :: Save Speaker to Repository
        int exp = speaker.getExp();
        speaker.setRegistrationFee(getFee(exp));
        try {
            speakerId = repository.saveSpeaker(speaker);
        } catch (Exception exception) {
            throw new SaveSpeakerException("Can't save a speaker.");
        }

        return speakerId;
    }

    private void validateSpeaker(Speaker speaker) {
        if (isEmptyOrNull(speaker.getFirstName())) {
            throw new ArgumentNullException("First name is required.");
        }
        if (isEmptyOrNull(speaker.getLastName())) {
            throw new ArgumentNullException("Last name is required.");
        }
        if (isEmptyOrNull(speaker.getEmail())) {
            throw new ArgumentNullException("Email is required.");
        }
        if (isValidEmailDomain(speaker.getEmail())) {
            throw new SpeakerDoesntMeetRequirementsException("Speaker doesn't meet our standard rules.");
        }
    }

    private boolean isValidEmailDomain(String email) {
        String[] domainsOfEmail = {"gmail.com", "live.com"};
        String emailDomain = getEmailDomain(email);
        return Arrays.stream(domainsOfEmail).filter(it -> it.equals(emailDomain)).count() != 1;
    }

    private boolean isEmptyOrNull(String properties) {
        return properties == null || properties.trim().equals("");
    }


    int getFee(int exp) {
        int fee = 0;
        if (exp <= 1) {
            fee = 500;
        } else if (exp <= 3) {
            fee = 250;
        } else if (exp <= 5) {
            fee = 100;
        } else if (exp <= 9) {
            fee = 50;
        }
        return fee;
    }

    public String getEmailDomain(String email) {
        String[] inputs = email.trim().split("@");
        if (inputs.length == 2) return inputs[1];
        throw new DomainEmailInvalidException();
    }

}
