package com.challenge.desafio;

// GitHub: MarceloSouza1983		e-mail: map_souza1983@gmail.com		Cel: (12) 98813-6040

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.challenge.annotation.*;
import com.challenge.interfaces.Calculavel;

public class CalculadorDeClasses implements Calculavel {

	@Override
	public BigDecimal somar(Object objeto) {
		return totalPorClasseEAnotacao(objeto, Somar.class);
	}

	@Override
	public BigDecimal subtrair(Object objeto) {
		return totalPorClasseEAnotacao(objeto, Subtrair.class);
	}

	@Override
	public BigDecimal totalizar(Object objeto) {
		return somar(objeto).subtract(subtrair(objeto));
	}
	
	private BigDecimal totalPorClasseEAnotacao(Object objeto, Class anotacao) {
        BigDecimal total = BigDecimal.ZERO;

        for(Field field : objeto.getClass().getDeclaredFields())  {
            field.setAccessible(true);
            if (field.getType().isAssignableFrom(BigDecimal.class)
                    && field.isAnnotationPresent(anotacao)) {
                try {
                    total = total.add((BigDecimal) field.get(objeto));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return total;
    }

}