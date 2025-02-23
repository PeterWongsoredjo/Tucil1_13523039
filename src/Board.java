public class Board extends Matrix {
    public void CreateBoard(int x, int y) {
        super.CreateMatrix(x, y);  
    }

    public boolean isBoardFull(){
        for (int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                if (getElement(i, j) == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    public class ConsoleColors {
        public static final String RESET = "\u001B[0m"; 
        public static final String[] COLORS = {
            "\u001B[31m", // Red
            "\u001B[32m", // Green
            "\u001B[33m", // Yellow
            "\u001B[34m", // Blue
            "\u001B[35m", // Purple
            "\u001B[36m", // Cyan
            "\u001B[91m", // Light Red
            "\u001B[92m", // Light Green
            "\u001B[93m", // Light Yellow
            "\u001B[94m", // Light Blue
            "\u001B[95m", // Light Purple
            "\u001B[96m", // Light Cyan
            "\u001B[37m", // White
            "\u001B[90m", // Gray
            "\u001B[41m", // Red Background
            "\u001B[42m", // Green Background
            "\u001B[43m", // Yellow Background
            "\u001B[44m", // Blue Background
            "\u001B[45m", // Purple Background
            "\u001B[46m", // Cyan Background
            "\u001B[100m", // Light Gray Background
            "\u001B[101m", // Light Red Background
            "\u001B[102m", // Light Green Background
            "\u001B[103m", // Light Yellow Background
            "\u001B[104m", // Light Blue Background
            "\u001B[105m"  // Light Purple Background
        };
    }

    public void printColoredMatrix() {
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getCol(); j++) {
                char pieceChar = getElement(i, j);
                if (pieceChar == '#') {
                    System.out.print("  ");
                } else if (pieceChar != '.') {
                    System.out.print(getColorForChar(pieceChar) + pieceChar + " " + ConsoleColors.RESET);
                } else {
                    System.out.print(pieceChar + " ");
                }
            }
            System.out.println();
        }
    }

    private String getColorForChar(char pieceChar) {
        if (pieceChar == '.') return ConsoleColors.RESET; 

        int colorIndex = (pieceChar - 'A') % ConsoleColors.COLORS.length;
        return ConsoleColors.COLORS[colorIndex];
    }
}

