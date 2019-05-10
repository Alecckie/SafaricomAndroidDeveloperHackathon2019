package ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.presenter;


 import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.R;
import ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.model.DecodeContributions;

public class AdapterDisplayContributions extends RecyclerView.Adapter<AdapterDisplayContributions.MyViewHolder>{

    //Declare context and the class with our list
    private Context mContext;
    private ArrayList<DecodeContributions> userContributionsList; //Our data class... this one contains the get and set methods to decode the data for us


    public AdapterDisplayContributions(Context mContext, ArrayList<DecodeContributions> userContributionsList) {
        this.mContext = mContext;
        this.userContributionsList = userContributionsList;
    }


    @NonNull
    @Override
    //Step one... load/inflate your layout
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view= mInflater.inflate(R.layout.activity_main_listview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    //Step three... Display data from the array list in the views you initialized in step two
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        if(userContributionsList.get(position).getWeek().equals(null)){
            holder.week_number.setText("...");
        }
        if(userContributionsList.get(position).getDeposit().equals(null)){
            holder.week_deposit.setText("...");
        }
        if(userContributionsList.get(position).getTotal().equals(null)){
            holder.week_total.setText("...");
        }
        holder.week_number.setText("Week "+userContributionsList.get(position).getWeek());
        holder.week_deposit.setText("Kes "+userContributionsList.get(position).getDeposit());
        holder.week_total.setText("Kes "+userContributionsList.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return userContributionsList.size();
    }

    //Step two... Initialize all the views in the layour you inflated in step one
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView week_number,week_deposit,week_total;

        public MyViewHolder(View itemView) {
            super(itemView);
            week_number= itemView.findViewById(R.id.week_number);
            week_deposit=itemView.findViewById(R.id.week_deposit);
            week_total=itemView.findViewById(R.id.week_total);
        }
    }

}