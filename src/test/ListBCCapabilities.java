package test;

import java.security.Provider;

import java.security.Security;
import java.util.Iterator;
public class ListBCCapabilities
{
    public static void main(
        String[]    args)
    {
    	java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Provider	provider = Security.getProvider("BC");
        
        Iterator  it = provider.keySet().iterator();
        
        while (it.hasNext())
        {
            String	entry = (String)it.next();
            
            // this indicates the entry refers to another entry
            
            if (entry.startsWith("Alg.Alias."))
            {
                entry = entry.substring("Alg.Alias.".length());
            }
            
            String  factoryClass = entry.substring(0, entry.indexOf('.'));
            String  name = entry.substring(factoryClass.length() + 1);

            System.out.println(factoryClass + ": " + name);
            
        }
    }
}

