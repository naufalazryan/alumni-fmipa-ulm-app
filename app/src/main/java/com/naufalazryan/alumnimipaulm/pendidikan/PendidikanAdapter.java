package com.naufalazryan.alumnimipaulm.pendidikan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naufalazryan.alumnimipaulm.ModelResponse.PekerjaanModelResponse.PekerjaanDataModel;
import com.naufalazryan.alumnimipaulm.ModelResponse.PendidikanModelResponse.PendidikanDataModel;
import com.naufalazryan.alumnimipaulm.R;

import java.util.List;

public class PendidikanAdapter extends RecyclerView.Adapter<PendidikanAdapter.MyViewHolder> {

    private List<PendidikanDataModel> listPendidikan;

    public PendidikanAdapter(List<PendidikanDataModel> pendidikanDataModelAdapter){
        this.listPendidikan = pendidikanDataModelAdapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pendidikan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvNamaPT.setText(listPendidikan.get(position).getPendPT());
        holder.tvJurusan.setText(listPendidikan.get(position).getPendJurusan());
        holder.tvTahunMulai.setText(listPendidikan.get(position).getPendMulai() + "-");
        holder.tvTahunSelesai.setText(listPendidikan.get(position).getPendSelesai());
    }


    @Override
    public int getItemCount() {
        return listPendidikan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamaPT, tvJurusan, tvTahunMulai, tvTahunSelesai;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNamaPT = itemView.findViewById(R.id.namaPT);
            tvJurusan =  itemView.findViewById(R.id.jurusan);
            tvTahunMulai = itemView.findViewById(R.id.tahunMulai);
            tvTahunSelesai = itemView.findViewById(R.id.tahunSelesai);
        }
    }

}
