package com.example.prakt16;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookAddActivity extends AppCompatActivity {

    private EditText editTextName, editTextAuthor;
    private Button addButton;
    private DataBase_Helper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        addButton = findViewById(R.id.add);

        //инилизациия бд
        dbHelper = new DataBase_Helper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookToDatabase();
            }
        });
    }

    private void addBookToDatabase() {
        String bookName = editTextName.getText().toString().trim();
        String bookAuthor = editTextAuthor.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = dbHelper.addBook(bookName, bookAuthor);

        if (result > 0) {
            Toast.makeText(this, "Книга добавлена", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookAddActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Ошибка добавления книги", Toast.LENGTH_SHORT).show();
        }
    }
}