package com.epam.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class op {
	static private int g(int n, int g) {
		if (g <= 1 || g >= (n - 1)) {
			return 1;
		}
		int result = 1;
		int c = 2;
		while (c <= g) {
			int d = n - g;
			if (c <= d) {
				result += g(d, c);
			}
			c++;
		}
		return result;
	}

	static public int count(int v) {
		int result = (int) (1 - Math.signum(Math.abs(v)));
		int c = 1;
		while (c <= v) {
			result += g(v, c);
			c++;
		}
		return result;
	}

	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("results2.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 70; i++) {
			int result = count(i);
			System.out.println(i + "," + result);
			pw.println(i + "," + result);
		}
		pw.close();
	}
}
