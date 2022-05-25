# SUDOKU bruteforce with Java

A short script that creates valid Sudoku boards and solves them using Java.


## Motivation

Exercise proposed by Objects-Oriented Programming class (of UFF's Computer Science course)
Additionally being used to experiment with GitHub features.


## Tech Stack

Java ```TODO: add version```


## ABOUT SUDOKU

Sudoku is a game comprised of a table (9x9 grid) intially containing a few digits. The player is expected to fill the table with additional digitis following simple rules:
- Each cell must only have one digit
- Each row must have the digits 1, 2, 3, 4, 5, 6, 7, 8 and 9, without those digits repeating in the row.
- Each column must have the digits 1, 2, 3, 4, 5, 6, 7, 8 and 9, without those digits repeating in the column.
- The 9x9 grid should be considerated as a 3x3 grid of sets, each one a 3x3 square of cells, that are non-overlaping.
- Each 3x3 set must have the digits 1, 2, 3, 4, 5, 6, 7, 8 and 9, without those digits repeating in the set.

When a initial game is created, it must follow the same non-repetition rules as the player, however it must leave several digits unfilled.

**Empty grid**
<table>
  <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp; </td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
  <tr><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td><td>&nbsp; </td></tr>
 </table>
 
 
 
 **Empty grid - Emphasys on 3x3 sets**
 
 _Notice that each 3x3 set MUST NOT have reapeating digits_
 <table>
  <tr>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
  </tr>
   <tr>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
  </tr>  
   <tr>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
    <td> <table>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        <tr><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td><td>&nbsp; &nbsp;</td></tr>
        </table></td>
  </tr> 
 </table>
 
 
 **Valid initial grid**
 <table>
  <tr><td>3</td><td></td><td></td><td></td><td>6</td><td>9</td><td></td><td></td><td></td></tr>
  <tr><td></td><td>5</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
  <tr><td></td><td></td><td>8</td><td></td><td></td><td></td><td></td><td></td><td>7</td></tr>
  <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>8</td></tr>
  <tr><td>2</td><td></td><td></td><td></td><td>3</td><td></td><td></td><td></td><td></td></tr>
  <tr><td></td><td></td><td></td><td></td><td>4</td><td></td><td></td><td></td><td></td></tr>
  <tr><td></td><td></td><td></td><td></td><td></td><td></td><td>5</td><td></td><td>2</td></tr>
  <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>1</td><td></td></tr>
  <tr><td></td><td></td><td></td><td>9</td><td></td><td></td><td></td><td></td><td></td></tr>
 </table>
