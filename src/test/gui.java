/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

//import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Mohamed Taha
 */
public class gui extends Application {
    ObservableList<String>names;
     ObservableList<String>tut;
     ObservableList<String>score;
fileGame file=new fileGame();
    static GridPane gridPane = new GridPane();
    ToggleGroup gameTypeGroup = new ToggleGroup();
    ToggleGroup rowsGroup = new ToggleGroup();
    ToggleGroup colsGroup = new ToggleGroup();
    ToggleGroup minesGroup = new ToggleGroup();
    ToggleGroup viewGroup = new ToggleGroup();
    guiGame g;
    ubdate ubd = new ubdate();
    Check check = new Check();
    Button[][] btnArr;
    String gameType;
    MenuItem newGame;
    MenuItem restart;
    MenuItem exit;
    MenuItem quiqSaveItem;
    MenuItem saveItem;
    RadioMenuItem whiteTheme;
    RadioMenuItem darkTheme;
    // All grids
    GridPane mainWindow;
    BorderPane gridWindow;
    GridPane choiceWindow;
    GridPane multiWindow;
    GridPane scoreWindow;
    GridPane LoadGrid;
    GridPane loadGameWindow;

    //All scene
    Scene mainScene;
    Scene gridScene;
    Scene choiceScene;
    Scene multiScene;
    Scene LoadScene;
    Scene loadGameScene;

    TabPane tabpane;
    Label scoreOnGui;
    String[] colors;

    public void setGame(guiGame g) {

        this.g = g;
    }

    public void changeTheme() {
        viewGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (viewGroup.getSelectedToggle() != null) {
                    if (viewGroup.getSelectedToggle().getUserData().toString().equals("white")) {
                        mainScene.getStylesheets().clear();
                        gridScene.getStylesheets().clear();
                        choiceScene.getStylesheets().clear();
                        multiScene.getStylesheets().clear();
                        mainScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());
                        gridScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());
                        choiceScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());
                        multiScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());

                        Show(g.rules.Row, g.rules.Col);
                    } else if (viewGroup.getSelectedToggle().getUserData().toString().equals("dark")) {
                        mainScene.getStylesheets().clear();
                        gridScene.getStylesheets().clear();
                        choiceScene.getStylesheets().clear();
                        multiScene.getStylesheets().clear();
                        mainScene.getStylesheets().add(gui.class.getResource("darkTheme.css").toExternalForm());
                        gridScene.getStylesheets().add(gui.class.getResource("darkTheme.css").toExternalForm());
                        choiceScene.getStylesheets().add(gui.class.getResource("darkTheme.css").toExternalForm());
                        multiScene.getStylesheets().add(gui.class.getResource("darkTheme.css").toExternalForm());

                        Show(g.rules.Row, g.rules.Col);

                    }

                }
            }
        });
    }

    public void getType(String x) {
        this.gameType = x;
    }

    public void createGrid() {
        //create grid

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(false);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        loadGameWindow.setAlignment(Pos.CENTER);
        loadGameWindow.setGridLinesVisible(false);
        loadGameWindow.setHgap(5);
        loadGameWindow.setVgap(5);
        LoadGrid.setAlignment(Pos.CENTER);
        LoadGrid.setGridLinesVisible(false);
        LoadGrid.setHgap(5);
        LoadGrid.setVgap(5);
        

    }

    public void createColors() {
        colors = new String[8];
        colors[0] = "#f7ca18";
        colors[1] = "#2c3e50";
        colors[2] = "#cf000f";
        colors[3] = "#fdcb6e";
        colors[4] = "#f7f1e3";
        colors[5] = "#f7d794";
        colors[6] = "#192a56";
        colors[7] = "#5efc45";

    }

    public void celebrate() {
        String path = Test.class.getResource("celebrate.wav").toString();
//        Media sound = new Media(path);
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
    }

    public void gameOver() {
        String path = Test.class.getResource("gameOver.wav").toString();
//        Media sound = new Media(path);
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
    }

    public void Show(int row, int col) {

        String path = Test.class.getResource("explosion.wav").toString();
//        Media sound = new Media(path);
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        Image mine = new Image("test/mine.png");
        Image flag = new Image("test/flag.png");
        Image flag2 = new Image("test/flag2.png");
        Image empty = new Image("test/empty.png");
        String dark = Test.class.getResource("darkTheme.css").toString();
        String white = Test.class.getResource("whteTheme.css").toString();
        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {
                SquareStaute s;
                s = g.grid.getSquare(i, j).getSquareStaute();
                if (s == SquareStaute.MarkedFalse) {
                    btnArr[i][j].setStyle("-fx-background-color:#e67e22 ;");
                    //#27ae60
                }
                if (s == SquareStaute.OpenMine) {

                    btnArr[i][j].setGraphic(new ImageView(mine));
                    if (btnArr[i][j].getStyle().toString().equals("")
                            || btnArr[i][j].getStyle().toString().equals("")
                            || btnArr[i][j].getStyle().toString().equals("-fx-background-color:#748082;")
                            || btnArr[i][j].getStyle().toString().equals("-fx-background-color:#bdc3c7;")) {
                        if (mainScene.getStylesheets().get(0).toString().equals(dark)) {
                            btnArr[i][j].setStyle("-fx-background-color:#bdc3c7;");
                        } else if (mainScene.getStylesheets().get(0).toString().equals(white)) {
                            btnArr[i][j].setStyle("-fx-background-color:#748082;");
                        }
                    }

                    if (g.gameStaute == GameStaute.run) {
                        if (g.grid.getSquare(i, j).player.Name.equals("1")) {
                            btnArr[i][j].setStyle("-fx-background-color:#9b59b6;");
                        } else if (g.grid.getSquare(i, j).player.Name.equals("0")) {
                            btnArr[i][j].setStyle("-fx-background-color:#3498db;");
                        }
                    }
                    //int r = g.CurrentMove.square.row, c = g.CurrentMove.square.col;
//                    if (g.grid.getSquare(g.CurrentMove.square.row, g.CurrentMove.square.col).getSquareStaute() == SquareStaute.OpenMine && g.Currentplayer.Staute != PlayerStaute.Lose) {
//                        
//                        mediaPlayer.play();                        
//                    }

                } else {

                    if (s == SquareStaute.OpenEmpty) {
                        if (g.grid.getSquare(i, j).player.Name.equals("1")) {
                            btnArr[i][j].setStyle("-fx-background-color:#9b59b6;");
                        } else if (g.grid.getSquare(i, j).player.Name.equals("0")) {
                            btnArr[i][j].setStyle("-fx-background-color:#3498db;");
                        }
                        if (g.grid.getSquare(i, j).shield) {
                            if (g.grid.getSquare(i, j).player.Name.equals("1")) {
                                btnArr[i][j].setStyle("-fx-background-color:red;");
                            } else if (g.grid.getSquare(i, j).player.Name.equals("0")) {
                                btnArr[i][j].setStyle("-fx-background-color:blue;");
                            }
                        }
                    }
                    if (s == SquareStaute.Marked) {
                        if (g.grid.getSquare(i, j).player.Name.equals("1")) {
                            btnArr[i][j].setGraphic(new ImageView(flag));
                        } else if (g.grid.getSquare(i, j).player.Name.equals("0")) {
                            btnArr[i][j].setGraphic(new ImageView(flag2));
                        }
                        if (mainScene.getStylesheets().get(0).toString().equals(dark)) {
                            btnArr[i][j].setStyle("-fx-background-color:#748082;");
                        } else if (mainScene.getStylesheets().get(0).toString().equals(white)) {
                            btnArr[i][j].setStyle("-fx-background-color:#bdc3c7;");
                        }
                    }

                    if (s == SquareStaute.Closed) {

                        if (mainScene.getStylesheets().get(0).toString().equals(dark)) {
                            btnArr[i][j].setStyle("-fx-background-color:#748082;");
                        } else if (mainScene.getStylesheets().get(0).toString().equals(white)) {
                            btnArr[i][j].setStyle("-fx-background-color:#bdc3c7;");
                        }

                        btnArr[i][j].setGraphic(new ImageView(empty));
                        btnArr[i][j].setText("");
                    }

                    if (g.grid.getSquare(i, j).IsMine() == false && g.grid.getSquare(i, j).arroundMines > 0 && g.grid.getSquare(i, j).getSquareStaute() == SquareStaute.OpenNumber) {

                        btnArr[i][j].setText(Integer.toString(g.grid.getSquare(i, j).arroundMines));
                        btnArr[i][j].setStyle("-fx-text-fill:" + colors[g.grid.getSquare(i, j).arroundMines]);

                        if (g.grid.getSquare(i, j).player.Name.equals("1")) {
                            btnArr[i][j].setStyle("-fx-font-family:Blackadder ITC ;-fx-background-color:#9b59b6;-fx-text-fill:" + colors[g.grid.getSquare(i, j).arroundMines]);
                        } else if (g.grid.getSquare(i, j).player.Name.equals("0")) {
                            btnArr[i][j].setStyle("-fx-font-family:Blackadder ITC ;-fx-background-color:#3498db;-fx-text-fill:" + colors[g.grid.getSquare(i, j).arroundMines]);
                        }

                        if (g.grid.getSquare(i, j).shield) {
                            if (g.grid.getSquare(i, j).player.Name.equals("1")) {
                                btnArr[i][j].setStyle("-fx-background-color:red;");
                            } else if (g.grid.getSquare(i, j).player.Name.equals("0")) {
                                btnArr[i][j].setStyle("-fx-background-color:blue;");
                            }
                        }
                    }

                }

                btnArr[i][j].setPrefSize(55, 55);

            }
        }
        scoreOnGui.setText(g.rules.ShowScore());
       
        

    }

    public MenuBar createMenu() {

        /**
         * *************** Menu ************
         */
        //create menu bar
        MenuBar menuBar = new MenuBar();
//set width
//menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
//create main menu
        Menu menu = new Menu("Menu");
//add items to main menu
        newGame = new MenuItem("New Game");
        restart = new MenuItem("Restart");
        exit = new MenuItem("Exit");

        menu.getItems().addAll(newGame, restart, new SeparatorMenuItem(), exit);

        Menu view = new Menu("View");
        whiteTheme = new RadioMenuItem("White theme");
        whiteTheme.setUserData("white");
        whiteTheme.setSelected(true);
        darkTheme = new RadioMenuItem("Dark Theme");
        darkTheme.setUserData("dark");

        viewGroup.getToggles().addAll(whiteTheme, darkTheme);
        view.getItems().addAll(whiteTheme, darkTheme);
        
        Menu save = new Menu("Save");
        quiqSaveItem = new MenuItem("quiq Save");
        saveItem = new MenuItem("Save");
        
        save.getItems().addAll(quiqSaveItem,saveItem);
        

        menuBar.getMenus().addAll(menu, view,save);

        return menuBar;
    }
PlayerMove move = new PlayerMove();

    @Override
    public void start(Stage primaryStage) {
        btnArr = new Button[6][8];
        // Platform.runLater(des);
        //des.setDaemon(true);
        ubd.start();
        check.start();

        createColors();
        tabpane = new TabPane();
        // All grids
        mainWindow = new GridPane();
        gridWindow = new BorderPane();
        choiceWindow = new GridPane();
        multiWindow = new GridPane();
        scoreWindow = new GridPane();
        LoadGrid = new GridPane();
        loadGameWindow = new GridPane();
        GridPane LoadTutWindow = new GridPane();
        GridPane alertGrid = new GridPane();
        alertGrid.setAlignment(Pos.CENTER);
        alertGrid.setGridLinesVisible(false);
        alertGrid.setHgap(5);
        alertGrid.setVgap(5);
        GridPane scoreBoardGrid = new GridPane();

        //All scene
        mainScene = new Scene(mainWindow, 400, 630);
        gridScene = new Scene(gridWindow, 400, 630);
        choiceScene = new Scene(tabpane, 400, 630);
        multiScene = new Scene(multiWindow, 400, 630);
        LoadScene = new Scene(LoadGrid,400,630);
        loadGameScene = new Scene (loadGameWindow,400,630);
        Scene loadTutScene = new Scene (LoadTutWindow,400,630);
         Scene alertScene = new Scene (alertGrid,400,630);
         Scene scoreBoardScene = new Scene (scoreBoardGrid,400,630);
        //Create Grid     
        createGrid();
        gridWindow.setTop(createMenu());
        gridWindow.setCenter(gridPane);
        // create Score board
        scoreOnGui = new Label();
        VBox hbox = new VBox();
        hbox.getChildren().addAll(scoreOnGui);
        hbox.setAlignment(Pos.CENTER);
        scoreOnGui.getStyleClass().add("score");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(hbox);
        gridWindow.setBottom(vbox);
        vbox.getStyleClass().add("scoreBoard");
        
        //score board grid
        ListView<String>scoreNames; 
       score= FXCollections.observableArrayList(); 
      scoreNames = new ListView<String>(score);
      scoreNames.setPrefSize(200, 300);
      Button loadScore = new Button("Load");
      Button cancelScore  = new Button("Cancel");
      
      scoreBoardGrid.add(scoreNames, 0, 0);
      scoreBoardGrid.add(loadScore, 0, 1);
      scoreBoardGrid.add(cancelScore, 1, 1);
      
       scoreBoardGrid.setAlignment(Pos.CENTER);
        scoreBoardGrid.setGridLinesVisible(false);
        scoreBoardGrid.setHgap(5);
        scoreBoardGrid.setVgap(5);
        
        //add action to score board buttons
        loadScore.setOnAction(e->
        {
       String path =  scoreNames.getSelectionModel().getSelectedItem();
           
           Button next = new Button("next");
             Button prev = new Button("prev");
             gridPane.add(next,0,10);
             gridPane.add(prev,1,10);
             g = (guiGame) file.loadGame(g.data+"\\"+path);
              AutoGame auto = new AutoGame();
              auto.copy(g.listMoves);
              auto.init(g.rules, g.grid);
              
              next.setOnAction(ee->
              {
                 
                  g=(guiGame) auto.next();
                   Show(g.grid.Row,g.grid.Col);
                   scoreOnGui.setText(auto.tutorial.size()+ " Score: " + g.Currentplayer.Score + "\nNomShield:" + g.Currentplayer.shildsNom);
                
              }
              );
              prev.setOnAction(eee->
              {
                 
                  g= (guiGame) auto.prev();
                    Show(g.grid.Row,g.grid.Col);
                     scoreOnGui.setText(auto.tutorial.size()+ " Score: " + g.Currentplayer.Score + "\nNomShield:" + g.Currentplayer.shildsNom);
              }
              );
             primaryStage.setScene(gridScene);
             scoreOnGui.setText("");
                        //Create Array Buttons
                        btnArr = new Button[g.grid.Row][g.grid.Col];

                        // add action to buttons
                        for (int i = 0; i < g.grid.Row; i++) {
                            for (int j = 0; j < g.grid.Col; j++) {
                                final int ii, jj;
                                ii = i;
                                jj = j;
                                btnArr[i][j] = new Button();
                                gridPane.add(btnArr[i][j], j + 1, i + 1);
                                    
                        

                            }
                        }
  
        }
        );
        
        cancelScore.setOnAction(e->
        {
            primaryStage.setScene(mainScene);
        }
        );
        
        //alert grid
        Label nameAlertLabel = new Label("Enter your Name");
        TextField nameAlertField = new TextField();
        
         Label fileAlertLabel = new Label("Enter The Name Of File");
        TextField fileAlertField = new TextField();
        
        Button saveTutrial = new Button("Save Tutrial");
        Button saveScore = new Button("Save Score");
        Button cancel = new Button("Cancel");
        
        alertGrid.add(nameAlertLabel,0,0);
        alertGrid.add(nameAlertField,0,1);
        
         alertGrid.add(fileAlertLabel,0,2);
        alertGrid.add(fileAlertField,0,3);
        
        alertGrid.add(saveTutrial,0,4);
        alertGrid.add(saveScore,1,4);
        alertGrid.add(cancel,2,4);
        
        
        
        //add action to alert grid
        saveTutrial.setOnAction(e->
        {
            String s=fileAlertField.getText().toString();
            if(s.equals(""))
            {
                
            }
            else
            file.saveGame(g,g.data+"\\"+s,g.tutorialPaths);
            primaryStage.setScene(mainScene);
        }
        );
        saveScore.setOnAction(e->
        {
            String s=fileAlertField.getText().toString();
            g.board=file.loadRecord(g.scoreBoard);
            if(g.board==null)
                g.board = new scoreBoard();
           Record r = new Record();
           r.col=g.grid.Col;
           r.mines=g.rules.MinesNumber;
           r.path = s;
           r.row = g.grid.Row;
           r.shields = g.rules.countShields;
  
           
            g.board.addRecord(r);
            file.saveRecord(g.board,g.scoreBoard);
             if(s.equals(""))
            {
                
            }
             else{
            file.saveGame(g,g.data+"\\"+s,g.tutorialPaths);
            primaryStage.setScene(mainScene);
             }
        }
        );
        
        //tut grid
        ListView<String>tutNames; 
       tut= FXCollections.observableArrayList(); 
      tutNames = new ListView<String>(tut);
      tutNames.setPrefSize(200, 100);
      Button loadTut = new Button("Load");
      Button cancelTut  = new Button("Cancel");
      
       LoadTutWindow.setAlignment(Pos.CENTER);
        LoadTutWindow.setGridLinesVisible(false);
        LoadTutWindow.setHgap(5);
        LoadTutWindow.setVgap(5);
        
      LoadTutWindow.add(tutNames,0,0);
       LoadTutWindow.add(loadTut,0,1);
        LoadTutWindow.add(cancelTut,1,1);
        
        //add action to tut grid
        loadTut.setOnAction(e->
        {
             String path =  tutNames.getSelectionModel().getSelectedItem();
               Button next = new Button("next");
             Button prev = new Button("prev");
             gridPane.add(next,0,10);
             gridPane.add(prev,1,10);
              g = (guiGame) file.loadGame(path);
              AutoGame auto = new AutoGame();
              auto.copy(g.listMoves);
              auto.init(g.rules, g.grid);
              
              next.setOnAction(ee->
              {
                 
                  g=(guiGame) auto.next();
                   Show(g.grid.Row,g.grid.Col);
                   scoreOnGui.setText(auto.tutorial.size()+ " Score: " + g.Currentplayer.Score + "\nNomShield:" + g.Currentplayer.shildsNom);
                
              }
              );
              prev.setOnAction(eee->
              {
                 
                  g= (guiGame) auto.prev();
                    Show(g.grid.Row,g.grid.Col);
                     scoreOnGui.setText(auto.tutorial.size()+ " Score: " + g.Currentplayer.Score + "\nNomShield:" + g.Currentplayer.shildsNom);
              }
              );
             primaryStage.setScene(gridScene);
             scoreOnGui.setText("");
                        //Create Array Buttons
                        btnArr = new Button[g.grid.Row][g.grid.Col];

                        // add action to buttons
                        for (int i = 0; i < g.grid.Row; i++) {
                            for (int j = 0; j < g.grid.Col; j++) {
                                final int ii, jj;
                                ii = i;
                                jj = j;
                                btnArr[i][j] = new Button();
                                gridPane.add(btnArr[i][j], j + 1, i + 1);
                                    
                        

                            }
                        }
                     
            
        }
        );
        
        cancelTut.setOnAction(e->
        {
            primaryStage.setScene(mainScene);
        }
        );
        
         //Load Grid
         Button quiqLoad = new Button("Quiq Load");
         Button loadGame = new Button("Load Game");
         Button loadTutorial = new Button("Load Tutrial");
         
         LoadGrid.add(quiqLoad,0,0);
         LoadGrid.add(loadGame,0,1);
         LoadGrid.add(loadTutorial,0,2);
         
         //add action to load button
         quiqLoad.setOnAction(e->
         {
            g = (guiGame) file.loadGame("mhd.bin");
          scoreOnGui.setText(g.rules.ShowScore());
                        //Create Array Buttons
                        btnArr = new Button[g.grid.Row][g.grid.Col];

                        // add action to buttons
                        for (int i = 0; i < g.grid.Row; i++) {
                            for (int j = 0; j < g.grid.Col; j++) {
                                final int ii, jj;
                                ii = i;
                                jj = j;
                                btnArr[i][j] = new Button();
                                gridPane.add(btnArr[i][j], j + 1, i + 1);
                                     if(ii+1==g.grid.Row && jj+1==g.grid.Col)
                                      ubd.interrupt();
                                     btnArr[i][j].setOnMouseClicked((MouseEvent c) -> {
                                       
                                    if (c.getButton() == MouseButton.SECONDARY) {

                                        move.typeMove = TypeMove.Mark;
                                    } else if (c.getButton() == MouseButton.PRIMARY) {
                                        move.typeMove = TypeMove.Reveal;
                                    }

                                    Square s = new Square(ii, jj);
                                    s.player = g.Currentplayer;

                                    move.square = s;
                                    move.player = g.Currentplayer;

                                    g.Currentplayer.setCurrentMove(move);
                                   // move = null;
                                   

                                });

                            }
                        }
          primaryStage.setScene(gridScene);   
         }
         );
         
         loadGame.setOnAction(e->
         {
             primaryStage.setScene(loadGameScene);
            ArrayList<String> arr = new ArrayList<String>();
            arr = file.loadPaths(g.loadPaths);
            names.clear();
            if(arr!=null)
             for(int i=0;i<arr.size();i++)
             {
                 names.add(arr.get(i));
             }
                      
         }
         );
         
         loadTutorial.setOnAction(e->
         {
             primaryStage.setScene(loadTutScene);
               ArrayList<String> arr = new ArrayList<String>();
            arr = file.loadPaths(g.tutorialPaths);
            tut.clear();
            if(arr!=null)
             for(int i=0;i<arr.size();i++)
             {
                 tut.add(arr.get(i));
             }
                      
         }
         );
         
        //loadGame grid
        Button load = new Button("Load");
        Button prevLoad = new Button("Cancel");
      ListView<String>listname;        
      names= FXCollections.observableArrayList();     
      listname = new ListView<String>(names);
      listname.setPrefSize(200, 100);
      
      
      loadGameWindow.add(listname,0,0);
      loadGameWindow.add(load,0,1);
      loadGameWindow.add(prevLoad,1,1);
      
      //add action to loadGame
      load.setOnAction(e->
      {
                         
        String path =  listname.getSelectionModel().getSelectedItem();
          g = (guiGame) file.loadGame(path);
          scoreOnGui.setText(g.rules.ShowScore());
                        //Create Array Buttons
                        btnArr = new Button[g.grid.Row][g.grid.Col];

                        // add action to buttons
                        for (int i = 0; i < g.grid.Row; i++) {
                            for (int j = 0; j < g.grid.Col; j++) {
                                final int ii, jj;
                                ii = i;
                                jj = j;
                                btnArr[i][j] = new Button();
                                gridPane.add(btnArr[i][j], j + 1, i + 1);
                                     if(ii+1==g.grid.Row && jj+1==g.grid.Col)
                                      ubd.interrupt();
                                     btnArr[i][j].setOnMouseClicked((MouseEvent c) -> {
                                       
                                    if (c.getButton() == MouseButton.SECONDARY) {

                                        move.typeMove = TypeMove.Mark;
                                    } else if (c.getButton() == MouseButton.PRIMARY) {
                                        move.typeMove = TypeMove.Reveal;
                                    }

                                    Square s = new Square(ii, jj);
                                    s.player = g.Currentplayer;

                                    move.square = s;
                                    move.player = g.Currentplayer;

                                    g.Currentplayer.setCurrentMove(move);
                                   // move = null;
                                   

                                });

                            }
                        }
          primaryStage.setScene(gridScene);
      }
      );
      
      prevLoad.setOnAction(e->
      {
          primaryStage.setScene(mainScene);
      }
      );
       
        //button main grid
        Image logo = new Image("test/logo.png");
        Button[] btn = new Button[5];
        btn[0] = new Button("Single game");
        VBox h0 = new VBox();
        h0.getChildren().addAll(btn[0]);
        h0.setAlignment(Pos.CENTER);
        btn[1] = new Button("Multi Player");
        VBox h1 = new VBox();
        h1.getChildren().addAll(btn[1]);
        h1.setAlignment(Pos.CENTER);
        btn[2] = new Button("Load");
        VBox h2 = new VBox();
        h2.getChildren().addAll(btn[2]);
        h2.setAlignment(Pos.CENTER);
        
         btn[3] = new Button("Score Board");
        VBox h3 = new VBox();
        h3.getChildren().addAll(btn[3]);
        h3.setAlignment(Pos.CENTER);
        
        btn[4] = new Button("Setting");
        VBox h4 = new VBox();
        h4.getChildren().addAll(btn[4]);
        h4.setAlignment(Pos.CENTER);
        
        

        mainWindow.addRow(0, new ImageView(logo));

        // mainWindow.setGridLinesVisible(false);
        mainWindow.add(h0, 0, 4);
        mainWindow.add(h1, 0, 5);
        mainWindow.add(h2, 0, 6);
        mainWindow.add(h3, 0, 7);
        mainWindow.add(h4, 0, 8);
        mainWindow.setAlignment(Pos.CENTER);
        mainWindow.setHgap(15);
        mainWindow.setVgap(15);

        // multi players choice
        Button start = new Button("Start");
        Button ret = new Button("Cancel");
        start.setStyle("-fx-font-weight: lighter;");
        ret.setStyle("-fx-font-weight: lighter;");
        RadioButton person = new RadioButton("With Person");
        person.setUserData("person");
        person.setSelected(true);
        RadioButton computer = new RadioButton("With computer");
        computer.setUserData("computer");
        ToggleGroup gameTypeGroup = new ToggleGroup();
        gameTypeGroup.getToggles().addAll(person, computer);
        multiWindow.setAlignment(Pos.CENTER);
        multiWindow.setHgap(15);
        multiWindow.setVgap(15);

        // Add to multi palyers grid
        multiWindow.add(person, 0, 8);
        multiWindow.add(computer, 0, 9);
        multiWindow.add(start, 1, 10);
        multiWindow.add(ret, 0, 10);

        //button choice grid
        Button retur = new Button("Cancel");
        Button submit = new Button("Ok");
        Button defaul = new Button("Set default");
        retur.setStyle("-fx-font-weight: lighter;");
        submit.setStyle("-fx-font-weight: lighter;");
        defaul.setStyle("-fx-font-weight: lighter;");
        Label label = new Label();
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(40);
        choiceWindow.getColumnConstraints().addAll(c1);
        label.setStyle("-fx-text-background-color:red; -fx-font-size: 15px;-fx-font-weight: bold;");

        choiceWindow.setAlignment(Pos.CENTER);
        choiceWindow.setHgap(5);
        choiceWindow.setVgap(5);
        Label mines = new Label("enter the number of mines:");
        TextField minesField = new TextField();
        minesField.setText("8");
        Label row = new Label("enter the number of rows:");
        TextField rowsField = new TextField();
        rowsField.setText("8");
        Label col = new Label("enter the number of colums:");
        TextField colsField = new TextField();
        colsField.setText("6");
        
        Label shiel = new Label("auto player can use shields?");
         CheckBox shielBox = new CheckBox();
         shielBox.setSelected(true);
         Label tim = new Label("time in role:");
        TextField timFiels = new TextField();
        timFiels.setText("10");
        
        Label countSheilds = new Label("count of shilds:");
        TextField countSheildsField = new TextField();
        countSheildsField.setText("5");
        
        Label sieldValue = new Label("Sield Value:");
        TextField sieldValueField = new TextField();
        sieldValueField.setText("100");
        
         

        HBox hbtn = new HBox(20);
        hbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbtn.getChildren().add(defaul);
        //Add to choice grid
        //  choiceWindow.add(mines, 0, 1);
        choiceWindow.addRow(1, mines);
        // choiceWindow.add(minesField, 0, 2);
        choiceWindow.addRow(2, minesField);
        //  choiceWindow.add(row, 0, 3);
        choiceWindow.addRow(3, row);
        //choiceWindow.add(rowsField, 0, 4);
        choiceWindow.addRow(4, rowsField);
        // choiceWindow.add(col, 0, 5);
        choiceWindow.addRow(5, col);
        //  choiceWindow.add(colsField, 0, 6);
        choiceWindow.addRow(6, colsField);
        choiceWindow.addRow(7, shiel);
        choiceWindow.addRow(7, shielBox);
        choiceWindow.addRow(9, tim);
        choiceWindow.addRow(10, timFiels);
        
        choiceWindow.addRow(11, countSheilds);
        choiceWindow.addRow(12, countSheildsField);
        
        choiceWindow.addRow(13, sieldValue);
        choiceWindow.addRow(14, sieldValueField);

        //  choiceWindow.add(retur, 0, 10);
        choiceWindow.addRow(15, retur, submit);
        // choiceWindow.add(submit, 1, 10);
        //choiceWindow.add(defaul, 0, 12);
        //  choiceWindow.addRow(17, label);
        choiceWindow.add(hbtn, 0, 16);
        choiceWindow.add(label, 0, 18, 2, 2);

        //  fields score grid
        Label emptyScore = new Label("reveal empty square:");
        TextField emptyScoreField = new TextField();
        emptyScoreField.setText("10");
        Label mineScore = new Label("reveal a mine:");
        TextField mineScoreField = new TextField();
        mineScoreField.setText("-100");
        Label markMineScore = new Label(" mark a mine:");
        TextField markMineineScoreField = new TextField();
        markMineineScoreField.setText("5");
        Label markEmptyScore = new Label(" mark a empty:");
        TextField markEmptyScoreScoreField = new TextField();
        markEmptyScoreScoreField.setText("-1");

        scoreWindow.setAlignment(Pos.CENTER);
        scoreWindow.setHgap(15);
        scoreWindow.setVgap(15);
        // add fields to score grid
        scoreWindow.add(emptyScore, 0, 1);
        scoreWindow.add(emptyScoreField, 0, 2);
        scoreWindow.add(mineScore, 0, 3);
        scoreWindow.add(mineScoreField, 0, 4);
        scoreWindow.add(markMineScore, 0, 5);
        scoreWindow.add(markMineineScoreField, 0, 6);
        scoreWindow.add(markEmptyScore, 0, 7);
        scoreWindow.add(markEmptyScoreScoreField, 0, 8);

        //create tabs to score
        Tab tab1 = new Tab("Grid");
        Tab tab2 = new Tab("Score");
        Tab newtab = new Tab();

        //add action to tabs and add tabs to tabpane
        EventHandler<Event> event
                = new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        if (newtab.isSelected()) {

                            Tab tab = new Tab("new");
                            tabpane.getTabs().add(tabpane.getTabs().size() - 1, tab);
                            tabpane.getSelectionModel().select(tabpane.getTabs().size() - 2);

                            if (tabpane.getTabs().size() == 4) {

                                if (tabpane.getTabs().get(0).getText().toString().equals("Score")) {
                                    tab.setText("Score");
                                    tab.setContent(scoreWindow);
                                } else if (tabpane.getTabs().get(0).getText().toString().equals("Grid")) {

                                    tab.setText("Grid");
                                    tab.setContent(choiceWindow);
                                }

                            } else if (tabpane.getTabs().size() == 3) {
                                if (tabpane.getTabs().get(0).getText().toString().equals("Grid")) {
                                    tab.setText("Score");
                                    tab.setContent(scoreWindow);
                                } else if (tabpane.getTabs().get(0).getText().toString().equals("Score")) {
                                    tab.setText("Grid");
                                    tab.setContent(choiceWindow);
                                }
                            } else {

                                tab.setText("Score");
                                tab.setContent(scoreWindow);
                            }

                            if (tabpane.getTabs().size() > 3) {
                                tabpane.getTabs().remove(0);
                            }

                        }
                    }
                };
        newtab.setOnSelectionChanged(event);

        tab1.setContent(choiceWindow);
        tab2.setContent(scoreWindow);
        tabpane.getTabs().addAll(tab1, tab2, newtab);

        //add action to main button
        for (int i = 0; i < 5; i++) {
            final int ii = i;

            btn[i].setStyle("-fx-background-radius:50px;-fx-font-weight: lighter;");
            btn[i].setPrefSize(150, 40);
            btn[i].setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    // single player button  
                    if (ii == 0) {
                        primaryStage.setScene(gridScene);

                        g.rules = g.createSingleRules(g.rules);

                        g.InitGame();
                        
                        
                        scoreOnGui.setText(g.rules.ShowScore());
                        //Create Array Buttons
                        btnArr = new Button[g.grid.Row][g.grid.Col];

                        // add action to buttons
                        for (int i = 0; i < g.grid.Row; i++) {
                            for (int j = 0; j < g.grid.Col; j++) {
                                final int ii, jj;
                                ii = i;
                                jj = j;
                                btnArr[i][j] = new Button();
                                gridPane.add(btnArr[i][j], j + 1, i + 1);

                                btnArr[i][j].setOnMouseClicked((MouseEvent c) -> {
//                                    if(g.gameStaute==GameStaute.over)
//                        {
//                          //primaryStage.setScene(alertScene);
//                        }

                                    if (c.getButton() == MouseButton.SECONDARY) {

                                        move.typeMove = TypeMove.Mark;
                                    } else if (c.getButton() == MouseButton.PRIMARY) {
                                        move.typeMove = TypeMove.Reveal;
                                    }

                                    Square s = new Square(ii, jj);
                                    s.player = g.Currentplayer;

                                    move.square = s;
                                    move.player = g.Currentplayer;

                                    
                                    g.Currentplayer.setCurrentMove(move);
                                   // move = null;
                                    if (g.Currentplayer.Staute == PlayerStaute.Win) {
                                        celebrate();
                                    } else if (g.Currentplayer.Staute == PlayerStaute.Lose) {
                                        primaryStage.setScene(alertScene);
                                        gameOver();
                                    }

                                });

                            }
                        }

                    } //multi players button
                    else if (ii == 1) {
                        primaryStage.setScene(multiScene);
                        
                    }
                    // load button
                     else if(ii==2)
                        {
                         primaryStage.setScene(LoadScene);

                          //g = (guiGame) g.file.loadGame("omran");
                           //primaryStage.setScene(gridScene);   
                        }  
                     //score board button
                     else if(ii==3)
                     {
                         TableView table = new TableView();
                         
                         scoreBoard s =new scoreBoard();
                         s= file.loadRecord(g.scoreBoard);
                         score.clear();
                         for(int k = 0;k<s.record.size();k++)
                         {
                           score.add(s.record.get(k).path );  
                         }
//                         table.setItems(score);
//                         TableColumn<scoreBoard,Integer> shields = new TableColumn<>("shields");
//                                                  shields.setCellFactory(new PropertyValueFactory<>("shields"));
//

//                         table.getColumns().addAll(shields);
                                           primaryStage.setScene(scoreBoardScene);       
                     }
                     // setting button
                    else if (ii == 4) {
                        primaryStage.setScene(choiceScene);

                    }

                }
            });
        }

        //Add Action to choice grid buttons
        submit.setOnAction(e
                -> {
                    int m = 8, r = 6, c = 8;

                    int emptyScoreValue = 10;
                    int mineScoreValue = 100;
                    int markMineineScoreValue = 5;
                    int markEmptyScoreScoreValue = 1;
                    int shieldValue=100;
                    int t=10;
                    int cs=5;
                    boolean s=true;
                    boolean bool = true;

                    try {
                        bool = true;
                        m = Integer.parseInt(minesField.getText().toString());
                        r = Integer.parseInt(rowsField.getText().toString());
                        c = Integer.parseInt(colsField.getText().toString());
                        t=Integer.parseInt(timFiels.getText().toString());
                        s=shielBox.isSelected();
                        cs=Integer.parseInt(countSheildsField.getText().toString());
                        emptyScoreValue = Integer.parseInt(emptyScoreField.getText().toString());
                        mineScoreValue = Integer.parseInt(mineScoreField.getText().toString());
                        markMineineScoreValue = Integer.parseInt(markMineineScoreField.getText().toString());
                        markEmptyScoreScoreValue = Integer.parseInt(markEmptyScoreScoreField.getText().toString());
                        shieldValue=Integer.parseInt(sieldValueField.getText().toString());
                        if (m <= 0 || r <= 0 || c <= 0) {
                            throw new illegalRulesExeption("enter only posetive number!!");
                        }
                        if (r > 10) {
                            throw new illegalRulesExeption("Max rows: 10 !!");
                        }

                        if (c > 20) {

                            throw new illegalRulesExeption("Max columns: 20!!");
                        }

                        if (m > (r * c) - 1) {
                            throw new illegalRulesExeption("Max mines: " + (r * c - 1) + "!!");
                        }

                    } catch (illegalRulesExeption ex) {
                        label.setText(ex.toString());
                        bool = false;
                    } catch (NumberFormatException ex) {
                        bool = false;
                        if (colsField.getText().toString().equals("") || rowsField.getText().toString().equals("") || minesField.getText().toString().equals("")) {
                            label.setText("There are empty fields");
                        } else {
                            label.setText("You must enter only numbers!!");
                        }
                    }

                    g.rules.initRules(m, r, c);
                    g.rules.score.empty.setValue(emptyScoreValue);
                    g.rules.score.markEmpty.setValue(markEmptyScoreScoreValue);
                    g.rules.score.markMine.setValue(markMineineScoreValue);
                    g.rules.score.mine.setValue(mineScoreValue);
                    g.rules.score.shield.setValue(shieldValue);
                    g.rules.autoSheild=s;
                    g.rules.timeRole=t;
                    g.rules.countShields=cs;
                    if (bool) {
                        primaryStage.setScene(mainScene);
                        label.setText("");
                    }

                }
        );

        defaul.setOnAction(e
                -> {
                    rowsField.setText("8");
                    colsField.setText("6");
                    minesField.setText("8");
                    timFiels.setText("10");
                     shielBox.setSelected(true);
                    emptyScoreField.setText("10");
                    mineScoreField.setText("-100");
                    markEmptyScoreScoreField.setText("-1");
                    markMineineScoreField.setText("5");
                    countSheildsField.setText("5");
                    sieldValueField.setText("100");
                }
        );

        retur.setOnAction(e -> primaryStage.setScene(mainScene));

        // Add action to multi palyer 
        gameTypeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (gameTypeGroup.getSelectedToggle() != null) {
                    getType(gameTypeGroup.getSelectedToggle().getUserData().toString());
                }
            }
        }
        );

        start.setOnAction(e -> {

            // if (gameType.equals("person")) {
            g.rules = g.createMultieRules(1, g.rules);
            // } 
            if (gameTypeGroup.getSelectedToggle().getUserData() == "computer") {
                if (gameType.equals("computer")) {
                    g.rules = g.createMultieRules(2, g.rules);

                }
            }
            //    g.rules.initRules(1, 2, 2);

            g.InitGame();

            scoreOnGui.setText(g.rules.ShowScore());
            //Create Array Buttons
            btnArr = new Button[g.grid.Row][g.grid.Col];
            primaryStage.setScene(gridScene);

            // add action to buttons
            for (int i = 0; i < g.grid.Row; i++) {
                for (int j = 0; j < g.grid.Col; j++) {
                    final int ii, jj;
                    ii = i;
                    jj = j;
                    btnArr[i][j] = new Button();

                    gridPane.add(btnArr[i][j], j + 1, i + 1);

                    btnArr[i][j].setOnMouseClicked((MouseEvent t) -> {
                        if (t.getButton() == MouseButton.SECONDARY) {

                            move.typeMove = TypeMove.Mark;
                        } else if (t.getButton() == MouseButton.PRIMARY) {
                            move.typeMove = TypeMove.Reveal;
                        }

                        Square s = new Square(ii, jj);
                        s.player = g.Currentplayer;

                        move.square = s;
                        move.player = g.Currentplayer;
                        boolean b1 = false;
                         if ( g.gameStaute == GameStaute.over) {
                                  primaryStage.setScene(alertScene);
                            }
                        if (g.gameStaute == GameStaute.run) {
                            g.Currentplayer.setCurrentMove(move);
                           // move = null;

                            // g.getNextMove();
                           /* if (g.AcceptPlayerMove()) {
                             g.ApplyPlayerMove();

                             b1 = true;

                             Show(g.grid.Row, g.grid.Col);
                             g.changePlayer();
                             */
                            //Show(g.grid.Row, g.grid.Col);
                           

                            //}
                            if (g.rules.typePlayers == 2 && b1 == true) {
//                            if(g.PlayerList[0].Score>g.PlayerList[1].Score &&g.gameStaute==GameStaute.over)
//                            {
//                               celebrate(); 
//                               System.out.println("111");
//                            }
//                                
//                           else if(g.PlayerList[0].Score<g.PlayerList[1].Score &&g.gameStaute==GameStaute.over)
//                            {
//                                System.out.println("22");
//                                gameOver();
//                            }

                                /*if (g.gameStaute == GameStaute.run) {
                                 // g.CurrentMove =move;     
                                 boolean bool = true;

                                 while (bool) {
                                 g.getNextMove();

                                 if (g.AcceptPlayerMove()) {
                                 g.ApplyPlayerMove();
                                 bool = false;
                                 }
                                 }

                                 Show(g.grid.Row, g.grid.Col);
                                 g.changePlayer();
                                 Show(g.grid.Row, g.grid.Col);

                                 }
                                 }*/
                            }
                            Show(g.grid.Row, g.grid.Col);
                            if (g.rules.typePlayers == 2) {
                                if (g.PlayerList[0].Staute == PlayerStaute.Win) {
                                    celebrate();
                                } else if (g.PlayerList[1].Staute == PlayerStaute.Win) {
                                    gameOver();
                                }

                            }
                        }
                    });

                }

            }
        });

        ret.setOnAction(e -> primaryStage.setScene(mainScene));

        // Add action to menu
        newGame.setOnAction(e
                -> {
                    g.restart();
                    Show(g.grid.Row, g.grid.Col);
                    gridPane.getChildren().clear();
                    primaryStage.setScene(mainScene);
                    // start(primaryStage);
                }
        );

        restart.setOnAction(e
                -> {
                    g.restart();
                    Show(g.grid.Row, g.grid.Col);

                }
        );
        
         quiqSaveItem.setOnAction(e
                -> {
                 //code here for quiq save menu  
//                 file.saveGame(g, "mhd.bin");
                }
        );
         
            saveItem.setOnAction(e
                -> {
                 //code here for  save menu  
                 FileChooser fc = new FileChooser();
                 File path =  fc.showSaveDialog(primaryStage);  
                 file.saveGame(g, path.toString(),g.loadPaths);
                }
        );

        exit.setOnAction(actionEvent -> Platform.exit());

        changeTheme();

        /*  Button b =new Button("retart");
         b.setPrefSize(55, 55);
         gridPane.add(b,3,10);
         b.setOnMouseClicked((MouseEvent e) -> {
         //  g.restart();
         g.InitGame();

         Show();
         //start(primaryStage);
         }); */
        mainScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());
        gridScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());
        choiceScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());
        multiScene.getStylesheets().add(gui.class.getResource("whteTheme.css").toExternalForm());

        primaryStage.setTitle("Test");
        primaryStage.setTitle("Test");
        primaryStage.setScene(mainScene);
        gridScene.widthProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Double width = (Double) newValue;
                gridPane.setPrefWidth(width);
            }
        }
        );
        primaryStage.show();
        g = new guiGame();
        //g.setPriority(g.MAX_PRIORITY);
        //g.start();
    }

    /**
     * @param args the command line arguments
     *
     *
     */
    public class ubdate extends Thread {

        boolean bool = false;

        @Override
        public void run() {
            while (true) {
                System.out.println("ubdate");
                if (g != null) {
                    if (g.gameStaute == GameStaute.run) {
                        try {
                            Thread.sleep(10000000);
                        } catch (InterruptedException ex) {
                            //Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            if (g.timer.getCounter() == 0) {
                                g.Currentplayer.Staute = PlayerStaute.Wait;
                                g.changePlayer();
                                
                                g.timer.reset();
                            }
                            g.CurrentMove = g.Currentplayer.GetPlayerMove(g.CurrentMove);
                            if (g.AcceptPlayerMove()) {
                                g.ApplyPlayerMove();
                                System.out.println("apply...");
                                g.changePlayer();
                                g.timer.reset();
                                bool = true;
                            }
                        } catch (Exception ex) {
                            System.out.println("ex..............");
                        }

                        if (true) {
                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    if (g != null) {
                                        if (g.gameStaute != null) {
                                            System.out.println("desplay...");

                                            Show(g.rules.Row, g.rules.Col);
                                        }
                                    }
                                }
                            });
                            bool = false;
                        }
//                    try{
//                        if(g != null)
//                    if(g.gameStaute != null)
//                    {
//                        System.out.println("desplay...");
//                        Show(g.rules.Row, g.rules.Col);
//                    }
//                    }catch(Exception ex){ System.out.println("ex...............");}

                    }
                }
            }
        }
    }

    
    class Check extends Thread
    {
        @Override
        public void run()
        {
            int prev = -1;
            while(true)
            {System.out.println("checked...");
            
                 if (g != null && g.Currentplayer!=null) 
                 {
                     if(prev != g.timer.getCounter())
                 {
                     
                     ubd.interrupt();
                     prev = g.timer.getCounter();
                 }
                 if(g.Currentplayer.currentMove != null)
                 {
                     ubd.interrupt();
                     System.out.println("ooooooooooosssssssssssssaaaaaaaaaaaaaaaallllllllllwwwwwmxkksckd");
                 }
                     
                 }
              
            }
        }
        
    }
    
    public void run(String[] args) {

        launch(args);

    }

}
