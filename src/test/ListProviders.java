package test;

import java.security.Provider;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ListProviders
{
    public static void main(String[] args)
    {
    	//Security.addProvider(new BouncyCastleProvider());
        Provider[] installedProvs = Security.getProviders();

        for (int i = 0; i != installedProvs.length; i++)
        {
            System.out.print(installedProvs[i].getName());
            System.out.print(": ");
            System.out.print(installedProvs[i].getInfo());
            System.out.println();
        }
        System.out.println(installedProvs.length);
    }
}

