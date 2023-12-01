import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//-----A class called csvdata that extends from its parent class data
public class CsvData extends Data {

    /*invokes the constructor of the superclass,
        to make sure that the constructor class is invoked before any additional code in the csv data is
       executed*/
    public CsvData() {
        super();


    }
    public void loadFile(File file) {

        //-----Puts our code in a try catch block to catch potential errors
        try {
            //--------Scans our file
            Scanner scan = new Scanner(file);

            //-------boolean value of true
            boolean firstIteration = true;

            //------Iterate through each line in the file
            while (scan.hasNext()) {
                //---------Read current line
                String line = scan.nextLine();

                //---------Split the line inte an array of string with "," (comma) and a limit of max 8 splits
                String[] array = line.split(",",8);

                //------If our firstiteration is true we take out the first column and saves it in columnnames
                if(firstIteration) {
                    for(int i = 0; i < array.length; i++ ) {

                        columnNames.add(array[i]);
                    }
                //------if or first iteration is false we take out the rest of the rows and columns and saves it in rows
                    firstIteration = false;
                } else {
                    rows.add(array);
                }

            }


            //-------error message for try-catch
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        }

    }
}
