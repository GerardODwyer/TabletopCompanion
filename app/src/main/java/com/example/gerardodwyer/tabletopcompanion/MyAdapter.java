package com.example.gerardodwyer.tabletopcompanion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardodwyer.tabletopcompanion.model.Item;

import java.util.List;

/**
 * Created by Gerard o dwyer on 11/02/2019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Item> itemsList;

    //Provide a reference to the views for each data item
    //Complex data items may need more than one view per item, and
    //You provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, id, item, lootedFrom, lore, type, unique;

        public MyViewHolder(View view) {
            super(view);

            description = (TextView) view.findViewById(R.id.description);
            id = (TextView) view.findViewById(R.id.id);
            item = (TextView) view.findViewById(R.id.item);
            lootedFrom = (TextView) view.findViewById(R.id.lootedFrom);
            lore = (TextView) view.findViewById(R.id.lore);
            type = (TextView) view.findViewById(R.id.type);
            unique = (TextView) view.findViewById(R.id.unique);
        }
    }

    //provide a suitable constructor
    public MyAdapter(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    //Create views (invoke layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create new view
        View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_rows, parent, false);

        return new MyViewHolder(itemView);
    }


    //Replace contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // -get element from your dataset at this position
        // -replace the contents of the view with that element
        Item item = itemsList.get(position);
        holder.description.setText(item.getDescription());
        holder.id.setText(item.getId());
        holder.item.setText(item.getItem());
        holder.lootedFrom.setText(item.getLootedFrom());
        holder.lore.setText(item.getLore());
        holder.type.setText(item.getType());
        holder.unique.setText(item.getUnique());
    }

    //Return the size of your dataset (Invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
