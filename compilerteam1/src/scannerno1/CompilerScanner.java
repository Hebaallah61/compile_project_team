
package scannerno1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JOptionPane;


public class CompilerScanner {
    
   
    
    int NoErrors = 0;
    public String line;
    int CurrentLineNumber = 1;
    int index = 0;
    int lexemeNumberInLine = 0;
    String keyword;

   

    public enum Tokens {
        Condition, Integer, SInteger, Character, String, Float,
        SFloat, Void, Break, Loop, Return, Struct, StartStatement, 
        ArithmeticOperation, LogicOperator, RelationalOperator, AssignmentOperator,
        AccessOperator, Braces, Constant, QuotationMark, Inclusion, Comment, Delimiter, UnMatched, IDENTIFIER, EndOfLine
    }

    public Tokens CheckKeyword(String word) {
        switch (keyword) {
            
            case "Yesif-Otherwise":
                return Tokens.Condition;
            case "Omw":
                return Tokens.Integer;
            case "SIMww":
                return Tokens.SInteger;
            case "Chji":
                return Tokens.Character;
            case "Seriestl":
                return Tokens.String;
            case "IMwf":
                return Tokens.Float;
            case "SIMwf":
                return Tokens.SFloat;
            case "NOReturn":
                return Tokens.Void;
            case "OutLoop":
                return Tokens.Break;
            case "RepeatWhen/Reiterate":
                return Tokens.Loop;
            case "GetBack":
                return Tokens.Return;
            case "Loli":
                return Tokens.Struct;
           
            case "Start":
                return Tokens.StartStatement;
           ///////////////////////////////// 
            case "Last":
                return Tokens.Delimiter;
            case "Include":
                return Tokens.Inclusion;
                
                
             ///////////////////////////////////////////////////////////////////////////////////////
             
                    
            ///////////////////////////////////////////////////////////////////////////////////
            default:
                if ( true) {
                    return Tokens.IDENTIFIER;
                } else {
                    ////////////////////////////////////////////////////
                    NoErrors++;
                    ////////////////////////////////////////////////////
                    return Tokens.UnMatched;
                }
        }
    }

    public Tokens GetToken(String character) {

        while (line.length() > index && line.charAt(index) == ' ') {
            index++;
        }
        if (index == line.length()) {
            return Tokens.EndOfLine;
        }
        
        
        if (line.charAt(index) == '=') {
            keyword = "=";
            if (line.length() > index + 1 && line.charAt(index + 1) == '=') {
                index++;
                keyword = "==";
                return Tokens.RelationalOperator;
            }
            return Tokens.AssignmentOperator;
        } 
        else if (line.charAt(index) == '+') {
            keyword = "+";
            return Tokens.ArithmeticOperation;
        } else if (line.charAt(index) == '<') {
            keyword = "<";
            if (line.length() > index + 1 && line.charAt(index + 1) == '=') {
                index++;
                keyword = "<=";
            }
            return Tokens.RelationalOperator;
        } else if (line.charAt(index) == '>') {
            keyword = ">";
            if (line.length() > index + 1 && line.charAt(index + 1) == '=') {
                index++;
                keyword = ">=";
            }
            return Tokens.RelationalOperator;
        } else if (line.charAt(index) == '-') {
            keyword = "-";
            return Tokens.ArithmeticOperation;
        } else if (line.length() > index + 1 && line.charAt(index) == '!' && line.charAt(index + 1) == '=') {
            keyword = "!=";
            index++;
            return Tokens.RelationalOperator;
            
        } else if (line.charAt(index) == '~') {
            keyword = "~";
            return Tokens.LogicOperator;
        } else if (line.length() > index + 1 && line.charAt(index) == '&' && line.charAt(index + 1) == '&') {
            keyword = "&&";
            index++;
            return Tokens.LogicOperator;
        } else if (line.length() > index + 1 && line.charAt(index) == '|' && line.charAt(index + 1) == '|') {
            keyword = "||";
            index++;
            return Tokens.LogicOperator;
            
            
        } else if (line.length() > index + 1 && line.charAt(index) == '/' && line.charAt(index + 1) == '@') {
            keyword = "/@";
            index++;
            return Tokens.Comment;
        } else if (line.length() > index + 1 && line.charAt(index) == '@' && line.charAt(index + 1) == '/') {
            keyword = "@/";
            index++;
            return Tokens.Comment;
        } else if (line.length() > index + 1 && line.charAt(index) == '/' && line.charAt(index + 1) == '^') {
            keyword = "/^";
            index++;
            return Tokens.Comment;
        } else if (line.charAt(index) == '*') {
            keyword = "*";
            return Tokens.ArithmeticOperation;
        } else if (line.charAt(index) == '/') {
            keyword = "/";
            return Tokens.ArithmeticOperation;
        } else if (line.charAt(index) == '\'') {
            keyword = "'";
            return Tokens.QuotationMark;
        } else if (line.charAt(index) == '"') {
            keyword = "\"";
            return Tokens.QuotationMark;
            
            
        } else if (line.length() > index + 1 && line.charAt(index) == '-' && line.charAt(index + 1) == '>') {
            keyword = "->";
            return Tokens.AccessOperator;
            
            
        } else if (line.charAt(index) == '$') {
            keyword = "$";
            return Tokens.Delimiter;
        } else if (line.charAt(index) == '.') {
            keyword = ".";
            return Tokens.Delimiter;
            
            
        } else if (line.charAt(index) == '{') {
            keyword = "{";
            return Tokens.Braces;
        } else if (line.charAt(index) == '}') {
            keyword = "}";
            return Tokens.Braces;
        } else if (line.charAt(index) == '[') {
            keyword = "[";
            return Tokens.Braces;
        } else if (line.charAt(index) == ']') {
            keyword = "]";
            return Tokens.Braces;
        }else if (line.charAt(index) == '(') {
            keyword = "(";
            return Tokens.Braces;           
        }
        else if (line.charAt(index) == ')') {
            keyword = ")";
            return Tokens.Braces;      
        } else if (Character.isAlphabetic((int) line.charAt(index))) {
            return AlphapetChecker();
        } else if (Character.isDigit(line.charAt(index))) {
            return DigitChecker();
        } else {
            keyword = line.charAt(index) + "";
            return Tokens.UnMatched;
        }
         
    }
//////////////////////////////////
    public Tokens AlphapetChecker() {
        keyword = "";
        while ((line.length() > index) && (line.charAt(index) != ' ') && (Character.isAlphabetic((int) line.charAt(index)) || (Character.isDigit(line.charAt(index))))) {
            keyword += line.charAt(index);
            index++;
        }
        index--;
        return CheckKeyword(keyword);
    }

    public Tokens DigitChecker() {
        keyword = "";
        while ((line.length() > index) && Character.isDigit(line.charAt(index))) {
            keyword += line.charAt(index);
            index++;
        }
        index--;
        return Tokens.Constant;
    }

    public void DisplayTokens(String file) throws IOException {
        Result re= new Result();
        BufferedReader br = new BufferedReader(new StringReader(file));
        index = 0;
        boolean matched;
        boolean BorderedSkip = false;
        Tokens tokenholder;
        while ((line = br.readLine()) != null) {
            while (index < line.length()) {
                tokenholder = GetToken(line);
                if (tokenholder == Tokens.Comment && keyword == "@/" && BorderedSkip) {
                    BorderedSkip = false;
                }
                if (!BorderedSkip) {
                    if (tokenholder != Tokens.EndOfLine) {
                        if (tokenholder == Tokens.UnMatched) {
                            matched = false;
                        } else {
                            matched = true;
                        }
                        lexemeNumberInLine++;
                        if (tokenholder == Tokens.Comment && keyword == "/@") {
                            BorderedSkip = true;
                        }
                        if (tokenholder == Tokens.Comment && keyword == "/^") {
                           
                            Result.AddRow(new Object[]{
                                                       CurrentLineNumber,
                                                       keyword,
                                                       tokenholder,
                                                       lexemeNumberInLine,
                                                       matched,
                                                       
                            });
                            break;
                        }

                          Result.AddRow(new Object[]{
                                                       CurrentLineNumber,
                                                       keyword,
                                                       tokenholder,
                                                       lexemeNumberInLine,
                                                       matched,
                                                       
                            });
                    }
                }
                index++;
            }
            CurrentLineNumber++;
            ClearLine();
            
        }
        ///////////////////////////////////////////////////////////
        System.out.println("No. of the error " +NoErrors); 
        ////////////////////////////////////////////////////////////
        re.setVisible(true);
    }

    public void ClearLine() {
        lexemeNumberInLine = 0;
        index = 0;
        lexemeNumberInLine = 0;
    }
    
    
   
}

