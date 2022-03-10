//package com.company;

/*asdasdka*/

import java.io.*;
public class Compiler {
    public static File file = new File("testfile.txt");
    public static InputStream in = null;
    public static BufferedWriter out = null;
    private static int temp,ntemp=' ';
    private static String token;

    public static void main(String[] args) {
        try {
            in=new FileInputStream(file);
            out = new BufferedWriter(new FileWriter("output.txt"));
            while(true) {
                getSym();
                if(temp == -1){
                    break;
                }
            }
            in.close();
            out.close();
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
            out.write("INTCON "+num+"\n");
        }
        else if(temp == '"'){
            do {
                catToken();
                temp=in.read();
            } while(temp != '"');
            catToken();
            out.write("STRCON "+token+"\n");
        }
        else if(temp=='='){
            ntemp=in.read();
            if(ntemp=='='){
                out.write("EQL ==\n");
                temp = ntemp;
                ntemp = ' ';
            }else{
                out.write("ASSIGN =\n");
            }
        }
        else if(temp == '<'){
            ntemp=in.read();
            if(ntemp=='='){
                out.write("LEQ <=\n");
                getChar();
            }else{
                out.write("LSS <\n");
            }
        }
        else if(temp == '>'){
            ntemp=in.read();
            if(ntemp=='='){
                out.write("GEQ >=\n");
                getChar();
            }else{
                out.write("GRE >\n");
            }
        }
        else if(temp == '!'){
            ntemp=in.read();
            if(ntemp=='='){
                out.write("NEQ !=\n");
                getChar();
            }else{
                out.write("NOT !\n");
            }
        }
        else if(temp =='&'){
            ntemp=in.read();
            if(ntemp=='&'){
                out.write("AND &&\n");
                getChar();
            }else{
                //TODO error
            }
        }
        else if(temp =='|'){
            ntemp=in.read();
            if(ntemp=='|'){
                out.write("OR ||\n");
                getChar();
            }else{
                //TODO error
            }
        }
        else if(temp == '+'){
            out.write("PLUS +\n");
        }
        else if(temp == '-'){
            out.write("MINU -\n");
        }
        else if(temp == '*'){
            out.write("MULT *\n");
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
                out.write("DIV /\n");
            }
        }
        else if(temp == '%'){
            out.write("MOD %\n");
        }
        else if(temp == ';'){
            out.write("SEMICN ;\n");
        }
        else if(temp == ','){
            out.write("COMMA ,\n");
        }
        else if(temp=='('){
            out.write("LPARENT (\n");
        }
        else if(temp==')'){
            out.write("RPARENT )\n");
        }
        else if(temp=='['){
            out.write("LBRACK [\n");
        }
        else if(temp==']'){
            out.write("RBRACK ]\n");
        }
        else if(temp=='{'){
            out.write("LBRACE {\n");
        }
        else if(temp=='}'){
            out.write("RBRACE }\n");
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
                out.write("INTTK "+token+"\n");
                break;
            case "main":
                out.write("MAINTK "+token+"\n");
                break;
            case "const":
                out.write("CONSTTK "+token+"\n");
                break;
            case "break":
                out.write("BREAKTK "+token+"\n");
                break;
            case "continue":
                out.write("CONTINUETK "+token+"\n");
                break;
            case "if":
                out.write("IFTK "+token+"\n");
                break;
            case "else":
                out.write("ELSETK "+token+"\n");
                break;
            case "while":
                out.write("WHILETK "+token+"\n");
                break;
            case "getint":
                out.write("GETINTTK "+token+"\n");
                break;
            case "printf":
                out.write("PRINTFTK "+token+"\n");
                break;
            case "return":
                out.write("RETURNTK "+token+"\n");
                break;
            case "void":
                out.write("VOIDTK "+token+"\n");
                break;
            default:
                out.write("IDENFR "+token+"\n");
                break;
        }

    }
}

