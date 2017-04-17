public class Utils {
	private static int irreducible = 0x1b;

	public static int galoisMultiplication(int a, int b) {
		int ans = 0;
		int curr = a;

		for (int i = 0; i < 8; i++) {
			if ((b & (1 << i)) > 0) {
				ans ^= curr;
			}

			if ((curr & (1 << 7)) > 0) {
				curr <<= 1;
				curr ^= irreducible;
			} else {
				curr <<= 1;
			}
			curr %= 0x100;
		}

		return ans;
	}

	public static int galoisAddition(int a, int b) {
		return a ^ b;
	}
}