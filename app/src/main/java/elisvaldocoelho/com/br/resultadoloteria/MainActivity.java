package elisvaldocoelho.com.br.resultadoloteria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnMegaSena).setOnClickListener(resultadoMegaSena());
    }

    private View.OnClickListener resultadoMegaSena(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ResultadoLoteriaActivity.class);
                startActivity(intent);

            }
        };
    }
}
