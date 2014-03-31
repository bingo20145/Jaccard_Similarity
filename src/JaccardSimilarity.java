import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.Random;

public class JaccardSimilarity {
	public static void main(String[] args) throws IOException {
		int numDocuments = 210519;
        int numWords = 5000;
        //create characteristic matrix
        //matrix is initialized to all 0's
		BitSet[] bitMatrix = new BitSet[numWords];
		for(int i = 0; i < numWords; i++) {
			bitMatrix[i] = new BitSet(numDocuments);
		}
        //parse data set and create matrix that describes
        //each song in terms of what words that contain
		bitMatrix = parseSongs(bitMatrix);

        int numHashFunctions = 100;
        //create signature matrix
        double[][] signatures = new double[numHashFunctions][numDocuments];
        //initialize signature matrix entires to infinity
        for (int i = 0; i < numHashFunctions; i++) {
            for (int j = 0; j < numDocuments; j++) {
                signatures[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        signatures = calculateSignatures(bitMatrix,numHashFunctions , numDocuments, signatures);
	}

    public static double[][] calculateSignatures(BitSet[] bitMatrix, int numHashFunctions, int numDocuments, double[][] signatures) {
        int[][] hashArray = generateHashFunction(numHashFunctions);
        for (int i = 0; i < numHashFunctions; i++) {
            int hash = calculateHash(i, hashArray[i][0], hashArray[i][1]);
            for (int j = 0; j < numDocuments; j++) {
                if(bitMatrix[i].get(j)) {
                    if (hash < signatures[i][j]) {
                        signatures[i][j] = hash;
                    }
                }
            }
        }
        return signatures;
    }
    /*
    *  hash functions are in the form ax+b mod p
    *  p = 210523 because 210523 is the closest prime number after 210519
    *  x = row number
    *  a and b are randomly generated and capped at 10000 for simplicity
    * */
    public static int calculateHash(int row, int a, int b) {
        return ((a*row) + b) % 210523;
    }
    /*
    * open data set and parse songs word by word
    * if a song contains a particular word then
    * the corresponding matrix value is set to 1 (true)
    * */
	public static BitSet[] parseSongs(BitSet[] b) throws IOException {
		File path = new File("mxm_dataset_train.txt");
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		int tempInts;
		int count = 0;
		while((line = br.readLine()) != null) {
			//increment song count
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
            count++;
		}
		//return bitset matrix
		return b;
	}


	/*
	 * generateHashFunction() takes in the desired number of hash functions to be used when
	 * computing the minhash of the lyric values, and randomly generates values to
	 * be used in the (ax+b mod p) hash function. It returns an array of randomly
	 * generated a and b values to use in hashing.
	 */
	public static int[][] generateHashFunction(int numHashFunctions) {
        //set seed for random number generator
        long seed = 0;
		//Define random object
		Random rand = new Random();
        //For testing, seed will be set to constant
        //In practice. seed will be set to System.currentTimeInMillis();
        rand.setSeed(seed);
		//allocate memory for array of hashing values
		int[][] hashArray = new int[numHashFunctions][2];

		//populate array with randomly generated integers
		for(int i = 0; i < numHashFunctions; i++) {
			hashArray[i][0] = rand.nextInt(10000);
			hashArray[i][1] = rand.nextInt(10000);
		}
		return hashArray;
	}

}
