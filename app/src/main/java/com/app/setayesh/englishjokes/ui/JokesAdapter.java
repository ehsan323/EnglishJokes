package com.app.setayesh.englishjokes.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.setayesh.englishjokes.model.pojo.Value;
import com.app.setayesh.englishjokes.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {

    private Context context;
    private List<Value> jokes;

    public JokesAdapter(Context context, List<Value> jokes) {
        this.context = context;
        this.jokes = jokes;
    }

    @Override
    public JokesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JokesViewHolder holder, int position) {
       Value value = jokes.get(position);
       holder.jokeContent.setText(value.getJoke());
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class JokesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.joke_content)
        TextView jokeContent;

        public JokesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
