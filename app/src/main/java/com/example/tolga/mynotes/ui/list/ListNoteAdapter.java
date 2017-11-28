package com.example.tolga.mynotes.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tolga.mynotes.Constant;
import com.example.tolga.mynotes.R;
import com.example.tolga.mynotes.database.Note;
import com.example.tolga.mynotes.ui.detail.DetailNoteActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tolga on 28.11.2017.
 */

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.ViewHolder>{

    private OnNoteClickListener onNoteClickListener;

    protected Context context;

    private List<Note> notes;

    interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    void setOnNoteClickListener(OnNoteClickListener listener) {
        onNoteClickListener = listener;
    }

    public void setNotes (List<Note> notes) {
        // TODO: Use DiffUtil
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(notes.get(position).getTitle()==null && notes.get(position).getValue() == null){
            holder.titleTextView.setText("No Title");
        }else{
            holder.titleTextView.setText(notes.get(position).getTitle());
            holder.valueTextView.setText(notes.get(position).getValue());
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailNoteActivity.class);
                intent.putExtra(Constant.ITEM_TITLE,notes.get(position).getTitle());
                intent.putExtra(Constant.ITEM_DESC,notes.get(position).getValue());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.note_title)
        TextView titleTextView;
        @BindView(R.id.note_value)
        TextView valueTextView;
        @BindView(R.id.notesContainer)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteClickListener.onNoteClick(notes.get(getAdapterPosition()));

        }
    }
}
