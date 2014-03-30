import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.Random;

public class MillionSongs_Jaccard_Similarity {
	public static void main(String[] args) throws IOException {
		int numHashfunctions = 100;
		int numberOfDocuments = 210519;
		BitSet[] bitArray = new BitSet[5000];
		for(int i = 0; i < 5000; i++) {
			bitArray[i] = new BitSet(numberOfDocuments);
		}
		double sigArray[][] = new double[numHashfunctions][210523];
		bitArray = parseSongs(bitArray);
		sigArray = calculateSignatures(bitArray, sigArray);
		for(int i = 0; i < 210519; i++) {
			System.out.print(sigArray[0][i] + ", ");
		}
	}
	
	/*
	 * The parseSongs function takes in a bit matrix, reads in each song lyric word by word,
	 * and sets the corresponding bit matrix value to true.
	 */
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
				//set the bit value of the word in the song to true
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
	
	public static int calculateHash(int row, int a, int b) {
		int x = (a*row+b)%210523;
		return x;
	}
	
	public static double[][] calculateSignatures(BitSet[] value, double[][] sigArray) {
		int numHashfunctions = 100;
		int[][] hashArray;
		hashArray = generateHash(numHashfunctions);
		for(int i = 0; i < numHashfunctions; i++) {
			for(int j = 0; j < 210523; j++) {
				sigArray[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		for(int i = 0; i < numHashfunctions; i++) {
			int hash = calculateHash(i, hashArray[i][0], hashArray[i][1]);
			for(int j = 0; j < 210519; j++) {
				if(value[i].get(j) == true) {
					if(hash < sigArray[i][j]) {
						sigArray[i][j] = hash;
					}
				}
			}
		}
		return sigArray;
	}

}
