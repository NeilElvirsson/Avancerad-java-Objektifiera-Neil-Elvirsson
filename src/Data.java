import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//-----Creates a abstract class called Data
public abstract class Data {

    //------Creates an protected arraylist of strings called columnnames
    protected ArrayList<String> columnNames;
    //------Creates an protected array list of stringarrays
    protected ArrayList <String[]> rows;

    //------Creates a public construct called Data
    public Data() {
        columnNames = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public abstract void loadFile(File file);

    public ArrayList<String> getColumnNames() {
        //-----return our columnnames we have in this class
        return this.columnNames;

    }
    public ArrayList <String[]> getRows() {
        //-----return our rows we have in this class
        return this.rows;
    }
}
