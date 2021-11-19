import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    public void Test_One_To_One_Connection_Between_MainServer_And_MainClient_On_Default_IP() {
        assertAll(
                () -> assertTrue(TestHelper.openPortWithSpecificIPAndPort("localhost", 8888)),
                () -> assertTrue(TestHelper.clientSendMessage("Client is here")),
                () -> assertTrue(TestHelper.serverReplyOnMessage("Ok")),
                () -> assertTrue(TestHelper.closeConnection())
        );
    }


    @Test
    public void test() throws Exception {
        int[] ports = new int[]{8833, 8877, 8899, 8855, 8866};

        for (int port : ports) {
            assertTrue(TestHelper.setUp("" + port));
        }

    }
}
