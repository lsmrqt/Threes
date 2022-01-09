/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threes;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author louis
 */
public class Grid {
    /*create a grid*/
    Integer gridGame[][]= new Integer[4][4];
    /*create a table of bomb*/
    Integer bombEmplacement[][]= new Integer[4][4];
    boolean bombNumber = false;
    boolean boxNumber = false;
    
    /* create the grid*/
    Grid(){
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                /* give the values 0 to every cell of the grid and bombEmplacement*/
                gridGame[i][j]=0;
                bombEmplacement[i][j]=0;
            }
        }
    }
    
    /*send the values of the grid to the score of the player*/
    int valuesGrid(int ligne, int column){
        return gridGame[ligne][column];
    }
    
    /*check if the player can move and check if the grid is full or not*/
    boolean noMove(){
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (gridGame[i][j]==0){
                    return false;
                }
            }
        }
        /*check if the player can do a vertival move*/
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                if ((gridGame[i][j]==gridGame[i+1][j] && gridGame[i][j]!=1 && gridGame[i][j]!=2)|| (gridGame[i][j]==1 && gridGame[i+1][j]==2) || (gridGame[i][j]==2 && gridGame[i+1][j]==1)){
                    /*the player can still move*/
                    return false;
                }
            }
        }
        /*check if the player can do a horizontal move*/
        for (int j=0; j<3; j++){
            for (int i=0; i<4; i++){
                if ((gridGame[i][j]==gridGame[i][j+1] && gridGame[i][j]!=1 && gridGame[i][j]!=2)|| (gridGame[i][j]==1 && gridGame[i][j+1]==2) || (gridGame[i][j]==2 && gridGame[i][j+1]==1)){
                    /*the player can still move*/
                    return false;
                }
            }
        }
       /*if the player can't move anymore*/
       return true;
    }
    
    /*epmty the grid for a new game*/
    void emptyGrid(){
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (gridGame[i][j]!=0){
                    /* give the values 0 to every cell of the grid*/
                    gridGame[i][j]=0;
                }
            }
        }
    }
    
    /* display the grid*/
    void displayGrid(){
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (bombEmplacement[i][j]!=0 || gridGame[i][j]==11){
                    /*display the bomb in red*/
                    if (bombEmplacement[i][j]!=0){
                        System.out.print("\u001B[31m" + bombEmplacement[i][j] + "\033[0m" + " ");
                    }
                    /*display a blocj in blue*/
                    if (gridGame[i][j]==11){
                        System.out.print("\u001B[33m" + gridGame[i][j] + "\033[0m" + " ");
                    }
                }
                if (gridGame[i][j]==10 && bombEmplacement[i][j]==0){
                    System.out.print("\u001B[31m" + 0 + "\033[0m" + " ");
                }
                
                if(gridGame[i][j]!=10 && gridGame[i][j]!=11){
                    System.out.print(gridGame[i][j]+ " ");
                }
            }
            /*comes back to the line*/
            System.out.println();
        }
    }
    /*kill a box*/
    void killBox(){
        
    }
    
    /*kill a bomb*/
    void killBomb(){
        
    }
    
    /*threes*/
    void threes(){
        
    }
    /*add an element in the grid*/
    void addElement(int choicePlayer){
        /*create an ArrayList who will stock the indice of an empty case*/ 
        ArrayList<Integer> caseEmpty = new ArrayList<>();
        /* create an ArrayList who will stock the values of the new values*/
        ArrayList<Integer> valuesList = new ArrayList<>();
        /*give 2 values to valuesElement*/
        valuesList.add(1);
        valuesList.add(2);
       
        /*addEl will save the indice of one of the empty case*/
        Integer addEl;
        
        /*values will save the values to add*/
        Integer values;
        Random add = new Random();
        Random val = new Random();
       
        /*take a number from 0 to the size of the ArrayList valuesList*/
        values = val.nextInt(valuesList.size());
        
        /*if bomb values is taken add 1 to bombNumber*/
        if (valuesList.get(values)==10){
            bombNumber=true;
        }
        
        /*if block values is taken add 1 to bombNumber*/
        if (valuesList.get(values)==11){
            boxNumber=true;
        }
        
        /*if the number of bomb or of the box1 is max remove values*/
        if (bombNumber==true){
            while(valuesList.get(values)==10 && valuesList.get(values)==11){
                values = val.nextInt(valuesList.size());
            }
        }
        
        if (boxNumber==true){
            if (bombNumber!=true){
                while(valuesList.get(values)==11){
                    values = val.nextInt(valuesList.size());
                }
            }
            if (bombNumber==true){
                while(valuesList.get(values)==11 || valuesList.get(values)==11){
                    values = val.nextInt(valuesList.size());
                }
            }
        }
        
        if (bombNumber==true && boxNumber==true){
            while(valuesList.get(values)==10 && valuesList.get(values)==11){
                values = val.nextInt(valuesList.size());
            }
        }
        
        /*move up*/
        if (choicePlayer==1){
            for (int j=0; j<4; j++){
                /*if a case is empty add the number of the column in caseEmpty*/
                if (gridGame[3][j]==0){
                    caseEmpty.add(j);
                }
            }
            
            /*if the size of the ArrayList caseEmpty is not 0*/ 
            if (caseEmpty.size()!=0){
                /*take the indice of the column with an empty case saved before in emptyCase*/
                addEl = add.nextInt(caseEmpty.size());
                gridGame[3][caseEmpty.get(addEl)]=valuesList.get(values);
               
            }   
        }
        
        /*move down*/
        if (choicePlayer==2){
            for (int j=0; j<4; j++){
                /*if a case is empty add the number of the column in caseEmpty*/
                if (gridGame[0][j]==0){
                    caseEmpty.add(j);
                }
            }
            /*if the size of the ArrayList caseEmpty is not 0*/ 
            if (!caseEmpty.isEmpty()){
                /*take the indice of the column with an empty case saved before in emptyCase*/
                addEl = add.nextInt(caseEmpty.size());
                gridGame[0][caseEmpty.get(addEl)]=valuesList.get(values);
               
            }
        }
        
        /*move left*/
        if (choicePlayer==3){
            for (int i=0; i<4; i++){
                /*if a case is empty add the number of the column in caseEmpty*/
                if (gridGame[i][3]==0){
                    caseEmpty.add(i);
                }
            }
            /*if the size of the ArrayList caseEmpty is not 0*/ 
            if (!caseEmpty.isEmpty()){
                /*take the indice of the ligne with an empty case saved before in emptyCase*/
                addEl = add.nextInt(caseEmpty.size());
                gridGame[caseEmpty.get(addEl)][3]=valuesList.get(values);
                
            }
        }
        
        /*move right*/
        if (choicePlayer==4){
            for (int i=0; i<4; i++){
                /*if a case is empty add the number of the column in caseEmpty*/
                if (gridGame[i][0]==0){
                    caseEmpty.add(i);
                }
            }
            
            /*if the size of the ArrayList caseEmpty is not 0*/ 
            if (!caseEmpty.isEmpty()){
                /*take the indice of the ligne with an empty case saved before in emptyCase*/
                addEl = add.nextInt(caseEmpty.size());
                gridGame[caseEmpty.get(addEl)][0]=valuesList.get(values);
                
            }
        }
    }
    
    /*minus 1 to every bomb counter*/
    boolean bombCounter(){
       for (int i=0; i<4; i++){
           for (int j=0; j<4; j++){
                if (bombEmplacement[i][j]!=0){
                    bombEmplacement[i][j]--;
                    /* if the counter of one bomb is null it's game over*/
                    if (bombEmplacement[i][j]==0){
                        return true;
                    }
                }
            }
        }
       return false;
    }
    
    /*move the grid in the direction choosen by the player*/
    void moveGrid(int choicePlayer){
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                /*move up*/
                /*don't move if i=0*/
                if (choicePlayer==1 && i>0){
                    /*if the values are 1 and 2*/
                    if((gridGame[i][j]==1 && gridGame[i-1][j]==2) || (gridGame[i][j]==2 && gridGame[i-1][j]==1)){
                        gridGame[i-1][j]=3;
                        gridGame[i][j]=0;
                    }
                    
                    /*if the values is the same*/
                    if(gridGame[i][j]==gridGame[i-1][j] && (gridGame[i][j]!=1 && gridGame[i][j]!=2)){
                        gridGame[i-1][j]=gridGame[i-1][j]*2;
                        gridGame[i][j]=0;
                    }
                    /*if the values is 0*/
                    if(gridGame[i-1][j]==0){
                        gridGame[i-1][j]=gridGame[i][j];
                        gridGame[i][j]=0;
                    }
                }
                
                /*move left*/
                /*don't move if j=0*/
                if (choicePlayer==3 && j>0){
                    /*if the values are 1 and 2*/
                    if((gridGame[i][j]==1 && gridGame[i][j-1]==2) || (gridGame[i][j]==2 && gridGame[i][j-1]==1)){
                        gridGame[i][j-1]=3;
                        gridGame[i][j]=0;
                    }
                    /*if the values is the same*/
                    if(gridGame[i][j]==gridGame[i][j-1] && (gridGame[i][j]!=1 && gridGame[i][j]!=2)){
                        gridGame[i][j-1]=gridGame[i][j-1]*2;
                        gridGame[i][j]=0;
                    }
                    /*if the values is 0*/
                    if(gridGame[i][j-1]==0){
                        gridGame[i][j-1]=gridGame[i][j];
                        gridGame[i][j]=0;
                    }
                }
            }
        }
        
        for (int i=3; i>=0; i--){
            for (int j=3; j>=0; j--){
                /*move down*/
                /*don't move if i=3*/
                if (choicePlayer==2 && i<3){
                    /*if the values are 1 and 2*/
                    if((gridGame[i][j]==1 && gridGame[i+1][j]==2) || (gridGame[i][j]==2 && gridGame[i+1][j]==1)){
                        gridGame[i+1][j]=3;
                        gridGame[i][j]=0;
                    }
                    /*if the values is the same*/
                    if(gridGame[i][j]==gridGame[i+1][j] && (gridGame[i][j]!=1 && gridGame[i][j]!=2)){
                        gridGame[i+1][j]=gridGame[i+1][j]*2;
                        gridGame[i][j]=0;
                    }
                    /*if the values is 0*/
                    if(gridGame[i+1][j]==0){
                        gridGame[i+1][j]=gridGame[i][j];
                        gridGame[i][j]=0;
                    }
                }
                /*move right*/
                /*don't move if j=3*/
                if (choicePlayer==4 && j<3){
                    /*if the values are 1 and 2*/
                    if((gridGame[i][j]==1 && gridGame[i][j+1]==2) || (gridGame[i][j]==2 && gridGame[i][j+1]==1)){
                        gridGame[i][j+1]=3;
                        gridGame[i][j]=0;
                    }
                    /*if the values is the same*/
                    if(gridGame[i][j]==gridGame[i][j+1] && (gridGame[i][j]!=1 && gridGame[i][j+1]!=2)){
                        gridGame[i][j+1]=gridGame[i][j+1]*2;
                        gridGame[i][j]=0;
                    }
                    /*if the values is 0*/
                    if(gridGame[i][j+1]==0){
                        gridGame[i][j+1]=gridGame[i][j];
                        gridGame[i][j]=0;
                    }
                }
            }
        }
    }
}
