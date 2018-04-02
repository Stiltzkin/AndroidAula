package com.example.kawag.appmaster2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonAdd;
    Spinner spinnerGenres;

    DatabaseReference databaseLivros;

    ListView listViewLivros;

    List<Livro> listLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseLivros = FirebaseDatabase.getInstance().getReference("livros");

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAdd = (Button) findViewById(R.id.buttonAddLivro);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenres);

        listViewLivros = (ListView) findViewById(R.id.listViewLivros);

        listLivro = new ArrayList<>();
        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addLivro();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseLivros.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listLivro.clear();
                for (DataSnapshot livroSnapshot : dataSnapshot.getChildren()) {
                    Livro livro = livroSnapshot.getValue(Livro.class);

                    listLivro.add(livro);
                }

                LivroList adapter = new LivroList(MainActivity.this, listLivro);
                listViewLivros.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addLivro() {
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)) {

            String id = databaseLivros.push().getKey();
            Livro livro = new Livro(id, name, genre);

            databaseLivros.child(id).setValue(livro);

            Toast.makeText(this, "Livro adicionado.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Entre com Nome", Toast.LENGTH_LONG).show();
        }
    }
}
