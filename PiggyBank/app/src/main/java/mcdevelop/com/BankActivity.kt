package mcdevelop.com

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import mcdevelop.com.databinding.ActivityBankBinding


class BankActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBankBinding
    private var totalAmount : Double = 0.0
    lateinit var sharedPreferences : SharedPreferences
    private lateinit var totalPreferences : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get intent
        val username = intent.getStringExtra("name").toString()
        //println("username: $username")
        binding.nameTv.text = "Welcome back $username"

        //SharedPreferences Initialization:
        sharedPreferences = this.getSharedPreferences("mcdevelop.com", MODE_PRIVATE)

        totalPreferences = sharedPreferences.getString("amount","0.0").toString()
        binding.totalView.text = "$totalPreferences"
        totalAmount = totalPreferences.toDouble() // her program basladiginda kaldigin yerden devam et!
        //println("total preferences: $totalPreferences")
    }

     fun deposit(view : View){
         if(!binding.moneyTv.text.isNullOrBlank()){
             totalAmount += binding.moneyTv.text.toString().toDouble()
             binding.totalView.text = "Amount: $totalAmount"

             //saving the totalview value onto my shared preferences object
             sharedPreferences.edit().putString("amount",totalAmount.toString()).apply()
             binding.moneyTv.text.clear()
         }
    }

     fun withdraw(view : View){
         if (!binding.moneyTv.text.isNullOrBlank()) {
             val withdrawalAmount : Double = binding.moneyTv.text.toString().toDouble()
             if(!binding.moneyTv.text.startsWith('.') && binding.moneyTv.text.isNotEmpty()
                 && totalAmount >= 0.0 && withdrawalAmount <= totalAmount ){

                 totalAmount -= withdrawalAmount
                 binding.totalView.text = "Amount: $totalAmount"
                 sharedPreferences.edit().putString("amount",totalAmount.toString()).apply()
             }
             binding.moneyTv.text.clear()
         }
    }
}