package com.babasoft.savkar.adapter;

/**
 * Created by s5 on 4/10/16.
 */
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.babasoft.savkar.R;
        import com.babasoft.savkar.dtos.CustomerDTO;

        import java.util.ArrayList;

public class MainViewListAdapter extends RecyclerView.Adapter<MainViewListAdapter.MyViewHolder> {

    private ArrayList<CustomerDTO> customersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
       public TextView txtname, txtemail, txtmobile,txtaddress,txtamount,txtinterestrate,txttotalamount;
       ImageView imageProfile;

        public MyViewHolder(View view) {
            super(view);
            imageProfile=(ImageView)view.findViewById(R.id.imageProfile);
            txtname = (TextView) view.findViewById(R.id.txtname);
            txtemail = (TextView) view.findViewById(R.id.txtemail);
            txtmobile = (TextView) view.findViewById(R.id.txtmobile);
            txtaddress = (TextView) view.findViewById(R.id.txtaddress);
            txtamount = (TextView) view.findViewById(R.id.txtamount);
            txtinterestrate = (TextView) view.findViewById(R.id.txtinterestrate);
            txttotalamount = (TextView) view.findViewById(R.id.txttotalamount);
        }
    }


    public MainViewListAdapter(ArrayList<CustomerDTO> customers) {
        this.customersList = customers;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loaninfo_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageProfile.setImageBitmap(getImageFromBLOB(customersList.get(position).getProfileImage()));
        CustomerDTO customer = customersList.get(position);
        holder.txtname.setText(customer.getCustomername());
        holder.txtemail.setText(customer.getCust_email());
        holder.txtmobile.setText(customer.getCust_phone());
        holder.txtaddress.setText(customer.getCust_address());
        holder.txtamount.setText(customer.getCust_amount()+"");
        holder.txtinterestrate.setText(customer.getCust_interest()+"% PA");
    }

    @Override
    public int getItemCount() {
        return customersList.size();
    }
    public static Bitmap getImageFromBLOB(byte[] mBlob)
    {
        byte[] bb = mBlob;
        return BitmapFactory.decodeByteArray(bb, 0, bb.length);
    }
}
