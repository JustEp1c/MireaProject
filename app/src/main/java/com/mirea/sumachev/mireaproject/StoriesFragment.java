package com.mirea.sumachev.mireaproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Story> stories = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private String story;
    private String nameOfStory;
    private int lastPosition;
    FloatingActionButton floatingBtn;

    private StorySaver storySaver;

    public StoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoriesFragment newInstance(String param1, String param2) {
        StoriesFragment fragment = new StoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);

        storySaver = new StorySaver(getActivity());
        stories = storySaver.loadStories();

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StoryAdapter(stories);
        recyclerView.setAdapter(adapter);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        lastPosition = sharedPreferences.getInt("lastPosition", 0);
        recyclerView.scrollToPosition(lastPosition);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                lastPosition = layoutManager.findFirstVisibleItemPosition();
            }
        });

        floatingBtn = view.findViewById(R.id.floatingBtn);
        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                final EditText inputText = new EditText(getActivity());
                inputText.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(inputText);

                builder.setTitle("Новая история")
                        .setMessage("Впишите название истории")
                        .setIcon(R.drawable.stories)
                        .setPositiveButton("Далее", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               nameOfStory = inputText.getText().toString();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                final EditText inputText = new EditText(getActivity());
                                inputText.setInputType(InputType.TYPE_CLASS_TEXT);
                                builder.setView(inputText);

                                builder.setTitle("Новая история")
                                        .setMessage("Впишите свою историю")
                                        .setIcon(R.drawable.stories)
                                        .setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                story = inputText.getText().toString();
                                                stories.add(new Story(nameOfStory, story));
                                                adapter.notifyItemInserted(stories.size() - 1);

                                                storySaver.saveStories(stories);
                                            }
                                        })
                                        .setNegativeButton("Отмена",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog,
                                                                        int id) {
                                                        dialog.cancel();
                                                    }
                                                });
                                builder.show();
                            }
                        })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                builder.show();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastPosition", lastPosition);
        editor.apply();
    }

    private static final class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<Story> stories;

        public StoryAdapter(List<Story> stories) {
            this.stories = stories;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.stories, parent, false)
            ){};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView name = holder.itemView.findViewById(R.id.storyName);
            TextView text = holder.itemView.findViewById(R.id.studentLast);

            name.setText(this.stories.get(position).getName());
            text.setText(this.stories.get(position).getText());
        }

        @Override
        public int getItemCount() {
            return this.stories.size();
        }
    }
}


