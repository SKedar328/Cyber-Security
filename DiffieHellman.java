import java.util.*;
class DiffieHellman {
    // Power function to return value of a ^ b mod P
    private static long powermod(long a, long b, long p)
    {
        if (b == 1)
            return a;
        else
            return (((long)Math.pow(a, b)) % p);
    }
    public static void main(String[] args)
    {
        long x, y,ka, kb;
 
        // Both the persons will be agreed upon the
        // public keys G and P
        Scanner sc=new Scanner(System.in);
         
        // A prime number P is taken
        System.out.println("Enter the value of P");
        long P=sc.nextLong();
        System.out.println("The value of P:" + P);
 
        // A primitive root for P, G is taken
        System.out.println("Enter the value of G");
        long G=sc.nextLong();
        System.out.println("The value of P:" + G);
 
 
        // Alice will choose the private key a
        System.out.println("Enter the private key for Alice");
        int a=sc.nextInt();
        //a = 4;
        System.out.println("The private key a for Alice:"
                           + a);
 
        // Gets the generated key
        x = powermod(G, a, P);
 
        System.out.println("Enter the private key for Bob");
        int b=sc.nextInt();
        //b = 3;
        System.out.println("The private key b for Bob:"
                           + b);
 
        // Gets the generated key
        y = powermod(G, b, P);
 
       
        ka = powermod(y, a, P); // Secret key for Alice
        kb = powermod(x, b, P); // Secret key for Bob
 
        System.out.println("Secret key for the Alice is:"
                           + ka);
        System.out.println("Secret key for the Bob is:"
                           + kb);
    }
}
 
