package utils.join_code;

public abstract class HexByteCodeUtils {

    static String shortToHexString(short s) {

        StringBuilder hex = new StringBuilder(Integer.toHexString(s - Short.MIN_VALUE));

        while (hex.length() < 4)
            hex.insert(0, "0");

        return hex.toString();
    }

    static short hexStringToShort(String hex) {
        return (short) (Integer.parseInt(hex, 16) + Short.MIN_VALUE);
    }



    static String byteToHexString(byte b) {

        String hex = Integer.toHexString(b - Byte.MIN_VALUE);
        if (hex.length() == 1)
            hex = "0" + hex;

        return hex;
    }

    static byte hexStringToByte(String hex) {
        return (byte) (Integer.parseInt(hex, 16) - Byte.MIN_VALUE);
    }



    public static byte[] longToBytes(long l) {

        byte[] data = new byte[4];

        data[0] = (byte) ((l >>> 24) & 0xFF);
        data[1] = (byte) ((l >>> 16) & 0xFF);
        data[2] = (byte) ((l >>> 8 ) & 0xFF);
        data[3] = (byte) (l & 0xFF);

        return data;
    }

}
