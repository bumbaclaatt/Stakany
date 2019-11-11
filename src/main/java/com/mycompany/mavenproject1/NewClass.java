package com.mycompany.mavenproject1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 import au.com.bytecode.opencsv.CSVReader;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;


public class NewClass {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) throws IOException 
    {
        int i = 0 ;
        char test;
        char testt = 'd';
        String str;
        String scr;
        int m = 0;
        int m1 =0;
        int n ;
        int n1 =0;
        int  p;
        int p1;
        List<List<String>> lastArray = null;
        List<List<String>> lastArray1 = null;
        
        try
        (FileWriter fw = new FileWriter( "c:\\5.csv" )) {
            try
                (CSVReader reader = new CSVReader(new FileReader("c:\\3.csv"),',')) {
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null)
                {
                    for(String token : nextLine)
                    {
                        System.out.println(token);
                        test = token.charAt(14);
                        System.out.println(test);
                        if (test == testt) {
                            str = token.substring(30);
                            Stakan current = GSON.fromJson(str,Stakan.class);
                            List<List<String>> currentArray = current.getAsks();
                            List<List<String>> currentArray1 = current.getBids();
                            if (i==0){                              
                                fw.write(token+"\n");
                                lastArray = current.getAsks();
                                lastArray1 = current.getBids();
                                m1 = lastArray1.size() - 1;
                                m = lastArray.size() - 1;
                                i = i+1;
                            }
                            else{
                                List<List<String>> resultArray = current.getAsks();
                                List<List<String>> resultArray1 = current.getBids();
                                n = currentArray.size() - 1;
                                n1 = currentArray1.size() -1;
                                for (int k = 0;k <= m;k++){
                                    for (int l = 0;l<= n;l++) {
                                        if (lastArray.get(k).get(0).equals(currentArray.get(l).get(0)) == true ){
                                            l= n;
                                        }
                                        else if (l == n){
                                            if (lastArray.get(k).get(0).equals(currentArray.get(l).get(0)) == false){
                                                p = resultArray.size();
                                                ArrayList<String> part = new ArrayList<String>();
                                                part.add(lastArray.get(k).get(0));
                                                part.add("0");
                                                resultArray.add(part);
                                            }
                                            
                                        }
                                    }
                                } 
                                for (int k = 0;k <= m1;k++){
                                    for (int l = 0;l<= n1;l++) {
                                        if (lastArray1.get(k).get(0).equals(currentArray1.get(l).get(0)) == true ){
                                            l= n1;
                                        }
                                        else if (l == n1){
                                            if (lastArray1.get(k).get(0).equals(currentArray1.get(l).get(0)) == false ){
                                                p1 = resultArray1.size();
                                                ArrayList<String> part1 = new ArrayList<String>();
                                                part1.add(lastArray.get(k).get(0));
                                                part1.add("0");
                                                resultArray1.add(part1);
                                            }
                                            
                                        }
                                    }
                                }
                                fw.write(token.substring(0,30)+"{\"mrid\":"+current.getMrId()+",\"id\":"+current.getId()+",\"bids\":"+resultArray1.toString()+",\"asks\":"+resultArray.toString()+",\"ts\":"+current.getTs()+",\"version\":"+current.getVersion()+",\"ch\":"+current.getCh()+"}"+"\n");
                                lastArray = current.getAsks();
                            }
                            
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
            }
        }
        
        
        
       
        
        
        
        
        
        
        
    }
}