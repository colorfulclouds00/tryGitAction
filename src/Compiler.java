//package com.company;

/*asdasdka*/

import java.io.*;
public class Compiler {
    public static File file = new File("testfile.txt");
    public static InputStream in = null;
    public static String m = "const int MOD = (4259+235*243/3+3*4*4*3*6*5/3*9293+385*4346+23643*139+1926+817+1952+1026+2869);";
    private static int temp,ntemp=' ';
    private static String token;

    public static void main(String[] args) {
        try {
            in=new FileInputStream(file);
            while(true) {
                getSym();
                if(temp == -1){
                    break;
                }
            }
            in.close();
            System.out.println("finish");
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getChar() throws IOException {
        temp= (ntemp == ' ')?in.read():ntemp;
        ntemp = ' ';
    }
    public static void getSym() throws IOException {
        getChar();
        clearToken();
        while(isSpace()||isNewline()||isTab()||isRline())
            getChar();
        if(isLetter()||temp == '_'){
            while(isLetter()||isDigit()||temp == '_'){
                catToken();
                temp=in.read();
                ntemp = temp;
            }
            temp = ntemp;
            reserve();
        }
        else if (isDigit()){
            while(isDigit()){
                catToken();
                temp=in.read();
                ntemp = temp;
            }
            temp = ntemp;
            int num = Integer.parseInt(token);
           System.out.println("INTCON "+num+"\n");
        }
        else if(temp == '"'){
            do {
                catToken();
                temp=in.read();
            } while(temp != '"');
            catToken();
           System.out.println("STRCON "+token+"\n");
        }
        else if(temp=='='){
            ntemp=in.read();
            if(ntemp=='='){
               System.out.println("EQL ==\n");
                temp = ntemp;
                ntemp = ' ';
            }else{
               System.out.println("ASSIGN =\n");
            }
        }
        else if(temp == '<'){
            ntemp=in.read();
            if(ntemp=='='){
               System.out.println("LEQ <=\n");
                getChar();
            }else{
               System.out.println("LSS <\n");
            }
        }
        else if(temp == '>'){
            ntemp=in.read();
            if(ntemp=='='){
               System.out.println("GEQ >=\n");
                getChar();
            }else{
               System.out.println("GRE >\n");
            }
        }
        else if(temp == '!'){
            ntemp=in.read();
            if(ntemp=='='){
               System.out.println("NEQ !=\n");
                getChar();
            }else{
               System.out.println("NOT !\n");
            }
        }
        else if(temp =='&'){
            ntemp=in.read();
            if(ntemp=='&'){
               System.out.println("AND &&\n");
                getChar();
            }else{
                //TODO error
            }
        }
        else if(temp =='|'){
            ntemp=in.read();
            if(ntemp=='|'){
               System.out.println("OR ||\n");
                getChar();
            }else{
                //TODO error
            }
        }
        else if(temp == '+'){
           System.out.println("PLUS +\n");
        }
        else if(temp == '-'){
           System.out.println("MINU -\n");
        }
        else if(temp == '*'){
           System.out.println("MULT *\n");
        }
        else if(temp == '/'){
            ntemp=in.read();
            if(ntemp == '*'){
                do{
                    ntemp = temp;
                    temp = in.read();
                }while((temp!='/')||(ntemp!='*'));
                ntemp = ' ';
            }
            else if(ntemp == '/'){
                do {
                    temp = in.read();
                }while (!isNewline());
                ntemp = ' ';
            }
            else{
               System.out.println("DIV /\n");
            }
        }
        else if(temp == '%'){
           System.out.println("MOD %\n");
        }
        else if(temp == ';'){
           System.out.println("SEMICN ;\n");
        }
        else if(temp == ','){
           System.out.println("COMMA ,\n");
        }
        else if(temp=='('){
           System.out.println("LPARENT (\n");
        }
        else if(temp==')'){
           System.out.println("RPARENT )\n");
        }
        else if(temp=='['){
           System.out.println("LBRACK [\n");
        }
        else if(temp==']'){
           System.out.println("RBRACK ]\n");
        }
        else if(temp=='{'){
           System.out.println("LBRACE {\n");
        }
        else if(temp=='}'){
           System.out.println("RBRACE }\n");
        }
    }
    public static boolean isSpace(){
        return temp == ' ';
    }
    public static boolean isNewline(){
        return temp=='\n';
    }
    public static boolean isTab(){
        return temp=='\t';
    }
    public static boolean isRline(){
        return temp=='\r';
    }
    public static boolean isLetter(){
        if(temp>='a'&&temp<='z'){
            return true;
        }else if(temp>='A'&&temp<='Z'){
            return true;
        }
        return false;
    }
    public static boolean isDigit(){
        return temp<='9'&& temp>='0';
    }

    public static void clearToken(){
        token= "";
    }

    public static void catToken(){
        char c =(char)temp;
        token=token+c;
    }

    public static void reserve() throws IOException {
        switch (token){
            case "int":
               System.out.println("INTTK "+token+"\n");
                break;
            case "main":
               System.out.println("MAINTK "+token+"\n");
                break;
            case "const":
               System.out.println("CONSTTK "+token+"\n");
                break;
            case "break":
               System.out.println("BREAKTK "+token+"\n");
                break;
            case "continue":
               System.out.println("CONTINUETK "+token+"\n");
                break;
            case "if":
               System.out.println("IFTK "+token+"\n");
                break;
            case "else":
               System.out.println("ELSETK "+token+"\n");
                break;
            case "while":
               System.out.println("WHILETK "+token+"\n");
                break;
            case "getint":
               System.out.println("GETINTTK "+token+"\n");
                break;
            case "printf":
               System.out.println("PRINTFTK "+token+"\n");
                break;
            case "return":
               System.out.println("RETURNTK "+token+"\n");
                break;
            case "void":
               System.out.println("VOIDTK "+token+"\n");
                break;
            default:
               System.out.println("IDENFR "+token+"\n");
                break;
        }

    }
}

