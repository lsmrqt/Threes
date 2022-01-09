/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threes;

import java.util.Scanner;

/**
 *
 * @author louis
 */
public class Game {
   Player namePlayer;
   Grid gridGame = new Grid();
   int choice;
   
   /*tell all the actions that the player can do*/
   int infoPlayer(){
       Scanner sc = new Scanner(System.in);
       System.out.println();
       System.out.println("What do you want to do ?");
       System.out.println("1-  Move up :");
       System.out.println("2-  Move down :");
       System.out.println("3-  Move left :");
       System.out.println("4-  Move rigth :");
       choice = sc.nextInt();
       while (choice<1 || choice>4){
           System.out.println("Please choose an available action :");
           choice = sc.nextInt();
       }
       System.out.println();
       return choice;
   }
   
   /*initialize the game*/
   void initializeGame(){
       /*empty the grid*/
       gridGame.emptyGrid();
       
       /*create the player*/
       Scanner sc = new Scanner(System.in);
       System.out.println("Choose your pseudo :");
       namePlayer= new Player(sc.nextLine());
       System.out.println();
       
       /*display the grid*/
       gridGame.displayGrid();
   }
   
   /*start the game*/
   void startGame(){
       initializeGame();
       Scanner sc = new Scanner(System.in);
       
       while(gridGame.noMove()==false){
           infoPlayer();
           gridGame.moveGrid(choice);
           /*game over*/
           if(gridGame.bombCounter()==true){
               break;
           }
           gridGame.addElement(choice);
           gridGame.displayGrid();
           namePlayer.ntry++;
        }
       
       /*calculate the score of the player*/
       for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                
                /*if the values is 1 or 2 just add the values to the socre of the player*/
                if (gridGame.valuesGrid(i,j)==1 || gridGame.valuesGrid(i,j)==2){
                    namePlayer.score = namePlayer.score + gridGame.valuesGrid(i,j);
                }
                
                /*give 2 times the values to the score of the player*/
                if (gridGame.valuesGrid(i,j)!=1 && gridGame.valuesGrid(i,j)!=2){
                    namePlayer.score= namePlayer.score+ gridGame.valuesGrid(i,j)*2;
                }
            }
        }
       
       System.out.println();
       System.out.println("La partie est terminé !");
       System.out.println(namePlayer.pseudo+" votre score est de "+ namePlayer.score +".");
       System.out.println(namePlayer.pseudo+" vous avez pu jouer "+ namePlayer.ntry +" coups.");
   }
}
