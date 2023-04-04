package com.naufalazryan.alumnimipaulm.pekerjaan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naufalazryan.alumnimipaulm.ModelResponse.PekerjaanModelResponse.PekerjaanDataModel;
import com.naufalazryan.alumnimipaulm.R;

import java.util.List;

public class PekerjaanAdapter extends RecyclerView.Adapter<PekerjaanAdapter.MyViewHolder> {

    private List<PekerjaanDataModel> listPekerjaan;

    public PekerjaanAdapter(List<PekerjaanDataModel> pekerjaanDataModelAdapter){
        this.listPekerjaan = pekerjaanDataModelAdapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pekerjaan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvPekerjaan.setText(listPekerjaan.get(position).getKjPekerjaan());
        holder.tvInstansi.setText(listPekerjaan.get(position).getKjInstansi());
        holder.tvTahunMulai.setText(listPekerjaan.get(position).getKjTahunMulai());
    }


    @Override
    public int getItemCount() {
        return listPekerjaan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPekerjaan, tvInstansi, tvTahunMulai;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvPekerjaan = itemView.findViewById(R.id.tampilPekerjaan);
            tvInstansi =  itemView.findViewById(R.id.tampilInstansi);
            tvTahunMulai = itemView.findViewById(R.id.tampilTahun);
        }
    }

}
