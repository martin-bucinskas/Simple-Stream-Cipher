package happysad.test.ssc;

import happysad.ssc.impl.StreamCipher;

public class SimpleCipherTest
{
	public static void main(String[] args)
	{
		StreamCipher streamCipher = new StreamCipher();
		
		byte[] key = "avb000v0500z00a".getBytes();
		byte[] data = "1234567890".getBytes();
		
		streamCipher.init(key);
		
		System.out.print("Key: \t");
		for(byte x : key)
		{
			System.out.print(String.format("%02X ", x));
		}
		
		System.out.println();
		System.out.print("A: \t");
		for(byte x : streamCipher.getA())
		{
			System.out.print(String.format("%02X ", x));
		}
		
		System.out.println();
		System.out.print("B: \t");
		for(byte x : streamCipher.getB())
		{
			System.out.print(String.format("%02X ", x));
		}
		
		System.out.println();
		System.out.print("C: \t");
		for(byte x : streamCipher.getC())
		{
			System.out.print(String.format("%02X ", x));
		}
		
		byte[] encrypted = streamCipher.encrypt(key, data);
		
		System.out.println();
		System.out.print("Encrypted: \t");
		for(byte x : encrypted)
		{
			System.out.print(String.format("%02X ", x));
		}
		
		byte[] decrypted = streamCipher.decrypt(key, encrypted);
		
		System.out.println();
		System.out.print("Decrypted: \t");
		for(byte x : decrypted)
		{
			System.out.print(String.format("%02X ", x));
		}
		
		System.out.println();
		System.out.print("Encrypted: ");
		for(byte b : encrypted)
		{
			System.out.print((char)(b));
		}
		
		System.out.println();
		System.out.print("Decrypted: ");
		for(byte b : decrypted)
		{
			System.out.print((char)(b));
		}
	}
}
