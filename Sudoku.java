import java.util.*;

public class Sudoku{

    public static void main(String[] args){
        Board tabuleiro = new Board(), solution = new Board();
        Random rand = new Random();
        solution = tabuleiro.solveBoard();
        tabuleiro = solution.fillThisManyRandomNumbers(25);
        tabuleiro.printBoard();
        solution.printBoard();
    }

    static class Board{
        public Cell[] cells;

        Board(){
            this.cells = new Cell[9*9];
            for(int i = 0; i < (9*9); i++){
                this.cells[i] = new Cell(0);
            }
        }

        Board solveBoard(){
            Board solved = this.copyBoard();
            for(int count = 1; count < 10; count++){
                for(int i = 0; i < 9*9; i++){
                    solved.updateOptionsAtIndex(i);
                    int[] thisOptions = solved.cells[i].getOptions();
                    if(!solved.cellIsFilled(i) && solved.howManyNonZeroElements(thisOptions) == count){
                        solved.solveCellAtIndex(i);
                        count = 1;
                        //solved.printOptionsAtIndex(i);
                        //wait(50);
                    }
                }
            }
            while(!solved.isGameWon()) solved = this.solveBoard();
            return solved;
        }

        public static void wait(int ms){
            try{
                Thread.sleep(ms);
            }
            catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }

        void solveCellAtIndex(int index){
            this.updateOptionsAtIndex(index);
            if(this.cellIsFilled(index)) return;
            int[] thisOptions = this.cells[index].getOptions();
            int randint = this.randomNonZeroElement(thisOptions);
            this.setNumberAtIndex(thisOptions[randint], index);
        }

        boolean isGameWon(){
            for(int i = 0; i < 9*9; i++){
                if(!this.cellIsFilled(i) || !this.cellIsValid(i))
                    return false;
            }
            return true;
        }

        int randomNonZeroElement(int[] arr){
            Random rand = new Random();
            int count = rand.nextInt(this.howManyNonZeroElements(arr)) + 1;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] != 0) count--;
                if(count == 0) return i;
            }
            return -1;
        }

        boolean hasSingleNonZero(int[] arr){
            return howManyNonZeroElements(arr) == 1;
        }

        void printOptionsAtIndex(int index){
            this.updateOptionsAtIndex(index);
            System.out.print("\n" + (this.rowFromIndex(index)+1) + "," + (this.columnFromIndex(index)+1) + ": ");
            this.printArrayNoZeros((this.cells[index].options));
        }

        int howManyNonZeroElements(int[] arr){
            int count = 0;
            for(int i = 0; i < arr.length; i++){
                if(arr[i] != 0)
                    count++;
            }
            return count;
        }

        void updateOptionsAtIndex(int index){
            int[] rowOptions = rowPossibilities(this.rowFromIndex(index));
            int[] columnOptions = columnPossibilities(this.columnFromIndex(index));
            int[] quadrantOptions = quadrantPossibilities(this.quadrantFromIndex(index));
            int[] totalOptions = new int[10];
            int number = this.numberFromIndex(index);
            if(this.cellIsFilled(index))
                totalOptions[number] = number;
            else {
                for(int i = 1; i < 10; i++){
                    if(!(rowOptions[i] == 0 || columnOptions[i] == 0 || quadrantOptions[i] == 0))
                        totalOptions[i] = i;
                }
            }
            this.cells[index].setOptions(totalOptions);
        }

        int numberFromIndex(int index){
            return this.cells[index].getNumber();
        }

        boolean cellIsFilled(int index){
            return this.numberFromIndex(index) != 0;
        }

        void printArrayNoZeros(int[] arr){
            System.out.print("[");
            for(int i = 0; i < arr.length; i++){
                if(arr[i] != 0) {
                    System.out.print(" " + arr[i] + " ");
                }
            }
            System.out.print("]");
        }

        int[] rowPossibilities(int row){
            int[] possibilities = new int[10];
            for(int i = 1; i < 10; i++){
                if(!this.numberIsInRow(i, row))
                    possibilities[i] = i;
            }
            return possibilities;
        }

        int[] columnPossibilities(int column){
            int[] possibilities = new int[10];
            for(int i = 1; i < 10; i++){
                if(!this.numberIsInColumn(i, column))
                    possibilities[i] = i;
            }
            return possibilities;
        }

        int[] quadrantPossibilities(int quadrant){
            int[] possibilities = new int[10];
            for(int i = 1; i < 10; i++){
                if(!this.numberIsInQuadrant(i, quadrant))
                    possibilities[i] = i;
            }
            return possibilities;
        }

        boolean numberIsInRow(int number, int row){
            for(int i = 0; i < 9; i++){
                if(this.numberFromIndex(row * 9 + i) == number)
                    return true;
            }
            return false;
        }

        boolean numberIsInColumn(int number, int column){
            for(int i = 0; i < 9; i++){
                if(this.numberFromIndex(column + 9 * i) == number)
                    return true;
            }
            return false;
        }

        boolean numberIsInQuadrant(int number, int quadrant){
            int[] qi = this.quadrantIndices(quadrant);
            for(int i = 0; i < 9; i++){
                if(this.numberFromIndex(qi[i]) == number)
                    return true;
            }
            return false;
        }

        boolean rowIsValid(int row){
            for(int i = 0; i < 9; i++){
                for(int j = i + 1; j < 9; j++){
                    if(this.cellsAreEqual(row * 9 + i, row * 9 + j))
                        return false;
                }
            }
            return true;
        }

        boolean columnIsValid(int column){
            for(int i = 0; i < 9; i++){
                for(int j = i + 1; j < 9; j++){
                    if(this.cellsAreEqual(column + 9 * i, column + 9 * j))
                        return false;
                }
            }
            return true;
        }

        boolean quadrantIsValid(int quadrant){
            int[] qi = this.quadrantIndices(quadrant);
            for(int i = 0; i < 9; i++){
                for(int j = i + 1; j < 9; j++){
                    if(this.cellsAreEqual(qi[i], qi[j]))
                        return false;
                }
            }
            return true;
        }

        int columnFromIndex(int index){
            return index % 9;
        }

        int rowFromIndex(int index){
            return index / 9;
        }

        int quadrantFromIndex(int index){
            int row = this.rowFromIndex(index);
            int column = this.columnFromIndex(index);
            if(row < 3){
                if(column < 3)
                    return 1;
                else if(column < 6)
                    return 2;
                else
                    return 3;
            }
            else if(row < 6){
                if(column < 3)
                    return 4;
                else if(column < 6)
                    return 5;
                else
                    return 6;
            }
            else {
                if(column < 3)
                    return 7;
                else if(column < 6)
                    return 8;
                else
                    return 9;
            }
        }

        int[] quadrantIndices(int quadrant){
            int row = ((quadrant-1) / 3) * 3;
            int column = ((quadrant+2) % 3) * 3;
            int[] indices = new int[9];
            int aux = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    indices[aux] = (row + i) * 9 + column + j;
                    aux++;
                }
            }
            return indices;
        }

        boolean cellsAreEqual(int index1, int index2){
            int one = this.cells[index1].getNumber();
            int two = this.cells[index2].getNumber();
            return (one == two && one != 0);
        }

        void typeNumberAtIndex(){
            Scanner teclado = new Scanner(System.in);
            int number;
            int index;
            do { number = teclado.nextInt();
            } while(isValidNumber(number));
            do { index = teclado.nextInt() - 1;
            } while(isValidIndex(index));
            setNumberAtIndex(number, index);
        }

        boolean isValidNumber(int number){
            return (number >= 0 && number <= 9);
        }

        boolean isValidIndex(int index){
            return (index >= 0 && index <= 9*9);
        }


        void setNumberAtIndex(int number, int index){
            this.cells[index].setNumber(number);
            this.updateOptionsAtIndex(index);
        }

        Board copyBoard(){
            Board copy = new Board();
            for(int i = 0; i < 9*9; i++){
                copy.cells[i].setNumber(this.cells[i].getNumber());
                copy.cells[i].setOptions(this.cells[i].getOptions());
            }
            return copy;
        }

        boolean cellIsValid(int index){
            return (this.rowIsValid(this.rowFromIndex(index)) &&
                    this.columnIsValid(this.columnFromIndex(index)) &&
                    this.quadrantIsValid(this.quadrantFromIndex(index))
                    );
        }

        boolean inputIsValid(int number, int index){
            Board copy = this.copyBoard();
            copy.setNumberAtIndex(number, index);
            return copy.cellIsValid(index);
        }

        Board fillThisManyRandomNumbers(int x){
            return this.eraseThisManyRandomNumbers(9*9 - x);
        }

        Board eraseThisManyRandomNumbers(int x){
            Board copy = this.copyBoard();
            int toErase = x;
            Random random = new Random();
            int index;
            for(int i = 0; i < toErase; i++){
                do {
                    index = random.nextInt(9*9);
                } while(!copy.cellIsFilled(index));
                copy.setNumberAtIndex(0, index);
            }
            return copy;
        }

        void printBoard(){
            System.out.println("\n -----------------------------");
            for(int i = 0; i < 9; i++){
                this.printRowOfCells(i);
                if((i+1) % 3 == 0)
                    System.out.println(" -----------------------------");
                else
                    System.out.println("|         |         |         |");
            }
            System.out.println();
        }

        void printRowOfCells(int row){
            for(int i = 0; i < 9; i++){
                if(i % 3 == 0)
                    System.out.print("| ");
                else
                    System.out.print(" ");
                this.cells[row * 9 + i].printCell();
                System.out.print(" ");

            }
            System.out.print("|\n");

        }

    }

    /*
    static class Row{
        public Cell[] column;

        Row(int row){
            this.column = new Cell[9];
            for(int i = 0; i < 9; i++){
                this.column[i] = new Cell(0);
            }
        }

        void printRow(){
            for(int i = 0; i < 9; i++){
                if(i % 3 == 0)
                    System.out.print("| ");
                else
                    System.out.print(" ");
                this.column[i].printCell();
                System.out.print(" ");

            }
            System.out.print("|\n");
        }

    }
    */
    /*
        public void printQuadrant(){
            for(int i = 0; i < 9; i++){
                if(i % 3 == 0)
                    System.out.println();
                this.cells[i].printCell();
            }

        }

        private boolean isPerfectSquare(int input){
            for(int i = 1; i < input/2; i++){
                if(i*i == input) return true;
            }
            return false;
        }
    }
    */
    static class Cell {
        private int number;
        private int[] options;

        Cell(int number){
            this.number = number;
            int[] possibilities = new int[10];
            possibilities[number] = number;
            this.setOptions(possibilities);
        }

        public void setOptions(int[] options){
            this.options = new int[10];
            for(int i = 0; i < 10; i++){
                this.options[i] = options[i];
            }
        }

        public void setNumber(int number){
            this.number = number;
            int[] possibilities = new int[10];
            this.setOptions(possibilities);
        }

        public int getNumber(){
            return this.number;
        }

        public int[] getOptions(){
            int[] options = new int[10];
            for(int i = 0; i < 10; i++){
                options[i] = this.options[i];
            }
            return options;
        }

        private boolean isValidNumber(int input){
            return (input >= 0 && input <= 9);
        }

        public void printCell(){
            if(this.number == 0) {
                System.out.print(" ");
            }
            else
                System.out.print(this.number);
        }
    }
}
