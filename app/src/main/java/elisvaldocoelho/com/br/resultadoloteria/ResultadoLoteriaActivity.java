package elisvaldocoelho.com.br.resultadoloteria;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResultadoLoteriaActivity extends AppCompatActivity {

    private static String TAG_CONCURSO = "concurso";
    private static String TAG_NUMERO = "numero";
    private static String TAG_CIDADE = "cidade";
    private static String TAG_LOCAL = "local";
    private static String TAG_VALOR_ACUMULADO = "valor_acumulado";
    private static String TAG_NUMEROS_SORTEADOS = "numeros_sorteados";

    ProgressDialog dialog;
    ArrayList<Concurso> concurso;

    TextView tvConcurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_loteria);

        initializer();

        new ParseJSONTask().execute();

    }

    private void initializer() {
        dialog = new ProgressDialog(this);
        concurso = new ArrayList<Concurso>();

        tvConcurso = (TextView) findViewById(R.id.tvConcurso);
    }

    private void updateUI(){
        String concursoString = "";

        for (Concurso aConcurso: concurso){
            concursoString += aConcurso.toString();
        }

        tvConcurso.setText(concursoString);
    }

    private class ParseJSONTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Carregando...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            WebServiceHandler webServiceHandler = new WebServiceHandler();
            String jsonStr = webServiceHandler.getJASJONData("http://developers.agenciaideias.com.br/loterias/megasena/json");

            try {

                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray concursoJSON = jsonObject.getJSONArray(TAG_CONCURSO);

                for (int i = 0; i < concursoJSON.length(); i++) {

                    Concurso aConcurso = new Concurso();

                    JSONObject concursoObject = concursoJSON.getJSONObject(i);

                    aConcurso.numero = concursoObject.getString(TAG_NUMERO);
                    aConcurso.cidade = concursoObject.getString(TAG_CIDADE);
                    aConcurso.local = concursoObject.getString(TAG_LOCAL);
                    aConcurso.valor_acumulado = concursoObject.getString(TAG_VALOR_ACUMULADO);
                    aConcurso.numeros_sorteados = concursoObject.getString(TAG_NUMEROS_SORTEADOS);

                    concurso.add(aConcurso);
                    Log.d("loteria", "Mega-Sena" + aConcurso);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(dialog.isShowing()){
                dialog.dismiss();
            }

            updateUI();
        }
    }

}
