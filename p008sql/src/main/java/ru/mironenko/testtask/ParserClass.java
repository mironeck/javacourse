package ru.mironenko.testtask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by nikita on 09.07.2017.
 */

/**
 * Parsers class
 */
public class ParserClass {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ParserClass.class);
    /**
    *Link to Document.
     */
    Document doc;

    /**
     * String to save url.
     */
    String url;

    /**
     * Link to ConnectToDB class
     */
    ConnectToDB connectToDB;

    /**
     * Constructor of the ParserClass. Creates instances of Document, ConnectToDB.
     * @param url
     */
    public ParserClass(String url) {
        this.url = url;
        try {
            this.doc = Jsoup.connect(this.url).get();
            this.connectToDB = new ConnectToDB();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Parses table of vacancies from the beginning of the year.
     * @throws IOException
     * @throws ParseException
     */
    public void parseTableFromBeginingOfTheYear() throws IOException, ParseException {

        int i = 1;
        boolean flag = true;
        while(flag) {
            this.doc = Jsoup.connect(this.url + i).get();
            Element table = this.doc.select("table.forumTable").first();
            Elements rows = table.select("tr");
            for (int j = 1; j < rows.size(); j++) {
                Element row = rows.get(j);
                Elements cols = row.select("td");
                String position = cols.get(1).text(); //vacancy
                String day = cols.get(5).text(); //day of vacancy's creation
                if (position.toLowerCase().contains("java")) {
                    if(position.toLowerCase().contains("script")) {
                        break;
                    } else {
                        Date date = convertTodayOrYesterdayToDate(day);
                        if(!isYearBegin(date)) {
                            flag = false;
                            break;
                        } else {
                            System.out.println(position + " " + date);
                            this.connectToDB.addVacancyToDB(position, date);
                        }
                    }
                }
            }
            i++;
        }
    }

    /**
     * Parses table of vacancies in one day
     * @throws ParseException
     * @throws IOException
     */
    public void parseTableInOneDay() throws ParseException, IOException {

        this.doc = Jsoup.connect(this.url + 1).get();
        Element table = this.doc.select("table.forumTable").first();
        Elements rows = table.select("tr");
        for (int j = 1; j < rows.size(); j++) {
            Element row = rows.get(j);
            Elements cols = row.select("td");
            String position = cols.get(1).text(); //vacancy
            String day = cols.get(5).text(); //day of vacancy's creation
            if (position.contains("Java")) {
                if(position.toLowerCase().contains("script")) {
                    break;
                } else {
                    Date date = convertTodayOrYesterdayToDate(day);
                    this.connectToDB.addVacancyToDB(position, date);
                }
            }
        }
    }

    /**
     * Checks of table of vacancies in DB is empty
     * @return true if empty
     */
    public boolean checkIsTableEmpty() {
        return this.connectToDB.isTableEmpty();
    }

    /**
     * Convert "сегодня" and "вчера" into date
     * @param day string implementation of date
     * @return date
     * @throws ParseException
     */
    private Date convertTodayOrYesterdayToDate(String day) throws ParseException {

        String stringResult = null;
        Date dateResult;
        String today = "сегодня";
        String yesterday = "вчера";
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy, HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yy");

        if(day.contains(today)) {

            String mmss = day.substring(9, 14);
            Date date = new Date();
            stringResult = dateFormat.format(date) + ", " + mmss;
            dateResult = formatter.parse(stringResult);

        } else if(day.contains(yesterday)) {

            String mmss = day.substring(7, 12);
            Date date = yesterday();
            stringResult = dateFormat.format(date) + ", " + mmss;
            dateResult = formatter.parse(stringResult);
        } else {

            dateResult = formatter.parse(day);
        }

        return dateResult;
    }

    /**
     * Creates yesterday date
     * @return
     */
    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * Checks is year begin
     * @param dateInput
     * @return true if year begin
     */
    private boolean isYearBegin(Date dateInput) {
        boolean result = true;

        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(dateInput);
        if(year.equals("2016")) {
            result = false;
        }
        return result;
    }


//    public static void main(String[] args) throws IOException, ParseException {
//
//        ParserClass psr = new ParserClass("http://www.sql.ru/forum/job-offers/");
//
//        psr.parseTableFromBeginingOfTheYear();
//
//    }
}
