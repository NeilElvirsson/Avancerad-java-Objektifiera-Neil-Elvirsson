import com.eclipsesource.json.Json;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class GUIFileReader extends JFrame {


    public static ArrayList<String> aryL = new ArrayList<>();
    private JPanel panel1;
    private JTable table1;

    private JScrollPane scrollPane;
    private JButton openFileButton;
    private JFileChooser fileChooser;
    public static DefaultTableModel model;


    GUIFileReader() {
        //-------Creates our window
        setContentPane(panel1);
        setTitle("Uppgift 2");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        //------Initiates new filechooser
        fileChooser = new JFileChooser();


        model = new DefaultTableModel();
        table1.setModel(model);

        //-----Creates actionlistener to our open file button
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //-------file chooser to open the file path
                fileChooser.setCurrentDirectory(new File("src"));
                int val = fileChooser.showOpenDialog(null);


                //------if file is collected, choose the file to selct and save in file
                if (val == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                   // System.out.println(file.getName());

                   String fileName = file.getName();

                   ArrayList<String> columnNames = new ArrayList<>();
                   ArrayList<String[]> rows = new ArrayList<>();
                    model = new DefaultTableModel();

                    table1.setModel(model);

                    //----initiste new csvdata and json data to clear from table
                    Data csvData = new CsvData();
                    JsonData jsonData = new JsonData();

                  //-----runs the file if its ends vid dot csv
                    if(fileName.endsWith(".csv")) {

                        //-----Loads cvs datafiles columns and rows
                        csvData.loadFile(file);
                       columnNames = csvData.getColumnNames();
                       rows = csvData.getRows();


                       //-----or runs the file if filename ends with .json
                    } else if(fileName.endsWith(".json")) {

                        //-----Loads json datafiles columns and rows
                        jsonData.loadFile(file);
                        columnNames = jsonData.getColumnNames();
                        rows = jsonData.getRows();
                    }

                    //----Iterates through each columnnames and adds them to the model
                    for(int i = 0; i < columnNames.size(); i++) {

                        String columnName = columnNames.get(i);
                        model.addColumn(columnName);
                    }

                   //----Iterates through each rows and adds them to the model
                    for(int i = 0; i < rows.size(); i++) {

                        model.addRow(rows.get(i));

                    }

                    //-------Enable the tablerow sorter to sort column in alphabetic order
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
                    table1.setRowSorter(sorter);
                    table1.getAutoCreateRowSorter();

                }


            }
        });


    }

    public static void main(String[] args) {

        // Create an instance of the GUIFileReader class
        SwingUtilities.invokeLater(() -> new GUIFileReader());

    }


}
