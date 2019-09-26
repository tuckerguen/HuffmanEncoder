
import java.util.*;
import java.io.*;


/**
 * Eoncodes and compresses an input file according
 * to a key file, and writes to an output file using
 * the Huffman encoding algorithm.
 * @author Tucker Guen
 */

public class HuffmanCompressor {  

  /** The comparator to initialize the priority queue storing HuffmanNodes */
  private Comparator<HuffmanNode> comparator = new HuffmanNodeComparator();

  /** The heap of Huffman nodes */
  private PriorityQueue<HuffmanNode> HuffmanTree = new PriorityQueue<HuffmanNode>(300, comparator);

  /** Stores the code of each char at index of each char's ASCII value */
  private String[] codes = new String[127];
  
  /** Stores frequency of each char at index of each char's respective ASCII value */
  private int[] charFrequencies = new int[127];
   
  /**
   * Reads a .txt file input and stores all the characters and their respective
   * frequency values
   * 
   * @param path text file to analyze
   * @throws FileNotFoundException
   */
  public void scanFile(String path) throws FileNotFoundException {
    try {
      File file = new File(path); //The file to be read
      BufferedReader reader = new BufferedReader(new FileReader(file)); //The reader to read that characters in the file
      int c = 0; //The value to store the char as the file is iterated through
      
      //Looks through the file and counts the frequencies of each character
      while ((c = reader.read()) != -1) {  
        if(c > 31 && c < 127) {
          charFrequencies[c]++;
        }
      }        
      reader.close();
    } catch (Exception e) {
      throw new FileNotFoundException();
    }
    
    // Create the Huffman nodes and arbitrarily place them in the queue
    for (int i = 32; i < 127; i++) {
      char c = (char) i;
      HuffmanNode newNode = new HuffmanNode(c, charFrequencies[i], null, null);
      HuffmanTree.add(newNode);   
    }
  }

  /**
   * Merges two huffman nodes
   * @param first the first node to be merged
   * @param second the second node to be merged with the first
   * @return The combined Huffman parent node
   */
  public HuffmanNode mergeNodes(HuffmanNode first, HuffmanNode second) {
    HuffmanNode parentNode = new HuffmanNode(null, first.getFrequency() + second.getFrequency(), first, second);
    return parentNode;    
  }

  /**
   * Creates the huffman tree using the nodes stored in
   * the priority queue
   */
  public void createHuffmanTree() { 
    while(HuffmanTree.size() != 1) {
      HuffmanTree.add(mergeNodes(HuffmanTree.poll(), HuffmanTree.poll()));
    }
  }

  /**
   * Uses the tree created by createHuffmanTree() to determine 
   * the codes for the characters in the tree
   * @param node the node to begin the traversal at
   * @param code the binary digit code
   */
  public void charEncoding(HuffmanNode node, String code) {
    if(node.getLeft() == null && node.getRight() == null && node.getInChar() != null) {
      codes[node.getInChar()] = code;
      return;
    }
    
    //Recursively traverses the tree and adds to the current character code based on traversal direction
    charEncoding(node.getLeft(), code + "0");
    charEncoding(node.getRight(), code + "1");
  }

  
  /**
   * Scans the inputFile, stores the character codes and uses
   * those codes to encode the outputFile and save the encoded
   * file in the directory of this project.
   * @param inputFile the file to create the character codes from
   * @param outputFile the file to encode using the character codes
   * @throws FileNotFoundException 
   */
  public void encodeFile(String inputFile, String encodingFile, String outputFile) throws FileNotFoundException {
    int unencodedBitSize = 0; //The number of bits in the original outputFile before encoding
    int encodedBitSize = 0; //The number of bits used in the outputFile after encoding
    scanFile(fileDirectory + encodingFile);
    createHuffmanTree();
    charEncoding(HuffmanTree.poll(), "");
    
    try {
      File file = new File(fileDirectory + inputFile); //File of the outputFile. 
      BufferedReader reader = new BufferedReader(new FileReader(file)); //Reader to read the outputFile
      BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile)); //Writer to write to the new encoded file

      int c = 0; //The value to store the char as the file is iterated through
      
      //Counts the bits of the original file and the encoded file. Encodes the output file
      while ((c = reader.read()) != -1) { 
        unencodedBitSize++;
        if(c > 31 && c < 127) {
          writer.write(codes[c]);
          encodedBitSize += codes[c].length();
        }
      } 
      unencodedBitSize *= 8;
      reader.close();
      writer.close();
    } catch (Exception e) {
      throw new FileNotFoundException();
    }
    // Prints the table of character codes
    for(int i = 32; i < 127; i++) {
      System.out.println((char)i + ":" + charFrequencies[i] + ":" + codes[i]);
    }
    System.out.println("Space saved: " + (unencodedBitSize - encodedBitSize));
  }

  /**
   * Main method to encode a file
   * @param args args[0] is imput file, args[1] is encoding file, args[2] is output file
   * @throws FileNotFoundException
   */
  public static void main(String[] args) throws FileNotFoundException {
    HuffmanCompressor x = new HuffmanCompressor();
    x.encodeFile(args[0], args[1], args[2]);
  }

}
