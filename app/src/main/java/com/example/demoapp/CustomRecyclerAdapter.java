package com.example.demoapp;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Car> car;

    public CustomRecyclerAdapter(Context context, List car) {
        this.context = context;
        this.car = car;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(car.get(position));

        Car c = car.get(position);

        holder.pName.setText(c.getProductName());
        holder.pDesc.setText(c.getProductDesc());
        Picasso.with(context)
                .load(c.getProductUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return car.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName;
        public TextView pDesc;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            pName = (TextView) itemView.findViewById(R.id.pNametxt);
            pDesc = (TextView) itemView.findViewById(R.id.pdesc);
            image = itemView.findViewById(R.id.userImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Car cr = (Car) view.getTag();
                    Dialog dialAddView = new Dialog(context,R.style.AlertDialogTheme);
                    dialAddView.setContentView(R.layout.view_item);
                    TextView pname = dialAddView.findViewById(R.id.text1);
                    TextView pdesc = dialAddView.findViewById(R.id.text2);
                    ImageView image = dialAddView.findViewById(R.id.image);
                    pname.setText(cr.getProductName());
                    pdesc.setText(cr.getProductDesc());
                    Picasso.with(context)
                            .load(cr.getProductUrl())
                            .into(image);

                    dialAddView.show();

                }
            });

        }
    }

}