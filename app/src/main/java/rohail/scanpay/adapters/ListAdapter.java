package rohail.scanpay.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import rohail.scanpay.Activities.HistoryActivity;
import rohail.scanpay.Activities.MainActivity;
import rohail.scanpay.Activities.SuggestionActivity;
import rohail.scanpay.R;
import rohail.scanpay.models.ItemModel;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<ItemModel> values;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(Context context, ArrayList<ItemModel> itemModelArrayList) {
        values = itemModelArrayList;
        this.context = context;
    }

    public void add(int position, ItemModel item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        if (context instanceof MainActivity) {
            setMainView(holder, position);
        } else if (context instanceof SuggestionActivity) {
            setSuggestionView(holder, position);
        } else if (context instanceof HistoryActivity) {
            setHistoryView(holder, position);
        }

    }

    private void setHistoryView(ViewHolder holder, int position) {
        String name = values.get(position).getName();
        holder.txtHeader.setText(name);
        holder.txtFooter.setText("Price: " + values.get(position).getTotal()
                + " PLN");
    }

    private void setSuggestionView(ViewHolder holder, int position) {
        String name = values.get(position).getName();
        holder.txtHeader.setText(name);

        holder.txtFooter.setVisibility(View.GONE);
    }

    private void setMainView(ViewHolder holder, int position) {
        String name = values.get(position).getName();
        holder.txtHeader.setText(name + " *" + values.get(position).getCount());

        holder.txtFooter.setText("Price: " + values.get(position).getPrice()
                + " " + values.get(position).getCurrency());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
        }
    }

}
