package com.pdm.p_62_bd_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiViewHolder> {
    private final Context context;
    private ArrayList<Item> datos;

    MiAdaptador(ArrayList<Item> datos, Context context) {
        this.datos = datos;
        this.context=context;
    }

    @Override
    public MiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MiViewHolder holder, int position) {
        String texto = datos.get(position).getNombrePersona();
        holder.personaNombre.setText(texto);
        int identificador=datos.get(position).getIdPersona();
        holder.personaId.setText(String.valueOf(identificador));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    class MiViewHolder extends RecyclerView.ViewHolder {
        TextView personaNombre;
        TextView personaId;
        MiViewHolder(View itemView) {
            super(itemView);
            personaNombre = itemView.findViewById(R.id.textView2);
            personaId=itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,SegundaActivity.class);
                    intent.putExtra("enviado",Integer.valueOf(personaId.getText().toString()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
