package Strategies;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ColluderTest {

    @Test
    public void testColluder() {
        Colluder thecolluder = Colluder.getInstance();
        int length = thecolluder.getCodelength();

        assertTrue (thecolluder.getPawnCode().length == length);
    }
}
