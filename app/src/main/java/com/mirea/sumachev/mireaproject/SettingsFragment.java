package com.mirea.sumachev.mireaproject;



import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SharedPreferences preferences;
    final String SAVED_NAME = "saved_name";
    final String SAVED_EMAIL = "saved_email";

    private EditText editName;
    private EditText editEmail;
    private Button btnSave;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        btnSave = view.findViewById(R.id.btnSave);
        editEmail = view.findViewById(R.id.editEmail);
        editName = view.findViewById(R.id.editName);
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        String name = preferences.getString(SAVED_NAME, "Empty");
        String email = preferences.getString(SAVED_EMAIL, "EmptyEmail");
        if (!name.equals("Empty") || !email.equals("EmptyEmail")) {
            editName.setText(name);
            editEmail.setText(email);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                editor.putString(SAVED_NAME, name);
                editor.putString(SAVED_EMAIL, email);
                editor.apply();
                Toast.makeText(getActivity(), "Имя и почта сохранены", Toast.LENGTH_SHORT).show();
                editName.setText(name);
                editEmail.setText(email);
            }
        });

        return view;
    }
}