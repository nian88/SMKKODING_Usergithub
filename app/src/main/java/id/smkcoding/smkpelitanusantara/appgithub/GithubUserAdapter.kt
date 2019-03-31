package id.smkcoding.smkpelitanusantara.appgithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.github_user_item.*

class GithubUserAdapter(private val context: Context, private val items: List<GithubUser>,
                        private val listener: (GithubUser) -> Unit) :
    RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.github_user_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val context: Context, override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindItem(item: GithubUser, listener: (GithubUser) -> Unit) {
            txtUsername.text = item.login
            txtUserRepo.text = item.reposUrl
            Glide.with(context).load(item.avatarUrl).into(imgUser)
            containerView.setOnClickListener { listener(item) }
        }

    }


}