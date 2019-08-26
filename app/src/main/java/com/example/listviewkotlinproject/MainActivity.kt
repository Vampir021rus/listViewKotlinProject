package com.example.listviewkotlinproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val baseURL = "https://rawgit.com/startandroid/data/master/messages/"
    var listmassge: List<Massage>? = null

    private var adapter:MsgAdapter? = null

    private lateinit var listView: ListView
    private lateinit var recyclerView:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMessage(this)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        listView = findViewById(R.id.listview)
        //создаем адаптер что бы потом поместить его в лист
        // для этого используем свой переопределенный адаптер


    }


    fun getMessage(context: Context) {
        var retrofit = Retrofit.Builder()
            //добавляем базывй адрес
            .baseUrl(baseURL)
            // создаем json конвертер
            .addConverterFactory(GsonConverterFactory.create())
            //билдим
            .build()

        //передаем в метод create класс интерфейса с методами и получаем реализацию API от Retrofit
        var api: MassageAPI = retrofit.create(MassageAPI::class.java)
        var messages = api.getMassage()

        //с помощью асиннхронного метода делаем запрос
        messages.enqueue(object : Callback<List<Massage>> {
            //метод в котором пришел ответ, ответ может быть удачным или создержать коды ошибок типа 404
            override fun onResponse(call: Call<List<Massage>>, response: Response<List<Massage>>) {
                //проверка на точ то ответ пришел удачный без всяких косяков типа 404 и тд
                if (response.isSuccessful) {
                    //если я правильно понял в response хранится весь ответ в формате json
                    // но чет не очень понял что хранить call
                    var str: String = ""
                    var listMsg = response.body()
                    var length = listMsg!!.size

                    if (listMsg != null)
                    {
                        listmassge = listMsg
                        adapter = MsgAdapter(context as MainActivity, listmassge!!)
                    //    listView.adapter = adapter

                        recyclerView.adapter =RecyclerAdapter(context, listmassge!! )
                    }




                    Log.d("Good", "response " + response.body());
                } else {
                    Log.d("Error", "Responce code" + response.code())
                }
            }

            //метод если есть какой то косяк с запросом
            override fun onFailure(call: Call<List<Massage>>, t: Throwable) {
                Log.v("Error2", t.toString())
            }
        })
    }
}
