package com.example.myfrags;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Fragment4 extends Fragment {

    private FragsData fragsData;
    private Observer<Integer> numberObserver;
    private EditText edit;
    private TextWatcher textWatcher;
    private boolean turnOffWatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        edit = view.findViewById(R.id.editTextNumber);

        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                turnOffWatcher = true;
                edit.setText(integer.toString());
            }
        };

        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!turnOffWatcher){
                    Integer i;
                    try {
                        i = Integer.parseInt(editable.toString());
                    } catch (NumberFormatException e){
                        i = fragsData.counter.getValue();
                    }
                    fragsData.counter.setValue(i);
                } else {
                    turnOffWatcher = !turnOffWatcher;
                }

            }
        };

        edit.addTextChangedListener(textWatcher);

        return view;
    }
}