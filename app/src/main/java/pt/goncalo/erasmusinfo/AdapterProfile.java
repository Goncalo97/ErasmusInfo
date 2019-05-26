package pt.goncalo.erasmusinfo;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.ViewHolderProfile> {
    private Cursor cursor;
    private Context context;

    public AdapterProfile(Context context) {
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        if (this.cursor != cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }
    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolderProfile onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemProfile = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);
        return  new ViewHolderProfile(itemProfile);
        /*
        View viewItemProfile = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);
        ViewHolderProfile viewHolderProfile = new ViewHolderProfile(viewItemProfile);
        return viewHolderProfile;
         */
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolderProfile holder, int position) {
        cursor.moveToPosition(position);
        MainProfile profile = MainProfile.fromCursor(cursor);
        holder.setProfile(profile);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        else return cursor.getCount(); //TODO: Check if else or without else???
    }

    private static ViewHolderProfile viewHolderProfileSelected = null;

    public class ViewHolderProfile extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private TextView textViewDate;

        private MainProfile profile;

        public ViewHolderProfile(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewProfileName);
            textViewDate = itemView.findViewById(R.id.textViewProfileDate);
            itemView.setOnClickListener(this);
        }

        public void setProfile(MainProfile profile) {
            this.profile = profile;
            textViewName.setText(profile.getName());
            textViewDate.setText(String.valueOf(profile.getAge()));
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if (viewHolderProfileSelected != null) viewHolderProfileSelected.unSelect();
            viewHolderProfileSelected = this;
            select();
            // Toast.makeText(context, profile.getName(), Toast.LENGTH_SHORT).show();
        }

        private void select() {
            itemView.setBackgroundResource(R.color.colorItemSelected);
        }

        private void unSelect() {
            itemView.setBackgroundResource(android.R.color.white);

        }
    }
}
