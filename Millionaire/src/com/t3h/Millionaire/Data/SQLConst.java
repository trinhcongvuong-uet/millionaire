package com.t3h.Millionaire.Data;

/**
 * Created by Wrongway on 11/01/2016.
 */
public class SQLConst {
    public  static final String SQL_GET_QUESTION =
            "SELECT * FROM (SELECT * FROM Question1 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question2 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question3 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question4 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question5 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question6 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question7 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question8 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question9 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question10 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question11 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question12 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question13 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question14 ORDER BY RANDOM() LIMIT 1) " +
                    " UNION " +
                    "SELECT * FROM (SELECT * FROM Question15 ORDER BY RANDOM() LIMIT 1)";
}
