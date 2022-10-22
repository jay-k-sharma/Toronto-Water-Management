package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import java.nio.ByteBuffer;
import java.util.Scanner;


public class DataController implements SerialPortMessageListenerWithExceptions {
    private static final byte[] DELIMITER = new byte[]{'\n'};
    private final ObservableList<XYChart.Data<Number, Number>> dataPoints;
    int global_value;

    public DataController() {
        this.dataPoints = FXCollections.observableArrayList();
    }

    public ObservableList<XYChart.Data<Number, Number>> getDataPoints() {
        return dataPoints;
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_RECEIVED ){
            return;
        }

        var data = serialPortEvent.getReceivedData();
        //change byte to int
        var value = ByteBuffer.wrap(data).getInt();
        //get time
        var time = System.currentTimeMillis();

        Scanner find = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Name:");
        String initname = find.nextLine();  // Read user input @id
        Person person = new Person(value,initname);

        var dataPoint = new XYChart.Data<Number,Number>(time,value);
        Platform.runLater(() -> this.dataPoints.add(dataPoint));

    }


    @Override
    public void catchException(Exception e) {
        e.printStackTrace();
    }

    @Override
    public byte[] getMessageDelimiter() {
        return DELIMITER;
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }


}