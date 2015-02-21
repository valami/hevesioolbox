package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valamas.hevesitoolbox.R;

import java.text.DecimalFormat;

import hu.valamas.hevesitoolbox.szamolasok.opengl.haromszograjz;

public class haromszog extends Activity {
    DecimalFormat df = new DecimalFormat("#");
    DecimalFormat df2 = new DecimalFormat("#.##");
    DecimalFormat df3 = new DecimalFormat("##");
    double pi = 3.141592653589793238462643383279502884197;

    double a;
    double b;
    double c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haromszog);
        final EditText a_in = (EditText) findViewById(R.id.a_in);
        final EditText b_in = (EditText) findViewById(R.id.b_in);
        final EditText c_in = (EditText) findViewById(R.id.c_in);
        final EditText alfa_fok_in = (EditText) findViewById(R.id.alfa_fok_in);
        final EditText alfa_perc_in = (EditText) findViewById(R.id.alfa_perc_in);
        final EditText alfa_masod_in = (EditText) findViewById(R.id.alfa_masod_in);
        final EditText beta_fok_in = (EditText) findViewById(R.id.beta_fok_in);
        final EditText beta_perc_in = (EditText) findViewById(R.id.beta_perc_in);
        final EditText beta_masod_in = (EditText) findViewById(R.id.beta_masod_in);
        final EditText gamma_fok_in = (EditText) findViewById(R.id.gamma_fok_in);
        final EditText gamma_perc_in = (EditText) findViewById(R.id.gamma_perc_in);
        final EditText gamma_masod_in = (EditText) findViewById(R.id.gamma_masod_in);
        final TextView terulet = (TextView) findViewById(R.id.terulet);

        Button szamit = (Button) findViewById(R.id.button);
        szamit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String a_in_s = a_in.getText().toString();
                String b_in_s = b_in.getText().toString();
                String c_in_s = c_in.getText().toString();
                String alfa_fok_in_s = alfa_fok_in.getText().toString();
                String alfa_perc_in_s = alfa_perc_in.getText().toString();
                String alfa_masod_in_s = alfa_masod_in.getText().toString();
                String beta_fok_in_s = beta_fok_in.getText().toString();
                String beta_perc_in_s = beta_perc_in.getText().toString();
                String beta_masod_in_s = beta_masod_in.getText().toString();
                String gamma_fok_in_s = gamma_fok_in.getText().toString();
                String gamma_perc_in_s = gamma_perc_in.getText().toString();
                String gamma_masod_in_s = gamma_masod_in.getText().toString();

                if (!a_in_s.matches("") & !b_in_s.matches("") & !c_in_s.matches("")) {
                    //három oldal
                    a = Double.parseDouble(a_in_s);
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);

                    double alfa_e = costetel_szog(c, b, a);
                    double beta_e = costetel_szog(c, a, b);
                    double gamma_e = costetel_szog(a, b, c);

                    double[] alfa = visszavalt(alfa_e);
                    double[] beta = visszavalt(beta_e);
                    double[] gamma = visszavalt(gamma_e);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_fok_in.setText(df.format(alfa[0]));
                        alfa_perc_in.setText(df.format(alfa[1]));
                        alfa_masod_in.setText(df.format(alfa[2]));
                        beta_fok_in.setText(df.format(beta[0]));
                        beta_perc_in.setText(df3.format(beta[1]));
                        beta_masod_in.setText(df3.format(beta[2]));
                        gamma_fok_in.setText(df.format(gamma[0]));
                        gamma_perc_in.setText(df3.format(gamma[1]));
                        gamma_masod_in.setText(df3.format(gamma[2]));
                        terulet.setVisibility(View.VISIBLE);
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                    }

                } else if (!a_in_s.matches("") & !b_in_s.matches("") & (!gamma_fok_in_s.matches("") | !gamma_perc_in_s.matches("") | !gamma_masod_in_s.matches(""))) {
                    // a-b-gamma
                    if (gamma_fok_in_s.matches(""))
                    {    gamma_fok_in.setText("0");  }
                    if (gamma_perc_in_s.matches(""))
                    {    gamma_perc_in.setText("0"); }
                    if (gamma_masod_in_s.matches(""))
                    {    gamma_masod_in.setText("0");}
                    a = Double.parseDouble(a_in_s);
                    b = Double.parseDouble(b_in_s);
                    double gamma_fok = Double.parseDouble(gamma_fok_in_s);
                    double gamma_perc = Double.parseDouble(gamma_perc_in_s);
                    double gamma_masod = Double.parseDouble(gamma_masod_in_s);

                    double gamma_szog = valt(gamma_fok, gamma_perc, gamma_masod);
                    c = costetel_oldal(a, b, gamma_szog);

                    double alfa_e = costetel_szog(c, b, a);
                    double beta_e = costetel_szog(c, a, b);
                    double[] alfa = visszavalt(alfa_e);
                    double[] beta = visszavalt(beta_e);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_fok_in.setText(df.format(alfa[0]));
                        alfa_perc_in.setText(df3.format(alfa[1]));
                        alfa_masod_in.setText(df3.format(alfa[2]));
                        beta_fok_in.setText(df.format(beta[0]));
                        beta_perc_in.setText(df3.format(beta[1]));
                        beta_masod_in.setText(df3.format(beta[2]));
                        c_in.setText(df2.format(c));
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                        terulet.setVisibility(View.VISIBLE);
                    }


                } else if (!a_in_s.matches("") & !c_in_s.matches("") & (!beta_fok_in_s.matches("") | !beta_perc_in_s.matches("") | !beta_masod_in_s.matches(""))) {
                    //a-c-béta
                    if (beta_fok_in_s.matches(""))
                    {    beta_fok_in.setText("0");   }
                    if (beta_perc_in_s.matches(""))
                    {    beta_perc_in.setText("0");  }
                    if (beta_masod_in_s.matches(""))
                    {    beta_masod_in.setText("0"); }
                    a = Double.parseDouble(a_in_s);
                    c = Double.parseDouble(c_in_s);
                    double beta_fok = Double.parseDouble(beta_fok_in_s);
                    double beta_perc = Double.parseDouble(beta_perc_in_s);
                    double beta_masod = Double.parseDouble(beta_masod_in_s);

                    double beta_szog = valt(beta_fok, beta_perc, beta_masod);
                    b = costetel_oldal(a, c, beta_szog);

                    double alfa_e = costetel_szog(c, b, a);
                    double gamma_e = costetel_szog(a, b, c);
                    double[] alfa = visszavalt(alfa_e);
                    double[] gamma = visszavalt(gamma_e);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_fok_in.setText(df.format(alfa[0]));
                        alfa_perc_in.setText(df3.format(alfa[1]));
                        alfa_masod_in.setText(df3.format(alfa[2]));
                        gamma_fok_in.setText(df.format(gamma[0]));
                        gamma_perc_in.setText(df3.format(gamma[1]));
                        gamma_masod_in.setText(df3.format(gamma[2]));
                        b_in.setText(df2.format(b));
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                        terulet.setVisibility(View.VISIBLE);
                    }
                } else if (!c_in_s.matches("") & !b_in_s.matches("") & (!alfa_fok_in_s.matches("") | !alfa_perc_in_s.matches("") | !alfa_masod_in_s.matches(""))) {
                    //b-c-alfa
                    if (alfa_fok_in_s.matches(""))
                    {   alfa_fok_in.setText("0");   }
                    if (alfa_perc_in_s.matches(""))
                    {   alfa_perc_in.setText("0");  }
                    if (alfa_masod_in_s.matches(""))
                    {   alfa_masod_in.setText("0"); }
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);
                    double alfa_fok = Double.parseDouble(alfa_fok_in_s);
                    double alfa_perc = Double.parseDouble(alfa_perc_in_s);
                    double alfa_masod = Double.parseDouble(alfa_masod_in_s);

                    double alfa_szog = valt(alfa_fok, alfa_perc, alfa_masod);
                    a = costetel_oldal(b, c, alfa_szog);

                    double beta_e = costetel_szog(c, a, b);
                    double gamma_e = costetel_szog(a, b, c);
                    double[] beta = visszavalt(beta_e);
                    double[] gamma = visszavalt(gamma_e);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        beta_fok_in.setText(df.format(beta[0]));
                        beta_perc_in.setText(df3.format(beta[1]));
                        beta_masod_in.setText(df3.format(beta[2]));
                        gamma_fok_in.setText(df.format(gamma[0]));
                        gamma_perc_in.setText(df3.format(gamma[1]));
                        gamma_masod_in.setText(df3.format(gamma[2]));
                        a_in.setText(df2.format(c));
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                        terulet.setVisibility(View.VISIBLE);
                    }
                } else if (!a_in_s.matches("") &  (!beta_fok_in_s.matches("") | !beta_perc_in_s.matches("") | !beta_masod_in_s.matches("")) & (!gamma_fok_in_s.matches("")| !gamma_perc_in_s.matches("")| !gamma_masod_in_s.matches("")) ) {
                    //a -béta-gamma
                    if (beta_fok_in_s.matches(""))
                    {    beta_fok_in.setText("0");  }
                    if (beta_perc_in_s.matches(""))
                    {   beta_perc_in.setText("0");  }
                    if (beta_masod_in_s.matches(""))
                    {   beta_masod_in.setText("0"); }
                    if (gamma_fok_in_s.matches(""))
                    {    gamma_fok_in.setText("0");  }
                    if (gamma_perc_in_s.matches(""))
                    {   gamma_perc_in.setText("0");  }
                    if (gamma_masod_in_s.matches(""))
                    {   gamma_masod_in.setText("0"); }
                    a = Double.parseDouble(a_in_s);
                    double beta_fok = Double.parseDouble(beta_fok_in_s);
                    double beta_perc = Double.parseDouble(beta_perc_in_s);
                    double beta_masod = Double.parseDouble(beta_masod_in_s);
                    double gamma_fok = Double.parseDouble(gamma_fok_in_s);
                    double gamma_perc = Double.parseDouble(gamma_perc_in_s);
                    double gamma_masod = Double.parseDouble(gamma_masod_in_s);

                    double beta_szog = valt(beta_fok, beta_perc, beta_masod);
                    double gamma_szog = valt(gamma_fok, gamma_perc, gamma_masod);
                    double alfa_szog = pi-(beta_szog+gamma_szog);

                    b = szinusztetel_oldal(beta_szog,alfa_szog,a);
                    c = szinusztetel_oldal(gamma_szog,alfa_szog,a);

                    double[] alfa = visszavalt(Math.toDegrees(alfa_szog));
                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_fok_in.setText(df.format(alfa[0]));
                        alfa_perc_in.setText(df3.format(alfa[1]));
                        alfa_masod_in.setText(df3.format(alfa[2]));
                        b_in.setText(df2.format(b));
                        c_in.setText(df2.format(c));
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                        terulet.setVisibility(View.VISIBLE);
                    }
                }   else if (!b_in_s.matches("") & ( !alfa_fok_in_s.matches("") | !alfa_perc_in_s.matches("") | !alfa_masod_in_s.matches("")) & (!gamma_fok_in_s.matches("") | !gamma_perc_in_s.matches("") | !gamma_masod_in_s.matches(""))) {
                    //b -alfa-gamma
                    if (alfa_fok_in_s.matches(""))
                    {    alfa_fok_in.setText("0");  }
                    if (alfa_perc_in_s.matches(""))
                    {   alfa_perc_in.setText("0");  }
                    if (alfa_masod_in_s.matches(""))
                    {   alfa_masod_in.setText("0"); }
                    if (gamma_fok_in_s.matches(""))
                    {    gamma_fok_in.setText("0");  }
                    if (gamma_perc_in_s.matches(""))
                    {   gamma_perc_in.setText("0");  }
                    if (gamma_masod_in_s.matches(""))
                    {   gamma_masod_in.setText("0"); }

                    b = Double.parseDouble(b_in_s);
                    double alfa_fok = Double.parseDouble(alfa_fok_in_s);
                    double alfa_perc = Double.parseDouble(alfa_perc_in_s);
                    double alfa_masod = Double.parseDouble(alfa_masod_in_s);
                    double gamma_fok = Double.parseDouble(gamma_fok_in_s);
                    double gamma_perc = Double.parseDouble(gamma_perc_in_s);
                    double gamma_masod = Double.parseDouble(gamma_masod_in_s);

                    double alfa_szog = valt(alfa_fok, alfa_perc, alfa_masod);
                    double gamma_szog = valt(gamma_fok, gamma_perc, gamma_masod);
                    double beta_szog = pi-(alfa_szog+gamma_szog);

                    a = szinusztetel_oldal(alfa_szog,beta_szog,b);
                    c = szinusztetel_oldal(gamma_szog,beta_szog,b);

                    double[] beta = visszavalt(Math.toDegrees(beta_szog));
                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        beta_fok_in.setText(df.format(beta[0]));
                        beta_perc_in.setText(df3.format(beta[1]));
                        beta_masod_in.setText(df3.format(beta[2]));
                        a_in.setText(df2.format(a));
                        c_in.setText(df2.format(c));
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                        terulet.setVisibility(View.VISIBLE);
                    }
                }else if (!c_in_s.matches("") & ( !alfa_fok_in_s.matches("") | !alfa_perc_in_s.matches("") | !alfa_masod_in_s.matches("")) & (!beta_fok_in_s.matches("") | !beta_perc_in_s.matches("") | !beta_masod_in_s.matches(""))) {
                    //c -alfa-béta
                    if (beta_fok_in_s.matches(""))
                    {    beta_fok_in.setText("0");  }
                    if (beta_perc_in_s.matches(""))
                    {   beta_perc_in.setText("0");  }
                    if (beta_masod_in_s.matches(""))
                    {   beta_masod_in.setText("0"); }
                    if (alfa_fok_in_s.matches(""))
                    {    alfa_fok_in.setText("0");  }
                    if (alfa_perc_in_s.matches(""))
                    {   alfa_perc_in.setText("0");  }
                    if (alfa_masod_in_s.matches(""))
                    {   alfa_masod_in.setText("0"); }

                    c = Double.parseDouble(c_in_s);
                    double alfa_fok = Double.parseDouble(alfa_fok_in_s);
                    double alfa_perc = Double.parseDouble(alfa_perc_in_s);
                    double alfa_masod = Double.parseDouble(alfa_masod_in_s);
                    double beta_fok = Double.parseDouble(beta_fok_in_s);
                    double beta_perc = Double.parseDouble(beta_perc_in_s);
                    double beta_masod = Double.parseDouble(beta_masod_in_s);

                    double alfa_szog = valt(alfa_fok, alfa_perc, alfa_masod);
                    double beta_szog = valt(beta_fok, beta_perc, beta_masod);
                    double gamma_szog = pi-(alfa_szog+beta_szog);

                    a = szinusztetel_oldal(alfa_szog,gamma_szog,c);
                    b = szinusztetel_oldal(beta_szog,gamma_szog,c);

                    double[] gamma = visszavalt(Math.toDegrees(gamma_szog));
                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        gamma_fok_in.setText(df.format(gamma[0]));
                        gamma_perc_in.setText(df3.format(gamma[1]));
                        gamma_masod_in.setText(df3.format(gamma[2]));
                        a_in.setText(df2.format(a));
                        b_in.setText(df2.format(b));
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                        terulet.setVisibility(View.VISIBLE);
                    }
                }else if (!a_in_s.matches("") & !b_in_s.matches("") & ( !alfa_fok_in_s.matches("") | !alfa_perc_in_s.matches("") | !alfa_masod_in_s.matches(""))) {
                    //a b-alfa -a nagyobb
                    if (alfa_fok_in_s.matches(""))
                    {    alfa_fok_in.setText("0");  }
                    if (alfa_perc_in_s.matches(""))
                    {   alfa_perc_in.setText("0");  }
                    if (alfa_masod_in_s.matches(""))
                    {   alfa_masod_in.setText("0"); }
                    a = Double.parseDouble(a_in_s);
                    b = Double.parseDouble(b_in_s);
                    if (a>b) {
                        double alfa_fok = Double.parseDouble(alfa_fok_in_s);
                        double alfa_perc = Double.parseDouble(alfa_perc_in_s);
                        double alfa_masod = Double.parseDouble(alfa_masod_in_s);

                        double alfa_szog = valt(alfa_fok, alfa_perc, alfa_masod);
                        double beta_szog = szinusztetel_szog(a,b,alfa_szog);
                        double gamma_szog = pi-(alfa_szog+beta_szog);
                        c = costetel_oldal(a, b, gamma_szog);

                        double[] gamma = visszavalt(Math.toDegrees(gamma_szog));
                        double[] beta = visszavalt(Math.toDegrees(beta_szog));
                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                        } else {
                            beta_fok_in.setText(df.format(beta[0]));
                            beta_perc_in.setText(df3.format(beta[1]));
                            beta_masod_in.setText(df3.format(beta[2]));
                            gamma_fok_in.setText(df.format(gamma[0]));
                            gamma_perc_in.setText(df3.format(gamma[1]));
                            gamma_masod_in.setText(df3.format(gamma[2]));
                            c_in.setText(df2.format(c));
                            terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                            terulet.setVisibility(View.VISIBLE);
                        }
                    }
                }else if (!a_in_s.matches("") & !b_in_s.matches("") & ( !beta_fok_in_s.matches("") | !beta_perc_in_s.matches("") | !beta_masod_in_s.matches(""))) {
                //a b-béta -b nagyobb
                    if (beta_fok_in_s.matches(""))
                    {    beta_fok_in.setText("0");  }
                    if (beta_perc_in_s.matches(""))
                    {   beta_perc_in.setText("0");  }
                    if (beta_masod_in_s.matches(""))
                    {   beta_masod_in.setText("0"); }
                a = Double.parseDouble(a_in_s);
                b = Double.parseDouble(b_in_s);
                if (a<b) {
                    double beta_fok = Double.parseDouble(beta_fok_in_s);
                    double beta_perc = Double.parseDouble(beta_perc_in_s);
                    double beta_masod = Double.parseDouble(beta_masod_in_s);

                    double beta_szog = valt(beta_fok, beta_perc, beta_masod);
                    double alfa_szog = szinusztetel_szog(a, b, beta_szog);
                    double gamma_szog = pi - (alfa_szog + beta_szog);
                    c = costetel_oldal(a, b, gamma_szog);

                    double[] gamma = visszavalt(Math.toDegrees(gamma_szog));
                    double[] alfa = visszavalt(Math.toDegrees(alfa_szog));
                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_fok_in.setText(df.format(alfa[0]));
                        alfa_perc_in.setText(df3.format(alfa[1]));
                        alfa_masod_in.setText(df3.format(alfa[2]));
                        gamma_fok_in.setText(df.format(gamma[0]));
                        gamma_perc_in.setText(df3.format(gamma[1]));
                        gamma_masod_in.setText(df3.format(gamma[2]));
                        c_in.setText(df2.format(c));
                        terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                        terulet.setVisibility(View.VISIBLE);
                    }
                }
                }else if (!a_in_s.matches("") & !c_in_s.matches("") & (!alfa_fok_in_s.matches("") | !alfa_perc_in_s.matches("") | !alfa_masod_in_s.matches(""))) {
                    //a-c-alfa-a nagyobb
                    if (alfa_fok_in_s.matches(""))
                    {    alfa_fok_in.setText("0");  }
                    if (alfa_perc_in_s.matches(""))
                    {   alfa_perc_in.setText("0");  }
                    if (alfa_masod_in_s.matches(""))
                    {   alfa_masod_in.setText("0"); }
                    a = Double.parseDouble(a_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (c<a) {
                        double alfa_fok = Double.parseDouble(alfa_fok_in_s);
                        double alfa_perc = Double.parseDouble(alfa_perc_in_s);
                        double alfa_masod = Double.parseDouble(alfa_masod_in_s);

                        double alfa_szog = valt(alfa_fok, alfa_perc, alfa_masod);
                        double gamma_szog = szinusztetel_szog(c,a,alfa_szog);
                        double beta_szog = pi-(alfa_szog+gamma_szog);
                        b = costetel_oldal(a, c, beta_szog);

                        double[] gamma = visszavalt(Math.toDegrees(gamma_szog));
                        double[] beta = visszavalt(Math.toDegrees(beta_szog));
                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                        } else {
                            beta_fok_in.setText(df.format(beta[0]));
                            beta_perc_in.setText(df3.format(beta[1]));
                            beta_masod_in.setText(df3.format(beta[2]));
                            gamma_fok_in.setText(df.format(gamma[0]));
                            gamma_perc_in.setText(df3.format(gamma[1]));
                            gamma_masod_in.setText(df3.format(gamma[2]));
                            b_in.setText(df2.format(b));
                            terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                            terulet.setVisibility(View.VISIBLE);
                        }
                    }

                }else if (!a_in_s.matches("") & !c_in_s.matches("") & (!gamma_fok_in_s.matches("") | !gamma_perc_in_s.matches("") | !gamma_masod_in_s.matches(""))) {
                    //a c-gamma -c nagyobb
                    if (gamma_fok_in_s.matches(""))
                    {    gamma_fok_in.setText("0");  }
                    if (gamma_perc_in_s.matches(""))
                    {   gamma_perc_in.setText("0");  }
                    if (gamma_masod_in_s.matches(""))
                    {   gamma_masod_in.setText("0"); }
                    a = Double.parseDouble(a_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (a<c) {
                        double gamma_fok = Double.parseDouble(gamma_fok_in_s);
                        double gamma_perc = Double.parseDouble(gamma_perc_in_s);
                        double gamma_masod = Double.parseDouble(gamma_masod_in_s);

                        double gamma_szog = valt(gamma_fok, gamma_perc, gamma_masod);
                        double alfa_szog = szinusztetel_szog(a,c,gamma_szog);
                        double beta_szog = pi-(alfa_szog+gamma_szog);
                        b = costetel_oldal(a, c, beta_szog);

                        double[] alfa = visszavalt(Math.toDegrees(alfa_szog));
                        double[] beta = visszavalt(Math.toDegrees(beta_szog));
                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                        } else {
                            beta_fok_in.setText(df.format(beta[0]));
                            beta_perc_in.setText(df3.format(beta[1]));
                            beta_masod_in.setText(df3.format(beta[2]));
                            alfa_fok_in.setText(df.format(alfa[0]));
                            alfa_perc_in.setText(df3.format(alfa[1]));
                            alfa_masod_in.setText(df3.format(alfa[2]));
                            b_in.setText(df2.format(b));
                            terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                            terulet.setVisibility(View.VISIBLE);
                        }
                    }

                }else if (!b_in_s.matches("") & !c_in_s.matches("") & (!beta_fok_in_s.matches("") | !beta_perc_in_s.matches("") | !beta_masod_in_s.matches(""))) {
                    //b c-beta -b nagyobb
                    if (beta_fok_in_s.matches(""))
                    {    beta_fok_in.setText("0");  }
                    if (beta_perc_in_s.matches(""))
                    {   beta_perc_in.setText("0");  }
                    if (beta_masod_in_s.matches(""))
                    {   beta_masod_in.setText("0"); }
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (c<b) {
                        double beta_fok = Double.parseDouble(beta_fok_in_s);
                        double beta_perc = Double.parseDouble(beta_perc_in_s);
                        double beta_masod = Double.parseDouble(beta_masod_in_s);

                        double beta_szog = valt(beta_fok, beta_perc, beta_masod);
                        double gamma_szog = szinusztetel_szog(c,b,beta_szog);
                        double alfa_szog = pi-(beta_szog+gamma_szog);
                        a = costetel_oldal(b, c, alfa_szog);

                        double[] alfa = visszavalt(Math.toDegrees(alfa_szog));
                        double[] gamma = visszavalt(Math.toDegrees(gamma_szog));
                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                        } else {
                            gamma_fok_in.setText(df.format(gamma[0]));
                            gamma_perc_in.setText(df3.format(gamma[1]));
                            gamma_masod_in.setText(df3.format(gamma[2]));
                            alfa_fok_in.setText(df.format(alfa[0]));
                            alfa_perc_in.setText(df3.format(alfa[1]));
                            alfa_masod_in.setText(df3.format(alfa[2]));
                            a_in.setText(df2.format(a));
                            terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                            terulet.setVisibility(View.VISIBLE);
                        }
                    }

                }else if (!b_in_s.matches("") & !c_in_s.matches("") & (!gamma_fok_in_s.matches("") | !gamma_perc_in_s.matches("") | !gamma_masod_in_s.matches(""))) {
                    //b c-gamma -c nagyobb
                    if (gamma_fok_in_s.matches(""))
                    {    gamma_fok_in.setText("0");  }
                    if (gamma_perc_in_s.matches(""))
                    {   gamma_perc_in.setText("0");  }
                    if (gamma_masod_in_s.matches(""))
                    {   gamma_masod_in.setText("0"); }
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (b<c) {
                        double gamma_fok = Double.parseDouble(gamma_fok_in_s);
                        double gamma_perc = Double.parseDouble(gamma_perc_in_s);
                        double gamma_masod = Double.parseDouble(gamma_masod_in_s);

                        double gamma_szog = valt(gamma_fok, gamma_perc, gamma_masod);
                        double beta_szog = szinusztetel_szog(b,c,gamma_szog);
                        double alfa_szog = pi-(beta_szog+gamma_szog);
                        a = costetel_oldal(b, c, alfa_szog);

                        double[] alfa = visszavalt(Math.toDegrees(alfa_szog));
                        double[] beta = visszavalt(Math.toDegrees(beta_szog));
                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "A Háromszög-egyenlőtlenség nem teljesül!", Toast.LENGTH_SHORT).show();
                        } else {
                            beta_fok_in.setText(df.format(beta[0]));
                            beta_perc_in.setText(df3.format(beta[1]));
                            beta_masod_in.setText(df3.format(beta[2]));
                            alfa_fok_in.setText(df.format(alfa[0]));
                            alfa_perc_in.setText(df3.format(alfa[1]));
                            alfa_masod_in.setText(df3.format(alfa[2]));
                            a_in.setText(df2.format(a));
                            terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                            terulet.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else {
                    {
                        Toast.makeText(getApplicationContext(),
                                "Túl kevés adat !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public double valt(double fok, double perc, double masod) {
        return (Math.toRadians(fok + (perc / 60) + (masod / 3600)));
    }
    public double[] visszavalt(double szog) {
        double fok = Math.floor(szog);
        double t1 = (szog - (Math.floor(szog))) * 60;
        double perc = (Math.floor((szog - (Math.floor(szog))) * 60));
        double masod = (t1 - perc) * 60;
        double[] valt = new double[3];
        valt[0] = fok;
        valt[1] = perc;
        valt[2] = masod;
        return (valt);
    }
    public double heron(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return (Math.sqrt(s * (s - a) * (s - b) * (s - c)));
    }
    public double costetel_szog(double a, double b, double c) {
        return (Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b))));
    }
    public double costetel_oldal(double a, double b, double gamma) {
        return (Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - 2 * a * b * (Math.cos(gamma))));
    }
    public double szinusztetel_szog(double tavfelso, double tavalso, double szogalso) {
        return (Math.asin((tavfelso / tavalso) *Math.sin(szogalso)));
    }
    public double szinusztetel_oldal (double szogfelso, double szogalso, double tavalso) {
        return ((Math.sin(szogfelso)/Math.sin(szogalso) )*tavalso);
    }
    public int egyenlotlen (double a ,double b ,double c){
        int  i = 0 ;
        if ( (a+b)>c ){
            i = i+1 ;
        }
        if ( (a+c)>b ){
            i = i+1 ;
        }
        if ( (b+c)>a ){
            i = i+1 ;
        }
        return (i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_haromszog, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return true;
        }
        if (id ==R.id.action_rajz)
        {
            if ( (a!=0) & (b!=0) & (c!=0) ) {
                Intent intent = new Intent(haromszog.this, haromszograjz.class);
                double[] message = new double[3];
                message[0] = a;
                message[1] = b;
                message[2] = c;
                intent.putExtra("numbers", message);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Elöször add meg a háromszög adatait !", Toast.LENGTH_SHORT).show();
                }
            }
        return super.onOptionsItemSelected(item);
    }
}