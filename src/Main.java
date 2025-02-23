import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nama file: ");
        String filename = scanner.nextLine();
        FileScanner.loadFile(filename);

        Board board = FileScanner.board;
        if (board == null) {
            return;
        }

        BruteForceSolver solver = new BruteForceSolver();
        solver.CreateSolver(board, FileScanner.pieces, FileScanner.totalPieces);
        boolean solutionFound = solver.solve(0);

        if (!solutionFound) {
            System.out.println("Solusi tidak ditemukan.");
            System.out.println("Total kasus yang ditinjau: " + solver.getCasesTried());
        }
    }
}
