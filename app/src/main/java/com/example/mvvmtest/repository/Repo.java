package com.example.mvvmtest.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mvvmtest.model.QuestionModel;
import com.example.mvvmtest.model.QuizItemModel;
import com.example.mvvmtest.model.ResultModel;
import com.example.mvvmtest.model.UserModel;
import com.example.mvvmtest.viewmodel.QuizItemViewModel;
import com.google.android.gms.measurement.internal.AppMeasurementDynamiteService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Repo {
    public static final String QUIZ_ITEM_LIST = "QuizItemList";
    public static final String USER_LIST = "UserList";
    public static Repo instance;
    private int currentuser_id;
    private ArrayList<UserModel> userList = new ArrayList<>();
    private ArrayList<QuizItemModel> quizItemModels = new ArrayList<>();
    public static Repo getInstance(){
        if (instance==null){
            instance = new Repo();
        }
        return instance;
    }

    public int getCurrentuser_id() {
        return currentuser_id;
    }

    public void setCurrentuser_id(int currentuser_id) {
        this.currentuser_id = currentuser_id;
    }

    private Repo(){
        loadUsers();
        sendQuizItems();
        loadQuizItems();
    }
    public ArrayList<UserModel> getUserList(){
        loadUsers();
        return userList;
    }

    public ArrayList<QuizItemModel> getQuizItemModels() {
        loadQuizItems();
        return quizItemModels;
    }

    public UserModel getUserById(int id){
        loadUsers();

        for(UserModel model : userList){
            if (model.getUser_id() == id){
                return model;
            }
        }
        return null;
    }
    public void addUser(UserModel model){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(USER_LIST).child(String.valueOf(model.getUser_id())).setValue(model);
    }
    private void loadUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child(USER_LIST);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    userList.add(dataSnapshot.getValue(UserModel.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updateUserById(UserModel model , int id){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(USER_LIST).child(id+"").removeValue();
        reference.child(USER_LIST).child(id+"").setValue(model);
    }
    public void addResultToUser(ResultModel resultModel , int userId){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        UserModel userModel =  getUserById(userId);
        int count = userModel.getResultList().size();
        reference.child(USER_LIST).child(userId + "").child("resultList").child(count+"").setValue(resultModel);
    }
    private void loadQuizItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child(QUIZ_ITEM_LIST);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quizItemModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    QuizItemModel model = dataSnapshot.getValue(QuizItemModel.class);
                    quizItemModels.add(model);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void sendQuizItems(){

        ArrayList<QuestionModel> arrayList = new ArrayList<>();
        arrayList.add(new QuestionModel("Столица Боливии - Сукре?" , true));
        arrayList.add(new QuestionModel("Столица Ватикана - Габороне?" , false));
        arrayList.add(new QuestionModel("Столица Гвинеи - Бисау?" , false));
        arrayList.add(new QuestionModel("Столица Дании - Копенгаген?" , true));
        arrayList.add(new QuestionModel("Столица Индии - Нью-Дели?" , true));
        arrayList.add(new QuestionModel("Столица Коморы - Киншаса?" , false));
        arrayList.add(new QuestionModel("Столица Лаоса - Вьентьян?" , true));
        arrayList.add(new QuestionModel("Столица Люксембург - Вильнюс?" , false));
        arrayList.add(new QuestionModel("Столица Мозамбика - Мапуту?" , true));
        arrayList.add(new QuestionModel("Столица Монголии - Батор?" , true));
        QuizItemModel quizItemModel = new QuizItemModel("Столицы(сложно)" , arrayList);

        ArrayList<QuestionModel> arrayList1 = new ArrayList<>();
        arrayList1.add(new QuestionModel("Столица Китая - Пекин?" , true));
        arrayList1.add(new QuestionModel("Столица России - Санкт-Петербург?" , false));
        arrayList1.add(new QuestionModel("Столица Турции - Стамбул?" , false));
        arrayList1.add(new QuestionModel("Столица Италии - Рим?" , true));
        arrayList1.add(new QuestionModel("Столица Ирана - Тегеран?" , true));
        arrayList1.add(new QuestionModel("Столица США - Нью-Йорк?" , false));
        arrayList1.add(new QuestionModel("Столица Бельгии - Брюссель?" , true));
        arrayList1.add(new QuestionModel("Столица Сингапура - Дакар?" , false));
        arrayList1.add(new QuestionModel("Столица Японии - Токио?" , true));
        arrayList1.add(new QuestionModel("Столица Польши - Варшава?" , true));
        QuizItemModel quizItemModel1 = new QuizItemModel("Столицы(легко)" , arrayList1);

        ArrayList<QuestionModel> arrayList2 = new ArrayList<>();
        arrayList2.add(new QuestionModel("4 + 5 = 10?" , false));
        arrayList2.add(new QuestionModel("Формула периметра: a + b * 2?" , false));
        arrayList2.add(new QuestionModel("23 - 34 = -11?" , true));
        arrayList2.add(new QuestionModel("18 + 22 = 30?" , false));
        arrayList2.add(new QuestionModel("42 + 23 = 65?" , true));
        arrayList2.add(new QuestionModel("Сумма углов квадрата: 420 градусов?" , false));
        arrayList2.add(new QuestionModel("12*4 = 48" , true));
        arrayList2.add(new QuestionModel("Число пи = 3.12?" , false));
        arrayList2.add(new QuestionModel("23 * 5 = 115?" , true));
        arrayList2.add(new QuestionModel("53 - 23 = 30" , true));
        QuizItemModel quizItemModel2 = new QuizItemModel("Матиматика" , arrayList2);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child(QUIZ_ITEM_LIST).removeValue();
        reference.child(QUIZ_ITEM_LIST).child(quizItemModel.getName()).setValue(quizItemModel);
        reference.child(QUIZ_ITEM_LIST).child(quizItemModel1.getName()).setValue(quizItemModel1);
        reference.child(QUIZ_ITEM_LIST).child(quizItemModel2.getName()).setValue(quizItemModel2);
    }

}
