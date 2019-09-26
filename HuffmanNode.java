/**
 * Represents a node in a Huffman Tree that contains a
 * frequency, character, and a left and right child
 * @author tucke
 *
 */

public class HuffmanNode {

  private Character nodeChar;
  
  /** The frequency of the character or the sum 
   * of children character frequencies in the text */
  private int frequency;
  
  private HuffmanNode left;
  
  private HuffmanNode right;

  public HuffmanNode(Character inChar, int frequency, HuffmanNode left, HuffmanNode right) {
    this.nodeChar = inChar;
    this.frequency = frequency;
    this.left = left;
    this.right = right;
  }  

  public Character getNodeChar() {
    return nodeChar;
  }

  public void setNodeChar(Character nodeChar) {
    this.nodeChar = nodeChar;
  }

  public int getFrequency() {
    return frequency;
  }

  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }

  public HuffmanNode getLeft() {
    return left;
  }

  public void setLeft(HuffmanNode left) {
    this.left = left;
  }

  public HuffmanNode getRight() {
    return right;
  }

  public void setRight(HuffmanNode right) {
    this.right = right;
  }
  
  
}
