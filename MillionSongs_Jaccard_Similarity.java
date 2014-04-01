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
		
		

		Vector[] bucket1 = new Vector[210524];
		bucket1 = reduceVector(bucket1);
		Vector[] bucket2 = new Vector[210524];
		bucket2 = reduceVector(bucket2);
		Vector[] bucket3 = new Vector[210524];
		bucket3 = reduceVector(bucket3);
		Vector[] bucket4 = new Vector[210524];
		bucket4 = reduceVector(bucket4);
		Vector[] bucket5 = new Vector[210524];
		bucket5 = reduceVector(bucket5);
		Vector[] bucket6 = new Vector[210524];
		bucket6 = reduceVector(bucket6);
		Vector[] bucket7 = new Vector[210524];
		bucket7 = reduceVector(bucket7);
		Vector[] bucket8 = new Vector[210524];
		bucket8 = reduceVector(bucket8);
		Vector[] bucket9 = new Vector[210524];
		bucket9 = reduceVector(bucket9);
		Vector[] bucket10 = new Vector[210524];
		bucket10 = reduceVector(bucket10);
		Vector[] bucket11 = new Vector[210524];
		bucket11 = reduceVector(bucket11);
		Vector[] bucket12 = new Vector[210524];
		bucket12 = reduceVector(bucket12);
		Vector[] bucket13 = new Vector[210524];
		bucket13 = reduceVector(bucket13);
		Vector[] bucket14 = new Vector[210524];
		bucket14 = reduceVector(bucket14);
		Vector[] bucket15 = new Vector[210524];
		bucket15 = reduceVector(bucket15);
		Vector[] bucket16 = new Vector[210524];
		bucket16 = reduceVector(bucket16);
		Vector[] bucket17 = new Vector[210524];
		bucket17 = reduceVector(bucket17);
		Vector[] bucket18 = new Vector[210524];
		bucket18 = reduceVector(bucket18);
		Vector[] bucket19 = new Vector[210524];
		bucket19 = reduceVector(bucket19);
		Vector[] bucket20 = new Vector[210524];
		bucket20 = reduceVector(bucket20);
		
		
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(0, 4, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket1[bucketValue].addElement(i);
		}
		System.out.println("Bucket 1 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(5, 9, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket2[bucketValue].addElement(i);
		}
		System.out.println("Bucket 2 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(10, 14, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket3[bucketValue].addElement(i);
		}
		System.out.println("Bucket 3 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(15, 19, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket4[bucketValue].addElement(i);
		}
		System.out.println("Bucket 4 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(20, 24, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket5[bucketValue].addElement(i);
		}
		System.out.println("Bucket 5 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(25, 29, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket6[bucketValue].addElement(i);
		}
		System.out.println("Bucket 6 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(30, 34, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket7[bucketValue].addElement(i);
		}
		System.out.println("Bucket 7 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(35, 39, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket8[bucketValue].addElement(i);
		}
		System.out.println("Bucket 8 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(40, 44, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket9[bucketValue].addElement(i);
		}
		System.out.println("Bucket 9 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(45, 49, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket10[bucketValue].addElement(i);
		}
		System.out.println("Bucket 10 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(50, 54, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket11[bucketValue].addElement(i);
		}
		System.out.println("Bucket 11 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(55, 59, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket12[bucketValue].addElement(i);
		}
		System.out.println("Bucket 12 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(60, 64, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket13[bucketValue].addElement(i);
		}
		System.out.println("Bucket 13 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(65, 69, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket14[bucketValue].addElement(i);
		}
		System.out.println("Bucket 14 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(70, 74, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket15[bucketValue].addElement(i);
		}
		System.out.println("Bucket 15 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(75, 79, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket16[bucketValue].addElement(i);
		}
		System.out.println("Bucket 16 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(80, 84, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket17[bucketValue].addElement(i);
		}
		System.out.println("Bucket 17 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(85, 89, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket18[bucketValue].addElement(i);
		}
		System.out.println("Bucket 18 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(90, 94, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket19[bucketValue].addElement(i);
		}
		System.out.println("Bucket 19 done");
		for(int i = 0; i < 210519; i++) {
			int bucketValue = prependSignal(95, 99, i, sigArray);
			bucketValue = Math.abs(bucketValue);
			bucket20[bucketValue].addElement(i);
		}
		System.out.println("Bucket 20 done");
		
		for(int i = 0; i < 210519; i++) {
			if(bucket1[i].size() > 1) {
				for(int j = 1; j < bucket1[i].size(); j++) {
					if(JacardSim(bucket1[i].get(j-1), JacardSim(bucket1[i].get(j), bitArray) == true) {
						count++;
					}
				}
			}
		}
	}
	
	
	
	public static Vector[] reduceVector(Vector[] v) {
		for(int i = 0; i < 210519; i++) {
			v[i] = new Vector(0);
		}
		return v;
	}
	
	public static int prependSignal(int start, int end, int column, int[][] sig) {
		String literal = "";
		long number;
		for(int i = start; i < end; i++) {
			literal = literal + Integer.toString(sig[i][column]);
		}
		number = Long.parseLong(literal);
		int result = (int)(number%210523);
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
			if(value[x].get(z) == value[i].get(z)) {
				if(value[x].get(z) == false) {
					//do nothing
				}
				else {
					intersect++;
				}
				union++;
			}

		}
		similarity = intersect/(double)union;
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
