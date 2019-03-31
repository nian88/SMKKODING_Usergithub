package id.smkcoding.smkpelitanusantara.appgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.smkcoding.smkpelitanusantara.appgithub.data.GithubService
import id.smkcoding.smkpelitanusantara.appgithub.data.apiRequest
import id.smkcoding.smkpelitanusantara.appgithub.data.httpClient
import id.smkcoding.smkpelitanusantara.appgithub.util.dismissLoading
import id.smkcoding.smkpelitanusantara.appgithub.util.showLoading
import id.smkcoding.smkpelitanusantara.appgithub.util.tampilToast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApiGetGithubUser()
    }
    private fun callApiGetGithubUser() {
        showLoading(applicationContext, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<GithubService>(httpClient)
        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<GithubUser>> {
            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<GithubUser>>, response: Response<List<GithubUser>>) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilGithubUser(response.body()!!)
                            else -> {
                                tampilToast(applicationContext, "")
                            }
                        }
                    else -> {
                        tampilToast(applicationContext, "")
                    }
                }
            }
        })
    }
    private fun tampilGithubUser(githubUsers: List<GithubUser>) {
        listGithubUser.layoutManager = LinearLayoutManager(this)
        listGithubUser.adapter = GithubUserAdapter(this, githubUsers) {
            val githubUser = it
            tampilToast(applicationContext, githubUser.login)
        }
    }
}
