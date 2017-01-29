package happysad.test.ssc;

import happysad.ssc.impl.SimpleStreamCipher;

public class LookupPermutationTest
{
	public static void main(String[] args)
	{
		SimpleStreamCipher ssc = new SimpleStreamCipher();
		
		byte[] key = "ABC4567891011121314151617181920212223242526272829303132".getBytes();
		byte[] data = "Hello my name is Martin.".getBytes();
		
		ssc.init(key);
		
		System.out.print("Data: ");
		
		for(byte b : data)
		{
			System.out.print(String.format("%02X", b) + " ");
		}
		
		System.out.println();
		
		StringBuilder sb = new StringBuilder();
		
		byte[] encr = ssc.encrypt(key, data);
		
		System.out.print("Encr: ");
		
		for(byte b : encr)
		{
			sb.append((char)(b));
			System.out.print(String.format("%02X", b) + " ");
		}
		
		System.out.println();
		
		byte[] decr = ssc.encrypt(key, encr);
		
		System.out.print("Decr: ");
		
		for(byte b : decr)
		{
			System.out.print(String.format("%02X", b) + " ");
		}
		
		System.out.println();
		
		System.out.println("Encrypted Text: " + sb.toString());
		
//		ssc.printLookup();
	}
}
