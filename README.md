# HuffmanEncoder

This was a project for my Data structures class in Spring of 2018. The main method takes three arguments: 
an input file to be encoded,
an encoding file to use for the character code generation
an output file to write the encoding to

The tests for this project were done using the Gadsby novel by Ernest Vincent Wright (a novel written without e's)

In this repository there are 4 other text files:
EncodedGadsbyWithGadsby.txt    : Gadsby encoded with Gadsby as the key text
Gadsby&GadsbyHuffmanCodes.txt  : The codes for each character produced by the algorithm when using Gadsby as the key text
EncodedGadsbyWithDict.txt      : Gadsby encoded with an english dictionary as the key text
Gadsby&DictHuffmanCodes.txt    : THe codes for each character produced by the algorithm when using an English dictionary as the key text

These are the number bits saved in compression by using each of the two key files:
Gadsby with Gadsby     : 1,046,207
Gadsby with Dictionary :  952,220

This makes sense since the Gadsby novel is written without e's, but e is the most common letter
in the english dictionary. The algorithm produces a suboptimal encoding for the Gadsby novel (no e's) 
when using the English dictionary (e's very common), because the algorithm creates shorter codes for 
higher frequency characters. So although the dictionary will give e's a short code, we don't make 
use of that optimization in Gadsby, and instead lose out on opportunities to have shorter codes for 
characters that appear with higher frequency in Gadsby. 
