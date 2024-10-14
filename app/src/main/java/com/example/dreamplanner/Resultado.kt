package com.example.dreamplanner

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Resultado : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        // Aplica ajustes de padding baseados nas barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recebe os valores da Intent
        val valorDesejado = intent.getDoubleExtra("VALOR_DESEJADO", 0.0)
        val mesesPlanejamento = intent.getIntExtra("MESES_PLANEJAMENTO", 0)
        val ganhoMensal = intent.getDoubleExtra("GANHO_MENSAL", 0.0)
        val gastoMensal = intent.getDoubleExtra("GASTO_MENSAL", 0.0) // Corrigido para Double
        val nomeSonho = intent.getStringExtra("NOME_SONHO") ?: "seu sonho" // Corrigido para String

        // Calcula sobra e possibilidade
        val sobra = ganhoMensal - gastoMensal
        val possivel = sobra * mesesPlanejamento

        // Calcula economia necessária por mês e por dia
        val economiaMes = valorDesejado / mesesPlanejamento
        val economiaDia = economiaMes / 30.42 // Aproximando para 30 dias por mês

        // Encontra as TextViews no layout
        val tvEconomiaDia = findViewById<TextView>(R.id.exibicaodia)
        val tvEconomiaMes = findViewById<TextView>(R.id.exibicaomes)
        val possibilidade = findViewById<TextView>(R.id.possibilidade)

        // Verifica se é possível alcançar o valor desejado
        if (valorDesejado > possivel) {
            possibilidade.text =
                "Parece desafiador, mas não desista! Com planejamento e dedicação, você pode encontrar formas de se aproximar da sua meta. Vamos juntos nessa!"
        } else {
            tvEconomiaMes.text =
                "Economia por mês para alcançar sua meta \"$nomeSonho\": R$ %.2f".format(economiaMes)
            tvEconomiaDia.text =
                "Economia por dia para alcançar sua meta \"$nomeSonho\": R$ %.2f".format(economiaDia)
        }
    }
}