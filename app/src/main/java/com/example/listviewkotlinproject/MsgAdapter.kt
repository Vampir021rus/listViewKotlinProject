package com.example.listviewkotlinproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MsgAdapter(
    private val context: MainActivity,
    private val datasourse: List<Massage>
) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //В первом параметре указывается идентификатор ресурса разметки, который мы собираемся надуть.
        // Во втором параметре указывается корневой компонент, к которому нужно присоединить надутые объекты.
        // В третьем параметре (если он используется) указывается, нужно ли присоединять надутые объекты к корневому элементу


        val rowView = inflater.inflate(R.layout.list_item_massage, parent, false)

        val msgID = rowView.findViewById(R.id.massageID) as TextView
        val msgImageView = rowView.findViewById(R.id.myimageView) as ImageView

        val massage = getItem(position)as Massage
        msgID.text = massage.id.toString()
        //с помощью библиотеки пикасо загружаем картинку с интеа в асинхронном потоке, + указывае базовое изображение если загрузка будет долгой
        Log.d("Good", massage.image)
        //так картинка грузится вмоем случае исходники картинок с запроса пустые(
        //Picasso.with(context).load("https://avatars.mds.yandex.net/get-pdb/1734828/b6319bf0-f40d-46fd-85f7-0049836afaad/s1200").placeholder(R.mipmap.ic_launcher).into(msgImageView)
        if(massage.image !="")
            Picasso.with(context).load(massage.image).placeholder(R.mipmap.ic_launcher).into(msgImageView)


        return rowView
    }

    override fun getItem(position: Int): Any {
        return datasourse[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datasourse.size
    }

}