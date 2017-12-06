package co.tdude.splitty;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class AddFriend extends AppCompatActivity {
    private CharSequence currentSearch;
    private TableLayout resultView;
    private DatabaseManager db;
    private ArrayList<Contact> selectedContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseManager(this);
        resultView = findViewById(R.id.resultTable);
        selectedContacts = new ArrayList<>();

        final EditText myTextBox = findViewById(R.id.search);
        myTextBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                search(s);
                currentSearch = s;
            }
        });
    }

    public void search(CharSequence s){
        resultView.removeAllViews();
        final ArrayList<Contact> contacts = db.selectContactByName(s.toString());
        for (int i = 0; i < contacts.size(); i++) {
            final TableRow row = new TableRow(this);
            TextView info = new TextView(this);
            Button addBtn = new Button(this);
            info.setText(contacts.get(i).getFirstName()+" "+contacts.get(i).getLastName()+" "+
            contacts.get(i).getEmail());
            final int j = i;
            addBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    addFriend(contacts.get(j).getId(), row);
                    row.setBackgroundColor(Color.GREEN);
                }
            });
            row.addView(info);
            row.addView(addBtn);
            resultView.addView(row, i);
        }
    }

    public void addFriend(int id, TableRow row){
        if(!(selectedContacts.contains(db.selectContactById(id)))){
            selectedContacts.add((db.selectContactById(id)));
        }
        else{
            selectedContacts.remove(db.selectContactById(id));
            row.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
