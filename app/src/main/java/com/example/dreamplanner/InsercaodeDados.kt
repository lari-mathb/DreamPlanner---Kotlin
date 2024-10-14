package com.example.dreamplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InsercaodeDados : AppCompatActivity() {

    lateinit var voltar: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insercaode_dados)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        voltar = findViewById(R.id.voltar)
        voltar.setOnClickListener { finish() }

        val botaoPlanejar = findViewById<ImageButton>(R.id.botaoPlanejar)
        val resultadodonomedameta = findViewById<EditText>(R.id.resultadonomedameta)
        val resultadoreceitamensal = findViewById<EditText>(R.id.resultadoreceitamensal)
        val resultadodespesamensal = findViewById<EditText>(R.id.resultadodespesamensal)
        val resultadovalordesejado = findViewById<EditText>(R.id.resultadovalordesejado)
        val resultadomesesdeplanejamento = findViewById<EditText>(R.id.resultadomesesdeplanejamento)


        // Ação quando o botão é clicado
        botaoPlanejar.setOnClickListener {
            val nomeSonho = resultadodonomedameta.text.toString()
            val valorEconomizar = resultadovalordesejado.text.toString().toDoubleOrNull()
            val tempoMeses = resultadomesesdeplanejamento.text.toString().toIntOrNull()
            val ganhoMensal = resultadoreceitamensal.text.toString().toDoubleOrNull()
            val gastoMensal = resultadodespesamensal.text.toString().toDoubleOrNull()


            val intent = Intent(this, Resultado::class.java).apply {
                putExtra("VALOR_DESEJADO", valorEconomizar)
                putExtra("MESES_PLANEJAMENTO", tempoMeses)
                putExtra("GANHO_MENSAL", ganhoMensal)
                putExtra("GASTO_MENSAL", gastoMensal)
                putExtra("NOME_SONHO", nomeSonho)
            }
            startActivity(intent)


        }
    }
}



