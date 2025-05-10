package utils.join_code;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static _main.resources._Resources.KEY_LIST;
import static utils.join_code.HexByteCodeUtils.*;

public class JoinCodeManager {

    public static String getJoinCode(short joinID) {

        try {
            byte[] key = getDailyKey();

            Inet4Address ip = (Inet4Address) InetAddress.getLocalHost();
            byte[] ipAddress = ip.getAddress();

            StringBuilder joinCode = new StringBuilder();

            for (int i = 0; i < 4; i++) {

                byte b = (byte) (ipAddress[i] + key[i]);
                joinCode.append(byteToHexString(b));
            }

            joinCode.append(shortToHexString(joinID));

            return joinCode.toString();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }



    public static byte[] getOriginalIP(String joinCode) {

        byte[] key = getDailyKey();

        byte[] originalIP = new byte[4];
        for (int i = 0; i < 4; i++) {

            int stringIndex = (i * 2);
            String hex = joinCode.substring(stringIndex, stringIndex + 2);

            byte ipValue = hexStringToByte(hex);
            originalIP[i] = (byte) (ipValue - key[i]);
        }

        return originalIP;
    }

    public static short getJoinID(String joinCode) {

        String hex = joinCode.substring(8, 12);
        return hexStringToShort(hex);
    }



    static byte[] getDailyKey() {

        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);
        int dayID = nowUTC.getDayOfYear();

        String keyLine;
        String[] lines = KEY_LIST.lines().toArray(String[]::new);
        keyLine = lines[dayID];

        long rawKey = Long.parseLong(keyLine, 16);

        return longToBytes(rawKey);
    }

}