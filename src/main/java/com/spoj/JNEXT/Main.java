package com.spoj.JNEXT;

// problem :: https://www.spoj.com/problems/JNEXT/
// blog post ::
// youtube tutorial ::

// don't copy above this line

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	
	// change to false before uploading
	private static final boolean DEBUG = true;
	
    public static void main(String[] args) throws Exception {
    	
    	String input = "" + Main.class.getResource("input.txt");
		input = input.replace("file:", "");
        InputReader in = new InputReader(DEBUG ? new FileInputStream(input) : System.in);
        OutputWriter out = new OutputWriter(System.out);
        
        int T = in.readInt();
               
        for(int i=0; i< T; i++) {
        	int N = in.readInt();
        	int[] arr = new int[N];
        	for (int j = 0; j < N; j++) {
				arr[j] = in.readInt();
			}
        	boolean res = Nextperm.nextPermutation(arr);
        	
        	if(res) {
        		for (int j = 0; j < arr.length; j++) {
    				out.print(arr[j]);
    			}
        	} else {
        		out.print("-1");
        	}
        	
        	out.println("");
        }
        out.flush();
        out.close();
    }
}
 
class InputReader {
    private final InputStream stream;
    private final byte buf[] = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;
 
    public InputReader(InputStream stream) {
        this.stream = stream;
    }
 
    public int read() {
        if(numChars == -1) throw new InputMismatchException();
        if(curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch(IOException e) {
                throw new InputMismatchException();
            }
            if(numChars <= 0) return -1;
        }
        return buf[curChar++];
    }
 
    public int readInt() {
        int c = read();
        while(isSpaceChar(c)) c = read();
        int sgn = 1;
        if(c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if(c < '0' || c > '9') throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while(!isSpaceChar(c));
        return res*sgn;
    }
 
    public long readLong() {
        int c = read();
        while(isSpaceChar(c)) c = read();
        int sgn = 1;
        if(c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if(c < '0' || c > '9') throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while(!isSpaceChar(c));
        return res*sgn;
    }
 
    public char readChar() {
        int c = read();
        while(isSpaceChar(c)) c = read();
        return (char) c;
    }
 
    public String readString() {
        int c = read();
        while(isSpaceChar(c)) c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while(!isSpaceChar(c));
        return res.toString();
    }
 
    public String readLine() {
        int c = read();
        while(isNewLineChar(c)) c = read();
        StringBuilder res = new StringBuilder();
        do {
            if(c<0) c += 256;
            res.appendCodePoint(c);
            c = read();
        } while(!isNewLineChar(c));
        return res.toString();
    }
 
    public String next() {
        return readString();
    }
 
    public boolean isSpaceChar(int c) {
        if(filter != null) return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
 
    public boolean isNewLineChar(int c) {
        if(filter != null) return filter.isNewLineChar(c);
        return c == '\n' || c == '\r' || c == -1;
    }
 
    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
        public boolean isNewLineChar(int ch);
    }
}
 
class OutputWriter {
    private final PrintWriter writer;
 
    public OutputWriter(OutputStream stream) {
        this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)));
    }
 
    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }
 
    public void print(Object...objects) {
        for(int i=0; i<objects.length; i++) {
            if(i != 0) writer.print(' ');
            writer.print(objects[i]);
        }
    }
 
    public void println(Object...objects) {
        print(objects);
        writer.println();
    }
 
    public void flush() {
        writer.flush();
    }
 
    public void close() {
        writer.close();
    }
}

class Nextperm {
	
	/* Basic integer array version */
	/**
	 * Computes the next lexicographical permutation of the specified array of integers in place,
	 * returning whether a next permutation existed. (Returns {@code false} when the argument
	 * is already the last possible permutation.)
	 * @param array the array of integers to permute
	 * @return whether the array was permuted to the next permutation
	 */
	public static boolean nextPermutation(int[] array) {
		// Find non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;
		if (i <= 0)
			return false;
		
		// Find successor to pivot
		int j = array.length - 1;
		while (array[j] <= array[i - 1])
			j--;
		int temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;
		
		// Reverse suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
		return true;
	}
	
	
	/* Generic array version */
	public static <T extends Comparable<? super T>> boolean nextPermutation(T[] array) {
		// Find non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1].compareTo(array[i]) >= 0)
			i--;
		if (i <= 0)
			return false;
		
		// Find successor to pivot
		int j = array.length - 1;
		while (array[j].compareTo(array[i - 1]) <= 0)
			j--;
		T temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;
		
		// Reverse suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
		return true;
	}
	
	
	/* Generic list version */
	public static <T extends Comparable<? super T>> boolean nextPermutation(List<T> array) {
		// Find non-increasing suffix
		int i = array.size() - 1;
		while (i > 0 && array.get(i - 1).compareTo(array.get(i)) >= 0)
			i--;
		if (i <= 0)
			return false;
		
		// Find successor to pivot
		int j = array.size() - 1;
		while (array.get(j).compareTo(array.get(i - 1)) <= 0)
			j--;
		Collections.swap(array, i - 1, j);
		
		// Reverse suffix
		Collections.reverse(array.subList(i, array.size()));
		return true;
	}
	
	
	/* Generic list version with comparator */
	public static <T> boolean nextPermutation(List<T> array, Comparator<T> comp) {
		// Find non-increasing suffix
		int i = array.size() - 1;
		while (i > 0 && comp.compare(array.get(i - 1), array.get(i)) >= 0)
			i--;
		if (i <= 0)
			return false;
		
		// Find successor to pivot
		int j = array.size() - 1;
		while (comp.compare(array.get(j), array.get(i - 1)) <= 0)
			j--;
		Collections.swap(array, i - 1, j);
		
		// Reverse suffix
		Collections.reverse(array.subList(i, array.size()));
		return true;
	}
	
}