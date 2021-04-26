package com.satanbakespancakes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Main {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = new PrintStream(System.out);
    public static void main(String[] args) {
	// write your code here
        String s = in.next().replace("0x", "").replace("L","");
        long p = Long.parseLong(s, 16);

        out.println("[DEBUG]: Your Long is " + p);

        CRC32 crc32 = new CRC32();

        File f = new File("passwds.txt");
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                boolean flag = true;
                String c = sc.nextLine().replace("\n", "");
                for (int i = 1; i< 10000; i++){
                    String tmp = c+Integer.toString(i);
                    out.println("[DEBUG]: Current word is " + tmp);
                    crc32.update(tmp.getBytes(StandardCharsets.UTF_8));
                    long currentValue = crc32.getValue();
                    out.println("[DEBUG]: Current hash is " + currentValue);
                    if (currentValue == p){
                        out.println("Your passwd is " + tmp);
                        flag = false;
                        break;
                    }
                    crc32.reset();
                }
                if (flag == false)
                    break;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
