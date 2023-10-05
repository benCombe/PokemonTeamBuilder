public class TextColour {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public TextColour(){

    }

    public String BLACK(){ return ANSI_BLACK;}
    public String RED(){ return ANSI_RED;}
    public String GREEN(){ return ANSI_GREEN;}
    public String YELLOW(){ return ANSI_YELLOW;}
    public String BLUE(){ return ANSI_BLUE;}
    public String PURPLE(){ return ANSI_PURPLE;}
    public String CYAN(){ return ANSI_CYAN;}
    public String WHITE(){ return ANSI_WHITE;}
    public String RESET(){ return ANSI_RESET;} 

    public String Background(String c){
        switch(c){
            case "RESET":
                return ANSI_RESET;

            case "BLACK":
                return ANSI_BLACK_BACKGROUND;

            case "RED":
            return ANSI_RED_BACKGROUND;

            case "GREEN":
            return ANSI_GREEN_BACKGROUND;

            case "YELLOW":
            return ANSI_YELLOW_BACKGROUND;

            case "BLUE":
            return ANSI_BLUE_BACKGROUND;

            case "PURPLE":
            return ANSI_PURPLE_BACKGROUND;

            case "CYAN":
            return ANSI_CYAN_BACKGROUND;

            case "WHITE":
            return ANSI_WHITE_BACKGROUND;

            default:
                return ANSI_RESET;
        }
    }

    
}
