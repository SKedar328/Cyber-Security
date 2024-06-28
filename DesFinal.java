import java.io.*;
public class DesFinal {
	public static int key[] = {
		// 1, 0, 1, 0, 0, 0, 0, 0, 1, 0
        //0,0,1,0,0,1,0,1,1,1
		1,1,1,1,0,1,0,1,1,1
	}; 
	public static int P10[] = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };
	public static int P8[] = { 6, 3, 7, 4, 8, 5, 10, 9 };
	public static int key1[] = new int[8];
	public static int key2[] = new int[8];

	int[] IP = { 2, 6, 3, 1, 4, 8, 5, 7 };
	int[] EP = { 4, 1, 2, 3, 2, 3, 4, 1 };
	int[] P4 = { 2, 4, 3, 1 };
	int[] IP_inv = { 4, 1, 3, 5, 7, 2, 8, 6 };

	int[][] S0 = { { 1, 0, 3, 2 },
				{ 3, 2, 1, 0 },
				{ 0, 2, 1, 3 },
				{ 3, 1, 3, 2 } };
	int[][] S1 = { { 0, 1, 2, 3 },
				{ 2, 0, 1, 3 },
				{ 3, 0, 1, 0 },
				{ 2, 1, 0, 3 } };

    void keygen(int p8[],int p10[],int key[],int key1[],int key2[]){
        int p10res[]=new int[key.length];
        for(int i=0;i<10;i++){
            p10res[i]=key[p10[i]-1];
        }
        leftshift(p10res,0,5);
        leftshift(p10res,5,10);
        for(int i=0;i<p8.length;i++){
            key1[i]=p10res[p8[i]-1];
        }
        // return key1;
        leftshift(p10res,0,5);
        leftshift(p10res,5,10);
        leftshift(p10res,0,5);
        leftshift(p10res,5,10);
        for(int i=0;i<p8.length;i++){
            key2[i]=p10res[p8[i]-1];
        }
        print(key1);
        System.out.println();
        print(key2);
    }
    public static void leftshift(int arr[],int start,int n){
        int val=arr[start];
        for(int i=start;i<n-1;i++){
          arr[i]=arr[i+1];
        }
        arr[n-1]=val;
    }
    public static void print(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    //encryption
	int[] encryption(int[] plaintext)
	{
		int[] arr = new int[8];
		for (int i = 0; i < 8; i++) {
			arr[i] = plaintext[IP[i] - 1];
		}
		int[] arr1 = function_(arr, key1);

		int[] after_swap = swap(arr1, arr1.length / 2);

		int[] arr2 = function_(after_swap, key2);

		int[] ciphertext = new int[8];

		for (int i = 0; i < 8; i++) {
			ciphertext[i] = arr2[IP_inv[i] - 1];
		}
		return ciphertext;
	}
	String binary_(int val)
	{
		if (val == 0)
			return "00";
		else if (val == 1)
			return "01";
		else if (val == 2)
			return "10";
		else
			return "11";
	}
//commmon function which will do the common functionalities 
//for encrypting and decrypting data
	int[] function_(int[] ar, int[] key_)
	{
		int[] l = new int[4];
		int[] r = new int[4];

		for (int i = 0; i < 4; i++) {
			l[i] = ar[i];
			r[i] = ar[i + 4];
		}

		int[] ep = new int[8];

		for (int i = 0; i < 8; i++) {
			ep[i] = r[EP[i] - 1];
		}

		for (int i = 0; i < 8; i++) {
			ar[i] = key_[i] ^ ep[i];
		}

		int[] l_1 = new int[4];
		int[] r_1 = new int[4];

		for (int i = 0; i < 4; i++) {
			l_1[i] = ar[i];
			r_1[i] = ar[i + 4];
		}

		int row, col, val;

		row = Integer.parseInt("" + l_1[0] + l_1[3], 2);
		col = Integer.parseInt("" + l_1[1] + l_1[2], 2);
		val = S0[row][col];
		String str_l = binary_(val);

		row = Integer.parseInt("" + r_1[0] + r_1[3], 2);
		col = Integer.parseInt("" + r_1[1] + r_1[2], 2);
		val = S1[row][col];
		String str_r = binary_(val);

		int[] r_ = new int[4];
		for (int i = 0; i < 2; i++) {
			char c1 = str_l.charAt(i);
			char c2 = str_r.charAt(i);
			r_[i] = Character.getNumericValue(c1);
			r_[i + 2] = Character.getNumericValue(c2);
		}
		int[] r_p4 = new int[4];
		for (int i = 0; i < 4; i++) {
			r_p4[i] = r_[P4[i] - 1];
		}

		for (int i = 0; i < 4; i++) {
			l[i] = l[i] ^ r_p4[i];
		}

		int[] output = new int[8];
		for (int i = 0; i < 4; i++) {
			output[i] = l[i];
			output[i + 4] = r[i];
		}
		return output;
	}
	// swapping purpose
	int[] swap(int[] array, int n)
	{
		int[] l = new int[n];
		int[] r = new int[n];

		for (int i = 0; i < n; i++) {
			l[i] = array[i];
			r[i] = array[i + n];
		}

		int[] output = new int[2 * n];
		for (int i = 0; i < n; i++) {
			output[i] = r[i];
			output[i + n] = l[i];
		}
		return output;
	}
	int[] decryption(int[] ar)
	{
		int[] arr = new int[8];

		for (int i = 0; i < 8; i++) {
			arr[i] = ar[IP[i] - 1]; 
		}

		int[] arr1 = function_(arr, key2);

		int[] after_swap = swap(arr1, arr1.length / 2);

		int[] arr2 = function_(after_swap, key1);

		int[] decrypted = new int[8];

		for (int i = 0; i < 8; i++) {
			decrypted[i] = arr2[IP_inv[i] - 1];
		}

		return decrypted;
	}

	public static void main(String[] args)
	{

		DesFinal obj = new DesFinal();

		obj.keygen(P8,P10,key,key1,key2); 
		int[] plaintext = {
			//1, 0, 0, 1, 0, 1, 1, 1
			0,1,0,0,1,0,1,0
		}; 
		System.out.println();
		System.out.println("Your plain Text is :");
		for (int i = 0; i < 8; i++) 
			System.out.print(plaintext[i] + " ");

		int[] ciphertext = obj.encryption(plaintext);

		System.out.println();
		System.out.println(
			"Your cipher Text is :"); 
		for (int i = 0; i < 8; i++)
			System.out.print(ciphertext[i] + " ");

		int[] decrypted = obj.decryption(ciphertext);

		System.out.println();
		System.out.println(
			"Your decrypted Text is :"); 
		for (int i = 0; i < 8; i++)
			System.out.print(decrypted[i] + " ");
	}
}
