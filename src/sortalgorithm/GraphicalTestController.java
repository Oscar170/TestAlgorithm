/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortalgorithm;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import model.SortAlgorithm;
import model.TimeInfo;
import model.Timer;

/**
 *
 * @author oscar
 */
public class GraphicalTestController implements Initializable {
    private ToggleGroup rbGroup = new ToggleGroup();
    private ToggleGroup rbGroup2 = new ToggleGroup();
    private SortAlgorithm sa = new SortAlgorithm();
    private Timer t = new Timer(false);

    @FXML
    private Canvas canvas;
    @FXML
    private RadioButton aType1;
    @FXML
    private RadioButton aType2;
    @FXML
    private RadioButton aType3;
    @FXML
    private RadioButton aType4;
    @FXML
    private CheckBox SAB; // SortAlgorithmType
    @FXML
    private CheckBox SABO;
    @FXML
    private CheckBox SAS;
    @FXML
    private CheckBox SAI;
    @FXML
    private CheckBox SAQ;
    @FXML
    private CheckBox SAM;
    @FXML
    private CheckBox SASH;
    @FXML
    private CheckBox SAH;
    @FXML
    private RadioButton s1;
    @FXML
    private RadioButton s2;
    @FXML
    private RadioButton s3;
    @FXML
    private RadioButton s4;
    @FXML
    private RadioButton s5;
    
    @FXML
    public void calcTime(ActionEvent e) {
        int i = Integer.parseInt(rbGroup.selectedToggleProperty().getValue().getProperties().get("gridpane-row").toString());
        int a = Integer.parseInt(rbGroup2.selectedToggleProperty().getValue().getProperties().get("gridpane-column").toString()) + 2;
        ArrayList<TimeInfo> info = new ArrayList<TimeInfo>();
        int[] aAux;
        int l;
        System.out.println(a);
        for(int c = 0; c < a; c++){
            if (i == 1) sa.fillArrays(i);
            if (i == 2) sa.fillArrays(i);
            if (i == 3) sa.fillArrays(i);
            if (i == 4) sa.fillArrays(i);
        
            l = sa.aDatos.length;
            aAux = new int[l];

            if (SAB.isSelected()) {
                t.start();
                sa.BubbleSort(sa.aDatos.clone());
                info.add(new TimeInfo(l, t.stop(), "BubbleSort"));
                System.out.println(info.get(info.size()-1));
            }
            if (SABO.isSelected()) {
                t.start();
                sa.BubbleSortOptim(sa.aDatos.clone());
                info.add(new TimeInfo(l, t.stop(), "BubbleSortOptim"));
                System.out.println(info.get(info.size()-1));
            }
            if (SAS.isSelected()) {
                t.start();
                sa.SelectionSort(sa.aDatos.clone());
                info.add(new TimeInfo(l, t.stop(), "SelectionSort"));
                System.out.println(info.get(info.size()-1));
            }
            if (SAI.isSelected()) {
                t.start();
                sa.InsertionSort(sa.aDatos.clone());
                info.add(new TimeInfo(l, t.stop(), "InsertionSort"));
                System.out.println(info.get(info.size()-1));
            }
            if (SAQ.isSelected()) {
                t.start();
                sa.QuickSort(sa.aDatos.clone(), 0, l - 1);
                info.add(new TimeInfo(l, t.stop(), "QuickSort"));
                System.out.println(info.get(info.size()-1));
            }
            if (SAM.isSelected()) {
                t.start();
                sa.MergeSort(sa.aDatos.clone(), aAux, 0, l - 1);
                info.add(new TimeInfo(l, t.stop(), "MergeSort"));
                System.out.println(info.get(info.size()-1));
            }
            if (SASH.isSelected()) {
                System.out.println("ShellSort no implementado");
            }
            if (SAH.isSelected()) {
                System.out.println("HeapSort no implementado");
            }
        }
        sa.c = 0;
        drawGraphic(info, a);
    }
    
    public void drawGraphic(ArrayList<TimeInfo> info, int a){
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<TimeInfo> infoB = new ArrayList<>();
        ArrayList<TimeInfo> infoBO = new ArrayList<>();
        ArrayList<TimeInfo> infoS = new ArrayList<>();
        ArrayList<TimeInfo> infoI = new ArrayList<>();
        ArrayList<TimeInfo> infoQ = new ArrayList<>();
        ArrayList<TimeInfo> infoM = new ArrayList<>();
        ArrayList<TimeInfo> infoSH = new ArrayList<>();
        ArrayList<TimeInfo> infoH = new ArrayList<>();
        gc.setLineWidth(2);
        int c, l; 
        long maxTime = 0;
        
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        gc.setFill(Paint.valueOf("FFFFFF"));
        gc.setStroke(Paint.valueOf("000000"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 0, 0);
        
        for(TimeInfo i : info){
            if(maxTime < i.getTime()) maxTime = i.getTime();
            if("BubbleSort".equals(i.getTipe())) infoB.add(i);
            if("BubbleSortOptim".equals(i.getTipe())) infoBO.add(i);
            if("SelectionSort".equals(i.getTipe())) infoS.add(i);
            if("InsertionSort".equals(i.getTipe())) infoI.add(i);
            if("QuickSort".equals(i.getTipe())) infoQ.add(i);
            if("MergeSort".equals(i.getTipe())) infoM.add(i);
            if("ShellSort".equals(i.getTipe())) infoSH.add(i);
            if("HeapSort".equals(i.getTipe())) infoH.add(i);
        }
        final double moveX = canvas.getWidth()/a;
        final double moveY = canvas.getHeight()/maxTime;
        
        //Bubble
        gc.setStroke(Paint.valueOf("61BD4F"));
        for(c = 0, l = infoB.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoB.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoB.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoB.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoB.get(c).getTime()*moveY-4, 7, 7);
        }
        
        //BubbleOptim
        gc.setStroke(Paint.valueOf("F2D600"));
        for(c = 0, l = infoBO.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoBO.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoBO.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoBO.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoBO.get(c).getTime()*moveY-4, 7, 7);
        }
        
        //Selection
        gc.setStroke(Paint.valueOf("EB5A46"));
        for(c = 0, l = infoS.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoS.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoS.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoS.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoS.get(c).getTime()*moveY-4, 7, 7);
        }
        
        //Insertion
        gc.setStroke(Paint.valueOf("0079BF"));
        for(c = 0, l = infoI.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoI.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoI.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoI.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoI.get(c).getTime()*moveY-4, 7, 7);
        }
        
        //Quick
        gc.setStroke(Paint.valueOf("FFAB4A"));
        for(c = 0, l = infoQ.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoQ.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoQ.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoQ.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoQ.get(c).getTime()*moveY-4, 7, 7);
        }
        
        //Merge
        gc.setStroke(Paint.valueOf("C377E0"));
        for(c = 0, l = infoM.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoM.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoM.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoM.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoM.get(c).getTime()*moveY-4, 7, 7);
        }
        
        //Shell
        gc.setStroke(Paint.valueOf("000000"));
        for(c = 0, l = infoSH.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoSH.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoSH.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoSH.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoSH.get(c).getTime()*moveY-4, 7, 7);
        }
        
        
        //Heap
        gc.setStroke(Paint.valueOf("00000"));
        for(c = 0, l = infoH.size(); c < l; c++){
            if(c == 0){
                gc.strokeLine(0, canvas.getHeight(), (c+1)*moveX, canvas.getHeight()-infoH.get(c).getTime()*moveY);
            } else {
                gc.strokeLine((c)*moveX, canvas.getHeight()-infoH.get(c-1).getTime()*moveY, (c+1)*moveX,canvas.getHeight()-infoH.get(c).getTime()*moveY);
            }
            gc.strokeOval((c+1)*moveX-3, canvas.getHeight()-infoH.get(c).getTime()*moveY-4, 7, 7);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Radio Button Group
        aType1.setToggleGroup(rbGroup);
        aType2.setToggleGroup(rbGroup);
        aType3.setToggleGroup(rbGroup);
        aType4.setToggleGroup(rbGroup);
        // Radio Button Group 2
        s1.setToggleGroup(rbGroup2);
        s2.setToggleGroup(rbGroup2);
        s3.setToggleGroup(rbGroup2);
        s4.setToggleGroup(rbGroup2);
        s5.setToggleGroup(rbGroup2);
        //Set canvas border
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        gc.setFill(Paint.valueOf("FFFFFF"));
        gc.setStroke(Paint.valueOf("000000"));
        gc.setLineWidth(1);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 0, 0);

        SAB.setStyle("-fx-text-fill: #61BD4F");
        SABO.setStyle("-fx-text-fill: #F2D600");
        SAS.setStyle("-fx-text-fill: #EB5A46");
        SAI.setStyle("-fx-text-fill: #0079BF");
        SAQ.setStyle("-fx-text-fill: #FFAB4A");
        SAM.setStyle("-fx-text-fill: #C377E0");
        SASH.setStyle("-fx-text-fill: #000000");
        SAH.setStyle("-fx-text-fill: #000000");
    }
}
