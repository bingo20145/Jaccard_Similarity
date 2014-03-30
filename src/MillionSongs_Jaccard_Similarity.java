import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.Random;

public class MillionSongs_Jaccard_Similarity {
	public static void main(String[] args) throws IOException {
		int numDocuments = 210519;
        int numWords = 5000;
		BitSet[] bitArray = new BitSet[numWords];
		for(int i = 0; i < numWords; i++) {
			bitArray[i] = new BitSet(numDocuments);
		}
		
		bitArray = parseSongs(bitArray);
		
		for(int i = 0; i < numWords; i++) {
			System.out.print(bitArray[i].get(0) + ", ");
		}

        int numHashFunctions = 100;
        int[][] hashArray;
        hashArray = generateHash(numHashFunctions);
        double[][] signature = new double[numHashFunctions][numDocuments];


	}
    /*
    *  hash functions are in the form ax+b mod p
    *  p = 210523, 210523 is the closest prime number after
    *  x = row number
    *  a and b are randomly generated and capped at 10000
    * */
    public static int calculateHash(int row, int a, int b) {
        int x = a*row + b % 210523;
        return x;
    }
	public static BitSet[] parseSongs(BitSet[] b) throws IOException {
		File path = new File("mxm_dataset_train.txt");
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		int tempInts;
		int count = -1;
		while((line = br.readLine()) != null) {
			//increment song count
			count = count + 1;
			//splitting up each line by comma
			String[] parts = line.split(",");
			for (int i = 2; i < parts.length; i++) {
				//splitting up word indices and counts
				String[] temp = parts[i].split(":");
				//convert word index to integer
				tempInts = Integer.parseInt(temp[0]);
				//set the boolean value of the word in the song to true
				b[tempInts-1].flip(count);
			}
		}
		//return bitset matrix
		return b;
	}


	/*
	 * generateHash() takes in the desired number of hash functions to be used when
	 * computing the minhash of the lyric values, and randomly generates values to
	 * be used in the (ax+b mod p) hash function. It returns an array of randomly
	 * generated a and b values to use in hashing.
	 */
	public static int[][] generateHash(int numHashFunctions) {
		//Define random object
		Random rand = new Random();
		//allocate memory for array of hashing values
		int[][] h = new int[numHashFunctions][2];

		//populate array with randomly generated integers
		for(int i = 0; i < numHashFunctions; i++) {
			h[i][0] = rand.nextInt(10000);
			h[i][1] = rand.nextInt(10000);
		}
		return h;
	}

}
