package com.nirvanaapp.sourcekode.demoasra;

        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;

        import java.util.ArrayList;

        import static com.nirvanaapp.sourcekode.demoasra.DatabaseHelper.Col2;
        import static com.nirvanaapp.sourcekode.demoasra.DatabaseHelper.Col3;
        import static com.nirvanaapp.sourcekode.demoasra.DatabaseHelper.DATABASE_NAME;
        import static com.nirvanaapp.sourcekode.demoasra.DatabaseHelper.TABLE;

public class EmployeeActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    EAdapter adapter;
    ArrayList<EmployeeModel> myList = new ArrayList<>();

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EAdapter(myList, new EAdapter.ItemListener() {
            @Override
            public void onItemClick(EmployeeModel item) {

            }

            @Override
            public void onDeleteItem(EmployeeModel item) {

            }
        });
        recyclerView.setAdapter(adapter);

        db = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        getData();

    }

    public void getData(){

        String mQuery = "SELECT * FROM "+TABLE+"";

        Cursor cursor = db.rawQuery(mQuery,null);

        if (cursor != null)
        if(cursor.moveToFirst()){
            do {
                EmployeeModel data = new EmployeeModel();
                data.setName(cursor.getString(1));
                data.setDept(cursor.getString(2));
                data.setSalary(cursor.getString(3));
                myList.add(data);
            }while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }



}
