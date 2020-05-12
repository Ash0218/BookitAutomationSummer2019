package com.bookit.db; // 020720



import com.bookit.utilities.DBUtility;

import java.util.Map;

/*
The idea of this class is very similar to the page class:
get actual results, but not from the UI, from the (QA) data base storage.

Here, I will write methods that return some information from the DB based on queries.
 */

public class BookitDB {

    /*
    It returns users email based on id.
     */

    public String getUserEmailById(int userId){ // 1
        // SQL (씨퀄) query to get email based on user id.
        String query = "SELECT email FROM users WHERE id = " + userId; // 2
        return DBUtility.getCellValue(query).toString(); // 3
        // this method will get(return) user email based on user id.
    }
}
