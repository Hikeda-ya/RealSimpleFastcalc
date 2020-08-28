package app.totemkey.realsimplefastcalc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import static app.totemkey.realsimplefastcalc.MainActivity.getTimepickerhour;
import static app.totemkey.realsimplefastcalc.MainActivity.getTimepikerminute;
import static app.totemkey.realsimplefastcalc.MainActivity.setKeypad_activated;

public class NewActivity extends AppCompatActivity {

    public String date;

    private static Boolean can_date_be_set;

    public static Boolean getCan_date_be_set() {
        return can_date_be_set;
    }

    public static void setCan_date_be_set(Boolean can_date_be_set) {
        NewActivity.can_date_be_set = can_date_be_set;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setKeypad_activated(false);

        Bundle extras = getIntent().getExtras();                                                    //trae datos del otro MainActivity
        assert extras != null;
        String txt = extras.getString("resultado");
        String newDate = extras.getString("datecalendar");
        date = newDate + " " + getTimepickerhour() + ":" + getTimepikerminute();

        //newDate
        //("dd/MM/yyyy hh:mm")

        TextView tv2 = findViewById(R.id.textView2);                                     //levanta el valor del TXT
        tv2.setText(txt);
    }

    public void openOldActivity(View view) {                                                     //vuelve al layout anterior
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    @SuppressLint("SimpleDateFormat")
    public void AddCalendarEvent(View view) throws ParseException {
        if (getCan_date_be_set()) {
            //crea un calendar
            Calendar calendarEvent = Calendar.getInstance();
            calendarEvent.setTime(Objects.requireNonNull(new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(date)));
            Intent i = new Intent(Intent.ACTION_EDIT);
            i.setType("vnd.android.cursor.item/event");
            i.putExtra("beginTime", calendarEvent.getTimeInMillis());
            i.putExtra("allDay", false);
            i.putExtra("title", "END FAST!");
            i.putExtra("endTime", calendarEvent.getTimeInMillis());
            startActivity(i);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "It's not healthy, trust me...";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}

