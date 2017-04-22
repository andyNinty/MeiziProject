package com.example.andy.bottomnavbar.meizifragment.model;

import java.util.List;

/**
 * api返回字段
 *
 * @SerializedName("results") 若考虑到命名的有意义, 可以使用gson的注解
 * Created by andy on 17-4-21.
 */

public class MeiziResponse {
    private boolean error;
    private List<Meizi> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Meizi> getResults() {
        return results;
    }

    public void setResults(List<Meizi> results) {
        this.results = results;
    }
}
