package com.example.usmanhussain.Todoapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class TodoModel {

    private static TodoModel sTodoModel;
    private ArrayList<Todo> mTodoList;


    public static TodoModel get(Context context) {
        if (sTodoModel == null) {
            sTodoModel = new TodoModel(context);
        }
        return sTodoModel;
    }

    private TodoModel(Context context){
        mTodoList = new ArrayList<>();
        String[] todoItems = {"Get Eggs", "Get Milk", "Complete Work", "Go to Shops", "Call Dad", "Clean Fridge", "Mow the Lawn"};

        for (int i = 0; i < 7; i++) {

            Todo todo = new Todo();
            todo.setTitle(todoItems[i]);
            todo.setDetail(todoItems[i]);
            todo.setComplete(false);
            mTodoList.add(todo);

        }
    }

    public Todo getTodo(UUID todosId) {

        for (Todo todo : mTodoList) {
            if (todo.getId().equals(todosId)){
                return todo;
            }
        }
        return null;
    }

    public ArrayList<Todo> getTodos() {
        return mTodoList;
    }
    public void addTodo(Todo todo){
        mTodoList.add(todo);
    }
    public void deleteTodo(Todo todo) {
        mTodoList.remove(todo);
    }
    public void completeTodoTrue(Todo todo) {
        todo.setComplete(true);
    }
    public void completeTodoFalse(Todo todo) {
        todo.setComplete(false);
    }
}