package com.spoj.PRIME1;

// problem :: https://www.spoj.com/problems/TEST/
// blog post ::
// youtube tutorial ::

// don't copy above this line

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

		// change to false before uploading
		private static final boolean DEBUG = true;

		public static void main(String[] args) throws java.lang.Exception {
		long beginTime = System.nanoTime();

		String input = "" + Main.class.getResource("input.txt");
		input = input.replace("file:", "");
		InputStream is = DEBUG ? new FileInputStream(input) : System.in;

		try (Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)))) {
			long count = scanner.nextLong();
			while (count-- != 0) {
				int x = scanner.nextInt();
				if (x==1) {
					x++;
				}
				int y = scanner.nextInt();
				getprimes(x, y);
			}
		}
	  }

	private static void getprimes(int minValue, int maxValue) {
		
		StringBuilder sb = new StringBuilder();
		boolean notPrimes[] = new boolean[31623];
		
		//-----------------------------------------------------------------------------
		// generating prime numbers from 1 to 31622 using sieve of eratosthenes.
		//-----------------------------------------------------------------------------
		// only up to because 31622 is square root of 1000000000 
		// loop is run 177 times because 177 is square root of 31622		   
		//-----------------------------------------------------------------------------
		// Actually all the elements of the notPrime[] are false, means all are prime.
		// we are eliminating composite numbers by removing multiples of prime numbers.
		//------------------------------------------------------------------------------
		// Lesson: if we run the loop only once for i=2 then also we will get the answer.
		// because we are not eliminating all the composites. But majority are removed.
		// As this loop runs less number of times, lower loop runs more number of times.
		// Both are equally efficient but this method has little edge.
		//------------------------------------------------------------------------------
		
		for (int i = 2; i <= 177; i++) {
			if (!notPrimes[i]) {
				for (int j = 2 * i; j <= 31622; j += i) {
					notPrimes[j] = true;
				}
			}
		}
		boolean[] result = new boolean[maxValue - minValue + 1];
		for (int k = 2; k <= 31622; k++) {
			if (!notPrimes[k]) {
				int p = minValue - minValue % k;
				if (p < minValue) {
					p += k;
				}
				if (p == k) {
					p += k;
				}
				while (p <= maxValue) {
					result[p - minValue] = true;
					p += k;
				}
			}
		}
		for (int k = minValue; k <= maxValue; k++) {
			if (!result[k - minValue]) {
				sb.append(k).append("\n");
			}
		}
		System.out.println(sb);
	}	
}