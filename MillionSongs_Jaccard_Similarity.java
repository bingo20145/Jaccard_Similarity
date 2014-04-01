import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;
import java.util.Random;
import java.util.Vector;


public class MillionSongs_Jaccard_Similarity {
	public static void main(String[] args) throws IOException {
		int numHashfunctions = 100;
		int numberOfDocuments = 210519;
		int count = 0;
		BitSet[] bitArray = new BitSet[5000];
		for(int i = 0; i < 5000; i++) {
			bitArray[i] = new BitSet(numberOfDocuments);
		}
		int sigArray[][] = new int[numHashfunctions][210523];
		for(int i = 0; i < numHashfunctions; i++) {
			for(int j = 0; j < numberOfDocuments; j++) {
				sigArray[i][j] = Integer.MAX_VALUE;
			}
		}
		bitArray = parseSongs(bitArray);
		sigArray = calculateSignatures(bitArray, sigArray);
		
		

		Vector<Integer>[] bucket = new Vector[210524];
		bucket = reduceVector(bucket);
		int temp = 0;
		int[][] indices = new int [15000][2];
		for(int i = 4; i < 100; i = i+5) {
			bucket = fillBucket(temp, i, bucket, sigArray);
			for(int k = 0; k < 210519; k++) {
				if(bucket[k].size() > 1) {
					if(!checkIndices(indices, bucket[k].get(0), bucket[k].get(1))) {
						for(int j = 1; j < bucket[k].size(); j++) {
							if(JacardSim(bucket[k].get(j-1), bucket[k].get(j), bitArray) == true) {
								indices[count][0] = bucket[k].get(j-1);
								indices[count][1] = bucket[k].get(j);
								count++;
							}
						}
					}
				}
				if(bucket[k].size() > 0) {
					bucket[k].clear();
				}
			}
			System.out.println("Count is: " + count);
			temp = i+1;
		}
		System.out.println("****HOLY FUCKING SHIT IT'S DONE!! THE COUNT WAS: " + count + "****");
	}
	
	
	
	public static Vector[] reduceVector(Vector[] v) {
		for(int i = 0; i < 210519; i++) {
			v[i] = new Vector<>(0);
		}
		return v;
	}
	
	public static boolean checkIndices(int[][] indexes, int smaller, int larger) {
		/*
		for(int i = 0; i < 15000; i++) {
			if((indexes[i][0] == smaller) && (indexes[i][1] == larger)) {
				return true;
			}
		}
		*/
		return false;
	}
	
	public static Vector[] fillBucket(int start, int end, Vector[] bucket, int[][] sigArray) {
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(start, end, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket[bucketValue].addElement(i);
		}
		System.out.println("Bucket " + ((start/5)+1) + " done");
		return bucket;
	}
	
	public static int prependSignal(int start, int end, int column, int[][] sig) {
		String literal = "";
		long number;
		for(int i = start; i < end; i++) {
			literal = literal + Integer.toString(sig[i][column]);
		}
		number = Long.parseLong(literal);
		int result = (int)(number%210523);
		if(result == 210523) {
			result = 210522;
		}
		result = Math.abs(result);
		return result;
		
	}
	
	/*
	 * The parseSongs function takes in a bit matrix, reads in each song lyric word by word,
	 * and sets the corresponding bit matrix value to true.
	 */
	public static BitSet[] parseSongs(BitSet[] b) throws IOException {
		File path = new File("C:/Users/bowmmh11/workspace/MillionSongs_Jaccard_Similarity/src/mxm_dataset_train.txt");
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		int tempInts = 0;
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
			h[i][0] = rand.nextInt(20);
			h[i][1] = rand.nextInt(10);
		}
		return h;
	}
	
	public static int[] calculateHash(int row, int[][] hashArray, int numHash) {
		int[] hash = new int[numHash];
		for(int i = 0; i < numHash; i++) {
			hash[i] = ((hashArray[i][0]*row)+hashArray[i][1])%9973;
		}
		return hash;
	}
	
	public static boolean JacardSim(int x, int i, BitSet[] value) {
		int intersect = 0;
		int union = 0;
		double similarity;
		for(int z = 0; z < 5000; z++) {
			if(value[z].get(x) == value[z].get(i)) {
				if(value[z].get(x) == false) {
					//do nothing
				}
				else {
					intersect++;
				}
			}
			else {
				union++;
			}

		}
		similarity = (double)intersect/(intersect+union);
		if(similarity >= 0.95) {
			return true;
		}
		else return false;
	}

	public static int[][] calculateSignatures(BitSet[] value, int[][] sigArray) {
		int numHashfunctions = 100;
		int[][] hashArray;
		hashArray = generateHash(numHashfunctions);
		int[] hashValues;
		
		for(int i = 0; i < 5000; i++) {
			hashValues = calculateHash(i, hashArray, numHashfunctions);
			for(int j = 0; j < 210519; j++) {
				if(value[i].get(j)) {
					for(int k = 0; k < numHashfunctions; k++) {
						if(sigArray[k][j] > hashValues[k]) {
							sigArray[k][j] = hashValues[k];
						}
					}
				}
			}
		}
		
		return sigArray;
	}

}
