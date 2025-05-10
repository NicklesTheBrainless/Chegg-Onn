package networking.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static _main.setting.NetworkingSettings.*;

public class NetworkingUtils {

    public static void sendData(BufferedWriter out, String prefix, String data) {

        try {
            out.write(prefix + data);
            out.newLine();
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String waitForData(BufferedReader in) throws IOException {

        long startTime = System.currentTimeMillis();

        String line = null;
        while (line == null) {

            line = in.readLine();

            long currentTime = System.currentTimeMillis();
            if ((currentTime - startTime) > MAX_WAIT_TIME_MILLIS)
                throw new RuntimeException("Waiting for data took too long!");
        }
        return line;
    }



    public static boolean checkJoinAccepted(BufferedReader in) throws IOException {

        String line = waitForData(in);

        if (!line.startsWith(ANSWER_PREFIX))
            return false;

        String answer = line.substring(ANSWER_PREFIX.length());
        return answer.equals(ACCEPT);
    }

    public static boolean checkJoinID(BufferedReader in, short realJoinID) throws IOException {

        String line = waitForData(in);

        if (!line.startsWith(JOIN_PREFIX))
            return false;

        String joinIdString  = line.substring(JOIN_PREFIX.length());
        short receivedJoinID = Short.parseShort(joinIdString);

        return receivedJoinID == realJoinID;
    }
}
