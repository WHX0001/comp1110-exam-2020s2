package comp1110.exam;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * COMP1110 Final Exam, Question 1.3 (harder)
 *
 * 5 Marks
 */
public class Q1Number {
  /**
   * This question is about a number finding puzzle.
   *
   * You will be given a square grid of numbers, represented as a single
   * array of integers.   Each value in the grid is a number between 0 and 9.
   * You will also be given a target number.   Your task is to try to find
   * the target number within the grid.  If you find it, you return the
   * index of the grid element holding the first digit of the number.  If
   * you can't find it, return -1.
   *
   * Numbers can be 'hidden' in the grid by consecutive digits being in
   * horizontally or vertically adjacent elements (but not diagonally).
   * When creating numbers, digits in the grid may only be used ONCE.
   *
   * For example, given the 3 x 3 grid [2, 4, 4, 0, 5, 7, 7, 1, 4] and the
   * target 42, you would return 1.   Visualizing the grid:
   *
   *    2 4 4
   *    0 5 7
   *    7 1 4
   *
   *    2 4 _
   *    _ _ _
   *    _ _ _
   *
   * The number 42 is 'hidden' in the first two elements of the grid, starting
   * at offset 1 then going left, so the answer is 1.
   *
   * Given the 6 x 6 grid [0, 8, 0, 9, 1, 1, 6, 7, 6, 3, 2, 9, 2, 7, 7, 9, 2, 8, 4, 0, 9, 2, 9, 2, 0, 5, 1, 1, 5, 9, 8, 5, 7, 1, 1, 7],
   * and the target 91507, you would return 20.  Visualizing the grid:
   *
   *  0 8 0 9 1 1
   *  6 7 6 3 2 9
   *  2 7 7 9 2 8
   *  4 0 9 2 9 2
   *  0 5 1 1 5 9
   *  8 5 7 1 1 7
   *
   *  _ _ _ _ _ _
   *  _ _ _ _ _ _
   *  _ 7 _ _ _ _
   *  _ 0 9 _ _ _
   *  _ 5 1 _ _ _
   *  _ _ _ _ _ _
   *
   *  The number 91507 is 'hidden' in the grid starting at location 20,
   *  then going down, then left, then up twice.
   *
   * @param grid An array of integers between 0 and 9, where the size of the
   *             array is square (1, 4, 9, 16 etc).
   * @param target A target number which may be hidden in the grid.
   * @return If the target number is hidden in the grid, return the location
   * (index) within the grid of the first digit of the target number, or -1
   * if it is not in the grid.
   */

  public static int find(int[] grid, int target) {
    // FIXME complete this method
    //initialization
    System.out.println("-------------------start------------------------");
    int res = -1;
    int[] targets = interpretTarget(String.valueOf(target));

    for(int i = 0; i < grid.length; i++){
      if(targets[0] == grid[i]){
        if(String.valueOf(target).length()>1){ // multiple targets
          if(searchNeighbour(grid,String.valueOf(target).substring(1), i)){//verify by its following targets
            res = i;
          }
        }else{//only one target
          System.out.println("mark");
          res = i;
        }
      }
    }

    return res;
  }

  public static boolean searchNeighbour(int[] grid, String targetString, int currentPosition){
    System.out.println(targetString);
    int[] temp = grid.clone();
    temp[currentPosition] = -100;
    int targets[] = interpretTarget(targetString);
    int target = targets[0];
    int[] searchPositions = findNeighbour(grid,targetString,currentPosition);
    for(int i: searchPositions){
      if(grid[i] == target){
        if(targetString.length()>1){
          temp[i] = -100;
          if(searchNeighbour(temp, targetString.substring(1), i)){
            return true;
          }else{
            return false;
          }
        }
        return true;
      }
    }
    return false;
  }

  public static int[] findNeighbour(int[] grid, String targetString, int currentPosition){
    int size = (int)Math.sqrt(grid.length);
    int currentXPosition = currentPosition%size;
    int currentYPosition = currentPosition/size;

    ArrayList<Integer> positions = new ArrayList<>();
    if(checkValidity(size, currentXPosition, currentYPosition-1)){
      if(grid[currentYPosition*size+currentXPosition-size] != -100){
        positions.add(currentYPosition*size+currentXPosition-size);
      }
    }
    if(checkValidity(size, currentXPosition-1, currentYPosition)){
      if(grid[currentYPosition*size+currentXPosition-1] != -100){
        positions.add(currentYPosition*size+currentXPosition-1);
      }
    }
    if(checkValidity(size, currentXPosition+1, currentYPosition)){
      if(grid[currentYPosition*size+currentXPosition+1] != -100) {
        positions.add(currentYPosition * size + currentXPosition + 1);
      }
    }
    if(checkValidity(size, currentXPosition, currentYPosition+1)){
      if(grid[currentYPosition*size+currentXPosition+size] != -100) {
        positions.add(currentYPosition * size + currentXPosition + size);
      }
    }
    return positions.stream().mapToInt(Integer::intValue).toArray();
  }
  public static boolean checkValidity(int size, int x, int y){
    boolean xValidity = (x >= 0) && (x <= size-1);
    boolean yValidity = (y >= 0) && (y <= size-1);
    return  xValidity && yValidity;
  }
  public static int[] interpretTarget(String target){
    int[] targets = new int[target.length()];
    for(int i = 0; i < target.length(); i++){
      targets[i] = Integer.parseInt(String.valueOf(target.charAt(i)));
    }
    return targets;
  }
}
