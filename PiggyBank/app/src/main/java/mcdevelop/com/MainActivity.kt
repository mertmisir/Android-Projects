package mcdevelop.com


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mcdevelop.com.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun register(view: View) {

        if(binding.nameText.text.isNullOrBlank()){
            Toast.makeText(this, "Please enter your name!", Toast.LENGTH_LONG).show()
        }else{
            val intent = Intent(this,BankActivity::class.java)
            intent.putExtra("name",binding.nameText.text.toString())
            startActivity(intent)
            //println("name:" + binding.nameText.text)
            finish()
        }
    }
}