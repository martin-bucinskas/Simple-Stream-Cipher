package happysad.ssc.impl;

import happysad.ssc.Cipher;

public class StreamCipher implements Cipher
{
	private byte[] a;
	private byte[] b;
	private byte[] c;
	
	public void init(byte[] key)
	{
		a = new byte[32];
		b = new byte[64];
		c = new byte[128];
		
		for(byte i = 0; i < a.length; i++)
			a[i] = i;
		for(byte i = 0; i < b.length; i++)
			b[i] = i;
		for(int i = 0; i < c.length; i++)
			c[(byte)i] = (byte)(i);
		
		for(int i = 0; i < key.length; i++)
		{
			byte swap_space;
			
			if(key[i] <= a.length)
			{
				if(i <= a.length)
				{
					swap_space = a[key[i]];
					a[key[i]] = a[i];
					a[i] = swap_space;
				}
			}
			
			if(key[++i] <= b.length)
			{
				if(i <= b.length)
				{
					swap_space = b[key[i]];
					b[key[i]] = b[i];
					b[i] = swap_space;
				}
			}
			
			if(key[++i] <= c.length)
			{
				if(i <= c.length)
				{
					swap_space = c[key[i]];
					c[key[i]] = c[i];
					c[i] = swap_space;
				}
			}
		}
		
		for(int x = 0; x < c.length; x++)
		{
			for(int y = 0; y < b.length; y++)
			{
				for(int z = 0; z < a.length; z++)
				{
					if(a[z] > (b[y] + c[x]) && a.length > z + 1)
					{
						byte swap_space = a[z + 1];
						a[z + 1] = a[z];
						a[z] = swap_space;
						
						if(z - 1 >= 0)
						{
							swap_space = b[z - 1];
							b[z - 1] = b[z];
							b[z] = swap_space;
						}
						else
						{
							swap_space = b[a.length];
							b[a.length] = b[z];
							b[z] = swap_space;
						}
					}
					else
					{
						byte swap_space = a[0];
						a[0] = a[z];
						a[z] = swap_space;
						
						if(z - 1 >= 0)
						{
							swap_space = b[z - 1];
							b[z - 1] = b[z];
							b[z] = swap_space;
						}
						else
						{
							swap_space = b[a.length];
							b[a.length] = b[z];
							b[z] = swap_space;
						}
					}
				}
				
				if(b[y] > (c[y] + c[x]) && b.length > y + 1)
				{
					byte swap_space = b[y + 1];
					b[y + 1] = b[y];
					b[y] = swap_space;
					
					if(y - 1 >= 0)
					{
						swap_space = c[y - 1];
						c[y - 1] = c[y];
						c[y] = swap_space;
					}
					else
					{
						swap_space = c[b.length];
						c[b.length] = c[y];
						c[y] = swap_space;
					}
				}
				else
				{
					byte swap_space = b[0];
					b[0] = b[y];
					b[y] = swap_space;
					
					if(y - 1 >= 0)
					{
						swap_space = c[y - 1];
						c[y - 1] = c[y];
						c[y] = swap_space;
					}
					else
					{
						swap_space = c[b.length];
						c[b.length] = c[y];
						c[y] = swap_space;
					}
				}
			}
			
			byte swap_space = c[x];
			
			for(byte k : key)
			{
				if(swap_space >= k)
				{
					if(x < c.length - 1)
					{
						c[x] = c[x + 1];
						c[x + 1] = swap_space;
					}
					else
					{
						c[x] = c[0];
						c[0] = swap_space;
					}
				}
				else
				{
					
				}
			}
		}
	}
	
	public byte[] getA()
	{
		return a;
	}
	
	public byte[] getB()
	{
		return b;
	}
	
	public byte[] getC()
	{
		return c;
	}

	public byte[] encrypt(byte[] key, byte[] data)
	{
		byte[] cipher_text = new byte[data.length];
		
		for(int i = 0; i < data.length; i++)
		{
			if(key.length > i)
				cipher_text[i] = (byte) (data[i] ^ key[i]);
			
			for(byte x : c)
			{
				cipher_text[i] ^= x;
			}
			
			for(byte y : b)
			{
				cipher_text[i] ^= (y ^ data[i]);
			}
			
			for(byte z : a)
			{
				cipher_text[i] ^= z;
//				cipher_text[i] = (byte) (cipher_text[i] << data[i]);
			}
		}
		
		return cipher_text;
	}

	public byte[] decrypt(byte[] key, byte[] data)
	{
		
		return encrypt(key, data);
	}

}
