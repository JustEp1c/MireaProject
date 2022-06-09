package com.mirea.sumachev.mireaproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mirea.sumachev.mireaproject.room.DatabaseCreator;
import com.mirea.sumachev.mireaproject.room.Student;
import com.mirea.sumachev.mireaproject.room.StudentDao;
import com.mirea.sumachev.mireaproject.room.StudentDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton addStudent;
    FloatingActionButton deleteStudent;

    private ArrayList<Student> students = new ArrayList<>();
    private StudentAdapter adapter;

    public RoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomFragment newInstance(String param1, String param2) {
        RoomFragment fragment = new RoomFragment();
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
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        addStudent = view.findViewById(R.id.floatingBtnAdd);
        deleteStudent = view.findViewById(R.id.floatingBtnDelete);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration decor = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decor);

        StudentDatabase db = DatabaseCreator.getInstance().getDatabase();
        StudentDao studentDao = db.studentDao();

        adapter = new StudentAdapter(students);

        List<Student> studentList = studentDao.getAll();
        adapter.setStudentList(studentList);

        recyclerView.setAdapter(adapter);


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                final EditText inputText = new EditText(getActivity());
                inputText.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(inputText);
                builder.setTitle("Добавление студента в БД")
                        .setMessage("Введите фамилию студента")
                        .setIcon(R.drawable.database)
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Далее", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Student student = new Student();
                                        student.setSurname(inputText.getText().toString());
                                        Log.i("TAG", "Фамилия получена");

                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                        final EditText inputText = new EditText(getActivity());
                                        inputText.setInputType(InputType.TYPE_CLASS_TEXT);
                                        builder.setView(inputText);
                                        builder.setTitle("Добавление студента в БД")
                                                .setMessage("Введите имя студента")
                                                .setIcon(R.drawable.database)
                                                .setNegativeButton("Отмена",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog,
                                                                                int id) {
                                                                dialog.cancel();
                                                            }
                                                        })
                                                .setPositiveButton("Далее", new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        student.setName(inputText.getText().toString());
                                                        Log.i("TAG", "Имя получено");

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                                        final EditText inputText = new EditText(getActivity());
                                                        inputText.setInputType(InputType.TYPE_CLASS_TEXT);
                                                        builder.setView(inputText);
                                                        builder.setTitle("Добавление студента в БД")
                                                                .setMessage("Введите отчество студента")
                                                                .setIcon(R.drawable.database)
                                                                .setNegativeButton("Отмена",
                                                                        new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog,
                                                                                                int id) {
                                                                                dialog.cancel();
                                                                            }
                                                                        })
                                                                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {

                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                                        student.setPatronymic(inputText.getText().toString());
                                                                        Log.i("TAG", "Отчество получено");
                                                                        studentDao.insert(student);

                                                                        List<Student> studentList = studentDao.getAll();
                                                                        adapter.setStudentList(studentList);

                                                                        Log.i("TAG", "Студент добавлен в БД");

                                                                        Toast.makeText(getActivity(), "Студент добавлен в БД!",
                                                                                Toast.LENGTH_SHORT).show();

                                                                    }
                                                                });
                                                        builder.show();
                                                    }
                                                });
                                        builder.show();
                                    }
                                });
                builder.show();
            }
        });

        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                final EditText inputText = new EditText(getActivity());
                inputText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                inputText.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(inputText);
                builder.setTitle("Удаление студента из БД")
                        .setMessage("Введите ID студента")
                        .setIcon(R.drawable.database)
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String text = inputText.getText().toString();
                                long id = Integer.parseInt(text);

                                Student student = studentDao.getById(id);
                                if (student == null) {
                                    Toast.makeText(getActivity(), "Студент с таким ID уже был удален!",
                                            Toast.LENGTH_SHORT).show();
                                    dialogInterface.cancel();
                                }
                                else {
                                    studentDao.delete(student);

                                    List<Student> studentList = studentDao.getAll();
                                    adapter.setStudentList(studentList);

                                    Toast.makeText(getActivity(), "Студент удален!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                builder.show();
            }
        });
        return view;
    }

    private static final class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Student> studentList;

        public StudentAdapter(List<Student> studentList) {
            this.studentList = studentList;
        }

        public void setStudentList(List<Student> studentList) {
            this.studentList = studentList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recycler_row_students, parent, false)
            ){};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView id = holder.itemView.findViewById(R.id.studentID);
            TextView lastName = holder.itemView.findViewById(R.id.studentLast);
            TextView firstName = holder.itemView.findViewById(R.id.studentFirst);
            TextView middleName = holder.itemView.findViewById(R.id.studentMiddle);

            long idOfStudent = this.studentList.get(position).getId();
            String s = String.valueOf(idOfStudent);
            id.setText(s);
            lastName.setText(this.studentList.get(position).getSurname());
            firstName.setText(this.studentList.get(position).getName());
            middleName.setText(this.studentList.get(position).getPatronymic());
        }

        @Override
        public int getItemCount() {
            return this.studentList.size();
        }
    }
}
