/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package First;
                                                                                                            
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Lexical{

 public static abstract class actio implements ActionListener{
     public void actionPerformed(ActionEvent e)
     {
      JFileChooser chooser = new JFileChooser("C:\\Users\\pc\\Downloads\\Lexical");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("txt" ,"txt");
        chooser.setFileFilter(filter);
      int returnedValue = chooser.showOpenDialog(null);
 
                // Open ففي حال قام المستخدم بإختيار ملف ثم نقر على
                if(returnedValue == JFileChooser.APPROVE_OPTION)
                {
                    
                   File selectedFile = chooser.getSelectedFile();
                    String filepath = selectedFile.getPath();
                }
     }
 }
 

    static String [] sTOR_Tokens;
    // This function reads input tokens from a file and stores it in a global string array called inputTokens
   public void readInput(File f)
    {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            int count=0;
            String s="";
            while (dis.available() != 0) {
                s += dis.readLine()+" ";
            }
            s+="$";  // Insert $ at end of input for matching wit $ in symbol in stack
            sTOR_Tokens= s.split(" ");
            for(int j=0;j<sTOR_Tokens.length;j++){
                sTOR_Tokens[j]  = sTOR_Tokens[j].replaceAll("\\u00a0", "");
            }
            fis.close();
            bis.close();
            dis.close();
        }
        catch (Exception ex) {}
    }
   public static boolean same_word(String str)
    {
        String keyword[] ={"omw","Yesif","otherwise ","simww","chji", "seriestl", "imwf", "simwf", "noreturn" ,"repeatwhen","retback","OutLoop","Loli"};
        if(!Character.isLowerCase(str.charAt(0)))
        {
           return false;
        }
        for(int i=0;i<keyword.length;i++)
        {

            if(str.matches(keyword[i]))
            {     
               return true;
            }
           
        }
               return false;
    }
        
   // static int count=0;
   public static void token(String str) {
            String lexeme="";
          
            for(int i=0;i<str.length();i++){
                if(Character.isLetter(str.charAt(i))){
                    lexeme+=str.charAt(i++);
                    while(i<str.length()&&(Character.isLetterOrDigit(str.charAt(i))||str.charAt(i)=='_')){
                        lexeme+=str.charAt(i++);
                    }
                    i--;
                    if(same_word(lexeme)){  
                        System.out.println("KeyWord : "+lexeme);
                     
                    }
                     
                    else
                    {  
                       
                        System.out.println("Identifier : "+lexeme);
                     //   bufferedWriter.write("identifier ");
                    }
                    lexeme="";
                }
                  
               
                  else if(i<str.length()-1 &&(str.charAt(i)=='/'&& str.charAt(i+1)=='^')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                    System.out.println("Comment  : "+lexeme);
                   
                    lexeme="";
                    break;
                }
                   else if((str.charAt(i)=='/'&& str.charAt(i+1)=='@')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                   // break;
                    
                   
                   
                    lexeme="";
                    System.out.println("Comment  : "+lexeme);
                   break;
                   
                   /*
                     if (character.equals("\"")) {
                                    listToken.add(new Token(lineNo,"SPCIAL SYMBOL", "SYMBOL", "\""));
                                    continue;
                                } else {
                                    isString = true;
                                    continue;
                                }
                   */
                }
                   
                       else if((str.charAt(i)=='s'&& str.charAt(i+1)=='t')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                    
                    System.out.println("start  : "+lexeme);
                   
                    lexeme="";
                  
                }
                else if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/'){
                    System.out.println("Arithmetic Operation  : "+str.charAt(i));
                    
                }//str.charAt(i)=='m'
                 else if(str.charAt(i)=='"'){
                    System.out.println("Quotation Mark   : "+str.charAt(i));
                    
                }
                     
                  else if(Character.isDigit(str.charAt(i))){
               
                    int flag1=0,flag2=0;
                    lexeme+=str.charAt(i++);
                    while(i<str.length()&&(Character.isDigit(str.charAt(i))))
                            {
                        if(Character.isDigit(str.charAt(i))){
                            lexeme+=str.charAt(i++);
                        }
       
                    System.out.println("Number : "+lexeme);
                 
                    lexeme="";
                        }

             
                  }
                  else if(i<str.length()-1 &&((str.charAt(i)=='='&& str.charAt(i+1)=='=')||(str.charAt(i)=='<'&& str.charAt(i+1)=='=')||(str.charAt(i)=='>'&& str.charAt(i+1)=='=')||(str.charAt(i)=='!'&& str.charAt(i+1)=='=')||((str.charAt(i)=='<')&& str.charAt(i+1)==' ')||str.charAt(i+1)=='>'&& str.charAt(i+1)==' ')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                    System.out.println("relational operators   : "+lexeme);
                   
                    lexeme="";
                }
                else if(i<str.length()-1 &&(str.charAt(i)=='-'&& str.charAt(i+1)=='>')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                    System.out.println("Access Operator   : "+lexeme);
                   
                    lexeme="";
                }
                else if(i<str.length()-1 &&(str.charAt(i)=='=')){
                    lexeme+=str.charAt(i);
                    System.out.println("Assignment operator  : "+lexeme);
                   
                    lexeme="";
                }
                else if(i<str.length()-1 &&(str.charAt(i)=='&'&& str.charAt(i+1)=='&')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                    System.out.println("Logic operators  : "+lexeme);
                   
                    lexeme="";
                }
                else if(i<str.length()-1 &&(str.charAt(i)=='|'&& str.charAt(i+1)=='|')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                    System.out.println("Logic operators  : "+lexeme);
                   
                    lexeme="";
                }
                else if(i<str.length()-1 &&(str.charAt(i)=='~')){
                   
                    lexeme+=str.charAt(i);
                    System.out.println("Logic operators  : "+lexeme);
                   
                    lexeme="";
                }
                 else if(i<str.length()-1 &&(str.charAt(i)=='/'&& str.charAt(i+1)=='^')){
                    lexeme+=str.charAt(i++);
                    lexeme+=str.charAt(i);
                    System.out.println("Comment  : "+lexeme);
                   
                    lexeme="";
                }
                else if(str.charAt(i)=='('){
                    System.out.println("Braces : "+str.charAt(i));
                  
                }
                else if(str.charAt(i)==')'){
                    System.out.println("Braces  : "+str.charAt(i));
                  
                }
                else if(str.charAt(i)=='{'){
                    System.out.println("Braces  : "+str.charAt(i));
                  
                }
                else if(str.charAt(i)=='}'){
                    System.out.println("Braces  : "+str.charAt(i));
                  
                }
                else if(str.charAt(i)=='['){
                    System.out.println("Braces : "+str.charAt(i));
                 
                }
                else if(str.charAt(i)==']'){
                    System.out.println("Braces  : "+str.charAt(i));
                   
                }
                else if(str.charAt(i)=='.'){
                    System.out.println("Line Delimiter  : "+str.charAt(i));
                   
                }
                else if(str.charAt(i)==','){
                    System.out.println(", : "+str.charAt(i));
                   // bufferedWriter.write(", ");
                }
                else if(str.charAt(i)=='$'){
                    System.out.println("Token Delimiter  : "+str.charAt(i));
                   
                }
                else if(Character.isWhitespace(str.charAt(i))){
                    
                }
                 
                else{
                    System.out.println("Error : "+str.charAt(i));
                  
                }
            }
   }
        
   
    public static void main(String[] args) {

        
      
      String line;
        
       try {
            FileReader fileReader =new FileReader("input.txt");
           
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                int i=1;
                while((line = bufferedReader.readLine()) != null) {
                    System.out.println("Line#"+i++);
                    token(line);                    
                    System.out.println("    --------------------");
                }
            }
        }
        catch(Exception ex) {}
    }
 }

