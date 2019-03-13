package com.example.gerardodwyer.tabletopcompanion;


import android.os.CpuUsageInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardodwyer.tabletopcompanion.model.Item;
import com.example.gerardodwyer.tabletopcompanion.model.NPC;

import java.util.List;

/**
 * Created by Gerard o dwyer on 11/02/2019.
 */

public class NPCAdapter extends RecyclerView.Adapter<NPCAdapter.MyViewHolder> {

    private List<NPC> npcsList;

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
    public NPCAdapter(List<NPC> npcsList) {
        this.npcsList = npcsList;
    }

    //Create views (invoke layout manager)
    @Override
    public NPCAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //create new view
        View npcsList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.npc_list_rows, parent, false);

        return new MyViewHolder(npcsList);
    }


    //Replace contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // -get element from your dataset at this position
        // -replace the contents of the view with that element
        NPC npc = npcsList.get(position);
        holder.name.setText(npc.getName());
        holder.weapon.setText(npc.getWeapon());
        holder.power.setText(npc.getPower());
        holder.allegiance.setText(npc.getAllegiance());
        holder.appearance.setText(npc.getAppearance());
        holder.backstory.setText(npc.getBackstory());
    }

    //Return the size of your dataset (Invoked by the layout manager)
    @Override
    public int getItemCount() {
        return npcsList.size();
    }
}
