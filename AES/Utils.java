package AES;

public class Utils {
	/* galois GF(2^8) irreducible polynomial */
	private static int irreducible = 0x1b;


	/**
	 * do galois multiplication
	 * @param a
	 * @param b
	 * @return
	 */
	public static byte galoisMultiplication(byte a, byte b) {
		int ans = 0;
		int curr = a & 0xff;

		for (int i = 0; i < 8; i++) {
			if (((b & (1 << i)) & 0xff) > 0) {
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

		return (byte)ans;
	}

	/**
	 * do galois addition
	 * @param a
	 * @param b
	 * @return byte
	 */
	public static byte galoisAddition(byte a, byte b) {
		return (byte)((a & 0xff) ^ (b & 0xff));
	}

	/**
	 * increment byte array by one
	 * @param b
	 * @return byte[]
	 */
	public static byte[] increment(byte[] b) {
		byte[] result = new byte[b.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = b[i];
		}

		int ans = 1;
		for (int i = result.length - 1; i >= 0; i--) {
			ans = (result[i] & 0xff) + ans;
			if (ans > 255) {
				result[i] = (byte)0x00;
				ans -= 255;
			} else {
				result[i] = (byte)ans;
				break;
			}
		}

		return result;
	}

	/**
	 * convert hexadecimal String to byte[]
	 * @param str
	 * @return byte[]
	 */
	public static byte[] hexToByte(String str) {
		byte[] b = new byte[(str.length() / 2)];
		for (int i = 0; i < (str.length() / 2); i++) {
			int dec = Integer.parseInt("" + str.charAt(2 * i) + str.charAt(2 * i + 1), 16);
			b[i] = (byte)dec;
		}
		return b;
	}
}