package com.example.railquest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TravellerListCustomAdapter extends RecyclerView.Adapter<TravellerListCustomAdapter.ViewHolder> {

    private List<String> travellerNames;
    private List<String> berthNames;
    private Context context;

    public TravellerListCustomAdapter(Context context, List<String> travellerNames, List<String> berthNames) {
        this.context = context;
        this.travellerNames = travellerNames;
        this.berthNames = berthNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.traveler_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTravellerName.setText(travellerNames.get(position));
        holder.txtBerthName.setText(berthNames.get(position));
    }

    @Override
    public int getItemCount() {
        return travellerNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTravellerName, txtBerthName;
        CardView parentCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTravellerName = itemView.findViewById(R.id.txtTravellerName);
            txtBerthName = itemView.findViewById(R.id.txtBerthName);
            parentCardView = itemView.findViewById(R.id.parent);
        }
    }
}
