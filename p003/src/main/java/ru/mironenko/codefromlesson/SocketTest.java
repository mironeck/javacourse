package ru.mironenko.codefromlesson;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by nikita on 17.01.2017.
 */
public class SocketTest {

    public static void main(String[] args)throws IOException {

        try (Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13))
        {
            InputStream inStream = s.getInputStream();
            Scanner in = new Scanner(inStream);

            while(in.hasNext()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }
}
