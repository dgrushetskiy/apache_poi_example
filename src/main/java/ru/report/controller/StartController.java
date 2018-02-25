package ru.report.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.report.Start;
import ru.report.model.House;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StartController {

    private static final String FILE_PATH = "C:/report/report.xlsx";
    private static DataFormat CLIPBOARD_DATA_FORMAT = new DataFormat(House.class.getName());
    private ObservableList<House> houses;
    private Start start;

    @FXML
    private TableView<House> tableViewData = new TableView<>();
    @FXML
    private TableColumn<House, Integer> columnUnomData;
    @FXML
    private TableColumn<House, String> columnAddressData;
    @FXML
    private TableView<House> tableViewUpdata = new TableView<>();
    @FXML
    private TableColumn<House, Integer> columnUnomUpdata;
    @FXML
    private TableColumn<House, String> columnAddressUpdata;
    @FXML
    private Button btnReport;

    public StartController() {
    }

    @FXML
    public void init() {
        columnUnomData.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getUnom()));
        columnAddressData.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getAddress()));
        tableViewData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       // tableViewData.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableViewData.setItems(getData());


        // Начало пертаскивания
        tableViewData.setOnDragDetected(event -> {
            TableView<House> modelTableView = (TableView) event.getSource();
            //House selectedItem = modelTableView.getSelectionModel().getSelectedItem();//SelectionMode.SINGLE
            List<House> selectedItems = new ArrayList<>(modelTableView.getSelectionModel().getSelectedItems());
            if (selectedItems != null) {
                ClipboardContent content = new ClipboardContent();
                content.put(CLIPBOARD_DATA_FORMAT, selectedItems);

                modelTableView.startDragAndDrop(TransferMode.ANY).setContent(content);
            }
            event.consume();
        });
        columnUnomUpdata.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getUnom()));
        columnAddressUpdata.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getAddress()));

        // Перетаскиваем с зажатой мышкой (до бросания)
        tableViewUpdata.setOnDragOver(event -> {
            if (event.getGestureSource() != event.getSource() && event.getDragboard().hasContent(CLIPBOARD_DATA_FORMAT)) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        // Бросание объекта
        tableViewUpdata.setOnDragDropped(event -> {
            TableView tableView = (TableView) event.getSource();
            Dragboard db = event.getDragboard();
            boolean completed = false;
            if (db.hasContent(CLIPBOARD_DATA_FORMAT)) {
             //   House content = (House) db.getContent(CLIPBOARD_DATA_FORMAT);//SelectionMode.SINGLE
                List<House> contents = (ArrayList<House>) db.getContent(CLIPBOARD_DATA_FORMAT);
                tableView.getItems().addAll(contents);
               //tableView.getItems().add(contents);//SelectionMode.SINGLE
                completed = true;
            }
            event.setDropCompleted(completed);
            event.consume();
        });

        btnReport.setOnAction(event -> {
            report();
        });

    }

    private void report() {
        houses = tableViewUpdata.getItems();
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet houseSheet = workbook.createSheet("House");

            int rowIndex = 0;
            for (House house : houses) {
                Row row = houseSheet.createRow(rowIndex++);
                int cellIndex = 0;
                row.createCell(cellIndex++).setCellValue(house.getAddress());
                row.createCell(cellIndex++).setCellValue(house.getVidPrava());
                row.createCell(cellIndex++).setCellValue(String.valueOf(house.getSqrOForm()));
            }

            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println(FILE_PATH + " is successfully written");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ObservableList<House> getData() {
        houses = FXCollections.observableArrayList();
        houses.add(new House(256, "Годовикова улица, дом 7", new BigDecimal("1286.10"), "Собственность города Москвы"));
        houses.add(new House(257, "Годовикова улица, дом 7", new BigDecimal("1286.10"), "Собственность города Москвы"));
        houses.add(new House(350, "Годовикова улица, дом 7", new BigDecimal("176.50"), "Собственность города Москвы"));
        houses.add(new House(765, "Годовикова улица, дом 8", new BigDecimal("1286.10"), "Собственность города Москвы"));
        houses.add(new House(430, "Годовикова улица, дом 9", new BigDecimal("126.30"), "Собственность города Москвы"));
        houses.add(new House(278, "Годовикова улица, дом 10", new BigDecimal("86.10"), "Собственность города Москвы"));
        houses.add(new House(259, "Годовикова улица, дом 11", new BigDecimal("1286.10"), "Собственность города Москвы"));
        houses.add(new House(312, "Годовикова улица, дом 99", new BigDecimal("1286.10"), "Собственность города Москвы"));
        return houses;
    }
}
