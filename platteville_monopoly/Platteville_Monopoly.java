/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platteville_monopoly;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Platteville_Monopoly extends Application implements EventHandler<ActionEvent> {
   int NUM_CARDS = 14;
   int NUM_PROPERTIES = 28;
   int NUM_NORMAL = 22;
   int NUM_UTILITY = 2;
   int NUM_BAR = 4;
   int NUM_TILES = 40;
   int this_turn = 0;
   int p1_list_count = 0;
   int p2_list_count = 0;
   int p3_list_count = 0;
   int p4_list_count = 0;
   String p1_balance, p2_balance, p3_balance, p4_balance;
   int total;
   int thisRent;
      
   Action_Cards ac [] = new Action_Cards [NUM_CARDS];
   Monetary_Cards mc [] = new Monetary_Cards [NUM_CARDS];
   Properties properties [] = new Properties [NUM_PROPERTIES];
   Gameboard_Layout traverse [] = new Gameboard_Layout [NUM_TILES];
   Player_Stats [] p = new Player_Stats [4];
   TableView<Property_List> player_properties = new TableView<>();
   ObservableList <Property_List> list = FXCollections.observableArrayList();
   Property_List p1_list [] = new Property_List [NUM_PROPERTIES];
   Property_List p2_list [] = new Property_List [NUM_PROPERTIES];
   Property_List p3_list [] = new Property_List [NUM_PROPERTIES];
   Property_List p4_list [] = new Property_List [NUM_PROPERTIES];
      
   Scene scene, nextScene, propertiesScene;
   Pane menu_pane, gameboard_pane;
   GridPane pane;
   ComboBox<String> propertyToSell = new ComboBox<>();
   Image menu_background, board, wood;
   Image [] dice1;
   Image [] dice2;
   Image [] pc;
   ImageView menu, gameboard, wood_background, first_dice, second_dice, property_cards;
   Circle p1_piece, p2_piece, p3_piece, p4_piece;
   Label player1, player2, player3, player4, prompt, prompt2, prompt3, prompt4, 
      buy, alreadyOwned, payRent, monopolyCard1, monopolyCard2, sellPropertyPrompt,
      getOutJail, inJailPrompt;
   TextField p1_money, p2_money, p3_money, p4_money, exception;
   Button create_game, viewProperties, back, dice_roll, buy_house, 
      buy_hotel, sellProperty, end_turn, forfeit, yes, no, yes2, no2, confirm, cancel;
      
   @Override
   public void start(Stage primaryStage) {
      for(int i = 0; i < 4; i++) {
         p[i] = new Player_Stats();      
      }
      primaryStage.setTitle("Platteville Monopoly");
      menu_pane = new Pane();
      gameboard_pane = new Pane();
      //gameboard_pane.setStyle("-fx-background-color: white");
      pane = new GridPane();
      pane.setStyle("-fx-background-color: #4682b4");
      pane.setPadding(new Insets(40,40,40,40));
      pane.setVgap(20); 
      
      scene = new Scene(menu_pane, 1000, 750);
      nextScene = new Scene(gameboard_pane, 1500, 900);
      propertiesScene = new Scene (pane, 450, 450);
      
      propertyToSell.setVisible(false);
      propertyToSell.setLayoutX(1035);
      propertyToSell.setLayoutY(600);
      propertyToSell.setPrefWidth(125);
      propertyToSell.setPrefHeight(40);
      
      menu_background = new Image ("platteville_monopoly/menu_background.png");
      menu = new ImageView(menu_background);
      menu.setFitHeight(750);
      menu.setPreserveRatio(true);
      
      board = new Image ("platteville_monopoly/gameboard.jpg");
      gameboard = new ImageView(board);
      gameboard.setFitHeight(800);
      gameboard.setPreserveRatio(true);
      gameboard.setLayoutX(50);
      gameboard.setLayoutY(50);
      
      wood = new Image ("platteville_monopoly/wood.jpg");
      wood_background = new ImageView(wood);
      wood_background.setFitHeight(1250);
      wood_background.setPreserveRatio(true);
      
      dice1 = new Image [7];
      for(int i = 1; i < 7; i++) {
         dice1 [i] = new Image ("platteville_monopoly/" + i + ".png");
      }
      first_dice = new ImageView ();
      first_dice.setImage(dice1[1]);
      first_dice.setFitHeight(75);
      first_dice.setPreserveRatio(true);
      first_dice.setLayoutX(1085);
      first_dice.setLayoutY(425);
      
      dice2 = new Image [7];
      for(int i = 1; i < 7; i++) {
         dice2 [i] = new Image ("platteville_monopoly/" + i + ".png"); 
      }
      second_dice = new ImageView ();
      second_dice.setImage(dice2[6]);
      second_dice.setFitHeight(75);
      second_dice.setPreserveRatio(true);
      second_dice.setLayoutX(1190);
      second_dice.setLayoutY(425);
      
      pc = new Image [28];
      for(int i = 0; i < 28; i++) {
         pc [i] = new Image ("platteville_monopoly/PC" + i + ".jpg");
      }
      property_cards = new ImageView ();
      property_cards.setImage(pc[1]);
      property_cards.setFitHeight(250);
      property_cards.setPreserveRatio(true);
      property_cards.setLayoutX(950);
      property_cards.setLayoutY(525);
      property_cards.setVisible(false);
      
      p1_piece = new Circle();
      p1_piece.setRadius(15);
      p1_piece.setCenterX(775);
      p1_piece.setCenterY(815);
      p1_piece.setStroke(Color.BLACK);
      p1_piece.setFill(Color.RED);
      
      p2_piece = new Circle();
      p2_piece.setRadius(15);
      p2_piece.setCenterX(815);
      p2_piece.setCenterY(815);
      p2_piece.setStroke(Color.BLACK);
      p2_piece.setFill(Color.GREEN);
      
      p3_piece = new Circle();
      p3_piece.setRadius(15);
      p3_piece.setCenterX(775);
      p3_piece.setCenterY(775);
      p3_piece.setStroke(Color.BLACK);
      p3_piece.setFill(Color.BLUE);
      
      p4_piece = new Circle();
      p4_piece.setRadius(15);
      p4_piece.setCenterX(815);
      p4_piece.setCenterY(775);
      p4_piece.setStroke(Color.BLACK);
      p4_piece.setFill(Color.PURPLE);
      
      player1 = new Label("Player 1");
      player1.setLayoutX(950);
      player1.setLayoutY(50);
      player1.setPrefWidth(200);
      player1.setPrefHeight(50);
      player1.setStyle("-fx-font-size:30");
      
      player2 = new Label("Player 2");
      player2.setLayoutX(950);
      player2.setLayoutY(100);
      player2.setPrefWidth(200);
      player2.setPrefHeight(50);
      player2.setStyle("-fx-font-size:30");
      
      player3 = new Label("Player 3");
      player3.setLayoutX(950);
      player3.setLayoutY(150);
      player3.setPrefWidth(200);
      player3.setPrefHeight(50);
      player3.setStyle("-fx-font-size:30");
      
      player4 = new Label("Player 4");
      player4.setLayoutX(950);
      player4.setLayoutY(200);
      player4.setPrefWidth(200);
      player4.setPrefHeight(50);
      player4.setStyle("-fx-font-size:30");
      
      prompt = new Label("<- Your Turn!");
      prompt.setTextFill(Color.web("#b22222"));
      prompt.setLayoutX(1225);
      prompt.setLayoutY(50);
      prompt.setPrefWidth(225);
      prompt.setPrefHeight(50);
      prompt.setStyle("-fx-font-size: 30");
      prompt.setVisible(true);
      
      prompt2 = new Label("<- Your Turn!");
      prompt2.setTextFill(Color.web("#006400"));
      prompt2.setLayoutX(1225);
      prompt2.setLayoutY(100);
      prompt2.setPrefWidth(225);
      prompt2.setPrefHeight(50);
      prompt2.setStyle("-fx-font-size:30");
      prompt2.setVisible(false);
      
      prompt3 = new Label("<- Your Turn!");
      prompt3.setTextFill(Color.web("#000080"));
      prompt3.setLayoutX(1225);
      prompt3.setLayoutY(150);
      prompt3.setPrefWidth(225);
      prompt3.setPrefHeight(50);
      prompt3.setStyle("-fx-font-size:30");
      prompt3.setVisible(false);
      
      prompt4 = new Label("<- Your Turn!");
      prompt4.setTextFill(Color.web("#4b0082"));
      prompt4.setLayoutX(1225);
      prompt4.setLayoutY(200);
      prompt4.setPrefWidth(225);
      prompt4.setPrefHeight(50);
      prompt4.setStyle("-fx-font-size:30");
      prompt4.setVisible(false);
      
      alreadyOwned = new Label ();
      alreadyOwned.setLayoutX(950);
      alreadyOwned.setLayoutY(550);
      alreadyOwned.setPrefWidth(500);
      alreadyOwned.setPrefHeight(50);
      alreadyOwned.setStyle("-fx-font-size: 25");
      alreadyOwned.setVisible(false);
      
      payRent = new Label ();
      payRent.setLayoutX(950);
      payRent.setLayoutY(600);
      payRent.setPrefWidth(450);
      payRent.setPrefHeight(50);
      payRent.setStyle("-fx-font-size: 25");
      payRent.setVisible(false);
      
      monopolyCard1 = new Label ();
      monopolyCard1.setLayoutX(975);
      monopolyCard1.setLayoutY(550);
      monopolyCard1.setPrefWidth(450);
      monopolyCard1.setPrefHeight(100);
      monopolyCard1.setStyle("-fx-font-size: 25");
      monopolyCard1.setVisible(false);
      
      monopolyCard2 = new Label ();
      monopolyCard2.setLayoutX(975);
      monopolyCard2.setLayoutY(600);
      monopolyCard2.setPrefWidth(450);
      monopolyCard2.setPrefHeight(100);
      monopolyCard2.setStyle("-fx-font-size: 25");
      monopolyCard2.setVisible(false);
      
      sellPropertyPrompt = new Label ("Please choose a property to sell.");
      sellPropertyPrompt.setLayoutX(1000);
      sellPropertyPrompt.setLayoutY(500);
      sellPropertyPrompt.setPrefWidth(450);
      sellPropertyPrompt.setPrefHeight(100);
      sellPropertyPrompt.setStyle("-fx-font-size: 25");
      sellPropertyPrompt.setVisible(false);
      
      inJailPrompt = new Label ("You are in JAIL! :(");
      inJailPrompt.setLayoutX(975);
      inJailPrompt.setLayoutY(500);
      inJailPrompt.setPrefWidth(450);
      inJailPrompt.setPrefHeight(100);
      inJailPrompt.setStyle("-fx-font-size: 30");
      inJailPrompt.setTextFill(Color.web("#b22222"));
      inJailPrompt.setVisible(false);
      
      getOutJail = new Label ("Whould you like to pay the $50 fine?");
      getOutJail.setLayoutX(975);
      getOutJail.setLayoutY(550);
      getOutJail.setPrefWidth(450);
      getOutJail.setPrefHeight(100);
      getOutJail.setStyle("-fx-font-size: 25");
      getOutJail.setVisible(false);
      
      yes2 = new Button ("Yes");
      yes2.setLayoutX(1050);
      yes2.setLayoutY(650);
      yes2.setPrefWidth(100);
      yes2.setPrefHeight(50);
      yes2.setVisible(false);
      
      no2 = new Button ("No");
      no2.setLayoutX(1200);
      no2.setLayoutY(650);
      no2.setPrefWidth(100);
      no2.setPrefHeight(50);
      no2.setVisible(false);
      
      buy = new Label ();
      buy.setVisible(false);
      
      p1_money = new TextField();
      p1_balance = Integer.toString(p[0].getCash());
      p1_money.setText(p1_balance);
      p1_money.setEditable(false);
      p1_money.setLayoutX(1100);
      p1_money.setLayoutY(50);
      p1_money.setPrefWidth(100);
      p1_money.setPrefHeight(25);
      p1_money.setStyle("-fx-font-size:20");
      
      p2_money = new TextField();
      p2_balance = Integer.toString(p[0].getCash());
      p2_money.setText(p2_balance);
      p2_money.setEditable(false);
      p2_money.setLayoutX(1100);
      p2_money.setLayoutY(100);
      p2_money.setPrefWidth(100);
      p2_money.setPrefHeight(25);
      p2_money.setStyle("-fx-font-size:20");
      
      p3_money = new TextField();
      p3_balance = Integer.toString(p[0].getCash());
      p3_money.setText(p3_balance);
      p3_money.setEditable(false);
      p3_money.setLayoutX(1100);
      p3_money.setLayoutY(150);
      p3_money.setPrefWidth(100);
      p3_money.setPrefHeight(25);
      p3_money.setStyle("-fx-font-size:20");
      
      p4_money = new TextField();
      p4_balance = Integer.toString(p[0].getCash());
      p4_money.setText(p4_balance);
      p4_money.setEditable(false);
      p4_money.setLayoutX(1100);
      p4_money.setLayoutY(200);
      p4_money.setPrefWidth(100);
      p4_money.setPrefHeight(25);
      p4_money.setStyle("-fx-font-size:20");
      
      exception = new TextField();
      exception.setEditable(true);
      exception.setLayoutX(925);
      exception.setLayoutY(800);
      exception.setPrefWidth(500);
      exception.setPrefHeight(25);
      exception.setStyle("-fx-font-size:20");
      
      create_game = new Button("Start Game");
      create_game.setLayoutX(400);
      create_game.setLayoutY(500);
      create_game.setPrefWidth(200);
      create_game.setPrefHeight(75);
      create_game.setStyle("-fx-font-size:20");
      
      back = new Button ("Return to Game");
      back.setPrefWidth(100);
      back.setPrefHeight(25);
      back.setStyle("-fx-font-size:12");
      
      dice_roll = new Button ("Roll Dice");
      dice_roll.setStyle("-fx-background-color: #ff8c00; -fx-border-color: #000080;");
      dice_roll.setLayoutX(990);
      dice_roll.setLayoutY(350);
      dice_roll.setPrefWidth(125);
      dice_roll.setPrefHeight(50);
      
      viewProperties = new Button ("View Properties");
      viewProperties.setStyle("-fx-background-color: #ff8c00; -fx-border-color: #000080;");
      viewProperties.setLayoutX(1115);
      viewProperties.setLayoutY(350);
      viewProperties.setPrefWidth(125);
      viewProperties.setPrefHeight(50);
      
      end_turn = new Button ("End Turn");
      end_turn.setStyle("-fx-background-color: #ff8c00; -fx-border-color: #000080;");
      end_turn.setLayoutX(1240);
      end_turn.setLayoutY(350);
      end_turn.setPrefWidth(125);
      end_turn.setPrefHeight(50);
      
      buy_house = new Button ("Buy House");
      buy_house.setStyle("-fx-background-color: #ff8c00; -fx-border-color: #000080;");
      buy_house.setLayoutX(990);
      buy_house.setLayoutY(300);
      buy_house.setPrefWidth(125);
      buy_house.setPrefHeight(50);
      
      buy_hotel = new Button ("Buy Hotel");
      buy_hotel.setStyle("-fx-background-color: #ff8c00; -fx-border-color: #000080;");
      buy_hotel.setLayoutX(1115);
      buy_hotel.setLayoutY(300);
      buy_hotel.setPrefWidth(125);
      buy_hotel.setPrefHeight(50);
      
      sellProperty = new Button ("Sell Property");
      sellProperty.setStyle("-fx-background-color: #ff8c00; -fx-border-color: #000080;");
      sellProperty.setLayoutX(1240);
      sellProperty.setLayoutY(300);
      sellProperty.setPrefWidth(125);
      sellProperty.setPrefHeight(50);
      
      forfeit = new Button ("Forfeit");
      forfeit.setLayoutX(400);
      forfeit.setLayoutY(500);
      forfeit.setPrefWidth(200);
      forfeit.setPrefHeight(75);
      
      yes = new Button ("Yes");
      yes.setLayoutX(1200);
      yes.setLayoutY(600);
      yes.setPrefWidth(100);
      yes.setPrefHeight(50);
      yes.setVisible(false);
      
      no = new Button ("No");
      no.setLayoutX(1350);
      no.setLayoutY(600);
      no.setPrefWidth(100);
      no.setPrefHeight(50);
      no.setVisible(false);
      
      confirm = new Button ("Confirm");
      confirm.setLayoutX(1210);
      confirm.setLayoutY(600);
      confirm.setPrefWidth(100);
      confirm.setPrefHeight(50);
      confirm.setVisible(false);
      
      cancel = new Button ("Cancel");
      cancel.setLayoutX(1210);
      cancel.setLayoutY(700);
      cancel.setPrefWidth(100);
      cancel.setPrefHeight(50);
      cancel.setVisible(false);
      
      TableColumn <Property_List, String> first = new TableColumn("ID");
      first.setMinWidth(50);
      first.setCellValueFactory(new PropertyValueFactory<>("listId"));
      
      TableColumn <Property_List, String> second = new TableColumn("Name");
      second.setMinWidth(200);
      second.setCellValueFactory(new PropertyValueFactory<>("name"));
      
      TableColumn <Property_List, String> third = new TableColumn("Price");
      third.setMinWidth(100);
      third.setCellValueFactory(new PropertyValueFactory<>("price"));
      
      //TableColumn<Properties, Double> third = new TableColumn ("Rent");
      //third.setMinWidth(100);
      //third.setCellValueFactory(new PropertyValueFactory<>("rent"));
      
      player_properties.setItems(list);
      player_properties.getColumns().addAll(first, second, third);
      
      menu_pane.getChildren().addAll(menu, create_game);
      gameboard_pane.getChildren().addAll(wood_background, gameboard, player1, 
         player2, player3, player4, p1_money, p2_money, p3_money, p4_money,
         viewProperties, dice_roll, exception, sellPropertyPrompt, propertyToSell,
         first_dice, second_dice, buy_house, buy_hotel, sellProperty, 
         p1_piece, p2_piece, p3_piece, p4_piece, prompt, prompt2, 
         prompt3, prompt4, end_turn, property_cards, yes, no,
         alreadyOwned, payRent,monopolyCard1, monopolyCard2, confirm, cancel, inJailPrompt, 
         getOutJail, yes2, no2);
      
      pane.add(player_properties, 0, 0);
      pane.add(back, 0, 1);
      
      primaryStage.setScene(scene);
      
      create_game.setOnAction((ActionEvent event) -> {
         primaryStage.setScene(nextScene);
         try {
            Scanner in1 = new Scanner(new File("Card_Input.txt"));
            LoadCards(in1, NUM_CARDS, ac, mc);
            //Scanner in2 = new Scanner(new File("Normal_Property_Input.txt"));
            //LoadNormalProperties (in2, NUM_NORMAL, normal);
            //Scanner in3 = new Scanner(new File("Utility_Property_Input.txt"));
            //LoadUtilityProperties (in3, NUM_UTILITY, utility);
            //Scanner in4 = new Scanner(new File("Bar_Property_Input.txt"));
            //LoadBarProperties (in4, NUM_BAR, bars); 
            Scanner in5 = new Scanner(new File("Gameboard_Layout.txt"));
            LoadGameboard (in5, NUM_TILES, traverse);
            Scanner in6 = new Scanner(new File("All_Properties.txt"));
            LoadProperties (in6, properties);
         }
         catch (FileNotFoundException e) {
            System.out.println("No file found");
         } catch (IOException ex) {
            Logger.getLogger(Platteville_Monopoly.class.getName()).log(Level.SEVERE, null, ex);
         }
      });

      back.setOnAction(event -> primaryStage.setScene(nextScene));
      
      dice_roll.setOnAction((ActionEvent event) -> {
         monopolyCard1.setVisible(false);
         monopolyCard2.setVisible(false);
         alreadyOwned.setVisible(false);
         payRent.setVisible(false);
         if(p[this_turn].getInJail() == 0) {
            if(p[this_turn].getDiceRoll() == false) {
            Random dice_face = new Random();
            int rand1 = dice_face.nextInt(6);
            rand1++;
            int rand2 = dice_face.nextInt(6);
            rand2++;
            System.out.println("Dice values: " + rand1 + ' ' + rand2);
            if(rand1 != rand2) {
               p[this_turn].setDiceRoll(true);
            }
         
            RollFirstDice(rand1);
            RollSecondDice(rand2);
         
            total = rand1 + rand2;
            UpdateLocation (total);
            }
            else {
               exception.setText("You have already rolled the dice!");
            }
         }
         else {
            exception.setText("You are in Jail!");
            inJailPrompt.setVisible(true);
            getOutJail.setVisible(true);
            yes2.setVisible(true);
            no2.setVisible(true);
         }
      });
      
      end_turn.setOnAction((ActionEvent event) -> {
         if(p[this_turn].getDiceRoll()==true) {
            if(p[this_turn].getInJail()!= 0)
               p[this_turn].updateinJail(-1);
            UpdatePlayerTurn();
            switch (this_turn) {
               case 0:
                  prompt4.setVisible(false);
                  prompt.setVisible(true);
                  break;
               case 1:
                  prompt.setVisible(false);
                  prompt2.setVisible(true);
                  break;
               case 2:
                  prompt2.setVisible(false);
                  prompt3.setVisible(true);
                  break;
               default:
                  prompt3.setVisible(false);
                  prompt4.setVisible(true);
                  break;
            }
            exception.clear();
            alreadyOwned.setVisible(false);
            payRent.setVisible(false);
            monopolyCard1.setVisible(false);
            monopolyCard2.setVisible(false);
         }
         else
            exception.setText("You cannot end turn without rolling the dice!");
      });
      
      yes.setOnAction((ActionEvent event) -> {
         int property = traverse[p[this_turn].getLocation()].getPropertyId();
         System.out.println("the player location is " + p[this_turn].getLocation());
         System.out.println("the property id is " + property);
         System.out.println("the property price is " + properties[property].getPrice());
         BuyProperty(property);
         UpdateCashTextField(this_turn);
         property_cards.setVisible(false);
         buy.setVisible(false);
         yes.setVisible(false);
         no.setVisible(false);
      });
      
      no.setOnAction((ActionEvent event) -> {
         property_cards.setVisible(false);
         buy.setVisible(false);
         yes.setVisible(false);
         no.setVisible(false);
      });
      
      yes2.setOnAction((ActionEvent event) -> {
         p[this_turn].setinJail(0);
         p[this_turn].setCash(-50);
         UpdateCashTextField(this_turn);
         inJailPrompt.setVisible(false);
         getOutJail.setVisible(false);
         yes2.setVisible(false);
         no2.setVisible(false);
      });
      
      no2.setOnAction((ActionEvent event) -> {
         inJailPrompt.setVisible(false);
         getOutJail.setVisible(false);
         yes2.setVisible(false);
         no2.setVisible(false);
      });
      
      viewProperties.setOnAction((ActionEvent event) -> {
         player_properties.getItems().clear();
         DisplayList(this_turn);
         primaryStage.setScene(propertiesScene);
      });
      
      sellProperty.setOnAction((ActionEvent event) -> {
         try {
            sellProperty();
            sellPropertyPrompt.setVisible(true);
            propertyToSell.setVisible(true);
            confirm.setVisible(true);
            cancel.setVisible(true);
         }
         catch (NullPointerException e) {
            exception.setText("You do not have any properties to sell.");
         }
      });
      
      confirm.setOnAction((ActionEvent event) -> {
         String property = propertyToSell.getValue();
            property = property.replaceAll("\\D+","");
            System.out.println();
            int id = Integer.parseInt(property);
            System.out.println(id);
            int length = 0;
            if(this_turn == 0) {
               length = p1_list_count;
               p[this_turn].setCash(p1_list[id].getPrice());
               properties[p1_list[id].getId()].setPlayer(10);
               for(int i = 0; i < length; i++){
                  if(p1_list[i].getListId() == id){
                     p1_list = removeElement(p1_list, i);
                     break;
                  }
               } 
               p1_list_count--;
            }
            else if(this_turn == 1) {
               length = p2_list_count;
               p[this_turn].setCash(p2_list[id].getPrice());
               properties[p2_list[id].getId()].setPlayer(10);
               for(int i = 0; i < length; i++){
                  if(p2_list[i].getListId() == id){
                     p2_list = removeElement(p2_list, i);
                     break;
                  }
               }
               p2_list_count--;
            }  
            else if(this_turn == 2) {
               length = p3_list_count;
               p[this_turn].setCash(p3_list[id].getPrice());
               properties[p3_list[id].getId()].setPlayer(10);
               for(int i = 0; i < length; i++){
                  if(p3_list[i].getListId() == id){
                     p3_list = removeElement(p3_list, i);
                     break;
                  }
               }
               p3_list_count--;
            }
            else if(this_turn == 3) {
               length = p4_list_count;
               p[this_turn].setCash(p4_list[id].getPrice());
               properties[p4_list[id].getId()].setPlayer(10);
               for(int i = 0; i < length; i++){
                  if(p4_list[i].getListId() == id){
                     p4_list = removeElement(p4_list, i);
                     break;
                  }
               }
               p4_list_count--;
            }
            UpdateCashTextField(this_turn); 
            FinishTransaction();  
      });
      
      buy_house.setOnAction((ActionEvent event) -> {
         exception.setText("You cannot buy a house at this time.");
      });
      
      buy_hotel.setOnAction((ActionEvent event) -> {
         exception.setText("You cannot buy a hotel at this time.");
      });
      
      cancel.setOnAction((ActionEvent event) -> {
         FinishTransaction();
      });
         
      primaryStage.show();
   }
   public void LoadCards(Scanner input, int cards, Action_Cards [] ac, 
      Monetary_Cards [] mc) throws IOException
   {
      for(int i = 0; i < cards; i++) {
         int value = Integer.parseInt(input.nextLine());
         String text1 = input.nextLine();
         String text2 = input.nextLine();
         mc[i] = new Monetary_Cards(value, text1, text2);
         System.out.println("Successfully added: " + mc[i].getPrompt1() + mc[i].getPrompt2());
      }
      
      for(int i = 0; i < cards; i++) {
         int location_id = Integer.parseInt(input.nextLine());
         String text1 = input.nextLine();
         String text2 = input.nextLine();
         ac[i] = new Action_Cards(location_id, text1, text2);
         System.out.println("Successfully added: " + ac[i].getPrompt1() + ac[i].getPrompt2());
      }
   }
   public void LoadProperties(Scanner input, Properties [] properties) {
      LoadNormalProperties(input, NUM_NORMAL, properties);
      int total = NUM_NORMAL + NUM_BAR;
      LoadBarProperties (input, NUM_NORMAL, total, properties);
      LoadUtilityProperties (input, total, total + NUM_UTILITY, properties);
   }
   public void LoadNormalProperties (Scanner input, int num, Properties [] properties) {
      for (int i = 0; i < num; i++)
      {
         String name = input.nextLine();
         int price = Integer.parseInt(input.nextLine());
         int newMortgage = Integer.parseInt(input.nextLine());
         int id = Integer.parseInt(input.nextLine());
         int numHouses = Integer.parseInt(input.nextLine());
         String color = input.nextLine();
         int rent = Integer.parseInt(input.nextLine());
         int h1 = Integer.parseInt(input.nextLine());
         int h2 = Integer.parseInt(input.nextLine());
         int h3 = Integer.parseInt(input.nextLine());
         int h4 = Integer.parseInt(input.nextLine());
         int hotel = Integer.parseInt(input.nextLine());
         properties[i] = new Normal_Properties (name, price, newMortgage, id, numHouses, 
            color, rent, h1, h2, h3, h4, hotel);
         System.out.println("Successfully added: " + name);
      }
   }
   
   public void LoadUtilityProperties (Scanner input, int start, int num, Properties [] properties) {
      for (int i = start; i < num; i++)
      {
         String name = input.nextLine();
         int price = Integer.parseInt(input.nextLine());
         int newMortgage = Integer.parseInt(input.nextLine());
         int id = Integer.parseInt(input.nextLine());
         int numHouses = Integer.parseInt(input.nextLine());
         properties[i] = new Utility_Properties (name, price, newMortgage, id, numHouses);
         System.out.println("Successfully added: " + name);
      }
   }
   
   public void LoadBarProperties (Scanner input, int start, int num, Properties [] properties) {
      for (int i = start; i < num; i++)
      {
         String name = input.nextLine();
         int price = Integer.parseInt(input.nextLine());
         int newMortgage = Integer.parseInt(input.nextLine());
         int id = Integer.parseInt(input.nextLine());
         int numHouses = Integer.parseInt(input.nextLine());
         properties[i] = new Bar_Properties (name, price, newMortgage, id, numHouses);
         System.out.println("Successfully added: " + name);
      }
   }
   
    public void LoadGameboard (Scanner input, int num, Gameboard_Layout [] traverse) {
      for (int i = 0; i < num; i++)
      {
         String temp = input.nextLine();
         char type = temp.charAt(0);
         int location_id = Integer.parseInt(input.nextLine());
         int property_id = Integer.parseInt(input.nextLine());
         int x = Integer.parseInt(input.nextLine());
         int y = Integer.parseInt(input.nextLine());
         traverse[i] = new Gameboard_Layout (type, location_id, property_id, x, y);
         System.out.println("Successfully added: " + type + " " + property_id);
      }
   }
   public void RollFirstDice (int face_value) {
       switch (face_value) {
            case 1:
               first_dice.setImage(dice1[1]);
               break;
            case 2:
               first_dice.setImage(dice1[2]);
               break;
            case 3:
               first_dice.setImage(dice1[3]);
               break;
            case 4:
               first_dice.setImage(dice1[4]);
               break;
            case 5:
               first_dice.setImage(dice1[5]);
               break;
            case 6:
               first_dice.setImage(dice1[6]);
               break;
            default:
               break;
         }
   }
   
   public void RollSecondDice (int face_value) {
       switch (face_value) {
            case 1:
               second_dice.setImage(dice2[1]);
               break;
            case 2:
               second_dice.setImage(dice2[2]);
               break;
            case 3:
               second_dice.setImage(dice2[3]);
               break;
            case 4:
               second_dice.setImage(dice2[4]);
               break;
            case 5:
               second_dice.setImage(dice2[5]);
               break;
            case 6:
               second_dice.setImage(dice2[6]);
               break;
            default:
               break;
         }
   }
    
   public void UpdateLocation (int total) {
      int next = 0;
      switch (this_turn) {
         case 0:
         {
            p[0].setLocation(total);
            next = p[0].getLocation();
            System.out.println("The location is " + next);
            p1_piece.setCenterX(traverse[next].getxCoordinate());
            p1_piece.setCenterY(traverse[next].getyCoordinate());
            break;
         }
         case 1:
         {
            p[1].setLocation(total);
            next = p[1].getLocation();
            System.out.println("The location is " + next);
            p2_piece.setCenterX(traverse[next].getxCoordinate());
            p2_piece.setCenterY(traverse[next].getyCoordinate());
            break;
         }
         case 2:
         {
            p[2].setLocation(total);
            next = p[2].getLocation();
            System.out.println("The location is " + next);
            p3_piece.setCenterX(traverse[next].getxCoordinate());
            p3_piece.setCenterY(traverse[next].getyCoordinate());
            break;
         }
         case 3:
         {
            p[3].setLocation(total);
            next = p[3].getLocation();
            System.out.println("The location is " + next);
            p4_piece.setCenterX(traverse[next].getxCoordinate());
            p4_piece.setCenterY(traverse[next].getyCoordinate());
            break;
         }
         default:
            break;
      }
      UpdateCashTextField(this_turn);
      TileAction (next);
    }
   
   public void TileAction(int next) {
      char type = traverse[next].getType();
      System.out.println("The type is " + type);
      int image = traverse[next].getPropertyId();
      System.out.println("The image is " + image);
      if (type == 'J') {
         p[this_turn].setinJail(3);
         UpdatePlayerTurn();
      }
      else if (type == 'O') {
         if(traverse[next].getLocationId() == 4)
            p[this_turn].setCash(-200);
         else if (traverse[next].getLocationId() == 38)
            p[this_turn].setCash(-75);
         UpdateCashTextField(this_turn);
      }
      else if (type == 'C') {
         Random chooseCard = new Random();
         int whichType = chooseCard.nextInt(1);
         if(whichType == 0) {
            int whichCard = chooseCard.nextInt(NUM_CARDS);
            monopolyCard1.setText(mc[whichCard].getPrompt1());
            monopolyCard1.setVisible(true);
            monopolyCard2.setText(mc[whichCard].getPrompt2());
            monopolyCard2.setVisible(true);
            p[this_turn].setCash(mc[whichCard].getValue());
            UpdateCashTextField(this_turn);
         } 
         else {
            int whichCard = chooseCard.nextInt(NUM_CARDS);
            monopolyCard1.setText(ac[whichCard].getPrompt1());
            monopolyCard1.setVisible(true);
            monopolyCard2.setText(ac[whichCard].getPrompt2());
            monopolyCard2.setVisible(true);
            p[this_turn].setLocation(ac[whichCard].getLocation());
         }
      }
      else if (type == 'X') {
         System.out.println("Nothing happens");
      }
      else {
         int property = traverse[p[this_turn].getLocation()].getPropertyId();
         if(properties[property].getPlayer_id() != 10) {
            PayRent(properties[property].getPlayer_id(), property);
         }
         else {
            property_cards.setImage(pc[image]);
            property_cards.setVisible(true);
            buy.setVisible(true);
            yes.setVisible(true);
            no.setVisible(true);
         }
      }
   }
   
   public void UpdatePlayerTurn() {
      p[this_turn].setDiceRoll(false);
      if(this_turn == 3)
            this_turn = 0;
      else
         this_turn++;
   }
   
   public void BuyProperty(int property) {
      if(traverse[p[this_turn].getLocation()].getType() == 'B')
         p[this_turn].setNumBars(1);
      p[this_turn].setCash(-(properties[property].getPrice()));
      UpdateCashTextField(this_turn);
      properties[property].setPlayer(this_turn);
      UpdatePropertyList(this_turn, properties[property]);
   }
   
   public void UpdateCashTextField(int player_id) {
      if(player_id == 0) {
         p1_balance = Integer.toString(p[0].getCash());
         p1_money.setText(p1_balance);
      }
      else if(player_id == 1) {
         p2_balance = Integer.toString(p[1].getCash());
         p2_money.setText(p2_balance);
      }
      else if(player_id == 2) {
         p3_balance = Integer.toString(p[2].getCash());
         p3_money.setText(p3_balance);
      }
      else if(player_id == 3) {
         p4_balance = Integer.toString(p[3].getCash());
         p4_money.setText(p4_balance);
      } 
   }
   
   public void PayRent(int property_owner_id, int property_id) {
      alreadyOwned.setText("This property is already owned by Player " + (property_owner_id + 1) + '.');
      alreadyOwned.setVisible(true);
      int houses = properties[property_id].getNumHouses();
      System.out.println(houses);
      if(houses > 0) {
         thisRent = properties[property_id].getRent(total);
         payRent.setText("Pay " + thisRent + " in rent.");
         payRent.setVisible(true);
      }
      else if (houses < 0) {
         int multiplier = p[property_owner_id].getNumBars();
         System.out.println(multiplier);
         thisRent = properties[property_id].getRent(multiplier);
         payRent.setText("Pay " + thisRent + " in rent.");
         payRent.setVisible(true);
      }
      else {
         thisRent = properties[property_id].getRent(houses);
         payRent.setText("Pay " + thisRent + " in rent.");
         payRent.setVisible(true);
      }
      p[this_turn].setCash(-thisRent);
      p[property_owner_id].setCash(thisRent);
      UpdateCashTextField(this_turn);
      UpdateCashTextField(property_owner_id);
   }
   
   public void UpdatePropertyList(int player_id, Properties p) {
      if(player_id == 0) {
         p1_list[p1_list_count] = new Property_List (p.getName(), p.getPrice(), p.getId(), p1_list_count);
         p1_list_count++;
      }
      else if(player_id == 1) {
         p2_list[p2_list_count] = new Property_List (p.getName(), p.getPrice(), p.getId(), p2_list_count);
         p2_list_count++;
      }
      else if(player_id == 2) {
         p3_list[p3_list_count] = new Property_List (p.getName(), p.getPrice(), p.getId(), p3_list_count);
         p3_list_count++;
      }
      else if(player_id == 3) {
         p4_list[p4_list_count] = new Property_List (p.getName(), p.getPrice(), p.getId(), p4_list_count);
         p4_list_count++;
      } 
   }
   
   public void DisplayList(int player_id) {
      if(player_id == 0) {
         for(int i = 0; i < p1_list_count; i++) {
            list.add(p1_list[i]);
         }
      }
      else if(player_id == 1) {
         for(int i = 0; i < p2_list_count; i++) {
            list.add(p2_list[i]);
         }
      }
      else if(player_id == 2) {
         for(int i = 0; i < p3_list_count; i++) {
            list.add(p3_list[i]);
         }
      }
      else if(player_id == 3) {
         for(int i = 0; i < p4_list_count; i++) {
            list.add(p4_list[i]);
         }
      } 
   }
   
   public void sellProperty() {
      propertyToSell.getItems().clear();
      if(this_turn == 0) {
         for(int i = 0; i < p1_list_count; i++) {
            propertyToSell.getItems().add(p1_list[i].getName() + " (" + p1_list[i].getListId() + ')');
         }
         propertyToSell.setValue(p1_list[0].getName() + " (" + p1_list[0].getListId() + ')');
      }
      else if(this_turn == 1) {
         for(int i = 0; i < p2_list_count; i++) {
            propertyToSell.getItems().add(p2_list[i].getName() + " (" + p2_list[i].getListId() + ')');
         }
         propertyToSell.setValue(p2_list[0].getName() + " (" + p2_list[0].getListId() + ')');
      }
      else if(this_turn == 2) {
         for(int i = 0; i < p3_list_count; i++) {
            propertyToSell.getItems().add(p3_list[i].getName() + " (" + p3_list[i].getListId() + ')');
         }
         propertyToSell.setValue(p3_list[0].getName() + " (" + p3_list[0].getListId() + ')');
      }
      else if(this_turn == 3) {
         for(int i = 0; i < p4_list_count; i++) {
            propertyToSell.getItems().add(p4_list[i].getName() + " (" + p4_list[i].getListId() + ')');
         }
         propertyToSell.setValue(p4_list[0].getName() + " (" + p4_list[0].getListId() + ')');
      }
      propertyToSell.setPrefWidth(150);
   }
   
   public Property_List[] removeElement( Property_List [] list, int index ){
      List<Property_List> tempList = new ArrayList<>(Arrays.asList(list));
      tempList.remove(index);
      return tempList.toArray(new Property_List[0]);
        //Property_List [] listOut = new Property_List [list.length - 1];
        //int remainingElements = list.length - ( index + 1 );
        //System.arraycopy(list, 0, listOut, 0, index);
        //System.arraycopy(list, index + 1, listOut, index, remainingElements);
   }
   
   private void FinishTransaction() {
      sellPropertyPrompt.setVisible(false);
      propertyToSell.setVisible(false);
      confirm.setVisible(false);
      cancel.setVisible(false);
   }
           
   @Override
   public void handle(ActionEvent event) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public static void main(String[] args) {
      launch(args);
   }
}
