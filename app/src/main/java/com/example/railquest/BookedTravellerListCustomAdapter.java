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

public class BookedTravellerListCustomAdapter extends RecyclerView.Adapter<BookedTravellerListCustomAdapter.ViewHolder> {

    private List<String> travellerNames;
    private List<String> travellerGenderAge;
    private List<String> travellerBookedStatus;
    private List<String> travellerCurrentStatus;
    private Context context;

    public BookedTravellerListCustomAdapter(Context context, List<String> travellerNames, List<String> travellerGenderAge, List<String> travellerBookedStatus, List<String> travellerCurrentStatus) {
        this.travellerNames = travellerNames;
        this.travellerGenderAge = travellerGenderAge;
        this.travellerBookedStatus = travellerBookedStatus;
        this.travellerCurrentStatus = travellerCurrentStatus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_traveller_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTravellerName.setText(travellerNames.get(position));
        holder.txtGenderAge.setText(travellerGenderAge.get(position));
        holder.txtStatus.setText(travellerBookedStatus.get(position));
        holder.txtCurrentStatus.setText(travellerCurrentStatus.get(position));

    }

    @Override
    public int getItemCount() {
        return travellerNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTravellerName, txtGenderAge, txtStatus, txtCurrentStatus;
        CardView parentCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTravellerName = itemView.findViewById(R.id.txtBookedTravellerName);
            txtGenderAge = itemView.findViewById(R.id.txtBoookedGenderAge);
            txtStatus = itemView.findViewById(R.id.txtBookedStatus);
            txtCurrentStatus = itemView.findViewById(R.id.txtCurrentStatus);

            parentCardView = itemView.findViewById(R.id.parent);
        }
    }
}
