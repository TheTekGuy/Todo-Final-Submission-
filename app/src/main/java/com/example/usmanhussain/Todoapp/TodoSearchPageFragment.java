package com.example.usmanhussain.Todoapp;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TodoSearchPageFragment extends Fragment {

    String search;
    RecyclerView mTodoRecyclerView;
    TodoAdapter mTodoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        search = getActivity().getIntent().getStringExtra("String");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        mTodoRecyclerView = (RecyclerView) view.findViewById(R.id.todo_recycler_view);
        mTodoRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI(){

        ArrayList todos = new ArrayList<>();
        TodoModel todoModel = TodoModel.get(getContext());
        todos = todoModel.getTodos();

        if (mTodoAdapter == null) {
            mTodoAdapter = new TodoAdapter(todos);
            mTodoRecyclerView.setAdapter(mTodoAdapter);
        } else {
            mTodoAdapter.notifyDataSetChanged();
        }
    }

    public class TodoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Todo mTodo;
        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private CheckBox checkBox;

        public TodoHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_todo, parent, false));

            mTextViewTitle = (TextView) itemView.findViewById(R.id.todo_title);
            mTextViewDate = (TextView) itemView.findViewById(R.id.todo_date);
            checkBox = (CheckBox) itemView.findViewById(R.id.status);
        }

        @Override
        public void onClick(View view) {}

        public void display(Todo todo){

            search = search.toLowerCase();

            if (todo.getTitle().toLowerCase().contains(search)) {
                mTodo = todo;
                mTextViewTitle.setText(mTodo.getTitle());
                mTextViewDate.setText(mTodo.getDate().toString());
                checkBox.setChecked(mTodo.isComplete());
            }
        }
    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoSearchPageFragment.TodoHolder> {

        private List<Todo> mTodos;

        public TodoAdapter(List<Todo> todo) {
            mTodos = todo;
        }

        @Override
        public TodoSearchPageFragment.TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TodoHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TodoHolder holder, int position) {
            Todo todo = mTodos.get(position);
                holder.display(todo);
        }

        @Override
        public int getItemCount() {
            return mTodos.size();
        }

    }
}