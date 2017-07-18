package ru.mironenko.testtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by nikita on 13.07.2017.
 */
public class Start implements Runnable {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(Start.class);

    /**
     * Link to ParserClass
     */
    ParserClass parserClass;

    /**
     * Constructor of Start
     * @param url
     */
    public Start(String url) {
        this.parserClass = new ParserClass(url);
    }

    @Override
    public void run() {

        try {
            if(this.parserClass.checkIsTableEmpty()) {
                try {
                    this.parserClass.parseTableFromBeginingOfTheYear();
                } catch (IOException e) {
                    log.error(e.getMessage());
                } catch (ParseException e) {
                    log.error(e.getMessage());
                }
            } else {
                try {
                    this.parserClass.parseTableInOneDay();
                } catch (ParseException e) {
                    log.error(e.getMessage());
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().sleep(24*60*60*1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        closeConnection();
    }

    /**
     * Closes connection to DB
     */
    public void closeConnection() {

        this.parserClass.connectToDB.closeConnection();
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new Start("http://www.sql.ru/forum/job-offers/"));
        thread.start();

    }
}
