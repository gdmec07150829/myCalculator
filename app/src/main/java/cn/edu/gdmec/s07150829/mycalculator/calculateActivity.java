package cn.edu.gdmec.s07150829.mycalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class calculateActivity extends AppCompatActivity {
    private Button calculateButton;
    private EditText weightEdittext;
    private RadioButton manradiobutton;
    private RadioButton womanradiobutton;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        calculateButton = (Button) findViewById(R.id.calculate);
        weightEdittext = (EditText) findViewById(R.id.weight);
        manradiobutton = (RadioButton) findViewById(R.id.man);
        womanradiobutton = (RadioButton) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        calculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!weightEdittext.getText().toString().trim().equals("")){
                    if (manradiobutton.isChecked()||womanradiobutton.isChecked()){
                            Double weight = Double.parseDouble(weightEdittext.getText().toString());
                            StringBuffer sb = new StringBuffer();
                            sb.append("-----------评估结果------------\n");
                        if (manradiobutton.isChecked()){
                            sb.append("男性标准身高：");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int) result+"cm");
                        }else {
                            sb.append("女性标准身高:");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int) result+"cm");
                        }
                        resultTextView.setText(sb.toString());
                    }else {
                        showMessage("请选择性别！");
                    }
                }else {
                    showMessage("请选择体重！");
                }
            }
        });
    }
    private void showMessage(String message) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){

            }
        });
        alert.show();
    }

    private double evaluateHeight( double weight ,String sex) {
     double height;
        if (sex == "男"){
            height = 170-(62-weight)/0.6;

        }else {
            height = 158-(52-weight)/0.5;

        }
        return  height;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
