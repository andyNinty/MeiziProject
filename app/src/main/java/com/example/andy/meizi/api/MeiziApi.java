package com.example.andy.meizi.api;

import com.example.andy.meizi.meizifragment.model.MeiziResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * meizi api
 * Created by andy on 17-4-21.
 */

public interface MeiziApi {
    /**
     * 妹子api
     *
     * @param number 每页显示数量
     * @param page   页数
     * @return meizi
     */
    @GET("data/福利/{number}/{page}")
    Observable<MeiziResponse> getMeizi(@Path("number") int number, @Path("page") int page);

}
