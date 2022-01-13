package com.app.pnrcheck;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Api {
    @Headers({
            "x-rapidapi-host:pnr-status-indian-railway.p.rapidapi.com",
            "x-rapidapi-key:d4dc795e8fmshb698dcb29bf3003p1b1715jsn4bbcebe7a88a"
    })
    @GET("{pnr}")

    Call<PnrModel> search(@Path("pnr") String pnr);
}
