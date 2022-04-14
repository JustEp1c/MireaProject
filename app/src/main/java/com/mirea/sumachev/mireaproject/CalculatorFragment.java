package com.mirea.sumachev.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView resultField;
    EditText numberField;
    TextView operationField;
    Double operand = null;
    String lastOperation = "=";

    Button numBtn1;
    Button numBtn2;
    Button numBtn3;
    Button numBtn4;
    Button numBtn5;
    Button numBtn6;
    Button numBtn7;
    Button numBtn8;
    Button numBtn9;
    Button numBtn0;
    Button numBtn;
    Button divBtn;
    Button sumBtn;
    Button multBtn;
    Button minBtn;
    Button equelsBtn;

    String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ","};
    String[] operations = new String[]{"/", "*", "-", "+", "=", };

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
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

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        resultField = view.findViewById(R.id.resultField);
        numberField = view.findViewById(R.id.numberField);
        operationField = view.findViewById(R.id.operationField);

        numBtn1 = view.findViewById(R.id.numberBtn1);
        numBtn1.setOnClickListener(this);
        numBtn2 = view.findViewById(R.id.numberBtn2);
        numBtn2.setOnClickListener(this);
        numBtn3 = view.findViewById(R.id.numberBtn3);
        numBtn3.setOnClickListener(this);
        numBtn4 = view.findViewById(R.id.numberBtn4);
        numBtn4.setOnClickListener(this);
        numBtn5 = view.findViewById(R.id.numberBtn5);
        numBtn5.setOnClickListener(this);
        numBtn6 = view.findViewById(R.id.numberBtn6);
        numBtn6.setOnClickListener(this);
        numBtn7 = view.findViewById(R.id.numberBtn7);
        numBtn7.setOnClickListener(this);
        numBtn8 = view.findViewById(R.id.numberBtn8);
        numBtn8.setOnClickListener(this);
        numBtn9 = view.findViewById(R.id.numberBtn9);
        numBtn9.setOnClickListener(this);
        numBtn0 = view.findViewById(R.id.numberBtn0);
        numBtn0.setOnClickListener(this);
        numBtn = view.findViewById(R.id.numberBtn);
        numBtn.setOnClickListener(this);
        divBtn = view.findViewById(R.id.divisionBtn);
        divBtn.setOnClickListener(this);
        multBtn = view.findViewById(R.id.multiplyBtn);
        multBtn.setOnClickListener(this);
        minBtn = view.findViewById(R.id.minusBtn);
        minBtn.setOnClickListener(this);
        sumBtn = view.findViewById(R.id.plusBtn);
        sumBtn.setOnClickListener(this);
        equelsBtn = view.findViewById(R.id.equelsBtn);
        equelsBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        for (String numberInArray : numbers) {
            if (button.getText().toString().equals(numberInArray)) {
                numberField.append(button.getText());

                if (lastOperation.equals("=") && operand != null) {
                    operand = null;
                }
            }
        }
        for (String operationInArray : operations) {
            if (button.getText().toString().equals(operationInArray)) {
                String op = button.getText().toString();
                String number = numberField.getText().toString();
                if (number.length() > 0) {
                    number = number.replace(',', '.');
                    try {
                        performOperation(Double.valueOf(number), op);
                    } catch (NumberFormatException ex) {
                        numberField.setText("");
                    }
                }
                lastOperation = op;
                operationField.setText(lastOperation);
            }
        }
    }
    private void performOperation(Double number, String operation){

        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }
}