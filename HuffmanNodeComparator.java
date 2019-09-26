import java.util.Comparator;

/**
 * Represents a comparator class for the HuffmanNode class
 * that allows the priority queue in HuffmanCoding to order
 * the nodes based on frequency
 * @author Tucker Guen
 *
 */

public class HuffmanNodeComparator implements Comparator<HuffmanNode> {
  @Override
  public int compare(HuffmanNode node1, HuffmanNode node2) {
      if(node1.getFrequency() < node2.getFrequency())
        return -1;
      else if(node1.getFrequency() > node2.getFrequency())
        return 1;
      else return 0;
    }
  }