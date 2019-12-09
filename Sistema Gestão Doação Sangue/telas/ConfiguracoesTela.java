/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author UCHIHA
 */
public class ConfiguracoesTela {

    public void atualizacaoDadosTabelasGraficos() {
        TelaDoador.getInstance().filtraListaDoador();
        TelaDoacao.getInstance().filtrarListaDoacao();
        TelaDoacao.getInstance().filtraListaDoadorDoacao();
        Graficos.getInstance().atualizarGraficos();
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }

    public void selectRadioButton(RadioButton r1, RadioButton r2, Boolean value) {
        if (value != null) {
            r1.setSelected(value);
            r2.setSelected(!value);
        } else {
            r1.setSelected(false);
            r2.setSelected(false);
        }
    }

    public void acaoRadioButton(RadioButton r1, RadioButton r2) {
        r1.setOnAction(e -> {
            r2.setSelected(false);
        });
        r2.setOnAction(e -> {
            r1.setSelected(false);
        });
    }

    public Boolean radioGroupReturn(RadioButton r1, RadioButton r2) {
        if (r1.isSelected()) {
            return true;
        }
        if (r2.isSelected()) {
            return false;
        }
        return null;
    }

}
