package com.example.programmerscalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class NumberSystem(val radix: Int) {
        HEX(16), DEC(10), OCT(8), BIN(2)
    }

    enum class Operator {
        AND, OR, NOT, NAND, NOR, XOR, ADD, SUB, MUL, DIV
    }

    private var currentNumberSys: NumberSystem = NumberSystem.DEC
    private var currentOperator: Operator? = null

    var isCalcPending = false
    var prevResult = 0
    var clearInput = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set Result Text
        tvResult.text = "0"

        // set number system setCurrentNumberSystem
        setCurrentNumberSystem(currentNumberSys)

        // Number System Button click events

        btnForHex.setOnClickListener {
            setCurrentNumberSystem(NumberSystem.HEX)
        }

        btnForDec.setOnClickListener {
            setCurrentNumberSystem(NumberSystem.DEC)
        }

        btnForOct.setOnClickListener {
            setCurrentNumberSystem(NumberSystem.OCT)
        }

        btnForBin.setOnClickListener {
            setCurrentNumberSystem(NumberSystem.BIN)
        }

        // input button click events

        inputBtnDouble0.setOnClickListener {
            inputReceived(inputBtnDouble0.text)
        }

        inputBtn0.setOnClickListener {
            inputReceived(inputBtn0.text)
        }

        inputBtn1.setOnClickListener {
            inputReceived(inputBtn1.text)
        }

        inputBtn2.setOnClickListener {
            inputReceived(inputBtn2.text)
        }

        inputBtn3.setOnClickListener {
            inputReceived(inputBtn3.text)
        }

        inputBtn4.setOnClickListener {
            inputReceived(inputBtn4.text)
        }

        inputBtn5.setOnClickListener {
            inputReceived(inputBtn5.text)
        }

        inputBtn6.setOnClickListener {
            inputReceived(inputBtn6.text)
        }

        inputBtn7.setOnClickListener {
            inputReceived(inputBtn7.text)
        }

        inputBtn8.setOnClickListener {
            inputReceived(inputBtn8.text)
        }

        inputBtn9.setOnClickListener {
            inputReceived(inputBtn9.text)
        }

        inputBtnA.setOnClickListener {
            inputReceived(inputBtnA.text)
        }

        inputBtnB.setOnClickListener {
            inputReceived(inputBtnB.text)
        }

        inputBtnC.setOnClickListener {
            inputReceived(inputBtnC.text)
        }

        inputBtnD.setOnClickListener {
            inputReceived(inputBtnD.text)
        }

        inputBtnE.setOnClickListener {
            inputReceived(inputBtnE.text)
        }

        inputBtnF.setOnClickListener {
            inputReceived(inputBtnF.text)
        }

        // Operator buttons click event

        inputBtnAddition.setOnClickListener {
            operatorSelected(Operator.ADD)
        }

        inputBtnSubtraction.setOnClickListener {
            operatorSelected(Operator.SUB)
        }

        inputBtnMultiply.setOnClickListener {
            operatorSelected(Operator.MUL)
        }

        inputBtnDivide.setOnClickListener {
            operatorSelected(Operator.DIV)
        }

        inputBtnAnd.setOnClickListener {
            operatorSelected(Operator.AND)
        }

        inputBtnOr.setOnClickListener {
            operatorSelected(Operator.OR)
        }

        inputBtnNot.setOnClickListener {
            operatorSelected(Operator.NOT)
        }

        inputBtnNand.setOnClickListener {
            operatorSelected(Operator.NAND)
        }

        inputBtnNor.setOnClickListener {
            operatorSelected(Operator.NOR)
        }

        inputBtnXor.setOnClickListener {
            operatorSelected(Operator.XOR)
        }

        // Clear buttons
        inputBtnCl.setOnClickListener {
            tvResult.text = "0"
        }

        inputBtnAC.setOnClickListener {
            tvResult.text = "0"
            prevResult = 0
            setResultText()
            if (currentOperator != null) {
                toogleOpBtnClr(currentOperator!!, false)
                currentOperator = null
                isCalcPending = false
                clearInput = false
            }
        }

        // Equal button click event
        inputBtnEqual.setOnClickListener {
            if (isCalcPending) {
                calculate()
                toogleOpBtnClr(currentOperator!!, false)
                currentOperator = null
                isCalcPending = false
            }
        }

    }

    private fun operatorSelected(operator: Operator) {

        if (currentOperator == null) {

            if (operator == Operator.NOT) {
                var temp = tvResult.text.toString().toInt(currentNumberSys.radix)
                tvResult.text = temp.inv().toString(currentNumberSys.radix)
                prevResult = tvResult.text.toString().toInt(currentNumberSys.radix)
                setResultText()
                clearInput = true
                return
            }
            prevResult = tvResult.text.toString().toInt(currentNumberSys.radix)
        } else if (currentOperator != null) {
            toogleOpBtnClr(currentOperator!!, false)
            clearInput = true
            if (operator == Operator.NOT) {
                var temp = tvResult.text.toString().toInt(currentNumberSys.radix)
                tvResult.text = temp.inv().toString(currentNumberSys.radix)
                calculate()
                isCalcPending = false
                clearInput = true
                currentOperator = null
                return
            }
            calculate()
        }
        currentOperator = operator
        isCalcPending = true
        clearInput = true
        toogleOpBtnClr(operator, true)
    }

    private fun calculate() {
        when (currentOperator) {

            Operator.ADD -> {
                prevResult += tvResult.text.toString().toInt(currentNumberSys.radix)
            }

            Operator.SUB -> {
                prevResult -= tvResult.text.toString().toInt(currentNumberSys.radix)
            }

            Operator.MUL -> {
                prevResult *= tvResult.text.toString().toInt(currentNumberSys.radix)
            }

            Operator.DIV -> {
                prevResult /= tvResult.text.toString().toInt(currentNumberSys.radix)
            }

            Operator.AND -> {
                prevResult = prevResult and tvResult.text.toString().toInt(currentNumberSys.radix)
            }

            Operator.OR -> {
                prevResult = prevResult or tvResult.text.toString().toInt(currentNumberSys.radix)
            }

            Operator.NAND -> {
                prevResult =
                    (prevResult and tvResult.text.toString().toInt(currentNumberSys.radix)).inv()
            }

            Operator.NOR -> {
                prevResult =
                    (prevResult or tvResult.text.toString().toInt(currentNumberSys.radix)).inv()
            }

            Operator.XOR -> {
                prevResult = prevResult xor tvResult.text.toString().toInt(currentNumberSys.radix)
            }

        }
        setResultText()

    }

    private fun toogleOpBtnClr(operator: Operator, selectedFlag: Boolean) {

        var setColorTo: Int?

        if (selectedFlag) {
            setColorTo = getColor(this, R.color.selectedNumberSystemColor)
        } else {
            setColorTo = getColor(this, R.color.unSelectedNumberSystemColor)
        }

        when (operator) {

            Operator.ADD -> {
                inputBtnAddition.setTextColor(setColorTo)
            }

            Operator.SUB -> {
                inputBtnSubtraction.setTextColor(setColorTo)
            }

            Operator.MUL -> {
                inputBtnMultiply.setTextColor(setColorTo)
            }

            Operator.DIV -> {
                inputBtnDivide.setTextColor(setColorTo)
            }

            Operator.AND -> {
                inputBtnAnd.setTextColor(setColorTo)
            }

            Operator.OR -> {
                inputBtnOr.setTextColor(setColorTo)
            }

            Operator.NOT -> {
                inputBtnNot.setTextColor(setColorTo)
            }

            Operator.NAND -> {
                inputBtnNand.setTextColor(setColorTo)
            }

            Operator.NOR -> {
                inputBtnNor.setTextColor(setColorTo)
            }

            Operator.XOR -> {
                inputBtnXor.setTextColor(setColorTo)
            }

        }
    }

    private fun inputReceived(input: CharSequence) {
        if (tvResult.text.trim() == "0" || clearInput) {
            tvResult.text = input
            clearInput = false
        } else {
            tvResult.text = tvResult.text.toString() + input
        }
    }

    private fun setResultText() {

        tvHex.text = prevResult.toString(NumberSystem.HEX.radix)
        tvDec.text = prevResult.toString()
        tvOct.text = prevResult.toString(NumberSystem.OCT.radix)
        tvBin.text = prevResult.toString(NumberSystem.BIN.radix)

        when (currentNumberSys) {
            NumberSystem.HEX -> {
                tvResult.text = tvHex.text
            }
            NumberSystem.DEC -> {
                tvResult.text = tvDec.text
            }
            NumberSystem.OCT -> {
                tvResult.text = tvOct.text
            }
            NumberSystem.BIN -> {
                tvResult.text = tvBin.text
            }
        }
    }

    private fun setCurrentNumberSystem(numSys: NumberSystem) {

        if (prevResult == 0 && tvResult.text.toString() != "0") {
            prevResult = tvResult.text.toString().toInt(currentNumberSys.radix)
        }

        currentNumberSys = numSys

        btnForHex.setTextColor(getColor(this, R.color.unSelectedNumberSystemColor))
        btnForDec.setTextColor(getColor(this, R.color.unSelectedNumberSystemColor))
        btnForOct.setTextColor(getColor(this, R.color.unSelectedNumberSystemColor))
        btnForBin.setTextColor(getColor(this, R.color.unSelectedNumberSystemColor))

        enableAllInput()

        when (numSys) {
            NumberSystem.HEX -> {
                btnForHex.setTextColor(
                    getColor(
                        this,
                        R.color.selectedNumberSystemColor
                    )
                )
            }
            NumberSystem.DEC -> {
                enableDecInputBtns()
                btnForDec.setTextColor(
                    getColor(
                        this,
                        R.color.selectedNumberSystemColor
                    )
                )
            }
            NumberSystem.OCT -> {
                enableOctInputBtns()
                btnForOct.setTextColor(
                    getColor(
                        this,
                        R.color.selectedNumberSystemColor
                    )
                )
            }
            NumberSystem.BIN -> {
                enableBinInputBtns()
                btnForBin.setTextColor(
                    getColor(
                        this,
                        R.color.selectedNumberSystemColor
                    )
                )
            }
        }

        setResultText()
    }

    private fun enableAllInput() {
        inputBtnA.isEnabled = true
        inputBtnB.isEnabled = true
        inputBtnC.isEnabled = true
        inputBtnD.isEnabled = true
        inputBtnE.isEnabled = true
        inputBtnF.isEnabled = true

        inputBtn2.isEnabled = true
        inputBtn3.isEnabled = true
        inputBtn4.isEnabled = true
        inputBtn5.isEnabled = true
        inputBtn6.isEnabled = true
        inputBtn7.isEnabled = true
        inputBtn8.isEnabled = true
        inputBtn9.isEnabled = true
    }

    private fun enableBinInputBtns() {
        enableOctInputBtns()

        inputBtn2.isEnabled = false
        inputBtn3.isEnabled = false
        inputBtn4.isEnabled = false
        inputBtn5.isEnabled = false
        inputBtn6.isEnabled = false
        inputBtn7.isEnabled = false

    }

    private fun enableOctInputBtns() {

        inputBtnA.isEnabled = false
        inputBtnB.isEnabled = false
        inputBtnC.isEnabled = false
        inputBtnD.isEnabled = false
        inputBtnE.isEnabled = false
        inputBtnF.isEnabled = false

        inputBtn8.isEnabled = false
        inputBtn9.isEnabled = false
    }

    private fun enableDecInputBtns() {

        enableOctInputBtns()

        inputBtn8.isEnabled = true
        inputBtn9.isEnabled = true
    }

}