package com.notloki.aasbalancefinal;
public class Ref {

    // For Title
    public static final String COMPANY_NAME = "All American Steel";
    public static final String VERSION = "0.2.1b";
    public static final String VERSION_DATE = "04/02/2018";

    // Screen Starting Size
    public static final int WIDTH = 850;
    public static final int HEIGHT = 920;


    // Weight of steel per unit  Default is 2 pounds per linear foot.
    public static final int STEEL_WEIGHT = 2;

    // Printer Network Data
    public static final String PRINTER_IP = "10.100.89.70";
    public static final int PRINTER_PORT = 9100;


    // Path to Datamax Print file.
    public static final String FILE_PATH = "./Balance.prn";


    // Byte addresses for Po Number and Balance Distance
    public static final int PO_FILE_POSITION = 5321;
    public static final int BALANCE_FILE_POSITION = 5354;
}
