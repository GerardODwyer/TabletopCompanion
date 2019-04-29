package com.example.gerardodwyer.tabletopcompanion;


import android.os.CpuUsageInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardodwyer.tabletopcompanion.model.Enemy;
import com.example.gerardodwyer.tabletopcompanion.model.Item;
import com.example.gerardodwyer.tabletopcompanion.model.NPC;

import java.util.List;

/**
 * Created by Gerard o dwyer on 11/02/2019.
 */

public class EnemyAdapter extends RecyclerView.Adapter<EnemyAdapter.MyViewHolder> {

    private List<Enemy> enemyList;

    //Provide a reference to the views for each data item
    //Complex data items may need more than one view per item, and
    //You provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, weapon, power, allegiance, appearance, backstory;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            weapon = (TextView) view.findViewById(R.id.weapon);
            power = (TextView) view.findViewById(R.id.power);
            allegiance = (TextView) view.findViewById(R.id.allegiance);
            appearance = (TextView) view.findViewById(R.id.appearance);
            backstory = (TextView) view.findViewById(R.id.backstory);
        }
    }

    //provide a suitable constructor
    public EnemyAdapter(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    //Create views (invoke layout manager)
    @Override
    public EnemyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create new view
        View enemyList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.npc_list_rows, parent, false);

        return new MyViewHolder(enemyList);
    }


    //Replace contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // -get element from your dataset at this position
        // -replace the contents of the view with that element
        Enemy enemy = enemyList.get(position);
        holder.name.setText(enemy.getName());
        holder.weapon.setText(enemy.getWeapon());
        holder.power.setText(enemy.getPower());
        holder.allegiance.setText(enemy.getAllegiance());
        holder.appearance.setText(enemy.getAppearance());
        holder.backstory.setText(enemy.getBackstory());
    }

    //Return the size of your dataset (Invoked by the layout manager)
    @Override
    public int getItemCount() {
        return enemyList.size();
    }
}
