package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterBusinessSuccessTest {

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
