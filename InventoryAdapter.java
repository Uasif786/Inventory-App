package com.example.inventoryapp_umarasif;

import android.app.AlertDialog;
import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {

    private final ArrayList<InventoryItem> items;
    private final DBHelper dbHelper;

    public InventoryAdapter(Context context, ArrayList<InventoryItem> items, DBHelper dbHelper) {
        this.items = items;
        this.dbHelper = dbHelper;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQty;
        Button editBtn, deleteBtn;

        public ViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.itemName);
            itemQty = view.findViewById(R.id.itemQuantity);
            editBtn = view.findViewById(R.id.btnEdit);
            deleteBtn = view.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    public InventoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryItem item = items.get(position);
        holder.itemName.setText(item.getName());
        holder.itemQty.setText("Qty: " + item.getQuantity());

        holder.editBtn.setOnClickListener(v -> {
            Context context = v.getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Edit Item");

            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_item, null);
            EditText nameInput = dialogView.findViewById(R.id.editItemName);
            EditText qtyInput = dialogView.findViewById(R.id.editItemQuantity);

            nameInput.setText(item.getName());
            qtyInput.setText(String.valueOf(item.getQuantity()));

            builder.setView(dialogView);
            builder.setPositiveButton("Save", (dialog, which) -> {
                String newName = nameInput.getText().toString();
                int newQty = Integer.parseInt(qtyInput.getText().toString());
                dbHelper.updateItem(item.getId(), newName, newQty);
                items.set(position, new InventoryItem(item.getId(), newName, newQty));
                notifyItemChanged(position);
            });

            builder.setNegativeButton("Cancel", null);
            builder.show();
        });

        holder.deleteBtn.setOnClickListener(v -> {
            dbHelper.deleteItem(item.getId());
            items.remove(position);
            notifyItemRemoved(position);
        });
    }

    public int getItemCount() {
        return items.size();
    }
}

