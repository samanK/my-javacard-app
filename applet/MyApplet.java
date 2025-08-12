import javacard.framework.*;

public class MyApplet extends Applet {

    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new HelloApplet();
    }

    protected MyApplet() {
        register();
    }

    public void process(APDU apdu) throws ISOException {
        if (selectingApplet()) {
            return;
        }
        byte[] buffer = apdu.getBuffer();
        short len = (short) "Hello".length();
        Util.arrayCopyNonAtomic("Hello".getBytes(), (short) 0, buffer, (short) 0, len);
        apdu.setOutgoingAndSend((short) 0, len);
    }
}
