package ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.R;
import ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.model.DecodeContributions;
import ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.presenter.AdapterDisplayContributions;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private EditText starting_amount;
    TextView total_savings;
    private String week,deposit,total;
    private int weekly_n,d;
    private Integer a=0;
    private static final int grand_n=52;
    public ArrayList<DecodeContributions> contributionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Ref custom toolbar
         */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("52 Weeks challenge savings");
        contributionsList = new ArrayList<>();

        total_savings= (TextView) findViewById(R.id.total_savings);
        starting_amount= (EditText)findViewById(R.id.starting_amount);
        /**
         * Add text change listener for real time updating
         */
        starting_amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(check_input(starting_amount.getText().toString())==true){
                    /**
                     * Update the dashboard first then load recyclerview
                     */
                    calculateFinalAmount();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //Function to check user input against the set metrics
    public boolean check_input(String input){
        if(input.equals("")){
            a=0;
            Toast.makeText(MainActivity.this,"Amount cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            a = Integer.parseInt(input);
        }
        if(a==null){
            Toast.makeText(MainActivity.this,"Amount cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(a <= 0){
            Toast.makeText(MainActivity.this,"Amount must be more than Zero", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(a >= 50000000){
            Toast.makeText(MainActivity.this,"Amount must be less than Fifty million Shillings", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Function to calculate contributions for the whole period and refresh the list for display in real time
     * This displays a recycler with the results
     */
    public void calculateContibutions(){
        /**
         *  THIS IS AN ARITHMETIC PROGRESSION HENCE BASIC MATH RULES APPLY
         *  IN ORDER TO GET THE NTH TERM AS WELL AS SUM OF THE AP
         */

        /**
         * Clear list before adding it to recyclerview
         */
        contributionsList.clear();

        a = Integer.parseInt(starting_amount.getText().toString());
        d = Integer.parseInt(starting_amount.getText().toString());

        /**
         * Loop through to get value for each week upto 52 weeks(grand n)
         */
        for(int i=1; i<=grand_n; i++){
            //weekly n
            weekly_n=i;

            //Week
            week= ""+i;

            //Deposit => ap=a+(n-1)d
            deposit= ""+(a+(weekly_n-1)*d);

            //Total => 0.5n[2a+(n-1)d]
            total= ""+((0.5*weekly_n)*((2*a)+((weekly_n-1)*d)));

            contributionsList.add( new DecodeContributions(week,deposit,total) );
        }

        /**
         * Get number of columns for the grid dynamically
         */
        int grid_cols=gridColumns(this,180);

        RecyclerView myrv= (RecyclerView) findViewById(R.id.recycleview_item_list);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(grid_cols, StaggeredGridLayoutManager.VERTICAL);
        myrv.setLayoutManager(sglm);
        /**
         * Set adapter after setting layout manager
         */
        AdapterDisplayContributions myAdapter= new AdapterDisplayContributions(this,contributionsList);
        /**
         * Notify dataset changed after clear to lead new data
         */
        myAdapter.notifyDataSetChanged();
        myrv.setAdapter(myAdapter);

    }

    /**
     * Calculate total savings at the end of 52 weeks and display it on the dashboard
     */
    public void calculateFinalAmount(){
        /**
         *  THIS IS AN ARITHMETIC PROGRESSION HENCE BASIC MATH RULES APPLY
         *  IN ORDER TO GET THE NTH TERM AS WELL AS SUM OF THE AP
         */

        //Clear list before adding it to recyclerview
        contributionsList.clear();

        a = Integer.parseInt(starting_amount.getText().toString());
        d = Integer.parseInt(starting_amount.getText().toString());

        /**
         * Final week to give final result
         */
        weekly_n=52;

        /**
         * Final week to give final result
         */
        week= ""+52;

        /**
         * Deposit => ap=a+(n-1)d
         */
        deposit= ""+(a+(weekly_n-1)*d);

        /**
         *
         * Total => 0.5n[2a+(n-1)d]
         */
        total= ""+((0.5*weekly_n)*((2*a)+((weekly_n-1)*d)));

        total_savings.setText("Kes "+total);

        /**
         *  Call calculateContibutions() function to load recyclerview after calculateFinalContibutions() as it executes slower than
         */
        if(check_input(starting_amount.getText().toString())==true){
            calculateContibutions();
        }

    }

    /**
     * Function to display appropriate number of grid columns based on screen size
     * @param context
     * @param columnWidthDp
     * @return number of columns to display on recycler
     */
    public static int gridColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

}
