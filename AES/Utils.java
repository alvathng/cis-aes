package AES;
import java.util.Arrays;

public class Utils {
	private static int irreducible = 0x1b;

	public static byte galoisMultiplication(byte a, byte b) {
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

		return (byte)ans;
	}

	public static byte galoisAddition(byte a, byte b) {
		return (byte)((a & 0xff) ^ (b & 0xff));
	}

	public static byte[] increment(byte[] b) {
		byte[] result = new byte[b.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = b[i];
		}

		int ans = 1;
		for (int i = result.length - 1; i >= 0; i--) {
			ans = (result[i] & 0xff) + ans;
			if (ans > 255) {
				result[i] = (byte)0xff;
				ans -= 255;
			} else {
				result[i] = (byte)ans;
				break;
			}
		}

		return result;
	}
}