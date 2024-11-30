package com.mallcco.andy.sustitutorio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mallcco.andy.sustitutorio.adapter.PostAdapter
import com.mallcco.andy.sustitutorio.databinding.ActivityEjercicio01Binding
import com.mallcco.andy.sustitutorio.model.Post
import com.mallcco.andy.sustitutorio.network.RetrofitClient
import kotlinx.coroutines.launch

class Ejercicio01Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Manejo de estados de carga y errores
        lifecycleScope.launch {
            try {
                showLoading(true)
                val posts = RetrofitClient.apiService.getPosts()
                binding.recyclerView.adapter = PostAdapter(posts, ::onPostClick, ::onPostLongClick)
                showLoading(false)
            } catch (e: Exception) {
                showLoading(false)
                Toast.makeText(this@Ejercicio01Activity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun onPostClick(post: Post) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("smsto:+51925137361")
            putExtra("sms_body", post.title)
        }
        startActivity(intent)
    }

    private fun onPostLongClick(post: Post) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("victor.saico@tecsup.edu.pe"))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.post_body_subject))
            putExtra(Intent.EXTRA_TEXT, post.body)
        }
        startActivity(Intent.createChooser(intent, getString(R.string.send_email)))
    }
}
