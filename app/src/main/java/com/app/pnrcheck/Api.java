package com.app.pnrcheck;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Api {
    @Headers({
            "",
            ""
    })
    @GET("{pnr}")

    Call<PnrModel> search(@Path("pnr") String pnr);
}
