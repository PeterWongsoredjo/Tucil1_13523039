import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {
    public static int boardRows, boardCols, totalPieces;
    public static Board board;
    public static List<Piece> pieces;

    public static void loadFile(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String[] firstLine = reader.readLine().trim().split(" ");
            if (firstLine.length != 3) {
                throw new IllegalArgumentException("Error: Ini pasti ngga ada dimensi kalo ngga jumlah piece yaa");
            }

            boardRows = Integer.parseInt(firstLine[0]);
            boardCols = Integer.parseInt(firstLine[1]);
            totalPieces = Integer.parseInt(firstLine[2]);

            if (boardRows <= 0 || boardCols <= 0 || totalPieces <= 0) {
                throw new IllegalArgumentException("Error: Dimensi board atau jumlah pieces tidak valid.");
            }

            pieces = new ArrayList<>(totalPieces);

            String mode = reader.readLine().trim();
            if (!mode.equals("DEFAULT") && !mode.equals("CUSTOM")) {
                throw new IllegalArgumentException("Error: Mode harus 'DEFAULT' atau 'CUSTOM'.");
            }

            if(mode.equals("CUSTOM")){
                Board customBoard = new Board();
                customBoard.CreateMatrix(boardRows, boardCols);

                for(int i = 0; i < boardRows; i++){
                    String line = reader.readLine();
                    if (line == null || line.trim().isEmpty()) {
                        throw new IllegalArgumentException("Error: Peta tidak lengkap untuk mode CUSTOM.");
                    }
                    for(int j = 0; j < boardCols; j++){
                        char c = line.charAt(j);
                        if(c != '.' && c != 'X') {
                            throw new IllegalArgumentException("Error: Peta hanya boleh berisi karakter '.' atau 'X'.");
                        }
                        if(c == '.'){
                            customBoard.setElement(i, j, '#');
                        }
                        else if(c == 'X'){
                            customBoard.setElement(i, j, '.');
                        }
                    }
                }

                board = customBoard;
            }
            else{
                Board defaultBoard = new Board();
                defaultBoard.CreateMatrix(boardRows, boardCols);
                board = defaultBoard;
            }

            List<String> pieceLines = new ArrayList<>();
            char currentLetter = '\0';
    
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; 
    
                if (!pieceLines.isEmpty() && line.trim().charAt(0) != currentLetter) {
                    Piece piece = new Piece();
                    piece.CreatePiece(pieceLines);  
                    pieces.add(piece);
                    pieceLines.clear();
                }
    
                currentLetter = line.trim().charAt(0);
                pieceLines.add(trimAfterLastChar(line).replace(" ", "."));
            }
    
            if (!pieceLines.isEmpty()) {
                Piece piece = new Piece();
                piece.CreatePiece(pieceLines);
                pieces.add(piece);
            }
    
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: Ngga ketemu file nya bro");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String trimAfterLastChar(String line) {
        int lastCharIndex = -1;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                lastCharIndex = i;
            }
        }
        return line.substring(0, lastCharIndex + 1);
    }
}
