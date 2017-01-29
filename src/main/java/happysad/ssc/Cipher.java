package happysad.ssc;

public interface Cipher
{
	public void init(byte[] key);
	
	public byte[] encrypt(byte[] key, byte[] data);
	
	public byte[] decrypt(byte[] key, byte[] data);
}
