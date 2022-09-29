package com.bkacad.nnt.simplelistd01;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Bind Id cho view
    private EditText edtInput;
    private Button btnAdd;
    private ListView lvTodos;

    // Listview
    private List<String> dataSource;
    // Adapter mặc định
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Link biến Java -> view XML
        edtInput = findViewById(R.id.edtInput);
        btnAdd = findViewById(R.id.btnAdd);
        lvTodos = findViewById(R.id.lvTodos);
        // Chuẩn bị dữ liệu
        dataSource = new ArrayList<>();
        dataSource.add("1. Đi chợ");
        // Tạo Adapter
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, dataSource);
        // Set Adapter cho listview
        lvTodos.setAdapter(arrayAdapter);

        // bắt sự kiện onclick
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ Edit Text -> thêm vào datasource -> render listview
                String input = edtInput.getText().toString();
                if (input.isEmpty()) {
                    edtInput.setError("Hãy nhập dữ liệu");
                    return;
                }
                dataSource.add(input);
                // Báo cho adapter biết dữ liệu thay đổi
                arrayAdapter.notifyDataSetChanged();
                // Reset lai edittext
                edtInput.setText("");
            }
        });
        lvTodos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, dataSource.get(i).toUpperCase(), Toast.LENGTH_SHORT).show();
            }
        });
        // Nhấn giữ để xoá
        lvTodos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataSource.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}