package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterBusinessTest {

    @Test
    @DisplayName("Speaker's First name is Null")
    public void firstNameIsNULL() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, new Speaker())
        );
        assertEquals("First name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Speaker's LastName is Null")
    public void lastNameIsNULL() {
        RegisterBusiness registerBusiness = new RegisterBusiness();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Kawisara");

        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertEquals("Last name is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Speaker's Email is Null")
    public void emailIsNULL() {
        RegisterBusiness registerBusiness = new RegisterBusiness();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Kawisara");
        speaker.setLastName("Bunyen");

        Exception exception = assertThrows(ArgumentNullException.class, () ->
                registerBusiness.register(null, speaker)
        );
        assertEquals("Email is required.", exception.getMessage());
    }

    @Test
    @DisplayName("Speaker's email domain is not support")
    public void emailIsNotInEmailDomain() {
        RegisterBusiness registerBusiness = new RegisterBusiness();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Kawisara");
        speaker.setLastName("Bunyen");
        speaker.setEmail("kawisara@mail.com");

        Exception exception = assertThrows(SpeakerDoesntMeetRequirementsException.class, () ->
                registerBusiness.register(null, speaker)
        );

        assertEquals("Speaker doesn't meet our standard rules.", exception.getMessage());
    }

    @Test
    @DisplayName("Speaker's email domain is invalid")
    public void emailIsInvalid() {
        RegisterBusiness registerBusiness = new RegisterBusiness();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Kawisara");
        speaker.setLastName("Bunyen");
        speaker.setEmail("kawi.mail.com");

        Exception exception = assertThrows(DomainEmailInvalidException.class, () ->
                registerBusiness.register(null, speaker)
        );

        assertNull(exception.getMessage());
    }

    @Test
    @DisplayName("Not have repository")
    public void notHaveRepositoryWhenRegister() {
        RegisterBusiness registerBusiness = new RegisterBusiness();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Kawisara");
        speaker.setLastName("Bunyen");
        speaker.setEmail("kawisara@gmail.com");
        speaker.setExp(9);

        Exception exception = assertThrows(SaveSpeakerException.class, () ->
                registerBusiness.register(null, speaker)
        );

        assertEquals("Can't save a speaker.", exception.getMessage());
    }

    @Test
    @DisplayName("Success case register business")
    public void successRegister() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        SpeakerRepository speakerRepository = (SpeakerRepository) speaker -> 1;

        Speaker speaker = new Speaker();
        speaker.setFirstName("Kawisara");
        speaker.setLastName("Bunyen");
        speaker.setEmail("kawisara@gmail.com");
        speaker.setExp(9);

        int actualResult = registerBusiness.register(speakerRepository, speaker);

        assertEquals(1, actualResult);
    }
}