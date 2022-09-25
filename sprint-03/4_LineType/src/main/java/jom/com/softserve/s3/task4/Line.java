package jom.com.softserve.s3.task4;

//Describe LineType enum here
enum LineType {
    SOLID, DOTTED, DASHED, DOUBLE
}

public class Line {
    public static String drawLine(LineType type){
        return "This line is " + type.name().toLowerCase() + " type";
    }
}
