package com.example.daniellamishan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.daniellamishan.model.Model;
import com.example.daniellamishan.model.Project;

import java.util.List;

public class ProtfolioFragment extends Fragment {

    RecyclerView projectsList;
    List<Project> protfolio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_protfolio, container, false);

        projectsList = view.findViewById(R.id.protfolio_list);
        protfolio = Model.instance().getAllProjects();

        projectsList.setLayoutManager(new LinearLayoutManager(getContext())); //define the recycler view to be a list
        ProtfolioRecyclerAdapter adapter = new ProtfolioRecyclerAdapter(getLayoutInflater(),protfolio);
        projectsList.setAdapter(adapter);

        return view;
    }

    //--------------------- menu view holder ---------------------------
    class ProtfolioViewHolder extends RecyclerView.ViewHolder {

        VideoView video;
        TextView usedSkills;
        Button goBtn;

        public ProtfolioViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            video = itemView.findViewById(R.id.list_row_video);
            usedSkills = itemView.findViewById(R.id.list_row_tv);
            goBtn = itemView.findViewById(R.id.list_row_btn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }

        public void bind(Project p) {
            video.setVideoPath(p.getVideoUrl());
            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true); // Set the video to loop
                    video.start(); // Start playing the video
                }
            });
            usedSkills.setText("Used Skills: " + p.getUsedSkills());
            goBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(p.getGoUrl()));
                    startActivity(intent);
                }
            });
        }
    }

    //--------------------- OnItemClickListener ---------------------------
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    //--------------------- menu recycler adapter ---------------------------
    class ProtfolioRecyclerAdapter extends RecyclerView.Adapter<ProtfolioViewHolder>{
        OnItemClickListener listener;
        LayoutInflater inflater;
        List<Project> data;

        public void setData(List<Project> data){
            this.data = data;
            notifyDataSetChanged();
        }

        public List<Project> getData(){
            return this.data;
        }

        public ProtfolioRecyclerAdapter(LayoutInflater inflater, List<Project> data){
            this.inflater = inflater;
            this.data = data;
        }

        // Set the OnItemClickListener
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
        // Create a view holder
        @NonNull
        @Override
        public ProtfolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.protfolio_list_row,parent,false);
            return new ProtfolioViewHolder(view,listener);
        }

        // Bind the data to the view holder
        @Override
        public void onBindViewHolder(@NonNull ProtfolioViewHolder holder, int position) {
            Project project = data.get(position);
            //Log.d("server", "dishName: " + dish.getDishName());
            holder.bind(project);
        }

        // Return the number of items in the data
        @Override
        public int getItemCount() {
            if (data == null) return 0;
            return data.size();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable strict mode for debugging purposes
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
    }
}