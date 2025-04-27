package com.example.inventoryapp_umarasif;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ArrayList<InventoryItem> itemList;
    private InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        dbHelper = new DBHelper(this);
        itemList = dbHelper.getAllItems();

        RecyclerView recyclerView = findViewById(R.id.inventoryRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new InventoryAdapter(this, itemList, dbHelper);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addItemButton).setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Item");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_item, null);
        EditText nameInput = dialogView.findViewById(R.id.editItemName);
        EditText qtyInput = dialogView.findViewById(R.id.editItemQuantity);

        builder.setView(dialogView);
        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = nameInput.getText().toString();
            int qty = Integer.parseInt(qtyInput.getText().toString());
            dbHelper.addItem(name, qty);
            itemList.clear();
            itemList.addAll(dbHelper.getAllItems());
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
