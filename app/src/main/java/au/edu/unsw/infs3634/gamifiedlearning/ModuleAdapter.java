package au.edu.unsw.infs3634.gamifiedlearning;
//INFS3634 Gamified Learning App Assignment T3 2020
//Group 20
//Caitlin O'Dowd z5183007
//Lauren Bleach z5208547
//Neil Matani z5162753


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>  {
    //implements filterable
    private List<Module> mModules;
    private List<Module> mModulesFiltered;
    private RecyclerViewClickListener mListener;
    public static final int SORT_METHOD_ALPHABETICAL = 1;
    public static final int SORT_METHOD_DESC_AVG = 2;
    public static final int SORT_METHOD_ASC_AVG = 3;


    //set up filtered list if we want search functionalities
    public ModuleAdapter(ArrayList<Module> modules, RecyclerViewClickListener listener) {
        mModules = modules;
        mListener = listener;
        mModulesFiltered = modules;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, String title);
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_cardview, parent, false);
        return new ModuleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleAdapter.ModuleViewHolder holder, int position) {
        Module modules = mModulesFiltered.get(position);
        holder.title.setText(modules.getTitle());
        holder.progressBar.setProgress(modules.getProgressBar());
        holder.image.setImageResource(modules.getImage());
        holder.itemView.setTag(modules.getTitle());
        holder.progress.setText("Average Quiz Score: " + modules.getProgressBar());
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, progress;
        public ImageView image;
        public ProgressBar progressBar;
        private RecyclerViewClickListener listener;

        public ModuleViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.tvTitle);
            progressBar = itemView.findViewById(R.id.progressBar);
            progressBar.setMax(5);
            image = itemView.findViewById(R.id.ivPhoto);
            progress = itemView.findViewById(R.id.tvProgress);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, (String) v.getTag());
        }

    }

    public void sort(final int sortMethod) {
        final String completed = "Completed";
        if(mModulesFiltered.size() > 0) {
            Collections.sort(mModulesFiltered, new Comparator<Module>() {
                @Override
                public int compare(Module o1, Module o2) {
                    if (sortMethod == SORT_METHOD_ALPHABETICAL) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    } else if (sortMethod == SORT_METHOD_DESC_AVG) {
                        return o2.getProgressBar() - o1.getProgressBar();
                    } else if (sortMethod == SORT_METHOD_ASC_AVG) {
                        return o1.getProgressBar() - o2.getProgressBar();
                    }
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }
        notifyDataSetChanged();

    }

}
