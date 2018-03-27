package com.notloki.aasbalancefinal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;

public class Controller {

    private ArrayList<Double> feetList = new ArrayList<Double>();
    private ArrayList<Double> massList = new ArrayList<Double>();
    private ArrayList<Double> individBalPoint = new ArrayList<Double>();

    private byte[] balancePointByte;
    private byte[] poNumberByte;

    private String finalAnswer;

    private double quantity1;
    private double quantity2;
    private double quantity3;
    private double quantity4;
    private double quantity5;
    private double quantity6;
    private double quantity7;
    private double quantity8;
    private double quantity9;
    private double quantity10;
    private double quantity11;
    private double quantity12;

    private double top;
    private double bottom;

    @FXML private Label logo;

    @FXML private TextField poNumber;

    @FXML private TextField bp;
    @FXML private TextField ft1;
    @FXML private TextField ft2;
    @FXML private TextField ft3;
    @FXML private TextField ft4;
    @FXML private TextField ft5;
    @FXML private TextField ft6;
    @FXML private TextField ft7;
    @FXML private TextField ft8;
    @FXML private TextField ft9;
    @FXML private TextField ft10;
    @FXML private TextField ft11;
    @FXML private TextField ft12;


    @FXML private TextField in1;
    @FXML private TextField in2;
    @FXML private TextField in3;
    @FXML private TextField in4;
    @FXML private TextField in5;
    @FXML private TextField in6;
    @FXML private TextField in7;
    @FXML private TextField in8;
    @FXML private TextField in9;
    @FXML private TextField in10;
    @FXML private TextField in11;
    @FXML private TextField in12;


    @FXML private TextField qty1;
    @FXML private TextField qty2;
    @FXML private TextField qty3;
    @FXML private TextField qty4;
    @FXML private TextField qty5;
    @FXML private TextField qty6;
    @FXML private TextField qty7;
    @FXML private TextField qty8;
    @FXML private TextField qty9;
    @FXML private TextField qty10;
    @FXML private TextField qty11;
    @FXML private TextField qty12;

    @FXML private Button processButton;
    @FXML private Button printButton;
    @FXML private Button quitButton;

    @FXML
    private void initialize() {

        Image image = new Image(getClass().getResourceAsStream("/AASLogo.jpg"));
        logo.setGraphic(new ImageView(image));
        printButton.setDisable(true);  // Disable Print until data has been processed.
    }

    @FXML
    private void quit() {
        System.out.println("Quitting");
        System.exit(0);
    }

    @FXML
    private void print() {
        System.out.println("Printing Label");
        printLayout();
        timeToPrint();
        printButton.setDisable(true);

    }

    @FXML
    private void process() {
        System.out.println("Processing");
        turnOffFields();
        collectData();
        getBalancePoint();

        ListIterator massIt = massList.listIterator();
        ListIterator bal = individBalPoint.listIterator();
        while(massIt.hasNext()) {
            while(bal.hasNext()) {
                double massTemp = Double.valueOf(massIt.next().toString());
                double balTemp = Double.valueOf(bal.next().toString());
                top += (massTemp * balTemp);

            }
        }
        resetIt(massIt);
        while(massIt.hasNext()) {
            bottom += Double.valueOf(massIt.next().toString());
        }
        double decimalAnswer = top / bottom;
        int finalFeet = (int) decimalAnswer;
        double fraction = decimalAnswer - finalFeet;
        int finalInches = (int)(12.0 * fraction);
        finalAnswer = String.valueOf(finalFeet) + "' " + String.valueOf(finalInches) + "\"";
        bp.setText(finalAnswer);

    }

    private void printLayout() {
        try {
            File f = new File(Ref.FILE_PATH);
            RandomAccessFile raf = new RandomAccessFile(f, "rwd");
            poNumberByte = poNumber.getText().getBytes();
            String balancePointString = finalAnswer;
            switch(balancePointString.length()) {
                case 8:
                    balancePointByte = balancePointString.getBytes();
                    break;
                case 7:
                    balancePointByte = (balancePointString + " ").getBytes();
                    break;
                case 6:
                    balancePointByte = (balancePointString + "  ").getBytes();
                    break;
                case 5:
                    balancePointByte = (balancePointString + "   ").getBytes();
                    break;
                case 4:
                    balancePointByte = (balancePointString + "    ").getBytes();
                    break;
                case 3:
                    balancePointByte = (balancePointString + "     ").getBytes();
                    break;
                case 2:
                    balancePointByte = (balancePointString + "      ").getBytes();
                    break;
                case 1:
                    balancePointByte = (balancePointString + "       ").getBytes();
                default:
                    System.out.println("ERROR");
                    break;
            }
            raf.seek(Ref.PO_FILE_POSITION);
            raf.write(poNumberByte, 0, poNumberByte.length);
            raf.seek(Ref.BALANCE_FILE_POSITION);
            raf.write(balancePointByte, 0, balancePointByte.length);
            raf.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void timeToPrint() {
        try {
            Socket socket = new Socket(Ref.PRINTER_IP, Ref.PRINTER_PORT);

            InputStream is = new FileInputStream(Ref.FILE_PATH);

            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            BufferedInputStream bis = new BufferedInputStream(is);

            DataInputStream dis = new DataInputStream(bis);

            while (dis.available() != 0) {
                os.write(dis.read());

            }
            os.close();
            dis.close();
            bis.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void turnOffFields() {
        poNumber.setDisable(true);


        ft1.setDisable(true);
        ft2.setDisable(true);
        ft3.setDisable(true);
        ft4.setDisable(true);
        ft5.setDisable(true);
        ft6.setDisable(true);
        ft7.setDisable(true);
        ft8.setDisable(true);
        ft9.setDisable(true);
        ft10.setDisable(true);
        ft11.setDisable(true);
        ft12.setDisable(true);


        in1.setDisable(true);
        in2.setDisable(true);
        in3.setDisable(true);
        in4.setDisable(true);
        in5.setDisable(true);
        in6.setDisable(true);
        in7.setDisable(true);
        in8.setDisable(true);
        in9.setDisable(true);
        in10.setDisable(true);
        in11.setDisable(true);
        in12.setDisable(true);

        qty1.setDisable(true);
        qty2.setDisable(true);
        qty3.setDisable(true);
        qty4.setDisable(true);
        qty5.setDisable(true);
        qty6.setDisable(true);
        qty7.setDisable(true);
        qty8.setDisable(true);
        qty9.setDisable(true);
        qty10.setDisable(true);
        qty11.setDisable(true);
        qty12.setDisable(true);

        poNumber.setDisable(true);

        processButton.setDisable(true);
        printButton.setDisable(false);
    }

    private void getBalancePoint() {
        ListIterator it = feetList.listIterator();
        while(it.hasNext()) {
            double temp = Double.valueOf(it.next().toString());
            massList.add(temp * Ref.STEEL_WEIGHT);
            individBalPoint.add(temp / 2);
        }
    }

    private void collectData() {

        if(!(qty1.getText().isEmpty())) {
            quantity1 = Double.valueOf(qty1.getText());
        } else {
            quantity1 = 0;
        }
        if(!(qty2.getText().isEmpty())) {
            quantity2 = Double.valueOf(qty2.getText());
        } else {
            quantity2 = 0;
        }
        if(!(qty3.getText().isEmpty())) {
            quantity3 = Double.valueOf(qty3.getText());
        } else {
            quantity3 = 0;
        }
        if(!(qty4.getText().isEmpty())) {
            quantity4 = Double.valueOf(qty4.getText());
        } else {
            quantity4 = 0;
        }
        if(!(qty5.getText().isEmpty())) {
            quantity5 = Double.valueOf(qty5.getText());
        } else {
            quantity5 = 0;
        }
        if(!(qty6.getText().isEmpty())) {
            quantity6 = Double.valueOf(qty6.getText());
        } else {
            quantity6 = 0;
        }
        if(!(qty7.getText().isEmpty())) {
            quantity7 = Double.valueOf(qty7.getText());
        } else {
            quantity7 = 0;
        }
        if(!(qty8.getText().isEmpty())) {
            quantity8 = Double.valueOf(qty8.getText());
        } else {
            quantity8 = 0;
        }
        if(!(qty9.getText().isEmpty())) {
            quantity9 = Double.valueOf(qty9.getText());
        } else {
            quantity9 = 0;
        }
        if(!(qty10.getText().isEmpty())) {
            quantity10 = Double.valueOf(qty10.getText());
        } else {
            quantity10 = 0;
        }

        if(!(qty11.getText().isEmpty())) {
            quantity11 = Double.valueOf(qty11.getText());
        } else {
            quantity11 = 0;
        }

        if(!(qty12.getText().isEmpty())) {
            quantity12 = Double.valueOf(qty12.getText());
        } else {
            quantity12 = 0;
        }

        if(!(in1.getText().isEmpty())) {
            while (quantity1 > 0) {
                feetList.add(Double.valueOf(ft1.getText()) + (Double.valueOf(in1.getText()) / 12));
                quantity1--;
            }
        } else if(!(ft1.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft1.getText()));
            } else {
            System.out.println("No Data Field 1");
        }
        if(!(in2.getText().isEmpty())) {
            while (quantity2 > 0) {
                feetList.add(Double.valueOf(ft2.getText()) + (Double.valueOf(in2.getText()) / 12));
                quantity2--;
            }
        } else if(!(ft2.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft1.getText()));
        } else {
            System.out.println("No Data Field 2");
        }
        if(!(in3.getText().isEmpty())) {
            while (quantity3 > 0) {
                feetList.add(Double.valueOf(ft3.getText()) + (Double.valueOf(in3.getText()) / 12));
                quantity3--;

            }
        } else if(!(ft3.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft3.getText()));
        } else {
            System.out.println("No Data Field 3");
        }
        if(!(in4.getText().isEmpty())) {
            while (quantity4 > 0) {
                feetList.add(Double.valueOf(ft4.getText()) + (Double.valueOf(in4.getText()) / 12));
                quantity4--;
            }
        } else if(!(ft4.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft4.getText()));
        } else {
            System.out.println("No Data Field 4");
        }
        if(!(in5.getText().isEmpty())) {
            while (quantity5 > 0) {
                feetList.add(Double.valueOf(ft5.getText()) + (Double.valueOf(in5.getText()) / 12));
                quantity5--;
            }
        } else if(!(ft5.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft5.getText()));
        } else {
            System.out.println("No Data Field 5");
        }
        if(!(in6.getText().isEmpty())) {
            while (quantity6 > 0) {
                feetList.add(Double.valueOf(ft6.getText()) + (Double.valueOf(in6.getText()) / 12));
                quantity6--;
            }
        } else if(!(ft6.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft6.getText()));
        } else {
            System.out.println("No Data Field 6");
        }
        if(!(in7.getText().isEmpty())) {
            while (quantity7 > 0) {
                feetList.add(Double.valueOf(ft7.getText()) + (Double.valueOf(in7.getText()) / 12));
                quantity7--;
            }
        } else if(!(ft7.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft7.getText()));
        } else {
            System.out.println("No Data Field 7");
        }
        if(!(in8.getText().isEmpty())) {
            while (quantity8 > 0) {
                feetList.add(Double.valueOf(ft8.getText()) + (Double.valueOf(in8.getText()) / 12));
                quantity8--;
            }
        } else if(!(ft8.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft8.getText()));
        } else {
            System.out.println("No Data Field 8");
        }
        if(!(in9.getText().isEmpty())) {
            while (quantity9 > 0) {
                feetList.add(Double.valueOf(ft9.getText()) + (Double.valueOf(in9.getText()) / 12));
                quantity9--;
            }
        } else if(!(ft9.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft9.getText()));
        } else {
            System.out.println("No Data Field 9");
        }
        if(!(in10).getText().isEmpty()) {
            while (quantity10 > 0) {
                feetList.add(Double.valueOf(ft10.getText()) + (Double.valueOf(in10.getText()) / 12));
                quantity10--;
            }
        } else if(!(ft10.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft10.getText()));
        } else {
            System.out.println("No Data Field 10");
        }
        if(!(in11).getText().isEmpty()) {
            while (quantity11 > 0) {
                feetList.add(Double.valueOf(ft11.getText()) + (Double.valueOf(in11.getText()) / 12));
                quantity11--;
            }
        } else if(!(ft11.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft11.getText()));
        } else {
            System.out.println("No Data Field 11");
        }
        if(!(in12).getText().isEmpty()) {
            while (quantity12 > 0) {
                feetList.add(Double.valueOf(ft12.getText()) + (Double.valueOf(in12.getText()) / 12));
                quantity12--;
            }
        } else if(!(ft12.getText().isEmpty())) {
            feetList.add(Double.valueOf(ft12.getText()));
        } else {
            System.out.println("No Data Field 12");
        }
    }

    private void resetIt(ListIterator listIterator) {
        while(listIterator.hasPrevious()) {
            listIterator.previous();
        }

    }

}
