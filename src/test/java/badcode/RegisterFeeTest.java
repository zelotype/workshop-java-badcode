package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterFeeTest {

    @ParameterizedTest
    @CsvSource({
            "0,500",
            "1,500",
            "2,250",
            "3,250",
            "4,100",
            "5,100",
            "9,50",
            "10,0"
    })
    public void testCalculateFeeWithDataTable(int experience, int expectedFee) {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        
        assertEquals(expectedFee, registerBusiness.getFee(experience));
    }

    @Test
    @DisplayName("Test calculate Fee from work experience")
    public void testCalculateFee() {
        RegisterBusiness registerBusiness = new RegisterBusiness();

        assertEquals(500, registerBusiness.getFee(-1));
        assertEquals(500, registerBusiness.getFee(1));
        assertEquals(250, registerBusiness.getFee(3));
        assertEquals(100, registerBusiness.getFee(5));
        assertEquals(50, registerBusiness.getFee(9));
        assertEquals(0, registerBusiness.getFee(10));
    }
}
