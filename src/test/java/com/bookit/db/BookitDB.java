package com.bookit.db; // 020720



import com.bookit.utilities.DBUtility;

import java.util.Map;

public class BookitDB {

    public String getUserEmailById(int userId){ // 1
        String query = "SELECT email FROM users WHERE id = " + userId; // 2
        return DBUtility.getCellValue(query).toString(); // 3
        // this method will get(return) user email based on user id.
    }
}
