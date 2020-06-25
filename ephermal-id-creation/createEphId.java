import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class createEphId {
	private static final String BROADCAST_KEY= "broadcast key";
	private static final int NUM_EPOCHS_PER_DAY = 24;
	//Length of EphID in bytes
	private static final int LENGTH_EPHID = 16;



	public static byte[] generateNewDayKey() {
		SecureRandom random = new SecureRandom();
		byte new_key[] = new byte[32];
		random.nextBytes(new_key);
		return new_key;

	}

	public static byte[] generateNextDayKey(byte[] current_day_key) {
		return getSHAencryptedKey(current_day_key);
	}

	public static byte[] getSHAencryptedKey(byte[] day_key) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		byte[] SK= md.digest(day_key);
		return SK;
	}


	//entry function
	public static List<byte[]> getEphIDsForDay(byte[] current_day_key) {

		byte[] stream_key = calculateHMAC(current_day_key);

		byte[] cipher_stream = generateCipherStream(stream_key);
		List<byte[]> ephIds = new ArrayList<>();
		
		for(int i=0;i<cipher_stream.length;i+=LENGTH_EPHID) {
			byte[] ephId = Arrays.copyOfRange(cipher_stream, i, i+LENGTH_EPHID);
			ephIds.add(ephId);
		}
		return ephIds;
	}

	private static byte[] generateCipherStream(byte[] stream_key) {


		Cipher cipher=null;
		try {
			cipher = Cipher.getInstance("AES/CTR/NoPadding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		byte[] nonce = new byte[96 / 8];
		byte[] iv = new byte[128 / 8];
		System.arraycopy(nonce, 0, iv, 0, nonce.length);

		Key keySpec = new SecretKeySpec(stream_key, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		try {
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		byte[] allZero = new byte[LENGTH_EPHID * NUM_EPOCHS_PER_DAY];
		byte[] ciphertext = null;
		try {
			ciphertext = cipher.doFinal(allZero);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error while generating cipher stream");
			e.printStackTrace();
		}
		System.out.println(ciphertext);
		return ciphertext;
	}

	public static byte[] getEphID(byte[] day_key, String algorithm) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		byte[] SK= md.digest(day_key);
		byte[] PRF= calculateHMAC(SK);
		//String cipher = getCipher(PRF,"FIXED_MESSAGE_123333255464326432644342");


		RC4 rc = new RC4("FIXED_MESSAGE".getBytes());
		byte[] cipher = rc.encrypt(PRF);
		System.out.println("Cipher: "+cipher+ " LEN: "+cipher.length);
		return PRF;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	//PRF function
	static public byte[] calculateHMAC(byte[] current_day_key) {
		byte[] BK = BROADCAST_KEY.getBytes();//"FIXED_MESSAGE".getBytes();

		byte[] hmacSha256 = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKeySpec = new SecretKeySpec(current_day_key, "HmacSHA256");
			mac.init(secretKeySpec);
			hmacSha256 = mac.doFinal(BK);
		} catch (Exception e) {
			throw new RuntimeException("Failed to calculate hmac-sha256", e);
		}
		return hmacSha256;
	}


	public static void main(String[] args) {

		Security.setProperty("crypto.policy", "unlimited");

		//String algorithm = "SHA-256"; // if you perfer SHA-2
		//String algorithm = "SHA3-256";

		//       String pText = "Hello World";
		/*String pText = "61e07c07e5678fcfa1a9525069d2af9a8386ee36f9394d736537f6fdceeabbca";
		System.out.println(String.format(OUTPUT_FORMAT, "Input (string)", pText));
		System.out.println(String.format(OUTPUT_FORMAT, "Input (length)", pText.length()));

		byte[] shaInBytes = createEphId.getEphID(pText.getBytes(UTF_8), algorithm);
		System.out.println(String.format(OUTPUT_FORMAT, algorithm + " (hex) ", bytesToHex(shaInBytes)));
		// fixed length, 32 bytes, 256 bits.
		System.out.println(String.format(OUTPUT_FORMAT, algorithm + " (length)", shaInBytes.length));*/
		byte[] current_day_key = generateNewDayKey();
		getEphIDsForDay(current_day_key);


	}
}
