package com.luckybox.service;

public class Combination {
	private int r;
	private Integer[] entrada;
	private int MAX;
	private int N;

	public Combination(Integer[] entrada, int r) {
		this.r = r;
		this.entrada = entrada;
		this.MAX = ~(1 << entrada.length);
		this.N = 1;
	}

	public boolean hasNext() {
		if (r != 0) {
			while (((this.N & this.MAX) != 0) && (countbits() != r))
				N += 1;
		}

		return (this.N & this.MAX) != 0;
	}

	private int countbits() {
		int i;
		int c;

		i = 1;
		c = 0;
		while ((this.MAX & i) != 0) {
			if ((this.N & i) != 0) {
				c++;
			}
			i = i << 1;
		}

		return c;
	}

	public int getSaidaLength() {
		if (r != 0) {
			return r;
		}

		return this.countbits();
	}

	public Integer[] next() {
		int saida_index, entrada_index, i;

		Integer[] saida = new Integer[this.getSaidaLength()];

		entrada_index = 0;
		saida_index = 0;
		i = 1;

		while ((this.MAX & i) != 0) {
			if ((this.N & i) != 0) {
				saida[saida_index] = entrada[entrada_index];
				saida_index += 1;
			}
			entrada_index += 1;
			i = i << 1;
		}

		N += 1;

		return saida;
	}
}