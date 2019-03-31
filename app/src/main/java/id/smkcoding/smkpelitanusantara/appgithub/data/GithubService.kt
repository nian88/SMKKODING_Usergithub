package id.smkcoding.smkpelitanusantara.appgithub.data

import id.smkcoding.smkpelitanusantara.appgithub.GithubUser
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("users")
    fun getUsers(): Call<List<GithubUser>>
}