package android.exercise.mini.interactions;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditTitleActivity extends AppCompatActivity {

  private boolean isEditing = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_title);

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    // setup - start from static title with "edit" button
    fabStartEdit.setVisibility(View.VISIBLE);
    fabEditDone.setVisibility(View.GONE);
    textViewTitle.setText("Page title here");
    textViewTitle.setVisibility(View.VISIBLE);
    editTextTitle.setText("Page title here");
    editTextTitle.setVisibility(View.GONE);

    // handle clicks on "start edit"
    fabStartEdit.setOnClickListener(v -> {
      fabStartEdit.animate().alpha(0f).setStartDelay(50L).setDuration(200L)
              .withEndAction(new Runnable() {
                @Override
                public void run() {fabStartEdit.setVisibility(View.INVISIBLE);}
              }).start();
      fabEditDone.setVisibility(View.VISIBLE);
      fabEditDone.animate().alpha(1f).setStartDelay(50L).setDuration(200L).start();;

      String last_static_title = textViewTitle.getText().toString();
      editTextTitle.setText(last_static_title);

      textViewTitle.setVisibility(View.INVISIBLE);
      editTextTitle.setVisibility(View.VISIBLE);

      this.isEditing = true;
    });

    // handle clicks on "done edit"
    fabEditDone.setOnClickListener(v -> {
      /*
      6. make sure that the keyboard is closed
       */
      fabEditDone.animate().alpha(0f).setStartDelay(50L).setDuration(200L)
              .withEndAction(new Runnable() {
                @Override
                public void run() {fabEditDone.setVisibility(View.INVISIBLE);}
              }).start();
      fabStartEdit.setVisibility(View.VISIBLE);
      fabStartEdit.animate().alpha(1f).setStartDelay(50L).setDuration(200L).start();;


      String user_input = editTextTitle.getText().toString();
      textViewTitle.setText(user_input);

      editTextTitle.setVisibility(View.INVISIBLE);
      textViewTitle.setVisibility(View.VISIBLE);

      editTextTitle.clearFocus();
      textViewTitle.requestFocus();

      this.isEditing = false;
    });
  }

  @Override
  public void onBackPressed() {
    // BACK button was clicked

    // find all views
    FloatingActionButton fabStartEdit = findViewById(R.id.fab_start_edit);
    FloatingActionButton fabEditDone = findViewById(R.id.fab_edit_done);
    TextView textViewTitle = findViewById(R.id.textViewPageTitle);
    EditText editTextTitle = findViewById(R.id.editTextPageTitle);

    if(this.isEditing) {
      editTextTitle.setVisibility(View.INVISIBLE);
      textViewTitle.setVisibility(View.VISIBLE);

      fabEditDone.animate().alpha(0f).setStartDelay(50L).setDuration(200L)
              .withEndAction(new Runnable() {
                @Override
                public void run() {fabEditDone.setVisibility(View.INVISIBLE);}
              }).start();
      fabStartEdit.setVisibility(View.VISIBLE);
      fabStartEdit.animate().alpha(1f).setStartDelay(50L).setDuration(200L).start();;

      this.isEditing = false;
    }
    else {
      super.onBackPressed();
    }
  }
}