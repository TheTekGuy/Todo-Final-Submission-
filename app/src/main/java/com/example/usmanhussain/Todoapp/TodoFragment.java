package com.example.usmanhussain.Todoapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class TodoFragment extends Fragment {

    private static final String ARG_TODO_ID = "todo_id";

    private Todo mTodo;
    private EditText mEditTextTitle;
    private EditText mTextDetail;
    private Button mButtonDate;
    private CheckBox mCheckBoxIsComplete;

    public static TodoFragment newInstance(UUID todoId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TODO_ID, todoId);

        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID todoId = (UUID) getArguments().getSerializable(ARG_TODO_ID);
        mTodo = TodoModel.get(getActivity()).getTodo(todoId);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        mEditTextTitle = (EditText) view.findViewById(R.id.todo_title);
        mEditTextTitle.setText(mTodo.getTitle());
        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTodo.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        mTextDetail = (EditText) view.findViewById(R.id.todo__text_detail);
        mTextDetail.setText(mTodo.getDetail());
        mTextDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTodo.setDetail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This line is intentionally left blank
            }
        });

        mButtonDate = (Button) view.findViewById(R.id.todo_date);
        mButtonDate.setText(mTodo.getDate().toString());
        mButtonDate.setEnabled(false);

        mCheckBoxIsComplete = (CheckBox) view.findViewById(R.id.todo_complete);
        mCheckBoxIsComplete.setChecked(mTodo.isComplete());
        mCheckBoxIsComplete.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTodo.setComplete(isChecked);
            }
        });

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.check_menu, menu);
        inflater.inflate(R.menu.uncheck_menu, menu);
        inflater.inflate(R.menu.delete_menu, menu);
        inflater.inflate(R.menu.email_menu, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.email_menu:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL  , new String[]{"test@test.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, mTodo.getTitle());
                email.putExtra(Intent.EXTRA_TEXT   , mTodo.getTitle() + " " + mTodo.getDetail() + " " + mTodo.getDate());
                startActivity(Intent.createChooser(email, "Send mail..."));


            case R.id.delete_todo:
                TodoModel.get(getActivity()).deleteTodo(mTodo);
                Toast.makeText(getActivity(), mTodo.getTitle() + " Deleted", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;

            case R.id.check_menu:
                    TodoModel.get(getActivity()).completeTodoTrue(mTodo);
                Toast.makeText(getActivity(), mTodo.getTitle() + " Completed",Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;

            case R.id.uncheck_menu:
                TodoModel.get(getActivity()).completeTodoFalse(mTodo);
                Toast.makeText(getActivity(), mTodo.getTitle() + " Not Completed",Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}