package hello.ivan.com.br.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Button;

import hello.ivan.com.br.calc.R;

public class MainActivity extends Activity
{
    TextView textView;
    String currentString="0",previusString=null;
    boolean isTempStringShown=false;
    int currentopperand=0;
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        int numberButtons[]={R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};
        NumberButtonClickListener numberClickListener=new NumberButtonClickListener();
        for(int id:numberButtons)
        { View v=findViewById(id);
            v.setOnClickListener(numberClickListener);
        }
        int opperandButtons[]={R.id.buttonPlus,R.id.buttonMinus,R.id.buttonDivide,R.id.buttonTimes,R.id.buttonDecimal,R.id.buttonClear,R.id.buttonEquals};
        OpperandButtonClickListener oppClickListener=new OpperandButtonClickListener();
        for(int id:opperandButtons)
        { View v=findViewById(id);
            v.setOnClickListener(oppClickListener);
        }
        setCurrentString("0");
    }
    void setCurrentString(String s)
    { currentString=s;
        textView.setText(s);
    }
    class NumberButtonClickListener implements OnClickListener
    { @Override public void onClick(View v)
    { if(isTempStringShown)
    { previusString=currentString;
        currentString="0";
        isTempStringShown=false;
    }
        String text=(String)((Button)v).getText();
        if(currentString.equals("0"))setCurrentString(text);
        else setCurrentString(currentString+text);
    }
    }
    class OpperandButtonClickListener implements OnClickListener
    { @Override public void onClick(View v)
    { int id=v.getId();
        if(id==R.id.buttonClear)
        { isTempStringShown=false;
            setCurrentString("0");
            previusString=null;
        }
        if(id==R.id.buttonDecimal)if(!currentString.contains("."))setCurrentString(currentString+".");
        if(id==R.id.buttonPlus||id==R.id.buttonMinus||id==R.id.buttonTimes||id==R.id.buttonDivide)
        { currentopperand=id;
            previusString=currentString;
            isTempStringShown=true;
        }
        if(id==R.id.buttonEquals)
        { double curr=Double.parseDouble(currentString);
            double result=0;
            if(previusString!=null)
            { double prev=Double.parseDouble(previusString);
                switch(currentopperand)
                { case R.id.buttonPlus: result=prev+curr; break;
                    case R.id.buttonMinus: result=prev-curr; break;
                    case R.id.buttonTimes: result=prev*curr; break;
                    case R.id.buttonDivide: result=prev/curr; break;
                }
            }
            setCurrentString(Double.toString(result));
        }
    }
    }
}