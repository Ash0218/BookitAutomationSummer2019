package com.bookit.utilities; // 020720

public interface Endpoints {
    String DELETE_STUDENT = "/api/students/{id}"; // 1
    String ADD_STUDENT = "/api/students/student"; // 2
    String GET_ALL_BATCHES = "/api/batches"; // 3
    // there is no public, static, final in #1-3 bc all interface
    //  automatically has public, static, final by default. So, they have String only.
}
