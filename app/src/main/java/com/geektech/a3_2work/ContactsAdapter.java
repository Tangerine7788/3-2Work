package com.geektech.a3_2work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    private Context rContext;
    private Call call;

    private List<ContactsModel> list;

    public ContactsAdapter(Context context, List<ContactsModel> list){
this.rContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts,parent,false);
        return new ContactsViewHolder(view);
    }
public interface Call{
       void callBack();

}
    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtPhone.setText(list.get(position).getPhone());

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rContext instanceof MainActivity){
                    ((MainActivity)rContext).callBack();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ list.get(position).getPhone()));
                    rContext.startActivity(intent);
                }
                Toast.makeText(holder.btnCall.getContext(),list.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtPhone;
        Button btnCall, btnMassage;


        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_conact_name);
            txtPhone = itemView.findViewById(R.id.txt_conact_phone);
            btnCall = itemView.findViewById(R.id.btn_call);
            btnMassage = itemView.findViewById(R.id.btn_massage);
        }
    }
}
