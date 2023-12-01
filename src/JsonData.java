import java.io.File;
import com.eclipsesource.json.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

//----public class that extends from our data class
public class JsonData extends Data {
    private Json jsonParser;


    @Override
    public void loadFile(File file) {
        //-------scan our file
        try {
            Scanner scan = new Scanner(file);
            String scanned = "";


            //---- read files from scan as long as there are element to read
            while(scan.hasNext()){

                //----Takes what we have scanned and puts it into our string gunvald
               String gunvald = scan.nextLine();

               //----Takes our scanned properties from gunvald and puts them into our empty string scanned
                scanned = scanned + gunvald;
            }

            //-----Parsing a json string represented by the scanned variable into a json object
            JsonValue parsedJson = Json.parse(scanned);

            //----parsed json is treated like an array
            JsonArray jsonArray = parsedJson.asArray();

            //----extracting the values of the json array storing them in the list values
            List<JsonValue> values = jsonArray.values();

           //--------iterate through values to get each index storring it in value
            for(int i = 0; i < values.size(); i++) {
               JsonValue value = values.get(i);
               //-------if index equals our first column add columns to column names
               if(i == 0){

                   //------converts our json value into a json object
                   JsonObject object = value.asObject();

                   //-------retrieves the key value of A,B,C and so on from the json object then adds to our list columnNames
                   columnNames.add( object.get("A").asString());
                   columnNames.add( object.get("B").asString());
                   columnNames.add( object.get("C").asString());
                   columnNames.add( object.get("D").asString());
                   columnNames.add( object.get("E").asString());
                   columnNames.add( object.get("F").asString());
                   columnNames.add( object.get("G").asString());
                   columnNames.add( object.get("H").asString());

               } else {
                   //----Else we get the rest of the objects and puts them in our list of string names
                   JsonObject objects = value.asObject();
                   List<String> names = objects.names();

                   // -------we iterate through each row and gets the names and store them in our string array rowValues
                   String[] rowValues = new String[names.size()];
                   for(int j = 0; j < names.size(); j++){

                       rowValues[j] = objects.get(names.get(j)).asString();
                   }
            //---------Adds rowValues to rows
                   rows.add(rowValues);
               }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
