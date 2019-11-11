/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import au.com.bytecode.opencsv.CSVReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Иван
 */
public class NewClassTest {
    
    
    @Test
    public void testMain() throws Exception {
    boolean m =true;
    int n = 0;
    NachSt test = new NachSt();
    Delty test1 = new Delty();
    ArrayList<String> test2 = test.main();
    ArrayList<String> test3 = test1.main();
    n= test2.size();
    for (int k =0;k<=n;k++){
      if (test2.get(k).equals(test3.get(k)) == false){
         m = false;  
      }
    }   
     assertEquals(m,true); 
    }
    
}
class Delty {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public ArrayList<String> main() throws IOException 
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
        String test1 = "0";
        ArrayList<String> NachSt1= new ArrayList<String>();
        
        try
        (FileWriter fw = new FileWriter( "c:\\2.csv" )) {
            try
                (CSVReader reader = new CSVReader(new FileReader("c:\\5.csv"),'\n')) {
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null)
                {
                    for(String token : nextLine)
                    {
                        test = token.charAt(14);
                        if (test == testt) {
                            str = token.substring(30);
                            Stakan current = GSON.fromJson(str,Stakan.class);
                            List<List<String>> currentArray = current.getAsks();
                            List<List<String>> currentArray1 = current.getBids();
                            if (i==0){                              
                                fw.write(token+"\n");
                                i = i+1;
                            }
                            else{
                                n = currentArray.size() - 1;
                                n1 = currentArray1.size() -1;
                                for (int k = 0;k <= n;k++){
                                    if (currentArray.get(n-k).get(1).equals("0") == true){
                                        currentArray.remove(n-k);
                                    }
                                } 
                                for (int k = 0;k <= n1;k++){
                                    if (currentArray1.get(n1-k).get(1).equals("0") == true){
                                        currentArray1.remove(n1-k);
                                    }
                                }
                                fw.write(token.substring(0,30)+"{\"mrid\":"+current.getMrId()+",\"id\":"+current.getId()+",\"bids\":"+currentArray1.toString()+",\"asks\":"+currentArray.toString()+",\"ts\":"+current.getTs()+",\"ch\":"+current.getCh()+",\"version\":"+current.getVersion()+"}"+"\n");
                            String l = token.substring(0,30)+"{\"mrid\":"+current.getMrId()+",\"id\":"+current.getId()+",\"bids\":"+currentArray1.toString()+",\"asks\":"+currentArray.toString()+",\"ts\":"+current.getTs()+",\"ch\":"+current.getCh()+",\"version\":"+current.getVersion()+"}";
                            NachSt1.add(l);
                            }
                            
                        }
                    }
                }
            }
            catch (FileNotFoundException e) {
            }
        }
        return NachSt1;
    }
}
class NachSt {
    public ArrayList<String> main() throws IOException 
    {
        int i=0;
        int i1=0;
        char test;
        char testt = 'd';
        ArrayList<String> Nach = new ArrayList<String>();
            try
                (CSVReader reader = new CSVReader(new FileReader("c:\\3.csv"),',')) {
                String [] nextLine;
                while ((nextLine = reader.readNext()) != null)
                {
                    for(String token : nextLine)
                    {
                        test = token.charAt(14);
                        System.out.println(test);
                        if (test == testt) {
                           Nach.add(i,token);
                           i=i+1;
  
                        }
                    }
                }
            }  
            catch (FileNotFoundException e) {
            }
            
     return Nach;       
    }
}

class Testing{
public boolean main() throws IOException{
boolean m =true;
int n = 0;
NachSt test = new NachSt();
Delty test1 = new Delty();
ArrayList<String> test2 = test.main();
ArrayList<String> test3 = test1.main();
n= test2.size();
for (int k =0;k<=n;k++){
  if (test2.get(k).equals(test3.get(k)) == false){
    m = false;  
  }
}
    return m;
}
}