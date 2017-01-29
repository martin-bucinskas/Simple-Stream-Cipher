package happysad.ssc.impl;

import happysad.ssc.Cipher;

public class SimpleStreamCipher implements Cipher
{
	private int[][] lookup;
	
	public SimpleStreamCipher()
	{
		lookup = new int[0x200][0x200];
	}
	
	/*
	 * (non-Javadoc)
	 * @see happysad.ssc.Cipher#init(byte[])
	 * 
	 * Fill a lookup table with 0x00000 - 0x40000
	 * 
	 * Iterate through key, if lookup[x][y] mod key[z] > key[z] & 0xA2, swap lookup.
	 * This would achieve a permutation in the lookup table.
	 */
	public void init(byte[] key)
	{
		for(int x = 0; x < 0x200; x++)
		{
			for(int y = 0; y < 0x200; y++)
			{
				lookup[x][y] = (x + 1) * (y + 1);
				
				for(int z = 0; z < key.length; z++)
				{
					if(lookup[x][y] % key[z] < (key[z] & 0xA2))
					{
						if(lookup[x].length - 1 >= y + 1)
						{
							int tmp = lookup[x][y + 1];
							lookup[x][y + 1] = lookup[x][y];
							lookup[x][y] = tmp;
						}
						else
						{
							int tmp = lookup[x][0];
							lookup[x][0] = lookup[x][y];
							lookup[x][y] = tmp;
						}
					}
				}
			}
		}
	}
	
	public void printLookup()
	{
		for(int x = 0; x < 512; x++)
		{
			System.out.print("Lookup[" + x + "]");
			for(int y = 0; y < 512; y++)
			{
				System.out.print(" " + lookup[x][y]);
			}
			System.out.println();
		}
	}
	
	public byte[] encrypt(byte[] key, byte[] data)
	{
		int a = key.length / 3;
		a = Math.round(a);
		
		byte x = key[a];
		byte y = key[a + a];
		byte z = key[a + a + a];
		
		if((x + y + z) % 2 == 0)
		{
			for(int i = 0; i < data.length; i++)
			{
				data[i] ^= lookup[x][y];
				data[i] ^= z;
				z ^= x;
			}
		}
		else
		{
			for(int i = 0; i < data.length; i++)
			{
				z ^= x;
				data[i] ^= z;
				data[i] ^= lookup[x][y];
				x++;
				
			}
		}
		
		return data;
	}
	
	public byte[] decrypt(byte[] key, byte[] data)
	{
		return encrypt(key, data);
	}
}
