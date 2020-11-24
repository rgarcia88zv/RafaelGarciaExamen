package com.example.rafaelgarciaexamen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context contexto;
    private ArrayList<Agenda> misContactos;

    public RecyclerViewAdapter(Context contexto, ArrayList<Agenda> misContactos) {
        this.contexto = contexto;
        this.misContactos = misContactos;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tvNameList.setText(misContactos.get(position).getNombre());
        holder.tvApeList.setText(misContactos.get(position).getApellidos());
        holder.tvNumList.setText(String.valueOf(misContactos.get(position).getTelefono()));
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(contexto.getApplicationContext(),UpdateActivity.class);
                i.putExtra("elegido", misContactos.get(position));
                contexto.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return misContactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameList;
        TextView tvApeList;
        TextView tvNumList;
        ConstraintLayout parent_layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameList = itemView.findViewById(R.id.tvNameList);
             tvApeList = itemView.findViewById(R.id.tvApeList);
            tvNumList = itemView.findViewById(R.id.tvNumList);

            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
