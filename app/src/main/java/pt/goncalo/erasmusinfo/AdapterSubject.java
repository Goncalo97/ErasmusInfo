package pt.goncalo.erasmusinfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSubject extends RecyclerView.Adapter<AdapterSubject.ViewHolderSubject> {
    private Cursor cursor;
    private Context context;

    public AdapterSubject(Context context) {
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
    public ViewHolderSubject onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemSubject = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        return new ViewHolderSubject(itemSubject);
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
    public void onBindViewHolder(@NonNull ViewHolderSubject holder, int position) {
        cursor.moveToPosition(position);
        Subject subject = Subject.fromCursor(cursor);
        holder.setSubject(subject);
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

    public Subject getSubjectSelected() {
        if (viewHolderSubjectSelected == null) return null;
        return viewHolderSubjectSelected.subject;
    }

    private static ViewHolderSubject viewHolderSubjectSelected = null;

    private String getProfileName(long idCollege) {
        ContentResolver mResolver = context.getContentResolver();

        Uri uriC = Uri.withAppendedPath(ErasmusInfoContentProvider.COLLEGE_ADDRESS, String.valueOf(idCollege));

        Cursor cursorC = mResolver.query(uriC, DbTableCollege.ALL_COLUMNS, null, null, null);
        Log.i("CURSOR QUERY", ""+cursorC);

        String collegeProfileID = "";

        if (!cursorC.moveToNext()) {
            Log.i("CURSOR", "Error: It was not possible to read the College.");
            //Toast.makeText(context, "Error: It was not possible to read the Profile.", Toast.LENGTH_LONG).show();
            //finish();
            //return;
        }

        College college;
        college = College.fromCursor(cursorC);
        //collegeName = String.valueOf(college.getName());
        collegeProfileID = String.valueOf(college.getIdProfile());
        cursorC.close();
        // cursor = null;

        Uri uri = Uri.withAppendedPath(ErasmusInfoContentProvider.PROFILE_ADDRESS, String.valueOf(collegeProfileID));

        Cursor cursor = mResolver.query(uri, DbTableProfile.ALL_COLUMNS, null, null, null);
        Log.i("CURSOR QUERY", ""+cursor);

        String profileName = "";

        if (!cursor.moveToNext()) {
            Log.i("CURSOR", "Error: It was not possible to read the Profile.");
            //Toast.makeText(context, "Error: It was not possible to read the Profile.", Toast.LENGTH_LONG).show();
            //finish();
            //return;
        }

        Profile profile;
        profile = Profile.fromCursor(cursor);
        profileName = String.valueOf(profile.getName());

        cursor.close();
        // cursor = null;

        return profileName;
    }

    private String getCollegeName(long idCollege) {
        ContentResolver mResolver = context.getContentResolver();

        Uri uri = Uri.withAppendedPath(ErasmusInfoContentProvider.COLLEGE_ADDRESS, String.valueOf(idCollege));

        Cursor cursor = mResolver.query(uri, DbTableCollege.ALL_COLUMNS, null, null, null);
        Log.i("CURSOR QUERY", ""+cursor);

        String collegeName = "";

        if (!cursor.moveToNext()) {
            Log.i("CURSOR", "Error: It was not possible to read the College.");
            //Toast.makeText(context, "Error: It was not possible to read the Profile.", Toast.LENGTH_LONG).show();
            //finish();
            //return;
        }

        College college;
        college = College.fromCursor(cursor);
        collegeName = String.valueOf(college.getName());

        cursor.close();
        // cursor = null;

        return collegeName;
    }

    public class ViewHolderSubject extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewCode;
        private TextView textViewName;
        private TextView textViewECTs;
        private TextView textViewEqualSubject;
        private TextView textViewScore;
        private TextView textViewProfileName;
        private TextView textViewCollegeName;


        private Subject subject;

        public ViewHolderSubject(@NonNull View itemView) {
            super(itemView);
            textViewCode = itemView.findViewById(R.id.textViewSubjectCode);
            textViewName = itemView.findViewById(R.id.textViewSubjectName);
            textViewECTs = itemView.findViewById(R.id.textViewSubjectECTs);
            textViewEqualSubject = itemView.findViewById(R.id.textViewSubjectEqualSubject);
            textViewScore = itemView.findViewById(R.id.textViewSubjectScore);
            textViewProfileName = itemView.findViewById(R.id.textViewSubjectProfile);
            textViewCollegeName = itemView.findViewById(R.id.textViewSubjectCollege);
            itemView.setOnClickListener(this);
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
            textViewCode.setText(subject.getCode());
            textViewName.setText(subject.getName());
            textViewECTs.setText(String.valueOf(subject.getECTS()));
            textViewEqualSubject.setText(subject.getEqualSubject());
            textViewScore.setText(subject.getScore());
            textViewProfileName.setText(getProfileName(subject.getIdCollege()));
            textViewCollegeName.setText(getCollegeName(subject.getIdCollege()));
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if (viewHolderSubjectSelected != null) viewHolderSubjectSelected.unSelect();
            viewHolderSubjectSelected = this;
            ((SubjectActivity) context).refreshMenuOptions();
            select();
        }

        private void select() {
            itemView.setBackgroundResource(R.color.colorItemSelected);
        }

        private void unSelect() {
            itemView.setBackgroundResource(android.R.color.white);

        }
    }
}
