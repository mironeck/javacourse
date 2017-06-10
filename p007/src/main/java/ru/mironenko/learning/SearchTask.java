package ru.mironenko.learning;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Created by nikita on 10.06.2017.
 */
public class SearchTask implements Runnable {

    /**
     * Constructs a SearchTask.
     * @param queue the queue from which to take files
     * @param keyword the keyword to look for
     */
    public SearchTask(BlockingQueue<File> queue, String keyword)
    {
        this.queue = queue;
        this.keyword = keyword;
    }

    public void run()
    {
        try
        {
            boolean done = false;
            while (!done)
            {
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY)
                {
                    queue.put(file);
                    done = true;
                }
                else search(file);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
        }
    }

    /**
     * Searches a file for a given keyword and prints all matching lines.
     * @param file the file to search
     */
    public void search(File file) throws IOException
    {
        Scanner in = new Scanner(new FileInputStream(file));
        int lineNumber = 0;
        while (in.hasNextLine())
        {
            lineNumber++;
            String line = in.nextLine();
            if (line.contains(keyword)) System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber,
                    line);
        }
        in.close();
    }

    private BlockingQueue<File> queue;
    private String keyword;
}
