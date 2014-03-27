import java.math.BigInteger;


public class asdasd {
	static BigInteger[][] mas;

	public static void main(String[] args) {
		int n = 32767;
		mas = new BigInteger[n + 1][n];
		System.out.println(count(n));
	}

	private static BigInteger count(int n) {
		BigInteger var = BigInteger.valueOf(n);
		BigInteger result = BigInteger.valueOf(1 - var.abs().signum());
		if (var.compareTo(BigInteger.ONE) <= 0) {
			var.add(BigInteger.ONE);
		}
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(var) <= 0; i = i
				.add(BigInteger.ONE)) {
			result = result.add(g(var, i));
		}
		return result;
	}

	private static BigInteger g(BigInteger var, BigInteger count) {
		BigInteger result = BigInteger.ONE;
		if (count.compareTo(var.subtract(BigInteger.ONE)) < 0) {
			BigInteger tmp = mas[var.intValue()][count.intValue()];
			if (tmp != null) {
				result = tmp;
			} else {
				BigInteger d = var.subtract(count);
				BigInteger c;
				if (d.compareTo(count) == -1) {
					c = d;
				} else {
					c = count;
				}
				for (BigInteger i = BigInteger.valueOf(2); i.compareTo(c) <= 0; i = i
						.add(BigInteger.ONE)) {
					result = result.add(g(d, i));
				}
				mas[var.intValue()][count.intValue()] = result;
			}
		}
		return result;

	}
}
