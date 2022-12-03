package sample;

import com.fazecast.jSerialComm.SerialPort;

public class SerialPortService {
    private SerialPortService() {}

    public static SerialPort getSerialPort(String portDescriptor) {
        var serialport = SerialPort.getCommPort(portDescriptor);

        serialport.setComPortParameters(9600, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        serialport.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        var hasOpened = serialport.openPort();
        if (!hasOpened) {
            throw new IllegalStateException("Failed to open port.");
        }

        return serialport;
    }
}