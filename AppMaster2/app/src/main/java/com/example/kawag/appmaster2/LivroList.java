package com.example.kawag.appmaster2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LivroList extends ArrayAdapter<Livro>{

    private Activity context;
    private List<Livro> livroList;

    public LivroList(Activity context, List<Livro> livroList){
        super(context, R.layout.list_layout, livroList);
        this.context = context;
        this.livroList = livroList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName = (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView)listViewItem.findViewById(R.id.textViewGenre);

        Livro livro = livroList.get(position);

        textViewName.setText(livro.getLivroName());
        textViewGenre.setText(livro.getLivroGenre());

        return listViewItem;
    }
}
