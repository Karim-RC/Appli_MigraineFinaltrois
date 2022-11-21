package fr.karimrc.appli_migrainefinal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.*
import java.util.*

const val CHOIX_DATE = ""

class VraiMigraineApp : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vrai_migraine_app)

        val mPickTimeBtn = findViewById<Button>(R.id.pickDateBtn)
        val textView = findViewById<TextView>(R.id.dateTT)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        textView.setText("$day / ${month + 1} / $year")

        mPickTimeBtn.setOnClickListener {

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView

                    textView.setText("$dayOfMonth / ${monthOfYear + 1} / $year")
                },
                year,
                month,
                day
            )
            dpd.show()

            val Intensite = resources.getStringArray(R.array.Migraine_Intensite)
            val AINS = resources.getStringArray(R.array.AINS)

            var spinner = findViewById<Spinner>(R.id.Intensité)
            if (spinner != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, Intensite
                )
                spinner.adapter = adapter

                spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
            }

            spinner = findViewById<Spinner>(R.id.spinnerMedoc)
            if (spinner != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, AINS
                )
                spinner.adapter = adapter

                spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
            }
            val button = findViewById<Button>(R.id.boutonEnvoyer)

            button.setOnClickListener {
                sendMessage()
            }
        }
    }

    @SuppressLint("WrongViewCast")
    private fun sendMessage() {
        TODO("Not yet implemented")

        val Date = findViewById<TextView>(R.id.dateTT)
        val messageDate = Date.text.toString()

        val intensite = findViewById<Spinner>(R.id.IntensiteMigraine)
        val messageIntensite = intensite.selectedItem.toString()

        var sharedpreferences = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedpreferences.edit()) {
            putString(CHOIX_DATE, messageDate)
            apply()
        }
    }
}
