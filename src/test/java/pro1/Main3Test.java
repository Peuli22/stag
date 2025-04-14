package pro1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Main3Test
{
    @Test
    void test01()
    {
        // TODO 3.3: Oprav test
        assertEquals(
                "jiri.haviger@uhk.cz",
                Main3.emailOfBestTeacher("KIKM",2024)
        );
    }
}