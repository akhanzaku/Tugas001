package id.sch.smktelkom_mlg.tugas01.xirpl3008.reservasitiketgalasesa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText isinama, isialamat, isino;
    RadioButton rbLaki, rbPr;
    Button bOK;
    TextView tvHasil, tvJk;
    CheckBox cbSilver, cbGold, cbRuby;
    Spinner spJurusan, spKelas;
    String arKelas[][] = {{"X-RPL1", "X-RPL2", "X-RPL3", "X-RPL4", "X-RPL5", "X-RPL6", "X-RPL1", "XI-RPL2", "XI-RPL3", "XI-RPL4", "XI-RPL5", "XI-RPL6", "XII-RPL1", "XII-RPL2", "XII-RPL3", "XII-RPL4", "XII-RPL5"},
            {"X-TKJ1", "X-TKJ2", "X-TKJ3", "X-TKJ4", "X-TKJ5", "X-TKJ6", "XI-TKJ1", "XI-TKJ2", "XI-TKJ3", "XI-TKJ4", "XI-TKJ5", "XII-TKJ1", "XII-TKJ2", "XII-TKJ3", "XII-TKJ4", "XII-TKJ5"}};
    ArrayList<String> listKelas = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isinama = (EditText) findViewById(R.id.editTextNama);
        isialamat = (EditText) findViewById(R.id.editTextAlamat);
        isino = (EditText) findViewById(R.id.editTextNo);
        rbLaki = (RadioButton) findViewById(R.id.radioButtonLaki);
        rbPr = (RadioButton) findViewById(R.id.radioButtonPerempuan);
        bOK = (Button) findViewById(R.id.buttonSubmit);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvJk = (TextView) findViewById(R.id.textView2);
        spJurusan = (Spinner) findViewById(R.id.spinnerJurusan);
        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        cbSilver = (CheckBox) findViewById(R.id.checkBoxSilver);
        cbGold = (CheckBox) findViewById(R.id.checkBoxGold);
        cbRuby = (CheckBox) findViewById(R.id.checkBoxRuby);

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKelas.setAdapter(adapter);

        spJurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listKelas.clear();
                listKelas.addAll(Arrays.asList(arKelas[pos]));
                adapter.notifyDataSetChanged();
                spKelas.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void doClick() {
        if (isValid()) {
            //edit text
            String nama = isinama.getText().toString();
            String alamat = isialamat.getText().toString();
            String no = isino.getText().toString();
            //radio button
            String jk = "(Not choosen)";

            if (rbLaki.isChecked()) {
                jk = rbLaki.getText().toString();
            } else if (rbPr.isChecked()) {
                jk = rbPr.getText().toString();
            }

            //spinner
            String kelas = spKelas.getSelectedItem().toString();

            //checkbox
            String bidang = "Tipe tiket yang anda pilih :  :\n";
            int startlen = bidang.length();
            if (cbSilver.isChecked()) bidang += cbSilver.getText() + "\n";
            if (cbGold.isChecked()) bidang += cbGold.getText() + "\n";
            if (cbRuby.isChecked()) bidang += cbRuby.getText() + "\n";

            if (bidang.length() == startlen) bidang += "(No object was choosen)";

            //hasil
            tvHasil.setText("Nama: " + nama + "\n" + "Alamat: " + alamat + "\n" + "No. Handphone: " + no + "\n" + "Jenis Kelamin: " + jk + "\n"
                    + "Kelas: " + kelas + "\n" + bidang);
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = isinama.getText().toString();
        String alamat = isialamat.getText().toString();
        String no = isino.getText().toString();
        String jk = "";

        if (nama.isEmpty()) {
            isinama.setError("Nama harus diisi");
            valid = false;
        } else if (nama.length() < 5) {
            isinama.setError("Nama minimal 5 karakter");
            valid = false;
        } else {
            isinama.setError(null);
        }

        if (alamat.isEmpty()) {
            isialamat.setError("Alamat harus diisi");
            valid = false;
        } else if (alamat.length() < 5) {
            isialamat.setError("Alamat minimal 5 karakter");
            valid = false;
        } else {
            isialamat.setError(null);
        }

        if (no.isEmpty()) {
            isino.setError("Nomor Handphone harus diisi");
            valid = false;
        } else if (no.length() < 11) {
            isino.setError("Nomor Handphone minimal 11 karakter");
            valid = false;
        } else {
            isino.setError(null);
        }


        if (jk == null) {
            tvJk.setError("");
            valid = false;
        } else {
            tvJk.setError(null);
        }
        return valid;
    }

}
