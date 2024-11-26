import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    private char[][] tabuleiro;
    private Scanner scanner;
    private Random random;
    private int tentativasRestantes;
    private int naviosRestantes;

    public BatalhaNaval() {
        tabuleiro = new char[9][9];
        scanner = new Scanner(System.in);
        random = new Random();
        tentativasRestantes = 30;
        naviosRestantes = 10;
        inicializarTabuleiro();
        posicionarNavios();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 && j > 0) {
                    tabuleiro[i][j] = (char) ('0' + j);
                } else if (j == 0 && i > 0) {
                    tabuleiro[i][j] = (char) ('0' + i);
                } else {
                    tabuleiro[i][j] = '~';
                }
            }
        }
    }

    private void posicionarNavios() {
        int naviosPosicionados = 0;

        while (naviosPosicionados < 10) {
            int linha = random.nextInt(8) + 1;
            int coluna = random.nextInt(8) + 1;

            if (tabuleiro[linha][coluna] == '~') {
                tabuleiro[linha][coluna] = 'N';
                naviosPosicionados++;
            }
        }
    }

    private void exibirTabuleiro() {
        for (char[] linha : tabuleiro) {
            for (char celula : linha) {
                if (celula == 'N') {
                    System.out.print("~  ");
                } else {
                    System.out.print(celula + "  ");
                }
            }
            System.out.println();
        }
    }

    private void realizarJogada() {
        char linha, coluna;

        while (tentativasRestantes > 0) {
            System.out.println("Tentativas restantes: " + tentativasRestantes);
            System.out.println("Navios restantes: " + naviosRestantes);
            exibirTabuleiro();

            System.out.println("Informe a coluna (1-8):");
            coluna = scanner.next().charAt(0);

            System.out.println("Informe a linha (1-8):");
            linha = scanner.next().charAt(0);

            int linhaIndex = linha - '0';
            int colunaIndex = coluna - '0';

            if (linhaIndex < 1 || linhaIndex > 8 || colunaIndex < 1 || colunaIndex > 8) {
                System.out.println("Coordenadas inválidas. Tente novamente.");
                continue;
            }

            if (tabuleiro[linhaIndex][colunaIndex] == 'A' || tabuleiro[linhaIndex][colunaIndex] == 'X') {
                System.out.println("Você já jogou nessa posição!");
                continue;
            }

            if (tabuleiro[linhaIndex][colunaIndex] == 'N') {
                tabuleiro[linhaIndex][colunaIndex] = 'A';
                naviosRestantes--;
                System.out.println("Você acertou um navio!");
            } else {
                tabuleiro[linhaIndex][colunaIndex] = 'X';
                System.out.println("Água! Tente novamente.");
            }

            tentativasRestantes--;

            if (naviosRestantes == 0) {
                System.out.println("Parabéns! Você destruiu todos os navios e venceu o jogo!");
                exibirTabuleiroFinal();
                return;
            }
        }

        System.out.println("Fim de jogo! Você usou todas as tentativas e perdeu.");
        exibirTabuleiroFinal();
    }

    private void exibirTabuleiroFinal() {
        System.out.println("Tabuleiro final:");
        for (char[] linha : tabuleiro) {
            for (char celula : linha) {
                System.out.print(celula + "  ");
            }
            System.out.println();
        }
    }

    public void iniciar() {
        System.out.println("Bem-vindo à Batalha Naval!");
        realizarJogada();
    }

    public static void main(String[] args) {
        BatalhaNaval jogo = new BatalhaNaval();
        jogo.iniciar();
    }
}
